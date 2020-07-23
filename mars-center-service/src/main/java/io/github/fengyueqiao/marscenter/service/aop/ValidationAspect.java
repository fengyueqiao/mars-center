package io.github.fengyueqiao.marscenter.service.aop;

import io.github.fengyueqiao.marscenter.service.validator.DefaultValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/5/19 0019.
 */

@Aspect
@Component
public class ValidationAspect {
    private final static Logger logger = LoggerFactory.getLogger(ValidationAspect.class);

    /** 以 service 包下定义的所有请求为切入点 */
    @Pointcut("execution(public * com.example.digitalsolution.service..*.*(..))")
    public void service() {}

    @Before("service()")
    public void preValidate(JoinPoint joinPoint) throws Throwable {
        DefaultValidator defaultValidator = new DefaultValidator();
        defaultValidator.validate(joinPoint.getArgs()[0]);
    }

}
