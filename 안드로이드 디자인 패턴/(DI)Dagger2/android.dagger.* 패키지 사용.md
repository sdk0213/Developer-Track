# android.dagger.*
* Dagger 2.10 버전 이전에서 발생했던 문제가 다음과 같다.
  * 문제점 - [코드 확인 여기서](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20디자인%20패턴/(DI)Dagger2/안드로이드와%20Dagger2.md)
    * 반복되는 보일러 플레이트 코드
    * 리펙토링 수준이 높다.
    * 멤버 주입 메서드의 매개 변수로 정확하 타입으 알아야 한다.
* 이러한 문제를 해결해주기 위해서 안드로이드에서는 android.dagger.* 패키지를 제공해준다.
---
### AndroidInjector<?>
* 안드로이드 프레임워크(Application, Activitiy, Fragment, Service, BroadCastReceiver, ContentProvider) 를 주입시켜주는 클래스
* Inject() 메서드가 포함되어있음
---
### AndroidInjectionModule
* AndroidInjector<?>의 팩토리를 멀티 바인딩으로 관리한다.
##### Application Component
* ```java
  @Singleton
  @Component(modules = {AndroidInjectionModule.class, AppModule.class})
  public interface AppComponent extends AndroidInjector<App> {
  
      @Component.Factory
      interface Factory extends AndroidInjector.Factory<App> {
      
      }
  }
### 전체적인 흐름
* ![androidDagger사용구조](https://user-images.githubusercontent.com/51182964/113390078-00257b80-93cc-11eb-8bbb-c1eee30416b9.png)
---
### bindAndroidInjectorFactory
* AndroidInjectionModule 내부에 있는 Map에 AndroidInjector.Factory를 멀티 바인딩한다.
##### Application Module
* ```java
  @Module(subcomponents = MainActivitySubcomponent.class)
  public abstract class AppModule {
      @Named("app")
      @Provides
      @Singleton
      static String provideString() {
          return "String from AppModule";
      }
      
      @Binds
      @IntoMap
      @ClassKey(MainActivity.class)
      abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(MainActivitySubcomponent.Factory factory);
   }
##### Application
* ```java
  public class App extends Application implements HasAndroidInjector {
    
      @Inject
      DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
      
      @Override
      public void onCreate() {
          super.onCreate();
          DaggerAppComponent.factory()
                  .create(this)
                  .inject(this);
      }
      
      @Override
      public AndroidInjector<Object> androidInjector() {
          return dispatchingAndroidInjector;
      }
  }
##### MainActivitiy SubComponent
* ```java
  @ActivityScope
  @SubComponent(modules = {MainActivityModule.class})
  public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
    
      @Subcomponent.Factory
      interface Factory extends AndroidInjector.Factory<MainActivitiy> {
      
      }
  }
##### MainActivitiy Module
* ```java
  @Module(Subcomponents = MainFragmentSubcomponent.class)
  public abstract class MainActivityModule {
  
      @Named("activity")
      @Provides
      @ActivityScope
      static String provideString() {
          return "String from MainActivitiyModule";
      }
      
      @Binds
      @IntoMap
      @ClassKey(MainFragment.class)
      abstract AndroidInjector.Factory<?> bindInjectorFactory(MainFragmentSubcomponent.Factory factory);
  }
  
##### MainActivitiy
* AndroidInjection.inject (this)는 super 전에 onCreate 메서드를 호출해야 한다.
* ```java
  public class MainActivity extends AppCompatActivity implements HasAndroidInjector {
      @Inject
      DispatchingAndroidInjector<Object> androidInjector;
  
      @Inject
      @Named("app")
      String appString;
      
      @Inject
      @Named("activity")
      String activityString;
      
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          AndroidInjection.inject(this);
          Log.e("MainActivity", appString);
          Lgo.e("MainActivity", activityString);
          super.onCraete(svaedInstance);
          setContentView(R.layout.activity_main);
          getSupportFragmentManager().beginTransaction()
                  .replace(R.id.container, new MainFragment())
                  .commit();
      }
      
      @Override
      public AndroidInjector<Object> androidInjector() {
          return androidInjector;
      }
  }
##### Fragment Component
* HasSupportFragmentInjector
  * android.support.v4.app.Fragment 전용
* HasFragmentInjector
  * android.app.Fragment 전용
* ```java
  @FragmentScope
  @SubCompoennt(modules = MainFragmentModule.class)
  public interface MainFragmentSubcomponent extends AndroidInjector<MainFragment> {
  
      @Subcomponent.Factory
      interface Factory extends AndroidInjector.Factory<MainFragment> {
       
      }
  }
##### Fragment Module
* ```java
  @Module
  public class MainFragmentModule {
  
      @Named("fragment")
      @Provides
      @FragmentScope
      String provideString() {
          return "String from fragment";
      }
  }
##### Fragment
* Fragment의 경우 super 전에 onAttach 메서드에서 AndroidSupportInjection.inject (this)를 호출 할 수 있다.
* ```java
  public class MainFragment extends Fragment {
      @Inject
      @Named("app")
      String appString;
      
      @Inject
      @Named("activity")
      String activityString;
      
      @Inject
      @Named("fragment")
      String fragmentString;
      
      @Override
      public void onAttach(Context context) {
          AndroidSupportInjection.inject(this);
          Log.e("MainFragment", appString);
          Log.e("MainFragment", activityString);
          Log.e("MainFragment", fragmentString);
          super.onAttach(context);
      }
   }
   
   // result:
   // E/MainActivity: String from AppModule
   // E/MainActivity: String from MainActivityModule
   // E/MainFragment: String from AppModule
   // E/MainFragment: String from MainActivityModule
   // E/MainFragment: String from Fragment
   
---
### 변화점
##### MainActivity
* ```java
  component = ((App)getApplication()).getAppComponent()
                .mainAcitivitYcomponentBuilder()
                .setModule(new MainActivityModule())
                .setActivity(this)
                .build();
  component.inject(this);
  
  // ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
  
  AndroidInjection.inject(this)
##### Fragment
* ```java
  if (getActivity() instanceof MainActivity) {
     ((MainActivity) getActivitiy()).getComponent()
             .mainFragmentComponentBuilder()
             .setModule(new MainFragmentModule())
             .setFragment(this)
             .build()
             .inject(this);
  }
 
  // ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
  
  AndroidSupportInjection.inject(this);
  
        
        
        

   
