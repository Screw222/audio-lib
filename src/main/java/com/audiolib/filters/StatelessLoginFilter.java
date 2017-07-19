/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.filters;

import com.audiolib.config.UserAuthentication;
import com.audiolib.model.entites.User;
import com.audiolib.services.TokenAuthService;
import com.audiolib.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Login Filter
 *
 * @author Victor Novikov
 */
public class StatelessLoginFilter
        extends AbstractAuthenticationProcessingFilter {

    Logger logger = LoggerFactory.getLogger(StatelessLoginFilter.class);

    private final TokenAuthService tokenAuthService;
    private final UserService userService;

    public StatelessLoginFilter(String urlMapping, TokenAuthService tokenAuthService,
            UserService userService, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(urlMapping));
        this.userService = userService;
        this.tokenAuthService = tokenAuthService;
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        User user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            logger.warn("Can't parse User from request.", e);
        }

        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());

        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        final User authenticatedUser = (User) userService.loadUserByUsername(authentication.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

        tokenAuthService.addAuthentication(response, userAuthentication);

        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
