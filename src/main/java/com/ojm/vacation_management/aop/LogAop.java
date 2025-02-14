package com.ojm.vacation_management.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAop {

    @Around("execution(* com.ojm.vacation_management..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start class: {}", joinPoint.getTarget().getClass().getName());
        log.info("Start signature: {}", joinPoint.getSignature());
        log.info("Args: {}", joinPoint.getArgs());

        Object proceed = joinPoint.proceed();

        log.info("End class: {}", joinPoint.getTarget().getClass().getName());
        log.info("End signature: {}", joinPoint.getSignature());
        log.info("Args: {}", joinPoint.getArgs());

        return proceed;
    }
}
