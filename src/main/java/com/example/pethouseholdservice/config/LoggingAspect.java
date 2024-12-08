package com.example.pethouseholdservice.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.pethouseholdservice.service.*.*(..))")
    public void logMethodEntry() {
        System.out.println("Entering method");
    }

    @After("execution(* com.example.pethouseholdservice.service.*.*(..))")
    public void logMethodExit() {
        System.out.println("Exiting method");
    }

    @AfterThrowing("execution(* com.example.pethouseholdservice.service.*.*(..))")
    public void logMethodException() {
        System.out.println("Exception in method");
    }
}
