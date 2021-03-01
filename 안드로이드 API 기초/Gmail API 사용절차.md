GMail API 사용 준비 절차
===
GMail API 준비
---
* 구글 콘솔에서 프로젝트를 생성하고 해당 프로젝트에서 사용할 Gmail API 활성화
  * OAuth 동의 화면 작성
      * OAuth 동의 화면에 OAuth 를 동의할때의 활용될 개인정보
* OAuth 2.0 화면 준비
  * GMail API 사용을 위한 OAuth2.0 인증
    * OAuth 2.0 을 사용하기 위해서는 다음과 같은 절차가 필요합니다.
      * 어플리케이션 홈페이지
      * 어플리케이션 개인정보처리방침
      * 어플리케이션 서비스 약관
      * API 사용 범위
* OAuth 2.0 구글로부터 승인 요청
  * 승인을 받지 않을경우 구글 콘솔에 미리 등록된 테스트 사용자 100명만 사용 가용
  * 확인 절차에는 대략 일주일정도 소모될것으로 예상
  * 구글측에 제공해야 하는 정보
    * 앱 개인정보처리방침의 공식 링크
    * 범위 내에서 가져오는 Google 사용자 데이터를 어떻게 사용할 계획인지 보여주는 Youtube 동영상
    * 민감하거나 제한된 사용자 데이터에 엑세스해야 하는 이유를 Google에 알리는 서면 설명서
    * Google Search Console 에서 확인된 모든 도메인
* 문제점
  * OAuth 동의화면을 보기위해서는 지금 사용하고 있는 인증방식을 구글에서 새롭게 제공하는 Google Sign-In 방식으로 변경해야함
  * 구글플레이에서 이메일 API를 사용 하는 앱들은 해당 화면으로 구성되어있음
  * 다른 코드를 사용하면 해당 화면이 노출은안됨
    * ```java
      credential = GoogleAccountCredential.usingOAuth2(mContext, Collections.singleton(GmailScopes.MAIL_GOOGLE_COM));
      httpTransport = AndroidHttp.newCompatibleTransport();
      jsonFactory = GsonFactory.getDefaultInstance();


      public void buildGmailService(){
          credential.setSelectedAccountName(SendEmailActivity.getLoggedInGoogleAccount());

          service = new Gmail.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("SecureCamera")
                .build();
                
      // 이렇게 진행후에 
      // Gmail API 를 호출하면 UserRecoverableAuthIOException 이 발생하는데 여기서 startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION); 를 호출하면 권하 확인 화면이 출력됨
      try{
      ...
      listResponse = mGoogleOAuth.getService().users().labels().list("me").execute();
      ...
      } catch (UserRecoverableAuthIOException e) {
        startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
      } 
                
  * 문서에서 제공한대로 코드를 작성하고 진행하여서 로그인까지는 되나 Gmail API 를 사용요청을 하면 OAuth 2.0 인증화면에서 진행이 안됨
  * 해당 방식을 사용하지 않아도 OAuth 2.0 사용자 정보 화면을 인증해주는지 확실치 않음
