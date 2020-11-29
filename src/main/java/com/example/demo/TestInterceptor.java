package com.example.demo;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author gao.rui36
 * @Date 2020/11/28
 **/
public class TestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(ThreadLocalMap.get("y"));
        Object gao = ThreadLocalMap.get("gao");
        if (gao != null) {
            System.out.println(gao.toString());
        }
//        ThreadLocal<Map> mapThreadLocal = new ThreadLocal<Map>();
//        Map map = mapThreadLocal.get();
//        Object rui = map.get("rui");
//        System.out.println(rui.toString());
        System.out.println(Thread.currentThread().getId());
        //System.out.println(request.getSession().getAttribute("gao").toString());
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext.getAttribute("gao"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HttpSession session = request.getSession();
//        ServletContext servletContext = session.getServletContext();
//        System.out.println(servletContext.getAttribute("gao"));

        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterHandle");
    }
}
