package com.craftsmengroup.paymentsystem.authserver.aop;

import java.lang.annotation.*;

/**
 * Аннотация для логирования начала/окончания метода + исключения
 * @see ServiceLoggingAspect
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
}
