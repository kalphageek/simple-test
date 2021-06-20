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
```
@EnableNeo4jRepositories
```
3. Test코드
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
4. query에서 'Table2' 문구를 포함하는 QueryEntity를 찾아서 그 Query에 사용된 전체 테이블을 리턴
```
match (q:QUERY)<-[:FROM]-(t) where q.query contains "Table2" return q,t
```