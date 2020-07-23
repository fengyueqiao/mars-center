package io.github.fengyueqiao.marscenter.dao.database;

import io.github.fengyueqiao.marscenter.dao.database.dataobject.AppInfoDO;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.FileInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/7/14 0014.
 */

@Mapper
public interface AppInfoTunnel {

    int create(AppInfoDO appInfoDO);

    int update(AppInfoDO appInfoDO);

    int delete(String id);

    List<AppInfoDO> list(@Param("groupId")String groupId, @Param("name")String name);

    AppInfoDO getByName(String name);

    AppInfoDO getById(String id);
}
