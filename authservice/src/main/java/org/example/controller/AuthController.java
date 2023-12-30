package org.example.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rats")
public class AuthController {
    @GetMapping("/getauthString")
    public String getString() {
        System.out.println("logging into authserver");
        return "welcome to auth screen";
    }

}
