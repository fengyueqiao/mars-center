package io.github.fengyueqiao.marscenter.dao.database.dataobject;

import lombok.Data;

/**
 * @author QinWei on 2020/7/16 0016.
 */

@Data
public class NodeInfoDO {
    String id;
    String name;
    String endpoint;
    String version;
    String comment;
    Long heartbeatTime;
}
