package me.kalpha.neo4jdml.controller;

import me.kalpha.neo4jdml.entity.QueryEntity;
import me.kalpha.neo4jdml.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/queries")
public class QueryController {
    @Autowired
    QueryService queryService;

    @GetMapping("/table/{name}")
    public Collection<QueryEntity> getQueriesByName(@PathVariable String name) {
        Collection<QueryEntity> queryEntities = queryService.findQuerieByTable(name);
        return queryEntities;
    }

    @GetMapping
    public Collection<QueryEntity> getAllQueries() {
        Collection<QueryEntity> queryEntities = queryService.findAllQueries();
        return queryEntities;
    }
}
