package com.jbk.Aspects;

import java.time.Duration;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginTimeStamp {

    // Before Advice: Executes before the target method
    @Before("execution(* com.jbk.controller.*.*(..))")
    public void startTime() {
        System.out.println("Start time logged before method execution: " + LocalDateTime.now());
    }

    // After Advice: Executes after the target method (whether it succeeds or throws an exception)
    @After("execution(* com.jbk.controller.*.*(..))")
    public void endTime() {
        System.out.println("End time logged after method execution: " + LocalDateTime.now());
    }

    // Around Advice: Wraps the target method, allowing execution before and after
    @Around("execution(* com.jbk.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("Around Advice: Method execution started at: " + start);

        // Proceed with the actual method execution
        Object result = joinPoint.proceed();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("Around Advice: Method execution ended at: " + end);
     // Calculate and print duration
        Duration duration = Duration.between(start, end);
        System.out.println("Duration: " + duration.toMillis() + " ms");

        return result;
    }
}
