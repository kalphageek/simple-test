## 공통 모듈
1. CommonConfig.java (POJO)
```java
@ConfigurationProperties @Getter @Setter # application.yml 자동읽기
```

2. CommonConfigAutoConfiguration.java (POJO Bean 등록)
```java   
# Bean 등록
@Configuration @Bean # application.yml 자동읽기

# 이 Bean을 사용하는 쪽에 동일한 Bean이 등록되지 않은 경우에만 등록.
@ConditionalOnMissingBean

# new로 새로운 객체 생성 필요
CommonConfig commonConfig = new CommonConfig();
```

3. resources/META-INF/spring.factories (POJO Bean 등록 AutoConfiguration)
```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
   me.kalpha.commonconfig.config.CommonConfigAutoConfiguration
```
4. pom.xml
    * 불필요 dependency 삭제
    * Maven Plugin 삭제

5. Common module 로 등록
    * mvn install -> Local 저장소에 등록