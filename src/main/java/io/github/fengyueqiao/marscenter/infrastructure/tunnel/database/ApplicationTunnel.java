package io.github.fengyueqiao.marscenter.infrastructure.tunnel.database;

import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject.ApplicationDO;
import io.github.fengyueqiao.marscenter.respoistory.tunnel.database.dataobject.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/9/9 0009.
 */

@Mapper
public interface ApplicationTunnel {

    int create(ApplicationDO dataObject);

    List<ApplicationDO> listByGroupId(String userId, String groupId);

    int delete(String id, String modifier);

    ApplicationDO getByName(String appName);
}
