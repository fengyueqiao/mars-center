package io.github.fengyueqiao.marscenter.dao.database;

import io.github.fengyueqiao.marscenter.dao.database.dataobject.NodeInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/7/16 0016.
 */

@Mapper
public interface NodeInfoTunnel {
    int create(NodeInfoDO nodeInfoDO);

    int update(NodeInfoDO nodeInfoDO);

    int delete(String id);

    List<NodeInfoDO> list(@Param("name")String name);

    NodeInfoDO getByName(String name);

    NodeInfoDO getById(String id);
}
