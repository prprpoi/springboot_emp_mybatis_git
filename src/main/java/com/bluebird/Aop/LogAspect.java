package com.bluebird.Aop;

import com.alibaba.fastjson.JSONObject;
import com.bluebird.mapper.OperateLogMapper;
import com.bluebird.po.OperateLog;
import com.bluebird.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Component
@Slf4j
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.bluebird.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //1.操作人ID----->获取请求头的jwt令牌,解析jwt对象
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");

        //2.操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //3.操作类的类名
        String className = joinPoint.getTarget().getClass().getName();

        //4.操作的方法名
        String methodName = joinPoint.getSignature().getName();

        //5.操作方法的传入参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //7.方法的执行耗时----->开始
        long begin = System.currentTimeMillis();

        //调用原始目标方法
        Object result = joinPoint.proceed();
        //方法的执行耗时----->结束
        long end = System.currentTimeMillis();
        //6.方法的返回值
        String returnValue = JSONObject.toJSONString(result);

        Long costTime = end - begin;

        //将数据封装到对象中
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        return result;
    }
}
