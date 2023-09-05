package me.kalpha.neo4jjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Node
@NoArgsConstructor
@Accessors(chain = true)
public class Query {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String systemId;
    private String query;
    private String tag;

    @Relationship(type = "from")
    private Set<Table> tables = new HashSet<>();
}
