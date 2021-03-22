# DI(Dependency Injection)(의존성주입) = 종속 항목 삽입 - [출처는 안드로이드 공식문서](https://developer.android.com/training/dependency-injection?hl=ko)
### 종속 항목 삽입(주입)이란?
* 클래스를 만들고 사용할때는 거의 원 헌드래드 퍼센트까지는 아니여도 많은경우에 다른 클래스가 필요하다. 이는 잘못 설계된 코드이거나 아키텍쳐가 개판일경우 더욱 심해진다. 
**여하튼 이런 많은경우에 다른클래스에 종속되는 다른 클래스를 삽입하는것을 종속 항목 삽입이라고 한다.**
* 주입(삽입)이란 생성자나 메서드 등을 통해 외부로부터 생성된 객체를 전달받는 것을 의미한다.
---
### 종속 항목 삽입을 넣지 않고 코딩할경우
* 코드를 유지보수 하기 힘들게 만들어주는 코딩방식
##### 그냥 자체 인스턴스를 가지고 초기화하기
* ```kotlin
  class Car {

    private val engine = Engine()

    fun start() {
        engine.start()
    }
    
    // 또는
    
    private val engine : A_Engine() // 만약 Engine()을 A사의 engine인 A_Engine()으로 변경시 여기랑
    
    fun start() {
      engine = A_Engine() // 여기까지 변경을 해야함
    }
  }

  fun main(args: Array) {
      val car = Car()
      car.start()
  }
##### 다른 곳에서 객체를 가져오기
* ```kotlin
  class Car {
  
    fun start()
        Main.getEngine.start() // Main, getEngine 은 아마도 static 
    }
    
  }
  
  fun main(args: Array) {
      val.car =  Car()
      car.start()
  }
---
### 위 코드의 문제점
* 서브클래스 또는 다른 클래스로 대체 구현을 쉽게 사용할 수 없다.
* 테스트 매우매우 힘들어짐
---
### 종속 항목 삽입 사용하기
##### 생성자 삽입
* ```kotlin
  class Car(private val engine: Engine) {

    fun start() {
        engine.start()
    }
  }

  fun main(args: Array) {
      val engine = Engine()
      val car = Car(engine)
      car.start()
  }
##### 필드 삽입(또는 setter 삽입)
* ```kotlin
  class Car {
    lateinit var engine: Engine

    fun start() {
        engine.start()
    }
  }

  fun main(args: Array) {
      val car = Car()
      car.engine = Engine()
      car.start()
  }
---
### 안드로이드에서 DI 구현 - [출처는 trend21c님의 티스토리입니다.](https://trend21c.tistory.com/2112?category=218613)
##### 기존 코드 
* ```kotlin
  class UserRepository(
      private val localDataSource: UserLocalDataSource,
      private val remoteDataSource: UserRemoteDataSource
  ) { ... }

  class UserLocalDataSource { ... }
  class UserRemoteDataSource(
      private val loginService: LoginRetrofitService
  ) { ... }


  class LoginActivity: Activity() {

      private lateinit var loginViewModel: LoginViewModel

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)

          // In order to satisfy the dependencies of LoginViewModel, you have to also
          // satisfy the dependencies of all of its dependencies recursively.
          // First, create retrofit which is the dependency of UserRemoteDataSource
          val retrofit = Retrofit.Builder()
              .baseUrl("https://example.com")
              .build()
              .create(LoginService::class.java)
  
          // Then, satisfy the dependencies of UserRepository
          val remoteDataSource = UserRemoteDataSource(retrofit)
          val localDataSource = UserLocalDataSource()
  
          // Now you can create an instance of UserRepository that LoginViewModel needs
          val userRepository = UserRepository(localDataSource, remoteDataSource)
  
          // Lastly, create an instance of LoginViewModel with userRepository
          loginViewModel = LoginViewModel(userRepository)
      }
  }
##### 문제점
* 보일러코드
  * LoginViewModel을 다른곳에서 또 쓰기라도 한다면 위 코드를 또 작성해야한다.
* 의존성에 맞게 선언순서 강박
  * 순서에 맞게 선언하고 userRepository를 넣어야한다. 
  * 예를들어서 C -> B -> A 순서대로 A를 쓰기위해 C부터 순서대로 선언하고 넣어야한다.
* 객체 재활용이 어렵다.
  * userRepository를 매번 선언하는것은 메모리적인 측면에서 비효율적이다.
##### DI 를 적용한 코드
* 앱 전체에서 공유되는 클래스(컨테이너) 만들기
  ```kotlin
  // Container of objects shared across the whole app
  class AppContainer {

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
    private val retrofit = Retrofit.Builder()
                            .baseUrl("https://example.com")
                            .build()
                            .create(LoginService::class.java)

    private val remoteDataSource = UserRemoteDataSource(retrofit)
    private val localDataSource = UserLocalDataSource()

    // userRepository is not private; it'll be exposed
    val userRepository = UserRepository(localDataSource, remoteDataSource)
  }
* 안드로이드 앱의 경우 Application 클래스 사용(어플리케이션 전체에서 사용과 모든 액티비티들이 접근해서 사용할수 있는 위치를 위해)
  ```kotlin
  class MyApplication : Application() { 
     val appContainer = AppContainer() 
  }
* Acitivty
  ```kotlin
  class LoginActivity: Activity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Gets userRepository from the instance of AppContainer in Application
        val appContainer = (application as MyApplication).appContainer
        loginViewModel = LoginViewModel(appContainer.userRepository)
    }
  }
##### 더 나아가 ViewModel 생성 Factory 패턴 적용하기
* ViewModel을 생성하고 관리하는 Factory 만들기
* ```kotlin
  // Definition of a Factory interface with a function to create objects of a type
  interface Factory {
      fun create(): T
  }

  // Factory for LoginViewModel.
  // Since LoginViewModel depends on UserRepository, in order to create instances of
  // LoginViewModel, you need an instance of UserRepository that you pass as a parameter.
  class LoginViewModelFactory(private val userRepository: UserRepository) : Factory {
      override fun create(): LoginViewModel {
          return LoginViewModel(userRepository)
      }
  }
* ```kotlin
    // AppContainer can now provide instances of LoginViewModel with LoginViewModelFactory
  class AppContainer {
    ...
    val userRepository = UserRepository(localDataSource, remoteDataSource)

    val loginViewModelFactory = LoginViewModelFactory(userRepository)
  }

  class LoginActivity: Activity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Gets LoginViewModelFactory from the application instance of AppContainer
        // to create a new LoginViewModel instance
        val appContainer = (application as MyApplication).appContainer
        loginViewModel = appContainer.loginViewModelFactory.create()
    }
  }
##### 그렇기 때문에 생기는 장점
* 인터페이스 기반 설계로 코드를 유연하게 한다.
* 주입하는 코드만 따로 변경하기에 리펙토링이 수월하다.
* **stub, mock 객체로 단위 테스트가 쉬워진다.** --> 가장 큰 장점으로 꼽힌다.
* 여러 개발자가 서로 사용하는 클래스를 독립적으로 개발할 수 있다.
* 결합도 하락(느슨)
* FakeEngine이라는 Engine의 테스트 더블을 생성하여 다양한 테스트에 맞게 구성할 수 있다.
* DI를 사용한다면 업데이트된 ElectricEngine 서브클래스의 인스턴스를 전달하기만 하면 되며 Car는 추가 변경 없이도 계속 작동
* 코드 변경이 용이해진다.
* 클래스 재사용 가능 및 종속 항목 분리: 종속 항목 구현을 쉽게 교체할 수 있습니다. 컨트롤 반전으로 인해 코드 재사용이 개선되었으며 클래스가 더 이상 종속 항목 생성 방법을 제어하지 않지만 대신 모든 구성에서 작동합니다.
* 리팩터링 편의성: 종속 항목은 API 노출 영역의 검증 가능한 요소가 되므로 구현 세부정보로 숨겨지지 않고 객체 생성 타임 또는 컴파일 타임에 확인할 수 있습니다.
* 테스트 편의성: 클래스는 종속 항목을 관리하지 않으므로 테스트 시 다양한 구현을 전달하여 다양한 모든 사례를 테스트할 수 있습니다.
##### 그럼에도 생기는 단점
* 간단한 프로그램을 만드는데는 코드량 때문에 번거로워진다.
* 코드 추적 하락, 가독성 하락
* Dagger2의 경우 어노테션 프로세서사용으로 빌드시간이 더 소요된다. --> 하지만 이를 사용할경우 다른 부분에서 크게 시간절약이 가능하다.
* 라이프 사이클에 따라서 Container를 삭제하는 등 직접 관리를 해야한다.
* 앱이 커질경우 Factory클래스와 같은 많은 클래스로 인해 보일러코드가 생긴다.
* 많은 코드 작성으로 에러가 발생이 늘어난다.
* 많은 코드로 잘못 사용할경우 메모리릭이 발생할수 있다.
