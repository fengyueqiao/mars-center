package io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject;

import lombok.Data;

/**
 * Created by Administrator on 2019/9/9 0009.
 */

@Data
public class DeployVersionDO extends DbDataObject {
    String appId;
    String fileName;
    String tgzName;
    String version;
    int isFileDeleted = 0;
    String md5sum = "";
    String creator = "";
}
