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

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    public static final String REST_SERVICE_URI = "http://localhost:8080/";

    @RequestMapping("/")
    String home() {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);

    }

    /* POST */
    private static void createAudio() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        Audio audio = new Audio(4, "Lol", "Sarah", "sd", 134, "url");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "audio", audio, Audio.class);
        System.out.println("Location : " + uri.toASCIIString());
    }

}
