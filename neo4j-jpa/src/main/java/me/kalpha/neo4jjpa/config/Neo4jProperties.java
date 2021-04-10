package me.kalpha.neo4jjpa.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@ConfigurationProperties(prefix = "spring.data.neo4j")
@Configuration
public class Neo4jProperties {
    private String uri;
    private String username;
    private String password;
}
