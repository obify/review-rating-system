package com.obify.rms.security;

import com.obify.rms.service.impl.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        try{
            Authentication authentication = authenticationService.getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            PrintWriter writer = response.getWriter();
//            writer.println(e.getMessage());
//            writer.flush();
//            writer.close();
              log.error(e.getMessage());
            resolver.resolveException(request, response, null, e);
        }
        filterChain.doFilter(request, response);
    }
}
