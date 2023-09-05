package me.kalpha.neo4jjpa.runner;

import me.kalpha.neo4jjpa.entity.Query;
import me.kalpha.neo4jjpa.entity.Table;
import me.kalpha.neo4jjpa.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TestRunner implements ApplicationRunner {
    @Autowired
    QueryRepository queryRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Table table1 = new Table("TAB1");
        Table table2 = new Table("TAB2");
        String queryString = "SELECT * FROM TAB1, TAB2 WHERE TAB1.C1 = TABL2.C2";
        Query query = new Query()
                .setName("Query1")
                .setTag("20230101")
                .setSystemId("HUBADM")
                .setQueryString(queryString)
                .setTables(Set.of(table1, table2));

        queryRepository.save(query);


        Table table3 = new Table("TAB3");
        Table table4 = new Table("TAB4");
        queryString = "SELECT * FROM TAB3, TAB4 WHERE TAB3.C1 = TABL4.C2";
        Query query2 = new Query()
                .setName("Query2")
                .setTag("20230101")
                .setSystemId("HUBADM")
                .setQueryString(queryString)
                .setTables(Set.of(table3, table4));

        queryRepository.save(query2);
    }
}
