package me.kalpha.neo4jdml.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@ToString(of = {"hashCode", "query"})
@Node("QUERY")
public class QueryEntity {
    @Id
    private Integer hashCode;
    private String query;

    @Relationship(type = "FROM", direction = Relationship.Direction.INCOMING)
    private Set<TableEntity> tables = new HashSet<>();

    public QueryEntity(String query) {
        this.hashCode = query.hashCode();
        this.query = query;
    }

    public void addTable(TableEntity entity) {
        if (tables == null) {
            tables = new HashSet<>();
        }
        tables.add(entity);
    }
}
