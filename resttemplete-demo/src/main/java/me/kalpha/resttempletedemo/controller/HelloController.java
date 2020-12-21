package me.kalpha.resttempletedemo.controller;

import me.kalpha.resttempletedemo.entity.Hello;
import me.kalpha.resttempletedemo.entity.World;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public Hello hello() throws InterruptedException {
        Thread.sleep(2000l);
        Hello hello = new Hello("hello");
        return hello;
    }

    @GetMapping("/world")
    public World world() throws InterruptedException {
        Thread.sleep(1000l);
        World world = new World("world");
        return world;
    }

//    @GetMapping("/hello")
//    public String hello() throws InterruptedException {
//        Thread.sleep(2000l);
//        return "hello";
//    }
//
//    @GetMapping("/world")
//    public String world() throws InterruptedException {
//        Thread.sleep(1000l);
//        return "world";
//    }
}
