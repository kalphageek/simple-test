package me.kalpha.neo4jdml.service;

import me.kalpha.neo4jdml.entity.QueryEntity;
import me.kalpha.neo4jdml.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    QueryRepository queryRepository;

    @Override
    public void save(QueryEntity queryEntity) {
        queryRepository.save(queryEntity);
    }

    @Override
    public void saveAll(List<QueryEntity> queryEntities) {
        queryRepository.saveAll(queryEntities);
    }

    @Override
    public Optional<QueryEntity> findByHashCode(Integer hashCode) {
        return queryRepository.findById(hashCode);
    }

    @Override
    public Collection<QueryEntity> findQuerieByTable(String name) {
        return queryRepository.findQueriesByTable(name);
    }

    @Override
    public Collection<QueryEntity> findAllQueries() {
        return queryRepository.findAll();
    }
}
