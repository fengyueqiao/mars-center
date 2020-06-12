package io.github.fengyueqiao.marscenter.client.api;

import io.github.fengyueqiao.marscenter.client.dto.TemplateDTO;
import io.github.fengyueqiao.marscenter.client.req.ListTemplateReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;

/**
 * Created by Administrator on 2019/9/5 0005.
 */

public interface TemplateServiceI {

    MultiResponse<TemplateDTO> listTemplate(ListTemplateReq req);
}
