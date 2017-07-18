/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib;

import com.audiolib.model.entites.Audio;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Victor Novikov
 */
@Controller
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MainController {

    @RequestMapping("/")
    String home() {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);

    }
}
