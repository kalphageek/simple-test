## 1-1. 공통 모듈
* common-config 모듈 참조

## 1-2. 공통 모듈 사용 Application
1. pom.xml
* dependency 등록
```xml
<dependency>
   <groupId>me.kalpha</groupId>
   <artifactId>common-config</artifactId>
   <version>0.0.2-SNAPSHOT</version>
</dependency>
```   
2. Codes
```text
@Autowired
CommonConfig commonConfig;
```

##2. DataSize 타입 테스트
* B 
* KB
* MB
* GB
* TB

##3. Actuator
1. application.properties
* show-details : 정보의 deptch (show-components로 변경 가능)
* always : 인증 여부
```properties
management.endpoint.health.show-details=always
```
2. Metric정보 보기
* /acutator/health Uri에 보여줄 정보(status, components, ping)를 보여준다.
* component는 Module에 적용된 resource에 따라 diskSpace / DB / ... 다르게 보인다.
```
http://localhost:8080/acutator/health
```