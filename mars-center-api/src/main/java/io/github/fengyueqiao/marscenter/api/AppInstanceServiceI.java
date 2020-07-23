package io.github.fengyueqiao.marscenter.api;

import io.github.fengyueqiao.marscenter.api.dto.AppInstance;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceListReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceStartReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceStopReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;

/**
 * @author QinWei on 2020/7/10 0010.
 */

public interface AppInstanceServiceI {
    /**
     * 启动应用实例
     * @param req
     * @return
     */
    MultiResponse<AppInstance> listAppInstance(AppInstanceListReq req);
    /**
     * 启动应用实例
     * @param req
     * @return
     */
    Response startAppInstance(AppInstanceStartReq req);
    /**
     * 关闭应用实例
     * @param req
     * @return
     */
    Response stopAppInstance(AppInstanceStopReq req);

    /**
     * 删除应用实例
     * @param req
     * @return
     */
    Response deleteAppInstance(AppInstanceDeleteReq req);
}
