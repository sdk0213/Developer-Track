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
---
### 구성하기
* 생명주기 순서와 상하관계에 맞춰서 컴포넌트는 다음과 같이 구성한다.
  * application(activity(fragemnt))
  * application은 앱에서 가장 먼저 생성된다.
* 모듈은 전부 BindsInstance로 외부에서 주입한다 => 이유는 모르겠다 -> 아마도 모듈에서 사용하는 안드로이드 관련 객체는 생명주기가 시작되었을때만 사용할수있기 때문이라고 추정한다.
* application에서 생성한 component를 mainactivity에서 다시 가져와 추가적으로 build를 진행하고 fragment는 mainactivity에서 component를 가져와 추가적으로 빌드를 진행한다.
이게 가능한 이유는 component-subcomponent의 관계를 맺고 있기 때문이다.
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
      
      void inject(MainAcitivity activitiy);
      
      @Subcomponent.Builder
      interface Builder {
          Builder setModule(MainActivityModule module);
          @BindsInstance
          Builder setActivity(MainActivity activity);
          MainActivityComponent build();
      }
  }
##### MainModule
* ```java
  @Module(subcomponents = MainFragmentCOmponent.class)
  public class MainActivityModule {
      @Provides
      @AcitivtyScope
      String priovideActivityName() {
          return MainAcitivty.class.getSimpleName();
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
          
          component = ((App)getApplication()).getAppComponent()
                  .mainAcitivitYcomponentBuilder()
                  .setModule(new MainActivityModule())
                  .setActivity(this)
                  .build();
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
      
      @Subcomponent.Builder
      interface Builder {
          MainFragmentComponent.Builder setModule(MainFragmentModule module);
          @BindsInstance
          MainFragmentComponent.Builder setFragment(MainFragment fragment);
          MainFragmentComponent build();
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
      String provideInt() {
          return new Random().nextInt();
      }

  }
---
### Fragment
* ```java
  public class MainFragment extends Fragment {
      
      @Inject
      SharedPreferences sharedPreferences;
      
      @Inject
      String activityName;
      
      @Inject
      Integer randomNumber;
      
      @Override
      public void onAttach(Context context) {
          super.onAttach(context);
          if (getActivity() instanceof MainActivity) {
              ((MainActivity) getActivitiy()).getComponent()
                      .mainFragmentComponentBuilder()
                      .setModule(new MainFragmentModule())
                      .setFragment(this)
                      .build()
                      .inject(this);
          }
          
          Log.d("MainFragment", activityName);
          Log.d("MainFragment", "randomNumber = " + randomNumber);
      }
  }
  
  // result
  // MainFragment: MainActivity
  // MainFragment: randomNumber = 1541652
          
    
    
