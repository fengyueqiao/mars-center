package io.github.fengyueqiao.marscenter.api;

import io.github.fengyueqiao.marscenter.api.dto.AppRelease;
import io.github.fengyueqiao.marscenter.api.req.AppReleaseCreateReq;
import io.github.fengyueqiao.marscenter.api.req.AppReleaseDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.AppReleaseListReq;
import io.github.fengyueqiao.marscenter.api.req.AppReleasePublishReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;

import java.io.OutputStream;

/**
 * Created by Administrator on 2020/7/10 0010.
 */

public interface AppReleaseServiceI {
    /**
     * 创建应用发行版
     * @param req
     * @return
     */
    SingleResponse<AppRelease> createAppRelease(AppReleaseCreateReq req);
    /**
     * 删除应用发行版
     * @param req
     * @return
     */
    Response deleteAppRelease(AppReleaseDeleteReq req);
    /**
     * 发布应用发行版
     * @param req
     * @return
     */
    Response publishAppRelease(AppReleasePublishReq req);
    /**
     * 查询应用发行版
     * @param req
     * @return
     */
    MultiResponse<AppRelease> listAppRelease(AppReleaseListReq req);
}
