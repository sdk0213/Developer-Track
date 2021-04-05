### ContributesAndroidInjector Annotation
* dagger-android의 기본 구현을 사용해서 코드를 구성한다면 상용구 코드(보일러 코드)를 사용해야된다. 초기에는 문제가 없지만 리펙토리를 한다거나 개발이 많이 진행될수록 dagger-android로 전환할떄 현재 앱에 가장 번거로운 것은 각각의 활동, 프래그먼트, 서비스 등에 대해 별도의 하위 구성 요소를 만들고이를 
DispatchingAndroidInjector의 injectorFactories에 추가해야한다. (@IntoSet 사용)
* activity를 만들 때마다 build() 하고 inject() 해주는 보기 안 좋은 boilerplate들이 많아진다.
* Subcomponent의 Factory가 다른 메서드나 클래스를 상속하지 않을때 Subcomponent를 정의하는 코드를 줄여준다.
##### Application
* ```java
  public class App extends DaggerApplication {

      @Override
      protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
          return DaggerAppComponent.factory().create(this);
      }

  }
##### App Component
* ```java
  @Component(modules = {AppModule.class, AndroidInjectionModule.class})
  @Singleton
  public interface AppComponent extends AndroidInjector<App> {
      @Component.Factory
      interface Factory extends AndroidInjector.Factory<App>{ }

  }
##### App Module
* ```java
  @Module
  public abstract class AppModule {

      @Provides
      @Singleton
      static SharedPreferences provideSharedPreferences(App app) {
          return app.getSharedPreferences("default", Context.MODE_PRIVATE);
      }

      @Provides
      @Singleton
      @Named("app")
      static String provideAppString(){
          return "String from AppModule";
      }

      @ContributesAndroidInjector(modules = MainActivityModule.class)
      @ActivityScope
      abstract MainActivity bindsMainActivity();
  
  }
##### MainActivity
* ```java
  public class MainActivity extends DaggerAppCompatActivity {
      @Inject
      SharedPreferences sharedPreferences;

      @Inject
      String activityName;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          getSupportFragmentManager().beginTransaction()
                  .replace(R.id.container, new MainFragment())
                  .commit();
  
      }
  }
##### MainActivity Module
* ```java
  @Module
  public abstract class MainActivityModule {

      @Provides
      @ActivityScope
      @Named("activity")
      static String provideActivityString(){
          return "String from MainActivityModule";
      }

      @Provides
      @ActivityScope
      static String provideActivityName() {
          return MainActivity.class.getSimpleName();
      }
  
      @ContributesAndroidInjector(modules = MainFragmentModule.class)
      @FragmentScope
      abstract MainFragment bindsMainFragment();


  }
##### MainFragment
* ```java
  public class MainFragment extends DaggerFragment {
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
          super.onAttach(context);
          Log.e("MainFragment", appString);
          Log.e("MainFragment", activityString);
          Log.e("MainFragment", fragmentString);
      }

  }
##### MainFragment Module
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
