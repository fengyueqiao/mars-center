package io.github.fengyueqiao.marscenter.service;

import io.github.fengyueqiao.marscenter.api.NodeInfoServiceI;
import io.github.fengyueqiao.marscenter.api.dto.AppInstance;
import io.github.fengyueqiao.marscenter.api.dto.AppInstanceStatus;
import io.github.fengyueqiao.marscenter.api.dto.NodeInfo;
import io.github.fengyueqiao.marscenter.api.dto.NodeInfo;
import io.github.fengyueqiao.marscenter.api.req.NodeInfoDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.NodeInfoListReq;
import io.github.fengyueqiao.marscenter.api.req.NodeStatusReportReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.dao.config.NodeConfig;
import io.github.fengyueqiao.marscenter.dao.database.AppInfoTunnel;
import io.github.fengyueqiao.marscenter.dao.database.AppInstanceTunnel;
import io.github.fengyueqiao.marscenter.dao.database.NodeInfoTunnel;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.AppInfoDO;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.AppInstanceDO;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.NodeInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author QinWei on 2020/7/16 0016.
 */

@Slf4j
@Service
public class NodeInfoServiceImpl implements NodeInfoServiceI {
    @Autowired
    NodeInfoTunnel nodeInfoTunnel;

    @Autowired
    NodeConfig nodeConfig;

    @Autowired
    AppInfoTunnel appInfoTunnel;

    @Autowired
    AppInstanceTunnel appInstanceTunnel;

    @Override
    public MultiResponse<NodeInfo> listNodeInfo(NodeInfoListReq req) {
        List<NodeInfo> nodeInfoList = new LinkedList<>();
        List<NodeInfoDO> nodeInfoDOList = nodeInfoTunnel.list(req.getName());
        nodeInfoDOList.forEach( nodeInfoDO -> {
            NodeInfo nodeInfo = new NodeInfo();
            BeanUtils.copyProperties(nodeInfoDO, nodeInfo);
            nodeInfo.setStatus(nodeConfig.getNodeState(nodeInfoDO.getHeartbeatTime()));
            nodeInfoList.add(nodeInfo);
        });
        return MultiResponse.ofWithoutTotal(nodeInfoList);
    }

    @Override
    public Response reportNodeStatus(NodeStatusReportReq req) {
        log.debug("reportNodeStatus from [{}]", req.getNodeStatus().getName());
        // 查看Node是否存在
        NodeInfoDO nodeInfoDO = nodeInfoTunnel.getByName(req.getNodeStatus().getName());
        if (nodeInfoDO == null) {
            // 若node不存在，创建
            nodeInfoDO = new NodeInfoDO();
            nodeInfoDO.setName(req.getNodeStatus().getName());
            nodeInfoDO.setComment("");
            nodeInfoDO.setEndpoint(req.getNodeStatus().getEndpoint());
            nodeInfoDO.setVersion(req.getNodeStatus().getVersion());
            nodeInfoDO.setHeartbeatTime(System.currentTimeMillis());
            nodeInfoTunnel.create(nodeInfoDO);
            log.info("join an new node [{}]", nodeInfoDO.getName());
        } else {
            // 更新状态
            nodeInfoDO.setHeartbeatTime(System.currentTimeMillis());
            nodeInfoTunnel.update(nodeInfoDO);
        }

        // 更新应用实例状态
        for (AppInstanceStatus appInstanceStatus : req.getAppStatusList()) {
            // 获取app id;
            AppInfoDO appInfoDO = appInfoTunnel.getByName(appInstanceStatus.getAppName());
            if (appInfoDO == null) {
                log.warn("appName:{} not found", appInstanceStatus.getAppName());
                continue;
            }

            // 获取app instance id;
            AppInstanceDO appInstanceDO = appInstanceTunnel.get(appInfoDO.getId(), nodeInfoDO.getId());
            if (appInstanceDO == null) {
                log.warn("appInstance appName:{} nodeName:{} is not exist", appInfoDO.getName(), nodeInfoDO.getName());
                continue;
            }

            // 若状态发生变更，更新app instance的状态
            if (!appInstanceStatus.getPresentState().equals(appInstanceDO.getPresentState())
               || appInstanceStatus.getPid().compareTo(appInstanceDO.getPid()) != 0) {
                appInstanceDO.setPresentState(appInstanceStatus.getPresentState());
                appInstanceDO.setPid(appInstanceStatus.getPid());
                appInstanceTunnel.update(appInstanceDO);
                log.info("appInstance appName:{} nodeName:{} state:{}=>{} pid:{}=>{}",
                        appInfoDO.getName(), nodeInfoDO.getName(),
                        appInstanceDO.getPresentState(), appInstanceStatus.getPresentState(),
                        appInstanceDO.getPid(), appInstanceStatus.getPid());
            }
            // 若版本号不对，打印告警日志
            if (!appInstanceStatus.getVersion().equals(appInstanceDO.getVersion())) {
                log.warn("appInstance appName:{} nodeName:{} version: {}=>{}",
                        appInfoDO.getName(), nodeInfoDO.getName(),
                        appInstanceDO.getVersion(), appInstanceStatus.getVersion());
            }
        }
        return Response.buildSuccess();
    }

    @Override
    public Response deleteNodeInfo(NodeInfoDeleteReq req) {
        nodeInfoTunnel.delete(req.getId());
        return Response.buildSuccess();
    }
}
