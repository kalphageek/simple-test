package me.kalpha.neo4jdml.repository;

import me.kalpha.neo4jdml.entity.QueryEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface QueryRepository extends Neo4jRepository<QueryEntity, Integer> {
    List<QueryEntity> findByQueryLike(String query);
    List<QueryEntity> findByFromsName(String name);
}
