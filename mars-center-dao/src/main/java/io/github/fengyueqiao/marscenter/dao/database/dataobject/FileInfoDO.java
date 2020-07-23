package io.github.fengyueqiao.marscenter.dao.database.dataobject;

import lombok.Data;

/**
 * @author QinWei on 2020/7/13 0013.
 */

@Data
public class FileInfoDO {
    String id;
    String name;
    String path;
    Long size;
    String md5sum;
    Integer isExist;
    Integer isZip;
    Integer isFullPath;
}
