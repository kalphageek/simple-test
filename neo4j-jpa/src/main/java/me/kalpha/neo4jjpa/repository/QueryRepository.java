package me.kalpha.neo4jjpa.repository;

import me.kalpha.neo4jjpa.entity.Query;
import me.kalpha.neo4jjpa.entity.Table;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface QueryRepository extends Neo4jRepository<Query, Long> {
}
