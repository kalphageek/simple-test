package me.kalpha.neo4jdml.repository;

import me.kalpha.neo4jdml.entity.QueryEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.List;

public interface QueryRepository extends Neo4jRepository<QueryEntity, Integer> {
    @Query("MATCH (q)<-[]-(t {name:'Table1'}), (q)<-[:FROM]-(t2)\n" +
           "RETURN q.hashCode, q.query")
    Collection<QueryEntity> findQueriesByTable(String name);
}
