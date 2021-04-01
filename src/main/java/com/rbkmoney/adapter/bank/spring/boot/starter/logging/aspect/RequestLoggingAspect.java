package com.rbkmoney.adapter.bank.spring.boot.starter.logging.aspect;

import com.rbkmoney.adapter.bank.spring.boot.starter.logging.annotation.GetLogging;
import com.rbkmoney.adapter.bank.spring.boot.starter.logging.annotation.PostLogging;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Aspect
@Configuration
public class RequestLoggingAspect {

    private static final String REQUEST_LOG = "Request [{} {}]: {}";
    private static final String RESPONSE_LOG = "Response [{} {}]: {}";

    @Around("@annotation(com.rbkmoney.adapter.bank.spring.boot.starter.logging.annotation.GetLogging)")
    public Object logGetRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        GetLogging getLogging = AnnotationUtils.findAnnotation(signature.getMethod(), GetLogging.class);

        RequestMethod method = getLogging.method();
        String endpoint = getLogging.value();

        return log(joinPoint, method, endpoint);
    }

    @Around("@annotation(com.rbkmoney.adapter.bank.spring.boot.starter.logging.annotation.PostLogging)")
    public Object logPostRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        PostLogging postLogging = AnnotationUtils.findAnnotation(signature.getMethod(), PostLogging.class);

        RequestMethod method = postLogging.method();
        String endpoint = postLogging.value();

        return log(joinPoint, method, endpoint);
    }

    private Object log(
            ProceedingJoinPoint joinPoint,
            RequestMethod method,
            String endpoint) throws Throwable {
        if (joinPoint.getArgs().length != 1) {
            log.debug("Unable to log request. Unsupported method signature with more than one argument: {}",
                    joinPoint.getArgs());
            return joinPoint.proceed();
        }

        Object request = joinPoint.getArgs()[0];
        log.info(REQUEST_LOG, method, endpoint, request);
        Object response = joinPoint.proceed();
        log.info(RESPONSE_LOG, method, endpoint, response);

        return response;
    }
}
