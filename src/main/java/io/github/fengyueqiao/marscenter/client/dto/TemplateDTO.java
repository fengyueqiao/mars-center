package io.github.fengyueqiao.marscenter.client.dto;

import java.util.List;

/**
 * Created by Administrator on 2019/9/5 0005.
 */

public class TemplateDTO {

    /**
     * 模板名称
     */
    String templateName;

    /**
     * 模板描述
     */
    String templateDesc;

    /**
     * 部署命令
     */
    String deployCommand;

    /**
     * 启动命令
     */
    String startCommand;

    /**
     * 停止命令
     */
    String stopCommand;

    /**
     * 查询状态命令
     */
    String statusCommand;

    /**
     * 查询状态命令
     */
    List<PlaceholderDTO> placeholderDTOList;

}
