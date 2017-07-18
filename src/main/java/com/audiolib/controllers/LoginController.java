/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.controllers;

import com.audiolib.config.UserAuthentication;
import com.audiolib.model.entites.User;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Victor Novikov
 */
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/user", method = RequestMethod.GET,
            produces = "application/json")
    public String getUser(Principal principial) {

        if (principial != null) {

            logger.info("Got User info for {} " + principial.getName());

            if (principial instanceof AbstractAuthenticationToken) {
                return ((User) ((AbstractAuthenticationToken) principial).getPrincipal()).toString();
            }
        }

        return null;
    }

    /**
     * Get Current user
     * @return 
     */
    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public String getCurrent() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            User user = (User) ((UserAuthentication) authentication).getDetails();
            logger.info("User {} with Authorities {} do query for Current User",
                    user.getUsername(), user.getAuthorities().toString());
            return user.toString();
        }
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest rq, HttpServletResponse rs) {

        SecurityContextLogoutHandler securityContextLogoutHandler
                = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(rq, rs, null);

    }

}
