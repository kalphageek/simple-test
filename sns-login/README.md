1. 사용자가 Web으로 실행
- User Access -> http://localhost:8000/ (redirect)
  
  2. Controller "/" 에 의해 "index.jsp"로 Redirect   
  - http://localhost:8000/index.jsp (User Click)
    
  3. Kakao 인증 link (카카오에 생성한 App Key 제공), 이때 카카오 사용자 동의 화면 뜬다   
  - https://kauth.kakao.com/oauth/authorize (redirect)
    
  4. Kakao 인증 link로 부터 redirect_uri 호출되면서 code값 return됨
  - http://localhost:8000/login (redirect)
    5. Controller /login에서 Kakao의 Access Token, Refresh Token 받기 (code 제공)   
    - getAccessToken (https://kauth.kakao.com/oauth/token)
    6. Controller /login에서 Kakao의 에 로그인한 User정보 받기 (Access Token 제공)   
    - getUserInfo (https://kapi.kakao.com/v2/user/me)
    
  7. index.jsp, login 완료 화면 display
  - http://localhost:8000/index.jsp (User Click)
    
  8. logout 버튼 클릭하면 Session으로 부터 userId정보 삭제하고, 카카오에 unlink 호출 
  - https://kapi.kakao.com/v1/user/unlink