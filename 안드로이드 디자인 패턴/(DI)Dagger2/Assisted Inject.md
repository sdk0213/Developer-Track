# Assisted Inject
---
### 필요성
* Dagger2 는 초반에 모든 설정을 해줘야 하는데 만약 객체가 중간에 서버로부터 데이터를 받아서 생성된다면 즉, 런타임중에 객체를 만들어서 해당 객체를 의존성주입해야하는경우는 어떻게 해야하는가? 에 대한 답으로 나온것이 Assisted Inject 이다.
* 런타임 데이터를 포함한 객체를 factory 패턴으로 생성할 수 있다. 쉽게 설명하자면, factory에 런타임 데이터만 넘겨주면 의존성 주입까지 된 객체를 얻을 수 있는 것이다. 
### 수동 의존 주입 도구
* Dagger와 AssitedInject를 활용하면 간단한 인터페이스로부터 자동으로 생성되는 **팩토리 구현체를 통해 많은 보일러플레이트 코드를 제거할 수 있다.**
* 사용의 이점
  * Workermanager 파라미터 주입
  * ViewModel SavedStateHandle
* factory에 런타임 데이터만 넘겨주면 의존성 주입까지 된 객체를 얻을 수 있는 것이다.
---
### 코드 1 
* @Module 클래스에 @AssitedModule 을 추가할경우 AssitedInject로 시작하는 RobotModule이 추가된다.
* AssistedInject 라이브러리는 메서드 시그니처를 기반으로 코드를 생성하므로, 타겟오브젝트의 **@Assisted 생성자 파라미터 이름과 Factory 메서드 파라미터 반드시 일치해야한다.**
##### AssitedModule 생성
* ```kotlin
  @AssistedModule
  @Module(includes = [AssistedInject_RobotModule::class])
  class RobotModule {
      @Provides
      fun provideRandomId() = Random.nextInt(10000)
  }
##### 주입받을 클래스
* ```kotlin
  class Robot @AssistedInject constructor(@Assisted val name: String, val id: Int) {
    fun say():String = "Hello, I'm ${name}.\nMy id is $id"
    @AssistedInject.Factory
    interface Factory {
        fun create(name: String): Robot
    }
  }
### 코드 2 - [출처는 cmplxn님의 velog](https://velog.io/@cmplxn/Assisted-Inject-사용하기)
* ```kotlin
  class MyClass @AssistedInject constructor(
      val age: Int,
      @Assisted val name: String
  }
* ```kotlin
  @AssistedInject.Factory
  interface Factory {
      fun create(name: String): MyClass
  }
* 기존에 제공해주던 Module -> Assisted Module로 수정해야된다.
  ```kotlin
  @AssistedModule
  @Module(include = [AssistedInject_MainActivityModule::class]) // 컴파일 에러가 나지만 런타임중에 생성되기에 에러가아님
  class MainActivityModule {
      @Provides
      fun provideInt(): Int = 33
      
      @Provides
      fun providePi(): Double = 3.14
  }  
* ```kotlin
  class MainActivity : DaggerAppCompatActivity() {
      @Inject
      lateinit var myClassFactory: MyClass.Factory // Factory를 @Inject 받음

      lateinit var name: String
      lateinit var myClass: MyClass

      override fun onCreate(savedInstanceState: Bundle?) {
          AndroidInjection.inject(this)
          name = "cmplxn"                        // 생성자 주입 name : String = "cmplxn"
          myClass = myClassFactory.create(name)  // 생성자 주입을 런타임중에 진행
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)
      }
  }
 
