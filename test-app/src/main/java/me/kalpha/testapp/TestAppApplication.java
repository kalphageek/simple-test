package me.kalpha.testapp;

import me.kalpha.commonconfig.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAppApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {
			@Autowired
			CommonConfig commonConfig;

			@Override
			public void run(ApplicationArguments args) throws Exception {
				System.out.println("====================");
				System.out.println(commonConfig.getMessage());
				System.out.println("====================");
			}
		};
	}
}
