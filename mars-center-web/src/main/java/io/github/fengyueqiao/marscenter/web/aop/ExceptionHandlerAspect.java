package io.github.fengyueqiao.marscenter.web.aop;

import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2020/4/27 0027.
 */

@ControllerAdvice
@Order(99)
public class ExceptionHandlerAspect {
    private Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response errorHandler(Exception e) {
        log.error("unknow error:", e);
        return Response.buildFailure(ErrorCode.E_UnknownError);
    }
}
