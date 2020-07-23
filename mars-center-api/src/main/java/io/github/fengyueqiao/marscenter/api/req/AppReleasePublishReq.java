package io.github.fengyueqiao.marscenter.api.req;

import lombok.Data;

import java.util.List;

/**
 * @author QinWei on 2020/7/13 0013.
 */

@Data
public class AppReleasePublishReq {
    String appId;
    String version;
    /**
     * 要发布的Node节点ID列表
     */
    List<String> nodeIdList;
    String settingState;
}
