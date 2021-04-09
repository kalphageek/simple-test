package me.kalpha.jpatest.neo4j.repository;

import me.kalpha.jpatest.neo4j.entity.Table;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TableRepository extends Neo4jRepository<Table, Long> {
}
