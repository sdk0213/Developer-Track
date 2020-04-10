서비스
===

[출처](https://fullstatck.tistory.com/23)

Service
---
* UI 없이 실행이 된다.
* startService() 로 시작한다.
* 아무 스레드에서 생성되며 아무곳에서 실행 가능
* Main Thread에 포함되는 백그라운드에서 동장
* 무거운 작업이면 Main Thread에 영향을 줄수 있음

IntentService
---
* Main Thread랑 상관없는 작업을 할 때 주로 이용
* Intent로 실행되며 새로운 스레드에서 onhandleIntent()가 불린다.
* 아무 스레드에서 생성되며 아무곳에서 실행 가능
* New Thread에서 동작
* Intent호출에 관해서 순차적으로 처리된다.
