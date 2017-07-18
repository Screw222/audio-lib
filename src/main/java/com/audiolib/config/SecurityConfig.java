/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.config;

import com.audiolib.filters.StatelessAuthFilter;
import com.audiolib.filters.StatelessLoginFilter;
import com.audiolib.services.TokenAuthService;
import com.audiolib.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Configuration for access
 *
 * @author Victor Novikov
 */
@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenAuthService tokenAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //.cors().disable()
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl().and().and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/public/build/bundle.js", "/public/css/style.css").permitAll()
                .antMatchers("/m/**", "/css/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/audio/**").hasAnyAuthority("USER", "ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "api/users/current/").hasAnyAuthority("USER", "ADMINISTRATOR")
                .anyRequest().hasAuthority("ADMINISTRATOR").and()
                .addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthService, userService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new StatelessAuthFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
