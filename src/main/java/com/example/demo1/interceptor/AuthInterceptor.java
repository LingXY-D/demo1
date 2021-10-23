package com.example.demo1.interceptor;
import com.example.demo1.annotation.AdminLogin;
import com.example.demo1.annotation.UserLogin;
import com.example.demo1.service.UserService;
import com.example.demo1.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    UserService userService;

    private boolean check(String token) {
        if (!StringUtils.hasLength(token)) {
            throw new RuntimeException("无token，请重新登录");
        }
        return JwtUtil.verifyToken(token);
    }

    private boolean isAdmin(String token) {
        int userId = JwtUtil.getUserID(token);
        return userService.isAdmin(userId);
    }

    private boolean isRefresh(String token) {
        return JwtUtil.getTokenType(token).equals("refresh");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bearer = request.getHeader("Authorization");
        String token = "";
        if (bearer != null && bearer.startsWith("Bearer ")) {
            token = bearer.substring(7);
        }
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(UserLogin.class)) {
            UserLogin userLogin = method.getAnnotation(UserLogin.class);
            if (userLogin.required()) return check(token);
        } else if (method.isAnnotationPresent(AdminLogin.class)) {
            AdminLogin adminLogin = method.getAnnotation(AdminLogin.class);
            if (adminLogin.required()) return (check(token) && isAdmin(token));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
