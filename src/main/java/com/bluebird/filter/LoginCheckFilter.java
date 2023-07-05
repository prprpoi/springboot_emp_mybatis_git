package com.bluebird.filter;

import com.alibaba.fastjson.JSONObject;
import com.bluebird.po.Result;
import com.bluebird.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
/*@WebFilter(urlPatterns = "/*")*/
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取路径的url
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2.判断请求路径是否包含login，如果包含,则放行
        if (url.contains("login")) {
            log.info("登陆操作,放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
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
            return;
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
            return;
        }

        //放行
        log.info("token登陆成功，放行本次请求");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
