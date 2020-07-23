package io.github.fengyueqiao.marscenter.dao.database;

import io.github.fengyueqiao.marscenter.dao.database.dataobject.TemplateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/7/16 0016.
 */

@Mapper
public interface TemplateTunnel {
    int create(TemplateDO templateDO);

    int update(TemplateDO templateDO);

    int delete(String id);

    List<TemplateDO> list(@Param("name")String name);

    TemplateDO getByName(String name);

    TemplateDO getById(String id);
}
