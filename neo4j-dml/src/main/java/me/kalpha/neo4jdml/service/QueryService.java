package me.kalpha.neo4jdml.service;

import me.kalpha.neo4jdml.entity.QueryEntity;

import java.util.List;
import java.util.Optional;

public interface QueryService {
    public void save(QueryEntity queryEntity);
    public void saveAll(List<QueryEntity> queryEntities);
    public Optional<QueryEntity> findByHashCode(Integer hashCode);
    public List<QueryEntity> findByQueryContains(String query);
    public List<QueryEntity> findByTableName(String name);
}
