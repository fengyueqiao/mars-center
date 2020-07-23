package io.github.fengyueqiao.marscenter.api.dto;

import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class Placeholder {
    /**
     * 占位符名称
     */
    String name;
    /**
     * 占位符值
     */
    String value;
    /**
     * 占位符默认值
     */
    String defaultValue;
}
