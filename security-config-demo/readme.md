## Spring Security 적용 (자동 설정)
1. pom.xml 적용
    > pom.xml을 적용하면 자동으로 InMemoryUser를 하나를 만들어서 인증될 수 있도록 해준다.
* 계정명 : user
* 패스워드 : c4b656f4-f4b2-4aed-acc0-5bc6198826a5 <- Run Log에서 확인
* Default 적용 범위 : 전체
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
## Test 적용
1. pom.xml
```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <version>5.4.2</version>
    <scope>test</scope>
</dependency>
```
2. Class 파일
    > @WithMockMvc를 Class 또는 Method 레벨에 적용하면 가상의 계정을 만들어서 Test에 적용해준다.


