[AAC-View Model(뷰 모델)](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko#java)
===
> 특징
* ViewModel의 목적은 UI 컨트롤러의 데이터를 캡슐화하여 구성이 변경되어도 데이터를 유지하는것
* 이를 활용해서 mvvm 패턴으로 구성이 가능하다.
* ViewModel 객체는 뷰 또는 **LifecycleOwners의 특정 인스턴스화보다 오래 지속**되도록 설계
* ViewModel을 다루는 테스트를 더 쉽게 작성 가능
* 수명 주기를 인식하는 Observable의 변경사항을 관찰해서는 안됨
* Application Context가 필요할경우 AndroidViewModel 클래스를 확장하고 생성자에 Application을 받는 생성자를 포함
* LifecycleOwner 의 생명주기르 알고있다.
* 범위내에서 데이터 공유가 쉽다.
 * 구글측에서는 컨텍스트 최대한 사용하지 말라고 권함 왜냐하면 컨텍스트는 날라가기 떄문에
> MVVM의 ViewModel 과의 관계 - [출처](https://wooooooak.github.io/android/2019/05/07/aac_viewmodel/)
* MVVM 의 viewmodel은 view와 1:n 의 관계를 가지지만 AAC-viewmodel은 activity 하나당 1개를 가지기 때문에 1:1의 관계이다.
* 액티비티 한 개 내에서만 유효한 싱글톤이다.
* MVVM 패턴의 ViewModel 을 사용해서 구현할수도 있지마 생명주기에 영향을 받기 때문에 ui가 시스템에 영향을 받아 재 생성되거나 제거될경우 데이터를 보관하는것에서 굉장히 복잡해진다.
> ViewModelProvier의 역할
* Viewmodel을 onCreate() 에서 초기화 해주는데 이것이 중복으로 객체가 생성되는것을 방지하기위해서, 즉 싱글톤과 같이 관리하기 위한 수단으로 ViewModelProvider를 사용한다.
* ViewModelProvider를 이용해서 ViewModelProvider의 Factory 인터페이스르 구현된 것을 초기화 할때 넣어줘야함
  * KeyedFactory
    * 함수상에서 존재하는데 Exception 처리된것을 보니 사용안하는것같음
  * NewInstanceFactory
    * 일반적인 사용 방법인것같음
  * AndroidViewModelFactory
    * NewInstanceFactory를 상속받고 Application 클래스로 구현됨. 아마도 context가 필요한 경우를 위해 구글이 제공해주는것같다.
---
### ViewModel의 생성 방법 - [출처 - readystory님의 tistory](https://readystory.tistory.com/176)
* ```kotlin
  var viewmodel = ViewModelProvier(this).get(MyViewModel::class.java)
* 여기서 get 함수를 살펴보면은 다음과 같이 설명되어있다.
##### get
* Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or an activity), associated with this {@code ViewModelProvider}.
* 해당 ViewModelProvider랑 관련되어있는 ViewModel이 있으면 있는것 반환 없으면 새로운 Scope에서 생성한다.
##### 1. Lifecycle Extensions
* 기본
* ```gradle
  implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
* ```java
  noParamViewModel = ViewModelProvider(this).get(NoParamViewModel::class.java)
##### 2. ViewModelProvider.NewInstanceFactory
* 기본 Factory 클래스 사용
* ```java
  noParamViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NoParamViewModel::class.java)
##### 3. ViewModelProvider.Factory
* ViewModelProvider.Factory 인터페이스를 직접 구현 하는방법
* 커스텀가능
* ```java
  class NoParamViewModelFactory : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
          return if (modelClass.isAssignableFrom(NoParamViewModel::class.java)) {
              NoParamViewModel() as T
          } else {
              throw IllegalArgumentException()
          }
      }
  }
##### 4. ViewModelProvider.Factory(파라미터 구현)
* ```java
  class HasParamViewModelFactory(private val param: String) : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
          return if (modelClass.isAssignableFrom(HasParamViewModel::class.java)) {
              HasParamViewModel(param) as T
          } else {
              throw IllegalArgumentException()
          }
      }
  }
 
  ...
  ..
  .
  // 사용 in Activity or fragment
  hasParamViewModel = ViewModelProvider(this, HasParamViewModelFactory(sampleParam)).get(HasParamViewModel::class.java)
##### 5. AndroidViewModelFactory
* Context필요할때 사용
* ```java
  noParamAndroidViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(NoParamAndroidViewModel::class.java)
##### 6. AndroidViewModelFactory(파라미터 구현)
* 구현은 ViewModelProvider.NewInstanceFactory 또는 ViewModelProvider.Factory 둘중 하나로 구현하면 된다.
* ```java
  class HasParamAndroidViewModel(application: Application, val param: String): AndroidViewModel(application)
* ```java
  class HasParamAndroidViewModelFactory(private val application: Application, private val param: String): ViewModelProvider.NewInstanceFactory() {
 
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
          if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
              try {
                  return modelClass.getConstructor(Application::class.java, String::class.java)
                      .newInstance(application, param)
              } catch (e: NoSuchMethodException) {
                  throw RuntimeException("Cannot create an instance of $modelClass", e)
              } catch (e: IllegalAccessException) {
                  throw RuntimeException("Cannot create an instance of $modelClass", e)
              } catch (e: InstantiationException) {
                  throw RuntimeException("Cannot create an instance of $modelClass", e)
              } catch (e: InvocationTargetException) {
                  throw RuntimeException("Cannot create an instance of $modelClass", e)
              }
          }
          return super.create(modelClass)
      }
  }
---
> 원리
* HolderFragment 라고 명명된 fragment 를 생성해서 엑티비티에 추가하고 HolderFragment가 viewmodel 맴버 변수들을 관리하는데 setRetainInstance(true) 를 사용해서 메모리에 프레그먼트를 남기는 기법을 사용하는 기법으로 만들어졌으니까 사실은 fragment이다.
  * 그러므로 ViewModelProvider를 사용해서 객체를 만들어야만 HolderFragment에 의해 관리된다.
* ViewModel은 ViewModelStore에서 내부적으로 HashMap<String, ViewModel> mMap = new HashMap<>() 으로 ViewModel들을 관리한다.
  * ViewModelStore는 다음과 같이 생겼다
  ![](img/viewModelStore.png)
  * 이를 ViewModelOwner 가 관리한다 코드는 다음과 같다.
    * ![](img/viewModelStoreOwner.png)
  * 이를 Fragment와 ComponentAcitivty에서 implementr 한다.
    * ![](img/ComponetActivityViewModelStore.png)
    * ![](img/fragmentViewModelStore.png)
* ```java 
  public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
  }

  ...
  ..
  public class MyActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getUsers().observe(this, users -> {
            // update UI
        });
    }
  }

> viewmodel 수명주기
* lifecycle
  * ![](img/viewmodel_lifecycle_comparewith_activity.png)
* 활동에서는 활동이 끝날 때까지 그리고 프래그먼트에서는 프래그먼트가 분리될 때까지 메모리에 남아 있다.
  * ViewModel이 처음 요청되었을 때부터 활동이 끝나고 폐기될 때까지 ViewModel은 존재

> viewmodel을 통한 fragment간 데이터 공유
* ```java
  public class SharedViewModel extends ViewModel {
     private final MutableLiveData<Item> selected = new MutableLiveData<Item>();

     public void select(Item item) { 
         selected.setValue(item);
     }

     public LiveData<Item> getSelected() {
         return selected;
     }
  } 

  public class MasterFragment extends Fragment { 

     public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
         SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
         itemSelector.setOnClickListener(item -> {
             model.select(item);
         });
     }
  }

  public class DetailFragment extends Fragment {

     public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
         SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
         model.getSelected().observe(getViewLifecycleOwner(), item -> {
            // Update the UI.
         });
     }
  }
* ![](img/viewmodel_fragment.png)

> ViewModel로 로더 전체과정
* room과 livedata를 사용
  * 데이터베이스가 변경되면 Room에서 LiveData에 변경을 알리고, 알림을 받은 LiveData는 수정된 데이터로 UI를 업데이트
* ![](img/viewmodel_dataload.png)

* 데이터가 더 복잡해지면 데이터 로드만을 위한 별도의 클래스를 사용
  * 구성 변경 시 데이터를 로드, 유지 및 관리하는 방법에 관한 자세한 내용은 UI 상태 저장을 참고하세요.
  * Android 앱 아키텍처 가이드에서는 이러한 함수를 처리하는 저장소 클래스 빌드를 제안

> 제약사향
* 절대로 Context를 참조해서는 안된다.
  * 화면이 회전되거나 엑티비티가 파괴될때 ViewModel이 Context를 참고하며 GC로부터 수거되지 못해서 메모리 누수가 발생한다.
  * 하지만 Context를 참고해야되는 부분이 생기기 때문에 안드로이드 ViewModel을 사용해서 싱글톤처럼 사용할수있다고 함
---
### by viewModels()
* ViewModelProvider를 사용하지 않고 viewmodel을 지연 생성 가능하다.
##### 적용 전
* ```kotlin
  private lateinit var viewModel: MyViewModel
  
  ...
  viewModel = VIewModelProvider(this).get(MyViewModel::class.java)
##### 적용 후
* ```kotlin
  private val viewModel: MyViewModel by viewModels()
