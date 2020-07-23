package io.github.fengyueqiao.marscenter.dao.database.dataobject;

import lombok.Data;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Data
public class AppReleaseDO {
    String id;
    String appId;
    String version;
    String fileId;
}
