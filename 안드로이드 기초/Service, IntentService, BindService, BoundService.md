서비스 - [참고하면 좋은자료](https://brunch.co.kr/@mystoryg/93)
===

Service - [출처](https://fullstatck.tistory.com/23)
---
* 길지않은 작업을 할때 사용되며, 만약 오래걸리는 작업을 Service에서 실행된다면 스레드를 사용해야한다. 
* UI 없이 실행이 된다.
* startService() 로 시작한다.
* 아무 스레드에서 생성되며 아무곳에서 실행 가능
* Main Thread에 포함되는 백그라운드에서 동작
* 무거운 작업이면 Main Thread에 영향을 줄수 있음
* return
  * when the phone runs out of memory and kills the service before it finishes executing
  * START_STICKY
    * tells the OS to recreate the service after it has enough memory and call onStartCommand() again with a null intent
  * START_NOT_STICKY
    * tells the OS to not bother recreating the service again.
  * START_REDELIVER_INTENT
    * tells the OS to recreate the service and redeliver the same intent to onStartCommand()
IntentService
---
* 오래걸리지만 메인스레드와 관련이 없는 작업을 할때 주로 이용한다. 메인스레드와 관련된 작업을 해야 한다면 Handler와 BroadCastIntent를 사용해야 한다.
* Main Thread랑 상관없는 작업을 할 때 주로 이용
* Intent로 실행되며 새로운 스레드에서 onhandleIntent()가 불린다.
* 아무 스레드에서 생성되며 **아무곳에서 실행 가능**
* **New Thread**에서 동작
* Intent호출에 관해서 **순차적**으로 처리된다.
* **모든 인텐트가 처리**되면 **onDestroy()**가 호출되며 서비스는 종료된다.

BindService - [출처](http://i5on9i.blogspot.com/2013/01/service-bind-local-service.html)
---
+ 일반적인 Service를 사용해도 Intent를 이용해서 간단한 수준의 정보는 주고 받을 수 있다. 하지만 service의 method등을 호출할수 없다.
+ onBind()는 Client가 딱 한번만 호출 한다. 마지막 Client가 unbind하면 시스템이 그 service를 destroy한다.
+ 기본 'Service'(unBoundService) 와는 다르게 activity 와 fragment와 data를 주고 받을수 있다.
+ 앱 내부의 기능을 외부로 제공할 때 사용한다.
+ 프로세스간 통신을 지원(원칙적으로 AIDL(Android Interface Definition Launguage)를 사용해야한다.)
+ Component <-> {onBind() --> IBinder} <-> Service
+ 만드는 방법은 다음과 같이 세가지가 있다.
  + IBinder를 extends하기
  + Messenger 사용
  + AIDL 이용

BoundService
---
+ 자신에게 Bind 를 할 수 있게 해주는 Service이다.
+ ... 바운드 서비스 계속 진행
