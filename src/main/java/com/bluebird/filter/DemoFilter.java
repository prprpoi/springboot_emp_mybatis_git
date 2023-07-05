package com.bluebird.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*指定当前过滤器拦截的请求*/
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override//初始化方法,Web服务器启动创建Filter是调用,只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
        System.out.println("init初始化方法执行了");
    }

    @Override//拦截到请求时,调用该方法,可调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override//销毁方法,服务器关闭时调用,只调用一次
    public void destroy() {
        //Filter.super.destroy();
        System.out.println("destory销毁方法执行了");
    }
}
