package me.kalpha.neo4jdml.service;

import me.kalpha.neo4jdml.entity.QueryEntity;

import java.util.List;

public interface QueryService {
    public void save(QueryEntity queryEntity);
    public QueryEntity findById(Integer hashCode);
    public List<QueryEntity> findByQueryLike(String query);
    public List<QueryEntity> findByFromsName(String name);
}
