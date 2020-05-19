Push 서비스
===

Push Message [출처 - rationalowl.tistory](https://rationalowl.tistory.com/6)
---
* Android 8.0 (O)의 백그라운드 제한으로 '유실없는 실시간 푸시 알림'지원이 불가능 해졌다.
* 이를 해결하기 위한 대표적인 방법으로는 다음이 있다.
  * 구글
    * GCM(Google Cloud Messaging)
    * FCM(Firebase Cloud Messaging) (전송률 90%)
  * 애플
    * APNS(Apple Push Notification Service)
* 위의 서비스의 장점
  * 쉬운 푸쉬 설정 (복잡한 과정 없애줌)
  * 전송률 향상
* 자체 푸쉬 서버를 사용하는 방법
  * MQTT 서버 ( or XMPP)
    * 비싸다.
    * 전송률은 좋다.
    * 속도가 느리다.
  * 자체 프로토콜
    * 잘 만들면 좋지만 못만들면 망한다.
  * 


upns
---
* **Uracle Push Notification System**
* MQTT Protocol 기반으로 Android Application으로 data를 전송하게 해주는 private Service이다.
* Server에서 가져와야할 새로운 data가 있음을 알리는 메시지를 지원 (4k(권고) ~ 256Mbtes)
* 단순히 원시 data를 android 단말로 보내며 이 data를 통해 application 에서 제어함
