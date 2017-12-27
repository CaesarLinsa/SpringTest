package com.soft.interceptor;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Interceptor是请求的拦截，在方法前后执行相应逻辑
 *  AOP是对方法的切入，可以是controller中方法，也可以不是
 */

public class DemoInterceptor extends HandlerInterceptorAdapter
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime=System.currentTimeMillis();

        request.setAttribute("startTime",startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long startTime=(long) request.getAttribute("startTime");

        request.removeAttribute("startTime");

        long endTime=System.currentTimeMillis();

        System.out.println("本次请求处理时间为："+new Long(endTime-startTime)+"ms");

        request.setAttribute("handingTime",endTime-startTime);

    }
}
