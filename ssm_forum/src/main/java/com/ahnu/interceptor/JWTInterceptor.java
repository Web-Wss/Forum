package com.ahnu.interceptor;

import com.ahnu.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    //    在目标方法执行之前 执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        String token = request.getHeader("Authorization");
        System.out.println(token);
        Map<String, Object> map = JWTUtils.parseToken(token);
        Object acct = map.get("acct");
        if (acct != null) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion");
    }
}
