package me.kalpha.neo4jjpa.repository;

import me.kalpha.neo4jjpa.entity.Relation;
import me.kalpha.neo4jjpa.entity.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TableRepositoryTest {
    @Autowired
    TableRepository tableRepository;

    @Test
    public void save() {
        Table table = Table.builder()
                .schema("HUBADM").systemId("1234").name("TAB1")
                .build();
        Relation relation = Relation.builder()
                .name("JOIN")
                .build();
        table.getRelations().add(relation);

        tableRepository.save(table);
    }


}