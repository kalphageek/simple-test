package me.kalpha.neo4jdml.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter @Setter
@NoArgsConstructor
@Node("TABLE")
public class TableEntity {
    @Id
    private String name;

    public TableEntity(String name) {
        this.name = name;
    }
}
