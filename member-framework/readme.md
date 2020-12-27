### spring-config-demo를 좀 더 real하게 구현한 예제입니다.
다음 기능을 가지고 있다.
* 회원가입
* 로그인
* 내정보 보기
* 관리자페이지 이동
* 로그아웃

### 기타
* Controller에 @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")를 이용해 설정된 권한을 체크합니다.
* thymeleaf의 "sec: "tag는 권한에 따라 페이지 링크를 숨길 수 있다.
