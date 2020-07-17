Context
===

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



