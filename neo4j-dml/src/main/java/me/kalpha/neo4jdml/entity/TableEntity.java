package me.kalpha.neo4jdml.entity;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Node("Table")
public class TableEntity {
    @Id
    private String name;

    public TableEntity(String name) {
        this.name = name;
    }
}
