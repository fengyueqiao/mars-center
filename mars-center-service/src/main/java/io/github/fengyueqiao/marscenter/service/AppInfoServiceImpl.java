package io.github.fengyueqiao.marscenter.service;

import io.github.fengyueqiao.marscenter.api.AppInfoServiceI;
import io.github.fengyueqiao.marscenter.api.dto.AppInfo;
import io.github.fengyueqiao.marscenter.api.req.AppInfoCreateReq;
import io.github.fengyueqiao.marscenter.api.req.AppInfoDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.AppInfoListReq;
import io.github.fengyueqiao.marscenter.api.req.AppInfoUpdateReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.exception.ErrorCode;
import io.github.fengyueqiao.marscenter.dao.database.AppInfoTunnel;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.AppInfoDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Service
public class AppInfoServiceImpl implements AppInfoServiceI{
    @Autowired
    AppInfoTunnel appInfoTunnel;

    @Override
    public Response createAppInfo(AppInfoCreateReq req) {
        AppInfoDO appInfoDO = appInfoTunnel.getByName(req.getAppInfo().getName());
        if (appInfoDO != null) {
            return Response.buildFailure(ErrorCode.E_ElementNameConflict);
        }

        appInfoDO = new AppInfoDO();
        BeanUtils.copyProperties(req.getAppInfo(), appInfoDO);
        appInfoTunnel.create(appInfoDO);
        return Response.buildSuccess();
    }

    @Override
    public Response updateAppInfo(AppInfoUpdateReq req) {
        if (appInfoTunnel.getById(req.getAppInfo().getId()) == null) {
            return Response.buildFailure(ErrorCode.E_RequestNotExist);
        }
        AppInfoDO appInfoDO = new AppInfoDO();
        BeanUtils.copyProperties(req.getAppInfo(), appInfoDO);
        appInfoTunnel.update(appInfoDO);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteAppInfo(AppInfoDeleteReq req) {
        appInfoTunnel.delete(req.getId());
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<AppInfo> listAppInfo(AppInfoListReq req) {
        List<AppInfo> appInfoList = new LinkedList<>();
        List<AppInfoDO> appInfoDOList = appInfoTunnel.list(req.getGroupId(), req.getName());
        appInfoDOList.forEach( appInfoDO -> {
            AppInfo appInfo = new AppInfo();
            BeanUtils.copyProperties(appInfoDO, appInfo);
            appInfoList.add(appInfo);
        });
        return MultiResponse.ofWithoutTotal(appInfoList);
    }
}
