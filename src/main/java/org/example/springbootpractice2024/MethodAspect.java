package org.example.springbootpractice2024;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MethodAspect {

    // Pointcut to execute on all methods within classes annotated with @RestController
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {}

    // Before advice to log method entry and input arguments
    @Before("controllerMethods()")
    public void logBeforeControllerCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Entering method: " + methodName + " args: " + args);
    }

    // After returning advice to log method exit and return value
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterControllerCall(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Method: " + methodName + " returned with value: " + result);
    }
}
