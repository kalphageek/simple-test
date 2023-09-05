package me.kalpha.neo4jjpa.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Node
public class Table {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Builder
    public Table(String name) {
        this.name = name;
    }
}
