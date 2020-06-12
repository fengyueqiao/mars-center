package io.github.fengyueqiao.marscenter.client.api;

import io.github.fengyueqiao.marscenter.client.dto.*;
import io.github.fengyueqiao.marscenter.client.req.*;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;

/**
 * Created by Administrator on 2019/9/5 0005.
 */

public interface NodeServiceI {

    MultiResponse<NodeDTO> listNode(ListNodeReq req);

    Response hearbeatFromNode(HearbeatFromNodeReq req);
}
