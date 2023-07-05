package com.bluebird.Aop;

import com.bluebird.po.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
//@Aspect
public class MyAspect {

    @Pointcut("execution(* com.bluebird.service.service.impl.* (..))")
    //@Pointcut("@annotation(com.bluebird.Aop.MyLog)")
    public void st() {
    }


    @Around("st()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around before.....");

        String className = joinPoint.getTarget().getClass().getName();//获取目标类名
        log.info("目标对象的类名:{}", className);


        String methodName = joinPoint.getSignature().getName();//获取目标方法名
        log.info("目标方法的方法名:{}", methodName);

        Object[] args = joinPoint.getArgs();//获取目标方法运行参数
        log.info("目标方法的参数:{}", Arrays.toString(args));

        Object result = joinPoint.proceed();//执行原始方法,获取返回值(环绕通知)


        log.info("around after.....");

        return result;
    }

    @Before("st()")
    public void before() {
        log.info("before.....");
    }

    @After("st()")
    public void after() {
        log.info("after.....");
    }

    @AfterReturning("st()")
    public void afterReturning() {
        log.info("afterReturning.....");
    }

    @AfterThrowing("st()")
    public void afterThrowing() {
        log.info("afterthrowing.....");
    }

}
