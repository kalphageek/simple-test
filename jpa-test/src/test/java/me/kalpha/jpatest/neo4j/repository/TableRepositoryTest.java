package me.kalpha.jpatest.neo4j.repository;

import me.kalpha.jpatest.neo4j.entity.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class TableRepositoryTest {

    @Autowired
    TableRepository tableRepository;

    @Test
    public void save() {
        Table tab1 = Table.builder()
                .schema("HUBADM").systemId("1234").name("TAB1")
                .build();
        Table tab2 = Table.builder()
                .schema("HUBADM").systemId("1234").name("TAB2")
                .build();
        Table tab3 = Table.builder()
                .schema("HUBADM").systemId("1234").name("TAB3")
                .build();
        Table tab4 = Table.builder()
                .schema("HUBADM").systemId("1234").name("TAB4")
                .build();

        tab1.getPartOf().add(tab2);
        tab1.getSummeryOf().add(tab3);
        tab4.getRefernceOf().add(tab1);
        tab4.getRefernceOf().add(tab2);

        tableRepository.save(tab1);
        tableRepository.save(tab2);
        tableRepository.save(tab3);
        tableRepository.save(tab4);
    }
}


