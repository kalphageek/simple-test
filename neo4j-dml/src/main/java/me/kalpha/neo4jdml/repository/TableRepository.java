package me.kalpha.neo4jdml.repository;

import me.kalpha.neo4jdml.entity.TableEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TableRepository extends Neo4jRepository<TableEntity, String> {
}
