/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 *
 * @author Victor Novikov
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

    public static String mybatisConfig;

    @Value("${mybatis.config}")
    public void setMybatisConfig(String mybatisConfig) {
        this.mybatisConfig = mybatisConfig;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer
            propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
