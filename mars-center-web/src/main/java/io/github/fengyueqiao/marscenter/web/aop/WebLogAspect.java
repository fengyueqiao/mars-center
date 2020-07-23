package io.github.fengyueqiao.marscenter.web.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/10/15 0015.
 */

//@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /** 以 controller 包下定义的所有请求为切入点 */
    @Pointcut("execution(public * com.example.digitalsolution.controller..*.*(..))")
    public void webLog() {}

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印请求相关参数
        logger.trace("========================================== Start ==========================================");
        // 打印请求 url
        logger.trace("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        logger.trace("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.trace("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.trace("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        if(request.getContentType() != null && request.getContentType().contains("json")) {
            logger.info("REQ: method:{}, args:{}", joinPoint.getSignature().getName(), JSON.toJSONString(joinPoint.getArgs()[0]));
        }
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        logger.trace("=========================================== End ===========================================");
        // 每个请求之间空一行
        logger.trace("");
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.trace("Response Args  : {}", JSON.toJSONString(result));
        // 执行耗时
        long timeMs = System.currentTimeMillis() - startTime;
        logger.info("RSP: method:{}, time:{} ms", proceedingJoinPoint.getSignature().getName(), timeMs);
        if(timeMs > 1000) {
            logger.warn("slow response " + proceedingJoinPoint.getSignature().getName() + "|" + timeMs);
        }
        return result;
    }

}
