/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.services;

import com.audiolib.config.UserAuthentication;
import com.audiolib.utils.TokenHandler;
import com.audiolib.model.entites.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author Victor Novikov
 */
@Component
public class TokenAuthService {

    private static final String AUTH_HEADER_NAME = "X-Auth-Token";

    private final TokenHandler tokenHandler;

    @Autowired
    public TokenAuthService(@Value("${token.secret}") String secret) {
        tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
    }

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = (User) authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest servletRequest) {
        final String token = servletRequest.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.extractUser(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

}
