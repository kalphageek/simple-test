package me.kalpha.neo4jdml.service;

import me.kalpha.neo4jdml.entity.QueryEntity;
import me.kalpha.neo4jdml.entity.TableEntity;
import me.kalpha.neo4jdml.repository.QueryRepository;
import me.kalpha.neo4jdml.repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataNeo4jTest
public class RepositoryTest {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    TableRepository tableRepository;

    @Test
    public void saveTest() {
        String t1Name = "Table1";
        TableEntity t1 = new TableEntity(t1Name);
        TableEntity t2 = new TableEntity("Table2");

        QueryEntity queryEntity = new QueryEntity("select * from Table1, Table2");
        queryEntity.workWith(t1);
        queryEntity.workWith(t2);
        queryRepository.save(queryEntity);

        Optional<TableEntity> optionalt1 = tableRepository.findById(t1Name);
        TableEntity t1_ = optionalt1.get();
        assertTrue(t1_.getName().equals(t1Name));
    }
}
