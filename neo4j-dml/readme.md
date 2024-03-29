### Node / Relation
> (QUERY) <-[FROM]- (TABLE)
1. pom.xml
> 2.4 대 버전을 사용 해야 한다.
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-neo4j</artifactId>
            <version>2.4.7</version>
		</dependency>
```
2. Application
> Main Class에 추가해야 한다
```
@EnableNeo4jRepositories
```
3. application.properties
```properties
spring.neo4j.uri=bolt://db.deogi:7687
spring.data.neo4j.username=neo4j
spring.data.neo4j.password=******
```
4. Test코드
> QueryEntity에 Relation이 있는 테이블을 assign하고 저장하면서, 자동으로 함께 저장된다. 여기서는 저장된 테이블을 find하고 Test한다. 
```java
        String t1Name = "Table1";
        TableEntity t1 = new TableEntity(t1Name);
        TableEntity t2 = new TableEntity("Table2");

        QueryEntity queryEntity = new QueryEntity("select * from Table1, Table2");
        queryEntity.workWith(t1);
        queryEntity.workWith(t2);
        queryRepository.save(queryEntity);

        Optional<TableEntity> optionalt1 = tableRepository.findById(t1Name);
        TableEntity t1_ = optionalt1.get();
        assertTrue(t1_.getName().equals(t1Name));
```
5. cypher query sample
> query에서 'Table2' 문구를 포함하는 QueryEntity를 찾아서 그 Query에 사용된 전체 테이블을 리턴
```
//노드 생성
create (t4:TABLE {name:"Table4"})
//관계 생성
match (q:QUERY)<-[:FROM]-(t) where q.query contains "Table2" return q,t
match (t1:TABLE {name:"Table1"})
match (t2:TABLE {name:"Table2"})
match (t3:TABLE {name:"Table3"})
match (t4:TABLE {name:"Table4"})
match (q1:QUERY {hashCode:1796840983})
match (q2:QUERY {hashCode:92834988})
match (q3:QUERY {hashCode:1796840984})
create 
(q2)<-[:FROM]-(t1)
create 
(q3)<-[:FROM]-(t1)

```