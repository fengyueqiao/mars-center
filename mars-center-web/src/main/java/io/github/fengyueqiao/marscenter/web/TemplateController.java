package io.github.fengyueqiao.marscenter.web;

import io.github.fengyueqiao.marscenter.api.TemplateServiceI;
import io.github.fengyueqiao.marscenter.api.dto.Template;
import io.github.fengyueqiao.marscenter.api.req.*;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QinWei on 2020/7/16 0016.
 */

@RestController
@RequestMapping("/api/template/v1")
public class TemplateController {
    @Autowired
    TemplateServiceI templateServiceI;

    @PostMapping(value = "/createTemplate")
    public Response createTemplate(@RequestBody TemplateCreateReq req) {
        return templateServiceI.createTemplate(req);
    }

    @PostMapping(value = "/updateTemplate")
    public Response updateTemplate(@RequestBody TemplateUpdateReq req) {
        return templateServiceI.updateTemplate(req);
    }

    @PostMapping(value = "/deleteTemplate")
    public Response deleteTemplate(@RequestBody TemplateDeleteReq req) {
        return templateServiceI.deleteTemplate(req);
    }

    @PostMapping(value = "/listTemplate")
    public MultiResponse<Template> listTemplate(@RequestBody TemplateListReq req) {
        return templateServiceI.listTemplate(req);
    }

    @PostMapping(value = "/getTemplate")
    public SingleResponse<Template> getTemplate(@RequestBody TemplateGetReq req) {
        return templateServiceI.getTemplate(req);
    }

}
