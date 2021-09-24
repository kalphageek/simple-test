package me.kalpha.neo4jdml.service;

import me.kalpha.neo4jdml.entity.QueryEntity;
import me.kalpha.neo4jdml.entity.TableEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QueryServiceTest {
    @Autowired
    QueryService queryService;

    @Test
    public void serviceTest() {
        String t1Name = "Table1";
        List<QueryEntity> sampleQueryEntities = generateSampleData(t1Name);

        Collection<QueryEntity> queryEntities1 = queryService.findQuerieByTable("Table2");
        System.out.println("queryEntities1.size() : " + queryEntities1.size());
        assertTrue(queryEntities1.size() == 2);
    }

    private List<QueryEntity> generateSampleData(String t1Name) {
        TableEntity t1 = new TableEntity(t1Name);
        TableEntity t2 = new TableEntity("Table2");
        TableEntity t3 = new TableEntity("Table3");
        TableEntity t4 = new TableEntity("Table4");

        QueryEntity queryEntity = new QueryEntity("select * from Table1, Table2, Table3");
        queryEntity.addTable(t1);
        queryEntity.addTable(t2);
        queryEntity.addTable(t3);

        QueryEntity queryEntity2 = new QueryEntity("select * from Table1, Table4");
        queryEntity2.addTable(t1);
        queryEntity2.addTable(t4);

        List<QueryEntity> queryEntities = new ArrayList<>();
        queryEntities.add(queryEntity);
        queryEntities.add(queryEntity2);

        return queryEntities;
    }
}