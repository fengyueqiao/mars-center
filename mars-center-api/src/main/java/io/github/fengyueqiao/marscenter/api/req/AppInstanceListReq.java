package io.github.fengyueqiao.marscenter.api.req;

import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class AppInstanceListReq {
    String appId;
    String nodeId;
    String nodeName;
}
