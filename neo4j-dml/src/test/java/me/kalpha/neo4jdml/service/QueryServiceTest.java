package me.kalpha.neo4jdml.service;

import me.kalpha.neo4jdml.entity.QueryEntity;
import me.kalpha.neo4jdml.entity.TableEntity;
import me.kalpha.neo4jdml.repository.QueryRepository;
import me.kalpha.neo4jdml.repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QueryServiceTest {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    TableRepository tableRepository;

    @Test
    public void saveTest() {
        queryRepository.deleteAll();
        tableRepository.deleteAll();

        String t1Name = "Table1";
        TableEntity t1 = new TableEntity(t1Name);
        TableEntity t2 = new TableEntity("Table2");
//        tableRepository.save(t1);
//        tableRepository.save(t2);

        QueryEntity queryEntity = new QueryEntity("select * from Table1, Table2");
        queryEntity.workWith(t1);
        queryEntity.workWith(t2);
        queryRepository.save(queryEntity);

        Optional<TableEntity> optionalt1 = tableRepository.findById(t1Name);
        TableEntity t3 = optionalt1.get();
        assertTrue(t3.getName().equals(t1Name));
    }
}