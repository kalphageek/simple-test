package me.kalpha.neo4jdml.entity;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@Node("QUERY")
public class QueryEntity {
    @Id
    private final Integer hashCode;
    private final String query;

    @Relationship(type = "FROM", direction = Relationship.Direction.INCOMING)
    private Set<TableEntity> froms = new HashSet<>();

    public QueryEntity(String query) {
        this.hashCode = query.hashCode();
        this.query = query;
    }

    public void workWith(TableEntity entity) {
        if (froms == null) {
            froms = new HashSet<>();
        }
        froms.add(entity);
    }
}
