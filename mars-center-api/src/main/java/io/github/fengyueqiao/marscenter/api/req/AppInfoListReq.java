package io.github.fengyueqiao.marscenter.api.req;

import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class AppInfoListReq {
    /**
     * 应用名称
     */
    String name;
    /**
     * 应用分组
     */
    String groupId;
}
