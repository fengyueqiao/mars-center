package io.github.fengyueqiao.marscenter.dao.database;

import io.github.fengyueqiao.marscenter.dao.database.dataobject.AppInstanceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Mapper
public interface AppInstanceTunnel {
    int create(AppInstanceDO appInstanceDO);

    int update(AppInstanceDO appInstanceDO);

    int delete(String id);

    List<AppInstanceDO> list(@Param("appId")String appId, @Param("nodeId")String nodeId);

    AppInstanceDO get(@Param("appId")String appId, @Param("nodeId")String nodeId);

    AppInstanceDO getById(String id);
}
