package me.kalpha.configurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private ServiceProperties properties;

    @GetMapping("/hello")
    public String hello() {
        return properties.getMessage();
    }
}
