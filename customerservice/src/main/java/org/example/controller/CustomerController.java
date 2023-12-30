package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getString")
    public String getString() {
        System.out.println("logging into auth");
        String response = restTemplate.getForObject("http://AUTHSERVICE/getauthString", String.class);
        return response;
    }
}
