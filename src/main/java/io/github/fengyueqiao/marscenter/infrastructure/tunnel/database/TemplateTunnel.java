package io.github.fengyueqiao.marscenter.infrastructure.tunnel.database;

import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject.TemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2019/9/12 0012.
 */

@Mapper
public interface TemplateTunnel {

    int create(TemplateDO dataObject);

    int delete(String id, String modifier);

    TemplateDO getById(String id);
}
