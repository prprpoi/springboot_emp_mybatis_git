package com.bluebird.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bluebird.po.Result;
import com.bluebird.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override//目标方法运行前运行,返回值为true,则放行;为false,则拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取路径的url
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2.判断请求路径是否包含login，如果包含,则放行
        if (url.contains("login")) {
            log.info("登陆操作,放行");
            return true;
        }

        //3.获取请求头中的令牌（token）
        String token = request.getHeader("token");

        //4.判断令牌是否存在,若不存在,返回错误信息
        if (!StringUtils.hasLength(token)) {
            log.info("请求头tokne为空,返回信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换为json对象  -----------> 阿里巴巴 fastJSON
            String notLogin = JSONObject.toJSONString(error);
            //获取输出流,将字符串响应给浏览器
            response.getWriter().write(notLogin);
            return false;
        }
        //解析token,如果解析失败，返回错误信息（未登录）
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("jwt解析失败,返回登陆");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        //放行
        log.info("token登陆成功，放行本次请求");
        return true;
    }

    @Override//目标资源方法运行后放行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle方法正在运行");
    }

    @Override//视图渲染完毕后运行，最后运行的
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion方法正在运行");
    }
}
