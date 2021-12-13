package com.ynov.olympicker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class HelloController {

    @RequestMapping("/")
    public String hello() {
        return "Hello";
    }
}
