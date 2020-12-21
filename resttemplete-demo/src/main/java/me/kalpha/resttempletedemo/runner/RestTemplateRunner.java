package me.kalpha.resttempletedemo.runner;

import me.kalpha.resttempletedemo.entity.Hello;
import me.kalpha.resttempletedemo.entity.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

@Order(1)
@Component
public class RestTemplateRunner implements ApplicationRunner {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();

        System.out.println("RestTemplateRunner");
        String helloResult = restTemplate
                .getForObject("http://localhost:8080/hello", String.class);
        System.out.println(helloResult.toString());

        String worldResult = restTemplate
                .getForObject("http://localhost:8080/world", String.class);
        System.out.println(worldResult.toString());
    }
/*
    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.println("RestTemplateRunner");
//        Hello helloResult = restTemplate.getForObject("http://localhost:8080/hello", Hello.class);
        String helloResult = restTemplate
                .getForObject("http://localhost:8080/hello", String.class);
        System.out.println(helloResult.toString());

//        World worldResult = restTemplate.getForObject("http://localhost:8080/world", World.class);
        String worldResult = restTemplate
                .getForObject("http://localhost:8080/world", String.class);
        System.out.println(worldResult.toString());

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
*/
}
