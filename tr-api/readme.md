### Tr API 서비스
> api를 통해 받은 데이터를 e-Hub DB에 저장하고, Kafka의 Topic으로 Pub 한다.

### 양방향 관계에서 infinite recursion 해결법
> @JsonIdentityInfo 추가 
```java
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Eqp1TrDet extends CreatedBaseEntity { 
}

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Eqp1Tr extends CreatedBaseEntity { 
}
```