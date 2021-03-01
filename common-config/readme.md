## 공통 모듈
1. CommonConfig.java
```text
@ConfigurationProperties @Getter @Setter # application.yml 파일 자동읽기 POJO
@ConfigurationProperties 만으로는 Bean으로 등록 안됩
    1. Application.java
    @ConfigurationPropertiesScan
        @ConfigurationProperties 를 찾아서 Bean으로 자동 등록할 뿐아니라 (@Component와 동일) IntelliJ에서는 application.properties의 자동완성도 지원하게 해준다.
```
2. CommonConfigAutoConfiguration.java
```text   
# POJO Bean 등록
@Configuration @Bean 

# 이 Bean을 사용하는 쪽에 동일한 Bean이 등록되지 않은 경우에만 사용.
@ConditionalOnMissingBean

# new로 새로운 객체 생성
CommonConfig commonConfig = new CommonConfig();
```
3. resources/META-INF/spring.factories
```properties
# POJO Bean 등록 AutoConfiguration 설정
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
   me.kalpha.commonconfig.config.CommonConfigAutoConfiguration
```
4. pom.xml
* groupId, artifactId, version 등록
```text
 <groupId>me.kalpha</groupId>
 <artifactId>common-config</artifactId>
 <version>0.0.1-SNAPSHOT</version>
```
* 불필요 dependency 삭제
* Maven Plugin 삭제
5. Common module 로 등록
* mvn install -> Local 저장소에 등록

## Application
1. pom.xml
* dependency 등록
```xml
<dependency>
   <groupId>me.kalpha</groupId>
   <artifactId>common-config</artifactId>
   <version>0.0.1-SNAPSHOT</version>
</dependency>
```   
2. Codes
```text
@Autowired
CommonConfig commonConfig;
```