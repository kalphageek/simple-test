package me.kalpha.neo4jdml.repository;

import me.kalpha.neo4jdml.entity.QueryEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.List;

public interface QueryRepository extends Neo4jRepository<QueryEntity, Integer> {
    @Query("MATCH (q:QUERY)<-[r:FROM]-(t:TABLE) where t.name =~ $name return q,r,t")
    Collection<QueryEntity> findQueriesByTable(String name);
}
