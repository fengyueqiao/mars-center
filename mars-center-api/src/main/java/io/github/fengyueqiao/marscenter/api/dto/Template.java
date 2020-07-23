package io.github.fengyueqiao.marscenter.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class Template {
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
    /**
     * 查询状态命令
     */
    List<Placeholder> placeholderList;
}
