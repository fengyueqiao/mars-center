package io.github.fengyueqiao.marscenter.api.dto;

import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class NodeInfo {
    String id;
    String name;
    String endpoint;
    String version;
    String comment;
    /**
     * 节点状态参考NodeStateEnum
     */
    String status;
}
