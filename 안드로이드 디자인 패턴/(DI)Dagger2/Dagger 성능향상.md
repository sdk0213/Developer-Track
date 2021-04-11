# Dagger 성능향상 - [출처](https://proandroiddev.com/dagger-2-on-android-the-official-guidelines-you-should-be-following-2607fd6c002e)
---
### 범위 지정은 비용이 많이 듭니다. 가능하면 피하세요.
* Scope는 비용이 많이 드는 작업이니 @Reusable, @Singleton 사용을 최대한 고려
---
### providea 메서드는 static으로 선언해라
* Dagger는 인스턴스화 할 필요가 없어져서 성능이 향상된다.
* ```kotlin
  @Module
  interface YourModule {
  
      companion object {
        // any bindings here are effectvely static and part of the module
        @Provides fun provideThatDependency() = ThatDependency()
      }
      
      @Binds fun bindThisDependency(thisImplementation: ThisImplementation): ThisDependency
  }
---  
### Application context는 @BindsInstance 사용 (지금은 @Component.Factory를 활용)
* ```kotlin
  @Singleton
  @Component(modules = [YourModule::class, ThatOtherModule::class])
  interface ApplicationComponent {

      @Component.Builder
      interface Builder {
          @BindsInstance fun applicationContext(applicationContext: Context): Builder
          fun build(): ApplicationComponent
      }
  }
---
### 인터페이스를 바인딩하고 싶을때는 @Binds 사용
* 속도가 더 빠름
* ```kotlin
  @Module
  object BookPresenterModule {

      @Provides @JvmStatic
      fun provideBookPresenter(bookPresenter: BookPresenterImpl): BookPresenter = bookPresenter
  }
* ```kotlin
  @Module
  abstract class BookPresenterModule {
  
      @Binds abstract fun bindBookPresenter(bookPresenter: BookPresenterImpl): BookPresenter
  }
---
### Testing -> 출처에서 확인

  
