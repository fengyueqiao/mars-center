package io.github.fengyueqiao.marscenter.dao.database.dataobject;

import lombok.Data;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Data
public class AppInfoDO {
    String id;
    /**
     * 应用名称
     */
    String name;
    /**
     * 应用类型，参考AppTypeEnum
     */
    String type;
    /**
     * 应用描述
     */
    String comment;
    /**
     * 应用模板
     */
    String templateId;
    /**
     * 应用分组
     */
    String groupId;
}
