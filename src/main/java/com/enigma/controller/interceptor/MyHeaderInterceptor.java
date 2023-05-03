package com.enigma.controller.interceptor;

import com.enigma.exception.UnauthorizedException;
import com.enigma.util.JwtUtil;
import com.enigma.util.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHeaderInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Kode berikut hanya digunakan dari branch 01-add-course-form sampai branch 07-protected-routes
        return true;

        /**
         * Kode berikut hanya digunakan ketika sudah masuk branch 08-interceptor
         *
         * if (request.getRequestURI().contains(UrlMapping.REGISTER) ||
         *                 request.getRequestURI().contains(UrlMapping.LOGIN)) {
         *             return true;
         *         }
         *         String token = request.getHeader("Authorization");
         *         System.out.println("TOKEN " + token);
         *         if (token == null) throw new UnauthorizedException();
         *         String[] bearerToken = token.split(" ");
         *         return jwtUtil.validateJwtToken(bearerToken[1]);
         */
    }
}
