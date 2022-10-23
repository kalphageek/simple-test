package me.kalpha.configurationproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ConfigurationpropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationpropertiesApplication.class, args);
	}

}
