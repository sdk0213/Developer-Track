Context
===
Context ( 내용 소스 : https://starryz.tistory.com/4 )
---
* 의미
  * 일반적인 프로그래밍
    * 설정 값에 따라서 바뀔 수 있는 전역 환경 정보, 객체의 상태 등을 담고 있는 객체
  * 안드로이드 프로그래밍
    * **어플리케이션 환경에 대한 전역 정보 인터페이스**
* [구조](https://ericyang505.github.io/android/Context.html)
  * ![](https://ericyang505.github.io/android/images/context.png)
  * 해당구조를 보면 알수있듯이 상속받아서 사용하는것이기 때문에 **Application이나 Activity, Service을 생성한 시점부터 이미 생성되어있는 Application Context를 사용**할수있다.
* Application Context
  * Application 인스턴스로부터 생성할 수 있는 Context
    * getApplicationContext() 사용
* Activity(Service) Context
  * Activity를 통해 얻을 수 있는 Context
  * Activity(Service)마다 모두 다른 고유한 인스턴스이다.
  * 고유한 Context = 같은 Base의 Context + 각 Activity 정보
* 역할
  * 컴포넌트를 관리하는 Process가 존재하고, 이 Process 가 어떤 Application Process를 요청했는지 식별하기 위함
  * 컴포넌트를 사용하기 위한 시작점 제공
  * 컴포넌트간 브릿지 역할을 수행
* 적용
  * Application Context
    * 메서드
      * getApplicationContext()
      * 위, 아래 동일
      * getApplication() in activity
    * application의 라이프사이클에 따라 생성/소멸
    * context 참조하는 작업이 activity 을 넘어선다면 대신 사용 가능
      * ex) 비동기 작업
    * **context 를 주입받기 어려운 클래스에서 application 클래스를 상속받은 전역 클래스를 통해 application 인스턴스를 정적으로 참조함으로서 application context를 사용**
    * **view나 dialog 관련 작업은 application context를 사용하면 exception이 발생**
      * ex) dialog는 WindowManager를 사용하고 이는 application (context) instance 에서 참조하고 있지 않음
  * Activity Context
    * 컴포넌트마다 존재
      * **Activity 자기 자신을 말한다.**
    * Activity 라이프사이클에 따라 생성/소멸
    * View와 관련된 클래스에 전달됨
      * 아래 코드에서 context는 해당 View가 속한 Activity Instance 이다.   
      * ```java
        <LinearLayout.java>
        public LinearLayout(Context context) {
           this(context, null);
        }
        ...
        <TextView.java>
        public TextView(Context context) {
           this(context, null);
        }
    * Fragment에서의 getContext() 또한 Activity Context이다.
    * 아래 코드에서 (대부분의 상황에서) activity 나 context 는 같은 값이다.
    * ```java
      <FragmentHostCallback.java>
      FragmentHostCallback(FragmentActivity activity) {
         this(activity, activity /*context*/, activity.mHandler, 0 /*windowAnimations*/);
      }
      FragmentHostCallback(Activity activity, Context context, Handler handler, int windowAnimations) { 
      ...
      
Context - [20.7.17] 이전 내용 - 정리 미약함
---
* 안드로이드의 정보를 담당하는것은 사실 시스템이 아니고 ActivityManagerService라는 일종의 다른 어플리케이션이다.
* 그리고 ActivityManagerService 에 접근하기 위해서는 자기가 어떤놈인지(id)가 필요하고 접근하게 해주는 놈이 필요하다
**그것이 바로 Context이다.**

* Context는 컴포넌트들이 생성될때, 어플이 시작될때 마다 생성된다.그리고 두가지 종류가 있다.
  * 1. Application Context (실행되어서 종료될때까지 동일한 객체) (생명주기에 영향을 받음)
  * 2. Activity Context (엑티비티가 onDestory() 된경우 삭제됨) (

* getApplication() 메소드를 통해 getApplicationContext()로 Context를 얻어 올 수 있다.
* Activity/Service 프레임워크는 Activity또는 Service가 실행 될때 기본 Context에다 필요한 정보를 warp한다.
* BroadcaseReceiver는 onRecevie()시 Context를 가져올 수 있는데, 이때의 Context는 ReveiverRestrictedContext이며 두가지 기능 registerReceiver()와 bindService()를 사용 할 수 없다.
* ContextProvider는 액세스후 getContext()를 통해 Context를 가져 올 수 있다.

* Context 참조
  * LoginActivity.this
    * LoginActivity.this 는 Acitivity를 상속받은 자신의 클래스를 참조하지만 Activity 또한 Context class를 상속받으므로 activity context를 제공하는데 사용될 수 있다.

* getApplication()
  * getApplication()은 Application 객체를 참조하지만 Application 클래스는 Context 클래스를 상속받으므로 application context를 제공하는데 사용될 수 있다.

* getApplicationContext()
  * getApplicationContext()는 application context를 제공한다.

* getBaseContext()
  * getBaseContext()는 activity context를 제공한다.



출처: https://shnoble.tistory.com/57 [노블의 개발이야기]
* 당신은 스타트업 SW 회사의 CEO입니다.
  이 회사엔 DB, UI등 회사의 모든 일을 처리하는 수석 아키텍트가 있습니다.
  이제 당신은 신입 개발자를 채용했습니다.
  이때 그 신입개발자가 DB, UI중 어디에서 일할지, 어떤일을 할지 결정하는것은 수석 아키텍트입니다.
  
  수석아키텍트 = context

  리모컨은 자원에 접근할 수 있게 해주고
  이로써 당연히 리모컨을 가진 사람은 자원에 접근할 수 있습니다.

  리모콘을 가진자많이 TV를 조종할수있다.



