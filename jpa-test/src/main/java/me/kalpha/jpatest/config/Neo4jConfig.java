package me.kalpha.jpatest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Setter가 필요하다
 * @ConfigurationProperties은 Bean으로 등록하지 않는다. @Configuration 필요함.
 */
@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "spring.data.neo4j")
public class Neo4jConfig {
    private String uri;
    private String username;
    private String password;
}
