package me.kalpha.resttempletedemo.runner;

import me.kalpha.resttempletedemo.entity.Hello;
import me.kalpha.resttempletedemo.entity.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.BodyInserters;
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
        helloMono();

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


    // Get Sample
    public void helloSync() {
        WebClient webClient = builder
                .build();

        Hello hello =
                webClient.get().uri("/hello")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Hello.class)
                        .flux()
                        .toStream()
                        .findFirst()
                        .orElse(new Hello());//Optional을 위한 default value

        System.out.println(hello);
    }

    // Get Sample
    public void helloMono() {
        WebClient webClient = builder
                .build();

        Mono<Hello> helloMono =
            webClient.get().uri("/hello")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Hello.class);;

        // subscribe해야 실제로 get()을 실행 함.
        // Async Non-blocking
        helloMono.subscribe(s -> {
            // 대기하다 응답이 오면 처리
            System.out.println(s);
        });
    }

//----------------------------------------------------------------------------------

    // Get Sample
    public Mono<Hello> getData() {
        WebClient webClient = builder
                .build();

        return
                webClient.get()
                        .uri("/hello")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Hello.class);
    }

    // Get Sample
    public Mono<Hello> getData(Integer id, String accessToken) {
        WebClient webClient = builder
                .build();

        return
                webClient.get()
                        .uri("/resource?id={ID}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .retrieve()
                        .bodyToMono(Hello.class)
                ;
    }



    // Form Sample
    public Mono<Hello> formData(String idValue, String pwdValue) {
        WebClient webClient = builder
                .build();

        return
            webClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("id", idValue)
                        .with("pwd", pwdValue)
                )
                .retrieve()
                .bodyToMono(Hello.class);
    }


    // Json Sample
    public Mono<Hello> jsonData(String loginInfo, String accessToken) {
        WebClient webClient = builder
                .build();

        return
            webClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .bodyValue(loginInfo)
                .retrieve()
                .bodyToMono(Hello.class);
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
