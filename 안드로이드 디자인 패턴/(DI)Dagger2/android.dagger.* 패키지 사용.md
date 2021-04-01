# android.dagger.*
* 문제점 - [코드 확인 여기서](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20디자인%20패턴/(DI)Dagger2/안드로이드와%20Dagger2.md)
  * 반복되는 보일러 플레이트 코드
  * 리펙토링 수준이 높다.
  * 멤버 주입 메서드의 매개 변수로 정확하 타입으 알아야 한다.
* 이러한 문제를 해결해주기 위해서 안드로이드에서는 android.dagger.* 패키지를 제공해준다.
---
### AndroidInjector<?>
* 안드로이드 프레임워크(Application, Activitiy, Fragment, Service, BroadCastReceiver, ContentProvider) 를 주입시켜주는 클래스
* Inject() 메서드가 포함되어있음
* 
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
##### Activitiy SubComponent
* ```java
  @ActivityScope
  @SubComponent(modules = {MainActivityModule.class})
  public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
    
      @Subcomponent.Factory
      interface Factory extends AndroidInjector.Factory<MainActivitiy> {
      
      }
  }
##### Activitiy Module
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
  
##### Activitiy
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
  public class MainFragemntModule {
  
      @Named("fragment")
      @Provides
      @FragemntScope
      String provideString() {
          return "String from fragemnt";
      }
  }
##### Fragment
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
          Log.e("MainFragemnt", fragmentString);
          super.onAttach(context);
      }
   }
   
   // result:
   // E/MainActivity: String from AppModule
   // E/MainActivity: String from MainActivityModule
   // E/MainFragment: String from AppModule
   // E/MainFragment: String from MainActivityModule
   // E/MainFragment: String from Fragment
