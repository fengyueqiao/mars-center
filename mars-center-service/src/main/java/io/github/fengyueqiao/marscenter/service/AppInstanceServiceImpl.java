package io.github.fengyueqiao.marscenter.service;

import io.github.fengyueqiao.marscenter.api.AppInstanceServiceI;
import io.github.fengyueqiao.marscenter.api.dto.AppInstance;
import io.github.fengyueqiao.marscenter.api.dto.enumeration.AppStateEnum;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceListReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceStartReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceStopReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.dao.database.AppInstanceTunnel;
import io.github.fengyueqiao.marscenter.dao.database.NodeInfoTunnel;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.AppInstanceDO;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.NodeInfoDO;
import io.github.fengyueqiao.marscenter.dao.http.MarsNodeTunnel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Service
public class AppInstanceServiceImpl implements AppInstanceServiceI {

    @Autowired
    AppInstanceTunnel appInstanceTunnel;

    @Autowired
    NodeInfoTunnel nodeInfoTunnel;

    @Autowired
    MarsNodeTunnel marsNodeTunnel;

    @Override
    public MultiResponse<AppInstance> listAppInstance(AppInstanceListReq req) {
        List<AppInstance> appInstanceList = new LinkedList<>();
        // 通过nodeName获取nodeId
        if (!StringUtils.isEmpty(req.getNodeName())) {
            NodeInfoDO nodeInfoDO = nodeInfoTunnel.getByName(req.getNodeName());
            if(nodeInfoDO != null) {
                req.setNodeId(nodeInfoDO.getId());
            } else {
                return MultiResponse.ofWithoutTotal(appInstanceList);
            }
        }

        List<AppInstanceDO> appInstanceDOList = appInstanceTunnel.list(req.getAppId(), req.getNodeId());
        appInstanceDOList.forEach( appInstanceDO -> {
            AppInstance appInstance = new AppInstance();
            BeanUtils.copyProperties(appInstanceDO, appInstance);
            appInstanceList.add(appInstance);
        });
        return MultiResponse.ofWithoutTotal(appInstanceList);
    }

    @Override
    public Response startAppInstance(AppInstanceStartReq req) {
        for (String id : req.getIdList()) {
            // 获取实例信息
            AppInstanceDO appInstanceDO = appInstanceTunnel.getById(id);

            // 获取node信息
            NodeInfoDO nodeInfoDO = nodeInfoTunnel.getById(appInstanceDO.getNodeId());

            // 发送命令给Node节点
            marsNodeTunnel.startAppInstance(nodeInfoDO.getEndpoint(), appInstanceDO.getAppName());

            // 更新数据库状态
            appInstanceDO = new AppInstanceDO();
            appInstanceDO.setAppId(id);
            appInstanceDO.setSettingState(AppStateEnum.Active.name());
            appInstanceTunnel.update(appInstanceDO);
        }
        return Response.buildSuccess();
    }

    @Override
    public Response stopAppInstance(AppInstanceStopReq req) {
        for (String id : req.getIdList()) {
            // 获取实例信息
            AppInstanceDO appInstanceDO = appInstanceTunnel.getById(id);

            // 获取node信息
            NodeInfoDO nodeInfoDO = nodeInfoTunnel.getById(appInstanceDO.getNodeId());

            // 发送命令给Node节点
            marsNodeTunnel.stopAppInstance(nodeInfoDO.getEndpoint(), appInstanceDO.getAppName());

            // 更新数据库状态
            appInstanceDO = new AppInstanceDO();
            appInstanceDO.setId(id);
            appInstanceDO.setSettingState(AppStateEnum.Inactive.name());
            appInstanceTunnel.update(appInstanceDO);
        }
        return Response.buildSuccess();
    }

    @Override
    public Response deleteAppInstance(AppInstanceDeleteReq req) {
        for (String id : req.getIdList()) {
            // 获取实例信息
            AppInstanceDO appInstanceDO = appInstanceTunnel.getById(id);

            // 获取node信息
            NodeInfoDO nodeInfoDO = nodeInfoTunnel.getById(appInstanceDO.getNodeId());

            // 发送命令给Node节点
            marsNodeTunnel.stopAppInstance(nodeInfoDO.getEndpoint(), appInstanceDO.getAppName());

            // 从数据库删除
            appInstanceTunnel.delete(id);
        }
        return Response.buildSuccess();
    }
}
