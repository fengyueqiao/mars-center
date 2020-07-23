package io.github.fengyueqiao.marscenter.service;

import io.github.fengyueqiao.marscenter.api.AppReleaseServiceI;
import io.github.fengyueqiao.marscenter.api.dto.*;
import io.github.fengyueqiao.marscenter.api.dto.AppRelease;
import io.github.fengyueqiao.marscenter.api.dto.enumeration.AppStateEnum;
import io.github.fengyueqiao.marscenter.api.req.AppReleaseCreateReq;
import io.github.fengyueqiao.marscenter.api.req.AppReleaseDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.AppReleaseListReq;
import io.github.fengyueqiao.marscenter.api.req.AppReleasePublishReq;
import io.github.fengyueqiao.marscenter.common.Constants;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import io.github.fengyueqiao.marscenter.dao.config.FileConfig;
import io.github.fengyueqiao.marscenter.dao.config.VersionConfig;
import io.github.fengyueqiao.marscenter.dao.database.*;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.*;
import io.github.fengyueqiao.marscenter.dao.http.MarsNodeTunnel;
import io.github.fengyueqiao.marscenter.dao.http.dto.req.AppInstanceDeployReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Slf4j
@Service
public class AppReleaseServiceImpl implements AppReleaseServiceI {
    @Autowired
    AppReleaseTunnel appReleaseTunnel;

    @Autowired
    AppInstanceTunnel appInstanceTunnel;

    @Autowired
    NodeInfoTunnel nodeInfoTunnel;

    @Autowired
    MarsNodeTunnel marsNodeTunnel;

    @Autowired
    FileInfoTunnel fileInfoTunnel;

    @Autowired
    FileConfig fileConfig;

    @Autowired
    TemplateTunnel templateTunnel;

    @Autowired
    AppInfoTunnel appInfoTunnel;

    @Override
    public SingleResponse<AppRelease> createAppRelease(AppReleaseCreateReq req) {
        AppReleaseDO appReleaseDO = new AppReleaseDO();
        if (StringUtils.isEmpty(req.getAppRelease().getVersion())) {
            req.getAppRelease().setVersion(VersionConfig.getVersionStr());
        }
        BeanUtils.copyProperties(req.getAppRelease(), appReleaseDO);
        appReleaseTunnel.create(appReleaseDO);
        AppRelease appRelease = new AppRelease();
        BeanUtils.copyProperties(appReleaseDO, appRelease);
        return SingleResponse.of(appRelease);
    }

    @Override
    public Response deleteAppRelease(AppReleaseDeleteReq req) {
        appReleaseTunnel.delete(req.getId());
        return Response.buildSuccess();
    }

    @Override
    public Response publishAppRelease(AppReleasePublishReq req) {
        for (String nodeId : req.getNodeIdList()) {
            // 若实例不存在，则创建
            AppInstanceDO appInstanceDO = appInstanceTunnel.get(req.getAppId(), nodeId);
            if(appInstanceDO == null) {
                appInstanceDO = new AppInstanceDO();
                appInstanceDO.setAppId(req.getAppId());
                appInstanceDO.setNodeId(nodeId);
                appInstanceDO.setPid(0);
                appInstanceDO.setPresentState(AppStateEnum.None.name());
                appInstanceDO.setSettingState(req.getSettingState());
                appInstanceDO.setVersion(req.getVersion());
                appInstanceTunnel.create(appInstanceDO);
                log.info("add new AppInstance app:{} node:{}", appInstanceDO.getAppId(), appInstanceDO.getNodeId());
            } else {
                if (!req.getVersion().equals(appInstanceDO.getVersion())) {
                    appInstanceDO.setVersion(req.getVersion());
                    appInstanceDO.setSettingState(req.getSettingState());
                    appInstanceTunnel.update(appInstanceDO);
                    log.info("appInstance app:{} node:{} version：{}=>{}",
                            appInstanceDO.getAppId(), appInstanceDO.getNodeId(),
                            appInstanceDO.getVersion(), req.getVersion());
                }
            }

            // 获取node信息
            NodeInfoDO nodeInfoDO = nodeInfoTunnel.getById(nodeId);
            // 获取版本信息
            AppReleaseDO appReleaseDO = appReleaseTunnel.getByVersion(req.getAppId(), req.getVersion());
            // 获取文件url
            FileInfoDO fileInfoDO = fileInfoTunnel.getById(appReleaseDO.getFileId());
            // 获取app信息
            AppInfoDO appInfoDO = appInfoTunnel.getById(appInstanceDO.getAppId());
            // 获取模板
            TemplateDO templateDO = templateTunnel.getById(appInfoDO.getTemplateId());

            // 调用Node接口发布版本
            AppInstanceDeployReq appInstanceDeployReq = new AppInstanceDeployReq();
            appInstanceDeployReq.setAutoStart(req.getSettingState().equals(AppStateEnum.Active.name()));
            Map<String, String> placeHolderMap = new HashMap<>();
            placeHolderMap.put("appName", appInstanceDO.getAppName());
            appInstanceDeployReq.setPlaceHolderMap(placeHolderMap);
            appInstanceDeployReq.setAppName(appInstanceDO.getAppName());
            String fileUrl = fileInfoDO.getPath();
            if (fileInfoDO.getIsFullPath().equals(Constants.NO)) {
                fileUrl = fileConfig.getFileDownloadUrl() + fileInfoDO.getPath();
            }
            appInstanceDeployReq.setFileUrl(fileUrl);
            appInstanceDeployReq.setScriptTemplate(templateDO.getContent());
            marsNodeTunnel.deployAppInstance(nodeInfoDO.getEndpoint(), appInstanceDeployReq);
        }
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<AppRelease> listAppRelease(AppReleaseListReq req) {
        List<AppRelease> appReleaseList = new LinkedList<>();
        List<AppReleaseDO> appReleaseDOList = appReleaseTunnel.list(req.getAppId());
        appReleaseDOList.forEach( appReleaseDO -> {
            AppRelease appRelease = new AppRelease();
            BeanUtils.copyProperties(appReleaseDO, appRelease);
            appReleaseList.add(appRelease);
        });
        return MultiResponse.ofWithoutTotal(appReleaseList);
    }
}
