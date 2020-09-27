package com.craftsmengroup.paymentsystem.authserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Логирует начало/окончание методов + исключения
 * @see Log
 * */
@Slf4j
@Aspect
@Component
public class ServiceLoggingAspect {

    @Pointcut("execution(@(@com.craftsmengroup.paymentsystem.authserver.aop.Log *) public * *(..))")
    public void logBased() {}

    @Pointcut("execution(@com.craftsmengroup.paymentsystem.authserver.aop.Log public * *(..))")
    public void log() {}

    @Pointcut("within(com.craftsmengroup.paymentsystem.authserver.service..*)")
    public void inServiceLayer() {}

    @Around("inServiceLayer() && (logBased() || log())")
    public Object logEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Service method {}#{} is called with params {}", className, methodName, Arrays.toString(args));
        Object o = joinPoint.proceed(args);
        log.info("Service method {}#{} is end with params {}", className, methodName, Arrays.toString(args));
        return o;
    }

    @AfterThrowing(pointcut = "inServiceLayer() && (logBased() || log())", throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.error("Service method {}#{} with params {} is throw exception {}", className, methodName, Arrays.toString(args), ex.toString());
    }
}
