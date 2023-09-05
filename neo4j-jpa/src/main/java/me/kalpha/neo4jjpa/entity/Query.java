package me.kalpha.neo4jjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Node(labels = "name")
@NoArgsConstructor
@Accessors(chain = true)
public class Query {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "name")
    private String name;
    private String queryString;
    private String tag;
    private String systemId;

    @Relationship(type = "from")
    private Set<Table> tables = new HashSet<>();
}
