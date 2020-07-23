package io.github.fengyueqiao.marscenter.api.req;

import lombok.Data;

import java.util.List;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class AppInstanceStartReq {
    /**
     * app instance id 列表
     */
    List<String> idList;
}
