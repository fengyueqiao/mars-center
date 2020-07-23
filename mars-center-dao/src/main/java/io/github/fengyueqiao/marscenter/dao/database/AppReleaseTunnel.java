package io.github.fengyueqiao.marscenter.dao.database;

import io.github.fengyueqiao.marscenter.dao.database.dataobject.AppReleaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/7/14 0014.
 */

@Mapper
public interface AppReleaseTunnel {

    int create(AppReleaseDO appReleaseDO);

    int delete(String id);

    List<AppReleaseDO> list(@Param("appId")String appId);

    AppReleaseDO getByVersion(@Param("appId")String appId, @Param("version")String version);
}
