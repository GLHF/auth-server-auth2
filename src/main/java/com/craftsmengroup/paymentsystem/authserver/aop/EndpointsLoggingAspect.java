package com.craftsmengroup.paymentsystem.authserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class EndpointsLoggingAspect {

    @Pointcut("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) public * *(..))")
    public void requestMappingBased() {}

    @Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping public * *(..))")
    public void requestMapping() {}

    @Around("(requestMapping() || requestMappingBased())")
    public Object logEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        var className = joinPoint.getSignature().getDeclaringTypeName();
        var methodName = joinPoint.getSignature().getName();
        var args = joinPoint.getArgs();

        log.info("Endpoint {}#{} is called with params {}", className, methodName, Arrays.toString(args));

        return joinPoint.proceed(args);
    }

}
