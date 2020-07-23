package io.github.fengyueqiao.marscenter.dao.database.dataobject;

import lombok.Data;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Data
public class AppInstanceDO {
    /**
     * ID
     */
    String id;
    /**
     * 应用ID
     */
    String appId;
    /**
     * 应用名称 (显示使用)
     */
    String appName;
    /**
     * 节点ID
     */
    String nodeId;
    /**
     * 节点名称 (显示使用)
     */
    String nodeName;
    /**
     * 进程ID
     */
    Integer pid;
    /**
     * 应用版本
     */
    String version;
    /**
     * 设置状态
     */
    String settingState;
    /**
     * 当前状态
     */
    String presentState;
}
