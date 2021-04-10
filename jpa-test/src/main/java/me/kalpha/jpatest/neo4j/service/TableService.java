package me.kalpha.jpatest.neo4j.service;

import me.kalpha.jpatest.neo4j.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TableService {
    @Autowired
    TableRepository tableRepository;


}
