package io.github.fengyueqiao.marscenter.api.dto;

import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class AppRelease {
    String id;
    String appId;
    String version;
    String fileId;
}
