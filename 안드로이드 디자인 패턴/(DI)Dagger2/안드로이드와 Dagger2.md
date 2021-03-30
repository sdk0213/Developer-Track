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
* Dagger Component(Sub) -> Application Component(Main)
* Fragment(Sub) -> Actitivty(Main)
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
          
    
    
