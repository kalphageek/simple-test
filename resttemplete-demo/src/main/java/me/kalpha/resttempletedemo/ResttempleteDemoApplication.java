package me.kalpha.resttempletedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ResttempleteDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResttempleteDemoApplication.class, args);
	}

	/**
	 * WebClient.Builder의 설정을 전역적으로 하는 경우 사용.
	 * Class에서는 WebClient.Builder를 호출해서 사용하기만 하면 된다.
	 * @return
	 */
	@Bean
	public WebClientCustomizer webClientCustomizer() {
		return webClientBuilder -> webClientBuilder
				.baseUrl("http://localhost:8080")
				.build();
	}

}
