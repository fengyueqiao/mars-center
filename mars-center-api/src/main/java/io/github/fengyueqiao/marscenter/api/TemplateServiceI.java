package io.github.fengyueqiao.marscenter.api;

import io.github.fengyueqiao.marscenter.api.dto.Template;
import io.github.fengyueqiao.marscenter.api.req.*;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;

/**
 * @author QinWei on 2020/7/10 0010.
 */

public interface TemplateServiceI {
    /**
     * 创建模板
     * @param req
     * @return
     */
    Response createTemplate(TemplateCreateReq req);
    /**
     * 修改模板
     * @param req
     * @return
     */
    Response updateTemplate(TemplateUpdateReq req);
    /**
     * 删除模板
     * @param req
     * @return
     */
    Response deleteTemplate(TemplateDeleteReq req);
    /**
     * 查询模板列表
     * @param req
     * @return
     */
    MultiResponse<Template> listTemplate(TemplateListReq req);
    /**
     * 查询模板详情
     * @param req
     * @return
     */
    SingleResponse<Template> getTemplate(TemplateGetReq req);
}
