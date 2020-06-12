package io.github.fengyueqiao.marscenter.infrastructure.tunnel.database;

import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject.DeployVersionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/9/9 0009.
 */

@Mapper
public interface DeployVersionTunnel {

    int create(DeployVersionDO dataObject);

    List<DeployVersionDO> listByAppId(String appId);

    int delete(String id, String modifier);

    DeployVersionDO getById(String id);
}
