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

## Spring Security 적용
1. "Spring Security 적용(자동 설정), Test 적용" 과 동일하게 적용
2. Account, AccountRepository, AccountService를 생성한다.
3. AccountService에 UserDetailsService를 implements한다.
4. loadUserByUsername을 Override한다.
   * 사용자가 입력한 User정보가 파라미터를 통해 UserDetails에 담긴다.
   * 인증이 되면 UserDetails를 리턴하고, 안되면 UsernameNotFoundException을 던진다
   * @param username : 사용자가 입력한 Username
   * Spring Boot는 UserDetails로 User 객체를 제공한다. loadUserByUsername에서는 이 User 객체에 Account객체를 담아서 return 한다.
5. <b>Account를 save할 때는 항상 PasswordEncoder로 encode해서 저장해야 한다.</b> 

## PasswordEncode로 encode하지 않은 password를 이용해 인증하는 방법
> passsword encode 필요 없음
```java
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * PasswordEncoder를 사용하지 않고 저장된 password를 사용해서 인증을 수행할 수 있다.
     * 이 경우 심각한 보안 결함이 발생할 수 있다. (일반적인 경우는 사용하면 안됨)
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
```


