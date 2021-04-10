package me.kalpha.jpatest.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
//@EnableJpaRepositories(basePackages = "com.example.easynotes.repository.relational")
@EnableNeo4jRepositories(basePackages = "me.kalpha.jpatest.neo4j.repository", transactionManagerRef = "neo4jTransactionManager")
@EnableTransactionManagement
public class Neo4jDataSourceConfiguration {
    @Autowired
    Neo4jProperties neo4jProperties;

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri(neo4jProperties.getUri())
                .credentials(neo4jProperties.getUsername(), neo4jProperties.getPassword())
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "me.kalpha.jpatest.neo4j.entity");
    }

    @Bean
    public Neo4jTransactionManager neo4jTransactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Bean
    public Session getSession() {
        return neo4jTransactionManager().getSessionFactory().openSession();
    }
}
