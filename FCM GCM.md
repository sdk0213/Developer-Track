FCM과 GCM
===

FCM,GCM 개념 - [출처](https://life-with-coding.tistory.com/28)
---
  + 서버에서 클라이언트 애플리케이션으로 메시지(알림)을 안정적으로 보낼수 있는 교차 플랫폼 메시징 솔루션
    + GCM
      + 구글에서 제공하는 서버에서 애플리케이션으로 푸시 메시지를 보낼 수 있는 서비스
      + android,ios 지원
      + 주소 : <https://android.googleapis.com/gcm/send>  
      + **Android 9.0 (Pie) 부터 작동하지 않는다. 그렇기 때문에 FCM을 사용해야한다.**
    + FCM
      + gcm의 상위 버젼
      + android, ios, mobild Web 지원
      + 주소 : <https://fcm.googleapis.com/fcm/send>
        + FCM 등록
          + 사용자는 FCM을 등록
          + FCM과 연결
          + FCM 등록 토큰을 리턴(to Client)
          + 클라이언트는 서버에 등록 토큰을 전달
       
FCM 실제 사용 방법 - [출처](https://kwon8999.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-FCM-%EA%B5%AC%ED%98%841%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%85%8B%ED%8C%85-%EB%B0%8F-%EA%B5%AC%ED%98%84?category=881640)
---
+ FCM 설치
  + **설치과정 출처 참고**
  + google-services.json 파일을 프로젝트에 삽입시켜야한다.
  + Firebase 종속성 추가
  + Android 8.0(Oreo) 부터는 Notification Channel을 넣어야 한다.
  + Payload
    + Push 데이터 형식, 포맷
    + Payload에 Notification이 있다면 onMessageReceived 이 작동하지 않는다.
                  
FCM 용어
---
* payload
  * 페이로드란 Push 데이터 형식, 포맷을 말하는 것입니다.
* collapse_key
  * 영문 뜻 : collapse = 무너뜨리다.
  * collapse_key를 가진 메시지가 이미 존재한다면 기존 메시지는 폐기되고 새로운 메시지로 변경되게 한다.
  * 기기가 연결되어 있지만 **잠자기 상태인 경우 우선순위가 낮은 메시지는 잠자기 상태가 해제될 때까지 FCM이 보관**합니다. 또한 이 시점에서 collapse_key 플래그가 역할을 수행합니다. 
   * 축소 키와 등록 토큰이 **동일한 메시지가 이미 저장되어 전송 대기 중이라면 이전 메시지가 삭제**되고 새 메시지로 대체됩니다. 즉, 이전 메시지가 새 메시지에 의해 축소됩니다. 
   * 하지만 **축소 키가 설정되어 있지 않으면 나중에 전송하기 위해 새 메시지와 이전 메시지가 둘 다 저장**됩니다.

          
          
