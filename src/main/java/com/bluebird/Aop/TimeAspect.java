package com.bluebird.Aop;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect//当前类不是一个普通类,是一个切面类(AOP类)
public class TimeAspect {

    //环绕     切入点表达式,返回值,
    @Around("com.bluebird.Aop.MyAspect.st()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {

        //1.记录开始时间
        long begin = System.currentTimeMillis();

        //2.调用原始方法
        Object result = joinPoint.proceed();

        //3.记录结束时间，拿到方法名,计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法花费了:{}ms", end - begin);

        return result;
    }


}
