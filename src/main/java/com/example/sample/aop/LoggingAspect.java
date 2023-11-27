package com.example.sample.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.sample.service..*.*(..))")
    private void cut() {}

    @Before("cut()")
    public void logBefore(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodDetail = methodDetail(methodSignature);
        // 입력 파라미터
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("[Before] {} || args : {}", methodDetail, args);
    }

    @AfterReturning(value = "cut()", returning = "result") // 메소드 실행 후
    public void logAfter(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodDetail = methodDetail(methodSignature);

        log.info("[After] {} || result : {}", methodDetail, result);
    }

    @AfterThrowing(pointcut = "cut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodDetail = methodDetail(methodSignature);

        log.error("[Exception] {} thrown in {}: {}", ex.getClass().getSimpleName(), methodDetail, ex.getMessage());
    }

    private String methodDetail(MethodSignature methodSignature) {
        // 메소드의 클래스와 메소드명
        String className = methodSignature.getDeclaringType().getName();
        String methodName = methodSignature.getMethod().getName();

        // 인자 타입
        Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        String parameters = Arrays.stream(parameterTypes)
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));

        return className + "." + methodName + "(" + parameters + ")";
    }
}
