package io.github.fengyueqiao.marscenter.api;

import io.github.fengyueqiao.marscenter.api.dto.AppInfo;
import io.github.fengyueqiao.marscenter.api.req.AppInfoCreateReq;
import io.github.fengyueqiao.marscenter.api.req.AppInfoDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.AppInfoListReq;
import io.github.fengyueqiao.marscenter.api.req.AppInfoUpdateReq;
import io.github.fengyueqiao.marscenter.common.dto.*;

public interface AppInfoServiceI {
    /**
     * 创建应用
     * @param req
     * @return
     */
    Response createAppInfo(AppInfoCreateReq req);
    /**
     * 更新应用
     * @param req
     * @return
     */
    Response updateAppInfo(AppInfoUpdateReq req);
    /**
     * 删除应用
     * @param req
     * @return
     */
    Response deleteAppInfo(AppInfoDeleteReq req);
    /**
     * 查询应用列表
     * @param req
     * @return
     */
    MultiResponse<AppInfo> listAppInfo(AppInfoListReq req);
}
