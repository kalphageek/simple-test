package me.kalpha.neo4jjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@NodeEntity
public class Table {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String schema;
    private String systemId;

    @Builder
    public Table(String name, String schema, String systemId) {
        this.name = name;
        this.schema = schema;
        this.systemId = systemId;
    }

    @Relationship(type = "has") // 이 relation들을 갖는다
    private Set<Relation> relations = new HashSet<>();
}
