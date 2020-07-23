package io.github.fengyueqiao.marscenter.api;

import io.github.fengyueqiao.marscenter.api.dto.AppInstance;
import io.github.fengyueqiao.marscenter.api.dto.AppInstanceStatus;
import io.github.fengyueqiao.marscenter.api.dto.NodeInfo;
import io.github.fengyueqiao.marscenter.api.req.NodeInfoDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.NodeInfoListReq;
import io.github.fengyueqiao.marscenter.api.req.NodeStatusReportReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

public interface NodeInfoServiceI {
    /**
     * 查询Node信息列表
     * @param req
     * @return
     */
    MultiResponse<NodeInfo> listNodeInfo(NodeInfoListReq req);
    /**
     * 上报节点状态信息（节点上报）
     * @param req
     * @return
     */
    Response reportNodeStatus(NodeStatusReportReq req);
    /**
     * 删除节点
     * @param req
     * @return
     */
    Response deleteNodeInfo(NodeInfoDeleteReq req);
}
