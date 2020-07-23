package io.github.fengyueqiao.marscenter.dao.database.dataobject;

import lombok.Data;

/**
 * @author QinWei on 2020/7/16 0016.
 */

@Data
public class TemplateDO {
    /**
     * 模板名称
     */
    String id;
    /**
     * 模板名称
     */
    String name;
    /**
     * 模板内容
     */
    String content;
}
