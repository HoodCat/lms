package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.lms.domain.User;

public class AuthUserHandlerMethodArgumentResolver
    implements HandlerMethodArgumentResolver{

    @Override
    public Object resolveArgument(
            MethodParameter parameter, 
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, 
            WebDataBinderFactory binderFactory) throws Exception {
        
        if(supportsParameter(parameter) == false) {
            return WebArgumentResolver.UNRESOLVED;
        }
        
        // @AuthUser가 붙어있고 타입이 UserVo
        HttpServletRequest request = 
                webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();
        if(session == null) {
            return null;
        }
        
        return session.getAttribute("authUser");
    }
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        /* 반환값이 true면 내가 파라미터를 다룬다는 얘기이다.*/
        
        //1. @AuthUser가 붙어 있는지를 확인
        AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

        //2. @AuthUser가 안붙어 있음
        if(authUser == null) {
            return false;
        }
        
        //3. Type이 UserVo인지 체크
        if(parameter.getParameterType().equals(User.class) == false) {
            return false;
        }
        
        return true;
    }

}
