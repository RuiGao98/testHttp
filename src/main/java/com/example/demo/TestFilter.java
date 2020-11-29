package com.example.demo;

import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gao.rui36
 * @Date 2020/11/28
 **/
@Order(1)
@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("gao", "rui");
        System.out.println(ThreadLocalMap.get("gao"));

        ThreadLocal<Map> mapThreadLocal = new ThreadLocal<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("rui", "yyyyyyyyy");
        mapThreadLocal.set(map);

        System.out.println(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}
