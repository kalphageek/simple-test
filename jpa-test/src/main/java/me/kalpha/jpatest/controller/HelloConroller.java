package me.kalpha.jpatest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConroller {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
