package me.kalpha.jpatest.neo4j.entity;

import lombok.Builder;
import lombok.Getter;
import me.kalpha.jpatest.config.Constants;
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

    @Relationship(type = Constants.REFERENCE_OF)
    private Set<Table> refernceOf = new HashSet<>();

    @Relationship(type = Constants.SUMMARY_OF)
    private Set<Table> summeryOf = new HashSet<>();

    @Relationship(type = Constants.PART_OF)
    private Set<Table> partOf = new HashSet<>();
}
