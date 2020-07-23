package io.github.fengyueqiao.marscenter.api;

import io.github.fengyueqiao.marscenter.api.dto.AppGroup;
import io.github.fengyueqiao.marscenter.api.req.AppGroupCreateReq;
import io.github.fengyueqiao.marscenter.api.req.AppGroupDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.AppGroupListReq;
import io.github.fengyueqiao.marscenter.api.req.AppGroupUpdateReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;

/**
 * @author QinWei on 2020/7/14 0014.
 */

public interface AppGroupServiceI {
    /**
     * 创建应用
     * @param req
     * @return
     */
    Response createAppGroup(AppGroupCreateReq req);
    /**
     * 更新应用
     * @param req
     * @return
     */
    Response updateAppGroup(AppGroupUpdateReq req);
    /**
     * 删除应用
     * @param req
     * @return
     */
    Response deleteAppGroup(AppGroupDeleteReq req);
    /**
     * 查询应用列表
     * @param req
     * @return
     */
    MultiResponse<AppGroup> listAppGroup(AppGroupListReq req);
}
