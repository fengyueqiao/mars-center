package io.github.fengyueqiao.marscenter.api.dto;

import java.util.List;

/**
 * @author QinWei on 2020/7/10 0010.
 */

public class ExtendTemplate {
    /**
     * 部署前命令
     */
    String preDeployCommand;
    /**
     * 部署后命令
     */
    String postDeployCommand;
    /**
     * 启动前命令
     */
    String preStartCommand;
    /**
     * 启动后命令
     */
    String postStartCommand;
    /**
     * 停止前命令
     */
    String preStopCommand;
    /**
     * 停止后命令
     */
    String postStopCommand;
    /**
     * 查询状态前命令
     */
    String preStatusCommand;
    /**
     * 查询状态后命令
     */
    String postStatusCommand;
    /**
     * 查询状态命令
     */
    List<Placeholder> placeholderList;
}
