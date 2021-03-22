# 안드로이드와 Dagger2 적용
### 안드로이드의 제약사항
* 엑티비티, 서비스 같은 생명주기를 갖는 컴포넌트로 구성된다.
* 프래그먼트는 엑티비티에 소속되어서 존재
* 안드로이드 컴포넌트들은 시스템에 의해서만 인스턴스화된다.
* ```java
  [AppModule],[NetworkModule],[DBMOdule]{
        Activity{
              [AcitivyModule]
                  Fragment{
                      [FragmentModule]
                  }
              }
        }
 }
##### AppComponent
* ```java
  @Component(modules = AppModule.class)
  @Singleton
  public interface AppComponent {
      MainActivityComponent.Builder mainActivityComponentBuilder();
      void inject(App app);
      
      @Component.Factory
      interface Factory {
          AppComponent create(
                  @BindsInstance 
                  App app, AppModule appModule
          );
      }
  }
##### Module
* ```java
  @Module(subcomponents = MainActivityComponent.class)
  public class AppModule {
    
      @Provides
      @Singleton
      SharedPrefereces provideSharedPreferences(App app){
        return app.getSharedPreferences(
                "default",
                Context.MODE_PRIVATE
        );
      }
  }
* 
