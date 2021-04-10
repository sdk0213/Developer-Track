# Dagger 성능향상 - [출처](https://proandroiddev.com/dagger-2-on-android-the-official-guidelines-you-should-be-following-2607fd6c002e)
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
