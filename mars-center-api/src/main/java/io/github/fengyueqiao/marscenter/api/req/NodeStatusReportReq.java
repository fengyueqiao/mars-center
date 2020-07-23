package io.github.fengyueqiao.marscenter.api.req;

import io.github.fengyueqiao.marscenter.api.dto.AppInstanceStatus;
import io.github.fengyueqiao.marscenter.api.dto.NodeStatus;
import lombok.Data;

import java.util.List;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class NodeStatusReportReq {
    /**
     * 节点状态
     */
    NodeStatus nodeStatus;
    /**
     * 运行在
     */
    List<AppInstanceStatus> appStatusList;
}
