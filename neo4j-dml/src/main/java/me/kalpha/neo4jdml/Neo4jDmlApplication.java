package me.kalpha.neo4jdml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jRepositories
@SpringBootApplication
public class Neo4jDmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jDmlApplication.class, args);
	}

}
