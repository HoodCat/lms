package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.lms.domain.User;

public class AuthAdminInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        if (handler instanceof HandlerMethod == false) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Auth auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
        
        if((auth instanceof Auth) == false) {
            return true;
        }
        
        HttpSession session = request.getSession();
        if(session == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        if(auth.role().equals(authUser.getRole())==false) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        return true;
    }
    
}
