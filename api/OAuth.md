OAuth
===
* 사용자가 소유한 정보가 필요할때(ex: google calender, gmail.. naver email) 해당 리소스 서버에어 인증하는 방식
* 과거에는 직접 리소스 서버에서 아이디와 비밀번호르 가져와서 처리르 하였으나 심각한 보안문제를 일으킬 가능성이 있기 때문에 Oauth가 등장했다.
* 순서
  * 리소스 서버(구글,네이버..)에 등록해야한다. "우리의 서비스는 당신들 서비스를 사용할것입니."
  * 그러면 리소스 서버에서 return 으로 client Id, client Secret(노출되면안됨) 두 개의 값을 발급해준다. client는 해당 정보를 저장함
  * 그리고 사용자가 클리이언트에 접속할때 안내 메시지를 받는다. "해당 클라이언트에서 당신의 구글 캘린더를 사용할수 있도록 허락해주십시오" 뜨고 허용버튼을 눌러줘야함
  * 사용자가 '허용' 해주었을때는 리소스 서버에서 code라는것을 준다.
  * 받은 code 값 + id + secret 값을 다시 리소스서버에 전송한다.
  * 리소스 서버가 해당값을 비교해서 검증
  * 검증이 완료되었다면 **access token 을 발급**(진짜 비밀번호) 해주는데 이를 클라이언트가 보관해놓음
  * 그리고 access token 을 이용해서 리소스서버에 접속하고 api 사용
* Access token
  * 수명이 존재한다. 
  * 그래서 다시 지난번의 과정을 해야 하는데 이는 번거로우니까 RefreshToken을 사용한다.
  * RefreshToken
    ![](img/refreshtoken.png)
    * RefreshToken 을 사용해서 Access Token을 다시 발급받는다.
    * 이때, RefreshToken을 다시 받는 경우도 있고 아닌 경우도 있다.
* [구글에서 OAuth는 모든 플랫폼에 사용되는 Google APIs Client Library 안드로이드에서 사용 가능한 GoogleAuthUtil 로 나뉜다.](https://stackoverflow.com/questions/22142641/access-to-google-api-googleaccountcredential-usingoauth2-vs-googleauthutil-get)
  * |Google APIs Client Library|GoogleAuthUtil|
    |:--:|:--:|
    |If you are developing for some other platform than Android you can not use GoogleAuthUtil as it is an Android specific library.|Using this requires no other libraries or external dependencies than the Google Play Services|
    |If you are developing a cross platform application you can use the Google APIs Client Library in your shared code for for both Android and other platforms.|Your app's footprint should be smaller since you don't have to include additional libraries.|
    |If you interact a lot with many of Google's services this library may make things easier for you.|If your interaction with Google is limited it might be easier to just use the GoogleAuthUtil directly instead of going trough another library.|
    |If you are already using this and it works as wanted there isn't really any drawback to continue using it as it is a wrapper for GoogleAuthUtil so you get all the advantages of GoogleAuthUtil compared to using the AccountManager or some other library based on the AccountManager.|GoogleAuthUtil shouldn't be that hard to use as it is, so using a library that wraps around it to simplify it might not be that much easier to use.|
