package io.github.fengyueqiao.marscenter.service;

import io.github.fengyueqiao.marscenter.api.TemplateServiceI;
import io.github.fengyueqiao.marscenter.api.dto.Template;
import io.github.fengyueqiao.marscenter.api.req.*;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import io.github.fengyueqiao.marscenter.common.exception.ErrorCode;
import io.github.fengyueqiao.marscenter.dao.database.TemplateTunnel;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.TemplateDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author QinWei on 2020/7/16 0016.
 */

@Service
public class TemplateServiceImpl implements TemplateServiceI {
    @Autowired
    TemplateTunnel templateTunnel;

    @Override
    public Response createTemplate(TemplateCreateReq req) {
        TemplateDO templateDO = templateTunnel.getByName(req.getTemplate().getName());
        if (templateDO != null) {
            return Response.buildFailure(ErrorCode.E_ElementNameConflict);
        }

        templateDO = new TemplateDO();
        BeanUtils.copyProperties(req.getTemplate(), templateDO);
        templateTunnel.create(templateDO);
        return Response.buildSuccess();
    }

    @Override
    public Response updateTemplate(TemplateUpdateReq req) {
        if (templateTunnel.getById(req.getTemplate().getId()) == null) {
            return Response.buildFailure(ErrorCode.E_RequestNotExist);
        }
        TemplateDO templateDO = new TemplateDO();
        BeanUtils.copyProperties(req.getTemplate(), templateDO);
        templateTunnel.update(templateDO);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteTemplate(TemplateDeleteReq req) {
        templateTunnel.delete(req.getId());
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<Template> listTemplate(TemplateListReq req) {
        List<Template> templateList = new LinkedList<>();
        List<TemplateDO> templateDOList = templateTunnel.list(req.getName());
        templateDOList.forEach( templateDO -> {
            Template template = new Template();
            BeanUtils.copyProperties(templateDO, template);
            templateList.add(template);
        });
        return MultiResponse.ofWithoutTotal(templateList);
    }

    @Override
    public SingleResponse<Template> getTemplate(TemplateGetReq req) {
        TemplateDO templateDO = templateTunnel.getById(req.getId());
        if (templateDO == null) {
            return SingleResponse.buildFailure(ErrorCode.E_RequestNotExist);
        }
        Template template = new Template();
        BeanUtils.copyProperties(templateDO, template);
        return SingleResponse.of(template);
    }
}
