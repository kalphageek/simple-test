package me.kalpha.neo4jjpa.repository;

import me.kalpha.neo4jjpa.entity.Table;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TableRepository extends Neo4jRepository<Table, Long> {
}
