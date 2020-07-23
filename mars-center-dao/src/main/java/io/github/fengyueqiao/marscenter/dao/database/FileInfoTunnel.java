package io.github.fengyueqiao.marscenter.dao.database;

import io.github.fengyueqiao.marscenter.dao.database.dataobject.FileInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author QinWei on 2020/7/13 0013.
 */

@Mapper
public interface FileInfoTunnel {
    int create(FileInfoDO fileInfoDO);

    int delete(String id);

    FileInfoDO getById(String id);
}
