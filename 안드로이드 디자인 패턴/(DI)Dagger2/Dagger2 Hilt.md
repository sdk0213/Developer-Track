# Dagger2 Hilt
### Dagger2 가 가지는 단점
* 엉청난양의 보일러 플레이트 코드
* 높은 지식수준 요구 (공부는 했지만 적용하기 쉽지 않고 코딩 난이도도 높고 상급 개발자가 아닌 초보 개발자입장에서 너무 어려울정도)
* DI(의존성 주입) 본연에 집중할수 있도록 Dagger의 강력한 기능은 가지면서 사용은 더 쉬운 DI 라이브러리
---
##### @HiltAndoridApp
* 의존성 주입 시작점 지정 
* Application에만 어노테션 가능 (Dagger는 Application으로부터 시작됨 그 이유는 Dagger2 특성을 안드로이드 프레임워크에 맞추기위함)
* ```kotlin
  @HiltAndroidApp
  class BaseApplication : Application()
##### @AndroidEntryPoint 
* ```kotlin
  @AndroidEntryPoint
  class GithubReposActivitiy : AppCompatActivity()
##### 주입하기
* ```kotlin
  @AndroidEntryPoint
  class GithubReposActivity : AppCompatActivity() {
    @Inject // 의존성을 주입받으려는 변수
    lateinit var adapter: GithubReposAdapter
  }
  
  ...
  ..
  .
  class GithubReposAdapter : @Inject constructor()  // 의존성을 생성하는 constructor
##### 의존성 생성
* ```java
  @Module
  @InstallIn(ActivitiyComponent::class)
  abstract class SchedulerProviderModule {
  
    @Binds
    abstract fun bindSchedulerProvider(schedulerProvider: ShedulerProvider): ScheudlerPorivderInterface
    
    @Provides
    fun providesSchedulerProvider(): SchedulerProviderInterface {
      return SchedulerProvider()
    }
  }
##### @ViewModelInject constructor
* ```java
  class GithubReposViewModel @ViewModelInject constructor(
    private val repository: GithubRepository,
    private val schedulerProvider: SchedulerProviderInterface,
    @Assisted private val savedStateHandle: SavedStateHandle
  ): ViewModel() {
  
.......
...
.
(작성중단 2021/04/05 Dagger2 를 우선 사용해보것이 중요하다고 생각)
