### ContributesAndroidInjector Annotation
* ```java
  @Documented
  @Retention(RUNTIME)
  @Target(METHOD)
  public @interface ContributesAndroidInjector {
      /** Modules to be installed in the generated {@link dagger.Subcomponent}. */
      Class<?>[] modules() default {};
  }
* 반환 타입을 통해 AndroidInjector 를 생성시켜주는 인터페이스
* dagger-android의 기본 구현을 사용해서 코드를 구성한다면 상용구 코드(보일러 코드)를 사용해야된다. 초기에는 문제가 없지만 리펙토리를 한다거나 개발이 많이 진행될수록 dagger-android로 전환할떄 현재 앱에 가장 번거로운 것은 각각의 활동, 프래그먼트, 서비스 등에 대해 별도의 하위 구성 요소를 만들고이를 DispatchingAndroidInjector의 injectorFactories에 추가해야한다. (@IntoSet 사용)
* activity를 만들 때마다 build() 하고 inject() 해주는 보기 안 좋은 boilerplate들이 많아진다.
* Subcomponent의 Factory가 다른 메서드나 클래스를 상속하지 않을때 Subcomponent를 정의하는 코드를 줄여준다.
* 쉽게말해서 아래 따위의 코드를 대신 Dagger가 작성한다.
  * ```java
    @ActivityScope
    @SubComponent(modules = {MainActivityModule.class})
    public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
  
        @Subcomponent.Factory
        interface Factory extends AndroidInjector.Factory<MainActivitiy> {
    
        }
    }
    
    ...
    
    @FragmentScope
    @SubCompoennt(modules = MainFragmentModule.class)
    public interface MainFragmentSubcomponent extends AndroidInjector<MainFragment> {

        @Subcomponent.Factory
        interface Factory extends AndroidInjector.Factory<MainFragment> {
     
        }
    }
  * 솔직히 개발자 입장에서는 그냥 완전 Dagger를 사용하기위해서 만든코드이고 반드시 작성해야하는것인데 굳이 이거 따로 class로 만들어서 작성할바에 ContributesAndroidInjector 를 사용하고 해당 모듈만 알려주면 자동으로 작성되니 정말 편할수없다.
---
#### ContributesAndroidInjector 예제 코드 [black-jin0427 님(정상에서 IT를 외치다) 출처](https://black-jin0427.tistory.com/104)
##### 기존 코드로 작성
* ```java
  @Module
  public class BurgerModule {

      @Provides
      Burger provideBurger(WheatBun bun, BeefPatty patty) {
          return new Burger(bun, patty);
      }

      @Provides
      WheatBun provideBun() {
          return new WheatBun();
      }

      @Provides
      BeefPatty providePatty() {
          return new BeefPatty();
      }
  }
* ```java
  @Component(modules = BurgerModule.class)
  public interface BurgerComponent {

      void inject(MainActivity activity);
  }
* ```java
  public class MainActivity extends AppCompatActivity {

     @Inject
     Burger burger;
     ..
     .
     
     onCreate....{
     
           DaggerBurgerComponent.builder()
                .build().inject(this);
                
     }
                
  }
##### 만약에 DetailActivity 에서 Burger 가 필요하다면 아래와 같이 코드를 또 추가/변경해야한다.
* ```java
  @Component(modules = BurgerModule.class)
  public interface BurgerComponent {

      void inject(MainActivity activity);

      //새로 주입할 곳(DetailActivity)을 추가해 주었습니다.
      void inject(DetailActivity activity);
  }
  
  // 컴포넌트 변경 + Activity에 또다시 빌드 코드 삽입
  
  public class DetailActivity extends AppCompatActivity {

      @Inject
      Burger burger;

  
      onCreate...{

          DaggerBurgerComponent.builder()
                  .build().inject(this);
        
      }
  }
##### 위 코드의 문제점
* activity를 만들 때마다 build() 하고 inject() 해주는 보기 안 좋은 boilerplate들이 많아진다. 
* 리펙토링중이라면? 전부다 inject가 필요하고 이를 개발자가 전부 신경쓰기란 여간 귀찮은것이 아니다.
##### AndroirdSupportInjectionModule
* If you want to use injection in v4.fragment then you should add AndroidSupportInjectionModule.class to your AppComponent modules.
##### ContributesAndroidInjector 사용
* 사용할 Activity를 모듈을 사용해서 처리
* ```java
  @Module
  abstract public class ActivityBinder {

      @ContributesAndroidInjector
      abstract MainActivity bindMainActivity();

      @ContributesAndroidInjector
      abstract DetailActivity bindDetailActivity();
  }
* ```java
  @Singleton
  @Component(
          modules = {
                  BurgerModule.class,
                  ActivityBinder.class,
                  AndroidSupportInjectionModule.class
          }
  )
* DaggerApplication과 DaggerAppCompatActivity를 상속해준다.
  ```java
  public class MyApplication extends DaggerApplication {

      @Override
      protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
          return DaggerAppComponent.builder().build();
      }
  }
  
  ...
  ..
  .
  
  public class DetailActivity extends DaggerAppCompatActivity {

      @Inject
      Burger burger;

      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          setTitle("DetailActivity");

          // DaggerBurgerComponent 함수는 필요 없습니다.
          /* DaggerBurgerComponent.builder()
                  .build().inject(this);*/

          Log.d("MyTag","DetailActivity burger bun : "
                  + burger.bun.getBun() + " , patty : " + burger.patty.getPatty());
  
      }
  }
---
### DaggerApplication
* 매번 HasAndroidInjector 를 구현하고 AndroidInjection.inject()를 호출하는 보일러 플레이트 코드를 줄여준다.
* 어플리케이션 컴포넌트를 반환시키는 코드를 작성하기만 하면 기존의 모든 코드를 대체 가능하다.
* ```java
  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
      return DaggerAppComponent.factory().create(this);
  }
public interface AppComponent extends AndroidInjector<MyApplication> {
---
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

      @ActivityScope
      @ContributesAndroidInjector(modules = MainActivityModule.class)
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
