/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.filters;

import com.audiolib.services.TokenAuthService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Authentication Filter
 *
 * @author Victor Novikov
 */
public class StatelessAuthFilter extends GenericFilterBean {

    private final TokenAuthService tokenAuthService;

    public StatelessAuthFilter(TokenAuthService tokenAuthService) {
        this.tokenAuthService = tokenAuthService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(
                tokenAuthService.getAuthentication((HttpServletRequest) request));

        filterChain.doFilter(request, response);
    }

}
