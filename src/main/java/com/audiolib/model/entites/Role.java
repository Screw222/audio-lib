/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.entites;

import org.springframework.security.core.GrantedAuthority;

/**
 * Spring Security Authory Role
 *
 * @author Victor Novikov
 */
public enum Role implements GrantedAuthority {
    USER, ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }

}
