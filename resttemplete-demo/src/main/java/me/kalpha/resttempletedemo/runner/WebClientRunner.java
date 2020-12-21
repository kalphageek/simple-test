package me.kalpha.resttempletedemo.runner;

import me.kalpha.resttempletedemo.entity.Hello;
import me.kalpha.resttempletedemo.entity.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Order(2)
@Component
public class WebClientRunner implements ApplicationRunner {
    @Autowired
    WebClient.Builder builder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = builder
                .build();

        System.out.println("WebClientRunner");
        Mono<Hello> helloMono = webClient.get().uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Hello.class);

        // subscribe해야 실제로 get()을 실행 함.
        // Async Non-blocking
        helloMono.subscribe(s -> {
            // 대기하다 응답이 오면 처리
            System.out.println(s);
        });

        Mono<World> worldMono = webClient.get().uri("/world")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(World.class);
        // subscribe해야 실제로 get()을 실행 함.
        // Async Non-blocking
        worldMono.subscribe(s -> {
            // 대기하다 응답이 오면 처리
            System.out.println(s);
        });
    }
/*
    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = builder
                .baseUrl("http://localhost:8080")
                .build();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.println("WebClientRunner");
        Mono<Hello> helloMono = webClient.get().uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Hello.class);

        // subscribe해야 실제로 get()을 실행 함.
        // Async Non-blocking
        helloMono.subscribe(s -> {
            // 대기하다 응답이 오면 처리
            System.out.println(s);
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

        Mono<World> worldMono = webClient.get().uri("/world")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(World.class);
        // subscribe해야 실제로 get()을 실행 함.
        // Async Non-blocking
        worldMono.subscribe(s -> {
            // 대기하다 응답이 오면 처리
            System.out.println(s);
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });
    }
    */
}
