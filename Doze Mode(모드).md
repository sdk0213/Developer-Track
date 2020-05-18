Doze Mode
===

Doze - [출처 : kimch3617 티스토리](https://kimch3617.tistory.com/entry/Doze-%EB%AA%A8%EB%93%9C%EB%9E%80-Doze-%EB%AA%A8%EB%93%9C-%EC%B5%9C%EC%A0%81%ED%99%94)
---
* Android M (6.0) 부터 생긴 기능으로 기기를 오랫동안 사용하지 않을경우 다음기능들의 활동을 지연시켜서 **배터리 소모를 줄여주는 모드** 이다.
  * 앱의 백그라운드 CPU
  * 앱의 백그라운드 Network
* Doze Mode의 시작
  * **전원을 충전하지 않고 화면이 꺼진 채로 기기를 일정 기간 정지 상태**로 두면 Doze모드 시작
* 유지보수 기간
  * ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FehdOrk%2Fbtqxdq00sRA%2FxFrEMckuubCvLYZkXuW0rK%2Fimg.png)
  * 계속 정지 상태로 두는것이 아닌 한번에 처리하는 시기가 있는데 이를 유지보수 기간이라고 한다.
  * 유지보수 기간은 시간이 지남에 따라 점차 줄어든다.
* Doze Mode의 해제
  * 기기를 움직임
  * 화면 키기
  * 충전
* Doze Mode에서의 제한 사항
  * Network Access 정지
  * WakeLock 무시
  * AlarmManager 알람이 다음 유지보수기간까지 보류
    * **Doze 모드에서도 해제를 원할경우** 다음과 같은 함수 사용
      * setAndAllowWhileIdle()
      * setExactAndAllowWhileIdle
      * setAlarmClock()
  * Wi-Fi 검색 안함
  * 동기화 어댑터 실행 안함
  * JobScheduler 실행 안함
* ** 가능한 FCM 사용 **
