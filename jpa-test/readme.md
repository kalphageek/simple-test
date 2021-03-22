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
   

## namedQuery
1. Entity에 Query 작성
2. App로딩 시점에 오류를 발견할 수는 장점이 있다.

## Page & Slice
1. Page는 totalElements를 통해 전체 레코드 카운트를 Query한다.
2. Slice는 '더보기'를 위해 Page Count + 1 을 통해 '더보기'할 내용이 있는지만 확인한다. -> Page에 비해 성능에 유리하다.
3. Page와 Slice의 사용법은 동일하며 Return Type만 변경하면 된다.
4. Page 사용시 성능개선을 위해 Count Query를 임의로 지정할 수 있다. 예) -> @Query(countQuery = "select count(m.id) from Member m from id > :id")
