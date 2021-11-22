# 안드로이드와 Dagger2 적용
### 주의사항??
* Room 컴파일러를 주입하지 않을경우 에러가 나는 증상이있음(원인모름)
  * ```gradle
    kapt "androidx.room:room-compiler:2.3.0"
* AndroidManifest 이름 필요
  * ```xml
    <application
        android:name="com.turtle.amatda.presentation.android.App"  // <------ 어플리케이션 이름 필수로 적기
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
---
### 구성하기
* 생명주기 순서와 상하관계에 맞춰서 컴포넌트는 다음과 같이 구성한다.
  * application(activity(fragemnt))
  * application은 앱에서 가장 먼저 생성된다.
* application -> mainactivity -> fragment 순서대로 component 를 가져오고 factory를 생성해야 한다.
* 마지막 fragment 에서 mainactivity 의 class name을 가져오는 모듈을 사용하는것을 보면 subcomponent에서 상위 component를 사용할수 있는것을확인가능하다.
---
### 전체적인 구조
---
* <img width="512" alt="AndroidDaggerᅳ" src="https://user-images.githubusercontent.com/51182964/113000320-ac881780-91aa-11eb-8e4f-a4f0d4010ae5.png">

---
### Application Code
##### AppComponent
* App = Application 이고 시스템에 의해서 생성되기 때문에 Factory @BindsInstance 메서드를 통해 오브젝트 그래프를 바인딩
* ```java
  @Component(modules = AppModule.class)
  @Singleton
  public interface AppComponent {
  
      MainActivityComponent.Builder mainActivityComponentBuilder();
      
      @Component.Factory
      interface Factory {
          AppComponent create(@BindsInstance App app);
      }
  }
##### Module 
* ```java
  @Module(subcomponents = MainActivityComponent.class)
  public class AppModule {
    
      @Provides
      @Singleton
      SharedPreferences provideSharedPreferences(App app) {
          return app.getSharedPreferences("default", Context.MODE_PRIVATE);
      }

      @Provides
      @Singleton
      @Named("app")
      String provideAppString(){
          return "String from AppModule";
      }
  }
##### : Application
* ```java
  public class App extends Application {
    
      private AppComponent appComponent;
      
      @Override
      public void onCreate() {
          super.onCreate();
          appComponent = DaggerAppComponent.factory();
              .create(this, new AppModule());
      }
      
      public AppComponent getAppComponent(){
          return appComponent;
      }
  }
---
### Main Code
* @ActivityScope
  * 액티비티 생명 주기 동안 동일한 인스턴스 제공을 보장해준다.
* @BindsInstance를 통해 시스템에 의해 생성되는 Acitivity 바인딩
##### MainSubComponent
* ```java
  @Subcomponent(modules = MainAcitivityModule.class)
  @ActivityScope
  public interface MainAcitivtyComponent {
      MainFragmentComponent.Builder mainFragmentComponentBuilder();
      
      void inject(MainActivity activity);

      @Subcomponent.Factory
      interface Factory {
          MainActivityComponent create();
      }
  }
##### MainModule
* ```java
  @Module(subcomponents = MainFragmentCOmponent.class)
  public class MainActivityModule {
      @Provides
      @ActivityScope
      @Named("activity")
      String provideActivityString(){
          return "String from MainActivityModule";
      }

      @Provides
      @ActivityScope
      String provideActivityName() {
          return MainActivity.class.getSimpleName();
      }
  }
---
### MainAcitivty Code
* ```java
  public class MainActivity extends AppCompatActivity {

      @Inject
      SharedPreferences sharedPreferences;
      
      @Inject
      String activityName;
      
      MainActivityComponent component;
      
      @Override
      protected void onCretae(Bundle savedInstanceState) {
          super.onCreate(savedInstance);
          setContentView(R.layout.activity_main);
          
          component = ((App) getApplication()).getAppComponent().mainActivityComponentFactory()
                .create();
          component.inject(this);
          
          
          getSupportFragemntManager().beginTransaction()
                  .replace(R.id.container, new MainFragment())
                  .commit();
                 
      }
      
      public MainActivityComponent getComponent() {
          return component;
      }
      
  }
---
### MainFragmentComponent
* ```java
  @FragmentScope
  @Subcomponent(modules = MainFragmentModule.class)
  public interface MainFragmentComponent {

      void inject(MainFragment fragment);

      @Subcomponent.Factory
      interface Factory {
          MainFragmentComponent create();
      }
  }
---
### MainFragmentModule
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
---
### Fragment
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
          ((MainActivity)getActivity()).getComponent().
                  mainFragmentComponentFactory().create().inject(this);
          Log.e("MainFragment", appString);
          Log.e("MainFragment", activityString);
          Log.e("MainFragment", fragmentString);
          super.onAttach(context);
      }
  }
  
  // result(출력결과)
  // MainFragment: String from AppModule
  // MainFragment: String from MainActivityModule
  // MainFragment: String from fragment
