[Google Sign-in](https://developers.google.com/identity/sign-in/android/sign-in)
---
* OAuth 2.0 을 사용해서 구글 로그인을 하는데 사용하는 방법을 설명한다.
* https://developers.google.com/identity/sign-in/android/start-integrating 사이트로 이동한뒤 중간에 Configure a project 를 클릭한다.
  * 이곳에서 생성할경우 그냥 구글 관리자 콘솔에서 만들어지는것과 다르게 안드로이드를 선택했을때 구글 로그인용 웹 어플리케이션 사용자 인증 정보도 추가로 만들어진다.
  아마도 구글 안드로이드도 로그인시에는 무조건 웹 어플리케이션으로 사용해야 하는것같다.
* **안드로이드에서느 절대로 안드로이드 클라이언트 ID 를 사용하지 않는다. 문서상에서 얘네들이 설명을 이상하게 하는것도 있고 여하튼 쓰지 않는다.** [관려 스텍오버플로](https://stackoverflow.com/questions/33583326/new-google-sign-in-android)


로그인
---
* 기본적으로 로그인을 요청할때 옵션을 설정할수있는데 기본코드는 다음과 같다.
  * ```java
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
  * 만약에 API 사용을 위해 Scope 를 추가하고싶다면 다음과 같이 코드를 추가하면된다.
    ```java
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestScopes(new Scope(GmailScopes.MAIL_GOOGLE_COM)) // GMAIL SCOPE
        .build();
* 마지막으로 이미 로그인을 진행한 사용자가 있는지 체크하려며 다음 코드를 사용해서 분별하면된다.
만약에 아무도 없다면 Null 값을 반환한다.
  * ```java
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
* 로그인을 요청하는 Intent는 다음과 같다.
  * ```java
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN); // RC_SIGN_IN 은 resultcode값 상수 생성  
  * 범위를 요청했을경우는 엑세스 권한을 물어보는것도 같이 등장한다.
* 토큰이 만료되었거나 해제 된경우는 GoogleSignInClient.silentSignIn 를 사용하면된다.

로그인 정보흭득
---
* 사용자가 권한과 이메일을 전부 확인하였을때 onActivityResult 로 값이 들어오고 GoogleSignInAccount이라는 객체를 얻을수있다.
  * ```java
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    .
    .
    .
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
       try {
           GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
           // The ApiException status code indicates the detailed failure reason.
           // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
           updateUI(null);
        }
    }
  * **GoogleSignInAccount 라는 객체는 대표적으로는 유저이름과 같은 로그인한 유저의 정보를 가지고 있는 객체이다.**
  * 해당 객체에서 다음을 사용해서 얻을수 있는 정보는 다음과 같다.
    * getId -> 구글아이디
    * getEmail -> 이메일주소
    * getIdToken -> 아이디 Token 값 -> [해당 토큰을 사용해서 백엔드서버에서 유저를 식별하는데 사용 가능하다 관련내용 참고 링크](https://developers.google.com/identity/sign-in/android/backend-auth)
  * ```java
    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
      if (acct != null) {
        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();
      }

로그아웃하기
---
* ```java
  private void signOut() {
  mGoogleSignInClient.signOut()
          .addOnCompleteListener(this, new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  // ...
              }
          });
  }
