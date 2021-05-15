# AssitedInject With Workmnager
### WorkManager
* WorkManager 를 Dagger2를 사용하였을때 파라미터를 추가하기에는 까다로운 면이 있다고 한다.
* 왜냐하면 WorkManger의 생성은 다음과 같이 이루어지기 때문이다.
  * ```kotlin
    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
  
    // 또는 주기적 작업 예약
    val request = PeriodicWorkRequestBuilder<SeedDatabaseWorker>().build()
  * RequestBuilder에 의해 생성되기 때문이다.
### WorkManager
   
--- 
### WorkManager를 통한 생정자 주입에 대한 미디엄 - 번역이 미흡합니다.
### [전문은 여기를 참고하세요](https://proandroiddev.com/dagger-2-setup-with-workmanager-a-complete-step-by-step-guild-bb9f474bde37)
* 우리는 Worker 클래스에 생성자주입을 할수없다. 왜냐하면 WorkerParameters과 appContext는 오직 런타임에서만 이용가능하기 때문이다. 그래서 대신 우리는 팩토리를 통해서 생정자 주입을 수행해야한다. 그리고 팩토리로 멀티바인드맵을 맞춰야한다. 이 멀티 바인드맵을 통해서 새로운 Worker 인스턴스를 주입해야한다. 

* 2019년 3월 6일날 워커매니저가 공식적으로 릴리즈되었다. 여기서는 워커매니저으 사용방법에 대한 것보다 어떻게 Dagger2를 사용해서 주입할수있는지에 대해서 논의할것이다.

##### 목표
* 이 튜토리얼을 마친다면 생성자 주입뿐만 아니라 어떻게 돌아가는지 완벽하 이해를 할수있다.
##### 문제
* Worker는 WorkManager에 의해서 초기화가 이루어진다. (마치 Acitivity와 Fragment가 Android FrameWork에 의해서 초기화되는것처럼)
* 그렇기 때문에 Context 와 WorkerParameters를 제외한 다른 파라미터를 생성자를 통해서 주입하는것을 기대하기는 힘들다.
* 우리가 유일하게 할수 있는일은 필드주입뿐이다. like...
 * ```kotlin
   class HelloWorldWorker (
       appContext: Context,
       params: WorkerParameters
   ) : Worker(appContext, params) {
   
       @Inject lateinit var foo: Foo
       
       override fun doWork(): Result {
           TODO()
       }
   }
* 필드 주입을 좋아하는 사람은 아무도 없지만 몇몇 경우에서 Injector에게 필요하다.

##### 해결책
* alpha 9 가 릴리즈되었고 안드로읻드 팀은 WorkerFactory라는 새로운 추상 클래스를 소개했다.
  * "A factory object that creates ListenableWorker instances. The facotry is invoked every time a work runs"
* 쉽게 말하면 WorkManager에 custom Factory를 등록하면 아무때나 호출이 될때 WorkManager가 Custom Facotry에 새로운 WorkerManager를 생성해달라고 요청한다. 이 점은 굉장한 이점이다 왜냐하면 우리는 Worker를 어떻게 생성할것인지에 대해서 결정할수 있기 때문이다. 기본 생성자에 더 이상 방해 받지 않는다.

#### Step by step
* Custom Factory(이하 SaampleWorkerFactory)에 대해서는 후에 알아보고 우선 어떻게 구현되는지에 대해 집중해보자.
* ```kotlin
  interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
  } 
* ```kotlin
  class Foo @Inject constructor() // test dependence

  class HelloWorldWorker(
      private val appContext: Context,
      private val params: WorkerParameters,
      private val foo: Foo // test dependence
      // add more dependencies here
  ) : Worker(appContext, params) {
      override fun doWork(): Result {
         TODO()
  }
  
    class Factory @Inject constructor(
        private val foo: Provider<Foo>
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return HelloWorldWorker(
                appContext,
                params,
                foo.get()
            )
        }
    }
  }
* HelloWorldWorker는 Foo 를 의존하기에 필요하다. 앞서 말한것처럼 생성자 주입을 통해 Foo를 할수가 없는데 그래서 우리는 HelloWorldWorker.Factory를 통해 생성자 주입을 수행한다. Factory 클래스명은 아무것이나 상관없다.
* 왜 우리는 ChildWorkerFactory 인터페이스가 필요한것일까? 이 인터페이스는 후에 Dagger Multi-bind에서 유용해진다.
* 이제 우리는 두가지 유형의 Factory를 가지고 있다.
  * WorkerFactory 
    * WorkManager에 등록해야하는 custom Factory로 우리의 구현은 SampleWorkerFactory이다.
  * ChildWorkerFactory
    * 생성자 주입을 지원하는 각각의 Worker에 대한 factory 
* 이제 모든 ChildWorkerFactory이 의존성 그래프에서 구현이 가능해진다. 이제 우리는 이런 ChildWorkerFactory들을 멀티바인드를 할수있게 되었다.
* ```kotlin
  @Module
  interface WorkerBindingModule {
      @Binds
      @IntoMap
      @WorkerKey(HelloWorldWorker::class)
      fun bindHelloWorldWorker(factory: HelloWorldWorker.Factory): ChildWorkerFactory
  }
* 그리고 커스텀 wokrerFactory 주입하자. 이 커스텀 팩토리는 Workmanager에 등록된다. 팩토리안에 팩토리가 있다...(?)
* 커스텀 팩토리를 WorkerFactory에 잘 바인들된것을 기억하자.
* ```kotlin
  class SampleWorkerFactory @Inject constructor(
      private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards        Provider<ChildWorkerFactory>>
  ) : WorkerFactory() {
      override fun createWorker(
          appContext: Context,
          workerClassName: String,
          workerParameters: WorkerParameters
      ): ListenableWorker? {
          val foundEntry =
              workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
          val factoryProvider = foundEntry?.value
              ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
          return factoryProvider.get().create(appContext, workerParameters)
      }
  }
* 마지막 중요한 단계 중 하나는 SampleWorkerFactory를 WorkManager에 등록하는 것입니다. 하나는 애플리케이션에, 다른 하나는 AndroidManifest.xml에 등록합니다.
* ```kotlin
  class SampleApplication : DaggerApplication() {
      @Inject lateinit var workerFactory: WorkerFactory
    
      override fun onCreate() {
          super.onCreate()
          // register ours custom factory to WorkerManager
          WorkManager.initialize(this, Configuration.Builder().setWorkerFactory(factory).build())
      }
  }
* **이런식으로더블 팩토리 패턴) 하면 잘작동함에도 불구하고 가장 나쁜 부분은 우리는 항상 ChildWorkerFactory를 손수 구현해야한다는것이다. 이 예제는 Foo하나밖에 없지만 만약에 10개개 그 이상이 있다면 우리는 10Worker가 필요하다. 기술적으로 가능하지만 이것은 크게 도움이 되지 않는 방법이다.**
### AssitedInjection 사용
* AssistedInject가 작동하는 곳입니다. Dagger 2와 호환되는 Square의 라이브러리입니다. 2 개의 작업이 있다.
  * 모든 ChildWorkerFactory 구현을 생성한다.
  * 모듈을 통해 이러한 구현을 종속성 그래프에 바인딩 (자세한 내용은 나중에 참조)
* 그래서 두개의 팩토리와 모든 바인딩 구현을 포함하는 모듈을 생성한다.
* Worker 클래스에 @AssistedInject를 선언하여라. 우리가 생성하기를 원하는 팩토리 매개벼누는 @Assisted로 선언한다. 그리고 팩토리는 @AssistedInject.Factory로 선언한다. 우리의 Worker 클래스는 이제 많이 깨끗해졌고 그리고 상당히 재미있는 점은 우리는 보일러플레이트 코드를 사용하지 않아도 된다는것이다.
* ```kotlin
  class HelloWorldWorker @AssistedInject constructor(
      @Assisted private val appContext: Context,
      @Assisted private val params: WorkerParameters,
      private val foo: Foo
  ) : Worker(appContext, params) {
      private val TAG = "HelloWorldWorker"
      override fun doWork(): Result {
          Log.d(TAG, "Hello world!")
          Log.d(TAG, "Injected foo: $foo")
          return Result.success()
      }

      @AssistedInject.Factory
      interface Factory : ChildWorkerFactory
  }
* AssistedInject는 모듈 내에서 해당 팩토리를 바인딩하기 때문에. 생성 된 모듈을 포함하는 모듈을 선언하고 @AssistedModule로 주석을 달고 컴포넌트에 추가합니다. 이렇게하면 컴포넌트 종속성 그래프에서 모든 ChildWorkerFactory 구현을 사용할 수 있습니다.
* ```kotlin
  @Module(includes = [AssistedInject_SampleAssistedInjectModule::class])
  @AssistedModule
  interface SampleAssistedInjectModule

  @Component(
      modules = [
          SampleAssistedInjectModule::class,
          WorkerBindingModule::class
      ]
  )
  interface SampleComponent {
      // setup
  }
* 이렇게하면 예상되로 잘 작동한다.

### 대안적인 방법
* 전에 이 문제가 있을때 많은 의논이 있었다. 요약하자면 여기는 두가지 가능한 해결책이 있는데 모두 기대된대로 작동하고 성능적으로 차이점을 `
### 왜이렇게 생성자주입을 어렵게 굳이 해야하는가?
* So what is my setup’s advantage? why do we need to spend such an effort on this huge setup? The answer, my friend, is TESTING. Since everything is injected through the constructor, we can easily mock our dependence to test the Worker implementation.
