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