## H2 설치 및 연결
1. h2database.com 접속해서 download
2. 설치
3. C:\Devtools\H2\bin\h2.bat 실행
  - h2 Web 화면 자동 load 됨
4. Web화면에서 Generic H2 (Server)로 연결
  - "jdbc:h2:tcp://localhost/~/test" 입력 후 연결
  - 만일 에러 발생 시 -> Database "~/test" not found, and IFEXISTS=true, so we cant auto-create it [90146-199]
    - DB파일이 없는 경우로, "jdbc:h2:~/test" 변경 입력 후 연결 <- 파일 생성되면서 직접 연결됨
      - 이는 파일에 직접 접근하는 방식으로 파일에 락이 걸려서 여러곳에서 접속을 못하는 문제가 있음
    - DB파일이 생성된 이후에는 "jdbc:h2:tcp://localhost/~/test" 로 접속

## DTO로 조회하기
1. Repository 설정
> 마치 객체를 생성하는 것 처럼 new 오퍼레이션을 사용해야 한다. 이때 Package 패스도 전체를 입력해야 한다.
```java
@Query("select new me.kalpha.jpatest.tr.entity.MemberDto(m.id, m.name, t.name) from Member m join m.team t");
List<MemberDto> findMemberDto();
```

## find[]ByUsername
1. []안에는 아무거나 들어가도 된다.
  - findByUsername 과 findXyzByUsername 은 동일한 의미이다.

## namedQuery
1. Entity에 Query 작성
2. App로딩 시점에 오류를 발견할 수는 장점이 있다.

## Page & Slice
1. Page는 totalElements를 통해 전체 레코드 카운트를 Query한다.
2. Slice는 '더보기'를 위해 Page Count + 1 을 통해 '더보기'할 내용이 있는지만 확인한다. -> Page에 비해 성능에 유리하다.
3. Page와 Slice의 사용법은 동일하며 Return Type만 변경하면 된다.
4. Page 사용시 성능개선을 위해 Count Query를 임의로 지정할 수 있다. 예) -> @Query(countQuery = "select count(m.id) from Member m from id > :id")
5. page.map(...)을 통해 Page를 Dto로 변환하기 가능하다.

## Fetch Join
1. fetch = FetchType.LAZY 로 설정된 경우 Join된 데이터는 실제 사용될때 Query가 나간다.
  - 이런 경우 Loop를 도는 경우에 N + 1 Query가 발생하게 되는데 이를 방지하기 위해, LAZY무시하고 Join Query가 실행되게 된다.
2. 사용법
  - JPQL : @Query("select m from Member m left join fetch m.team")
  - @EntityGraph(attributePaths = {"team"})
  - @Query("select m from Member m") + @EntityGraph(attributePaths = {"team"})

## Query Hint
1. 성능개선을 위해 Read Only로만 Query를 설정할 수 있다. 성능테스트 후에 사용해야 한다.

## Custom Repository
1. MemberCustomRepository Interface 생성
2. MemberCustomRepositoryImpl Class 생성
3. MemberRepository에 MemberCustomRepository Interface 상속

## Auditing 
1. BaseTimeEntity, BaseEntity 생성 
  - @EntityListeners(AuditingEntityListener.class), @MappedSuperclass 를 Class에 적용한다.
  - Main Class에 
2. Main Class 변경
  - @EnableJpaAuditing을 Class에 적용한다. 
3. bean을 등록한다.
  - Session으로 부터 User ID를 리턴하는 bean을 등록한다.
    ```java
        @Bean
        public AuditorAware<String> auditorAware() {
            return () -> Optional.of("jpa-test");
        }
    ```

## Pageable
1. Global Default Size 설정, application.yml
```yaml
spring:
  data:
    web:
      pageable:
        default-page-size: 6
```
2. 메소드 Default Size 설정, Pageable 아규먼트에 추가
```
Pageable pageable --> @PageableDefault(size = 8) Pageable pageable
```
3. Entity -> DTO
```java
Page<Member> page = memberRepository.findAll(pageable);
Page<MemberDto> dtoPage = page.map(member -> new MemberDto(member.getId(), member.getUsername(), member.getTeamId()));
retur dtoPage;
```
    