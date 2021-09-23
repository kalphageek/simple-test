1. 사용자(Resource Owner)가 Web(Client)으로 실행
- User Access -> http://localhost:8000/ (redirect)
  
  2. Controller "/" 에 의해 "index.jsp"로 Redirect   
  - http://localhost:8000/index.jsp (User Click)
    
  3. Kakao(Resource Server) 인증 link (카카오에 생성한 App Key or Client Id/Client Secret 제공), 
    * 이때 카카오에 로그인이 안되어 있으면 로그인 페이지, 되어 있으면 사용자 동의 화면 뜬다   
      또한 사전에 등록한 redirect_url이 같은지 비교한다.
      카카오의 권한 승인 화면을 보고 사용자가 승인한다.
    * Login이 안된 경우
    ```json
    {
      "error": {
        "code": 401,
        "message": "Request is missing required authentication credential. Expected OAuth 2 access token, login cookie or other valid authentication credential. See https://developers.google.com/identity/sign-in/web/devconsole-project.",
        "errors": [
          {
            "message": "Login Required.",
            "domain": "global",
            "reason": "required",
            "location": "Authorization",
            "locationType": "header"
          }
        ],
        "status": "UNAUTHENTICATED"
      }
    }
    ```
  - https://kauth.kakao.com/oauth/authorize (redirect)
    
  4. Kakao 인증 link로 부터 redirect_uri 호출되면서 code(Authorization Code: 임시비번) 값 return됨
  - http://localhost:8000/login (redirect)
    5. Controller /login에서 Kakao의 Access Token, Refresh Token 받기 (code 제공)   
    - getAccessToken (https://kauth.kakao.com/oauth/token)
    6. Controller /login에서 Kakao의 에 로그인한 User정보 받기 (Access Token 제공)   
    - getUserInfo (https://kapi.kakao.com/v2/user/me)
    
  7. index.jsp, login 완료 화면 display
  - http://localhost:8000/index.jsp (User Click)
    
  8. logout 버튼 클릭하면 Session으로 부터 userId정보 삭제하고, 카카오에 unlink 호출 
  - https://kapi.kakao.com/v1/user/unlink
  
  9. refresh token 재발급 참조
  - https://datatracker.ietf.org/doc/html/rfc6749
  - https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#refresh-token