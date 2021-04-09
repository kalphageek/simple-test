package me.kalpha.neo4jjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Getter
@Builder
@AllArgsConstructor
@NodeEntity
public class Relation {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
