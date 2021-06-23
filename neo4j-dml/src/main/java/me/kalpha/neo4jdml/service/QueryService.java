package me.kalpha.neo4jdml.service;

import me.kalpha.neo4jdml.entity.QueryEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QueryService {
    public void save(QueryEntity queryEntity);
    public void saveAll(List<QueryEntity> queryEntities);
    public Optional<QueryEntity> findByHashCode(Integer hashCode);
    public Collection<QueryEntity> findQuerieByTable(String name);
    public Collection<QueryEntity> findAllQueries();
}
