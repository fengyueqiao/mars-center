package io.github.fengyueqiao.marscenter.api.req;

import lombok.Data;

import java.util.List;

/**
 * @author QinWei on 2020/7/22 0022.
 */

@Data
public class AppInstanceDeleteReq {
    /**
     * app instance id 列表
     */
    List<String> idList;
}
