### Activity LifeCycle Callback 등록하여 수집하기
* 엑티비티의 라이프사이클 콜백으로 받기
* 콜백으로 받아서 해쉬맵으로 정리하여도 되고 리스트로 정리하여도된다.
* 정보 수집후에는 컴포넌트단에서 각 어플리케이션 사정에 맞게 넘겨받으면된다.`
* 어플리케이션에서 상속받지 않아도되나 Activity 나 Fragment 에서 상속받아 콜백을 받을경우에는 해당 컴포넌트 라이프사이클에 영향을 받기 때문에 어플리케이션에서 콜백받아 수집하는것이 가장 데이터를 안정적으로 받을수있다.
* ```java
  ..App... Application implements Application.ActivityLifecycleCallbacks
  
    // 해쉬맵으로 정리 하여도되고 리스트로 정리도 가능
    private static Map<String, Lifecycle.Event> lifeCycleMap = new HashMap<>();
    
    ..onCreate(onSave.......){
        super.onCreate();
        
        registerActivityLifecycleCallbacks(this); // <--------- 콜백으로 등록
    }
  
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        lifeCycleMap.put(activity.getClass().getSimpleName(), Lifecycle.Event.ON_CREATE);

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        lifeCycleMap.put(activity.getClass().getSimpleName(), Lifecycle.Event.ON_RESUME);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        lifeCycleMap.put(activity.getClass().getSimpleName(), Lifecycle.Event.ON_PAUSE);

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override

    public void onActivityDestroyed(@NonNull Activity activity) {
        lifeCycleMap.put(activity.getClass().getSimpleName(), Lifecycle.Event.ON_DESTROY);

    }
    
* 모든 상태에 대해 콜백이 가능하다.
  * <img width="546" alt="스크린샷 2022-02-08 오후 9 38 33" src="https://user-images.githubusercontent.com/51182964/152988684-719dea2d-f1d9-41cf-98e9-9143a70a139d.png">

---
### Fragment LifeCycle Callback 등록하여 수집하기 - [출처 - stackoverflow](https://stackoverflow.com/questions/32723055/hooking-into-fragments-lifecycle-like-application-activitylifecyclecallbacks)
* ```java
  final FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            Log.i("BACKSTACK","Count" +fragmentManager.getBackStackEntryCount());
        }
* ```java
  @RequiresApi(api = Build.VERSION_CODES.O)
  public class PerfLifecycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks
  implements Application.ActivityLifecycleCallbacks
