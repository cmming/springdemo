package com.example.springdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/user")
public class UserController {

    @PostMapping("")
    public void store() {

    }
}
