package io.github.fengyueqiao.marscenter.api.dto;

import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class AppInfo {
    String id;
    /**
     * 应用名称
     */
    String name;
    /**
     * 应用类型，参考 AppTypeEnum
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
