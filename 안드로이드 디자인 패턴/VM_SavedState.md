# ViewModel - SavedState
### 사전지식
##### onSavedInstance의 한계점
* Bundle limit
  * This research was performed in 2014, newer versions of Android may crash with bundles over 500kb
##### ViewModel의 한계점
* ViewModel을 사용하더라도 프로세스에 의해 중단된 경우에는 복원이 불가능하다.
* ViewModel은 메모리 내에서 존재하기때문에 activity 생명주기보다는 길지만 프로세스 종료까지는 안전하지 않다.
* https://www.charlezz.com/wordpress/wp-content/uploads/2020/06/ViewModel-Basic.mp4
---
### SavedStateHandle
* **SavedStateHandle 객체는 Ke
y-Value 형태인 Map 구조이며 SavedStateHandle은 프로세스가 시스템에 의해 종료되더라도 유지된다.**
* 예상치못하 시스템에 의한 종료로부터 살아남을수있다.
* <img width="907" alt="스크린샷 2021-05-04 오후 5 10 15" src="https://user-images.githubusercontent.com/51182964/116976244-d974a080-acfb-11eb-9572-d895b052be67.png">
##### 특징
* 시스템이 프로세스를 종료하더라도 동일한 정보를 유지
##### 주의점 - [charlezz님](https://charlezz.medium.com/ui-상태-저장-및-복원의-필요성-a00297e7a20b)
* 작은 데이터 -> SavedStateHandle, 큰 데이터 -> db
* ViewModel에 캐시된 결과가 있는지 확인후 재쿼리하기
* 테스트
  * 개발자 모드 > 액티비티 유지안함 옵션을 활성화 하자. 백그라운드로 진입하는 순간 바로 Activity가 소멸되고, 포어그라운드로 재진입했을 때 새로운 Activity가 생성되는 것을 확인할 수 있다. 또한 개발자 모드에서 백그라운드 프로세스 개수에 제한을 거는 방법도 유효하다.
##### 메서드
* get(String key) : T
* contains(String key) : boolean
* remove(String key) : T
* set(String key, T value)
* keys() : Set
* getLiveData(String key) : MutableLiveData
##### code
* ```kotlin
  class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this)).get(MainViewModel::class.java)
    }
  }
* ```kotlin
  class SavedStateViewModel(private val handle: SavedStateHandle) : ViewModel()
  
  fun ...
    handle["HELLO"] = "WORLD"
    
  fun ...
    handle.getLiveData("HELLO")   
    
  // 데이터를 다른 값으로 갱신하는 경우에는 LiveData가 이를 Observe하고 있기 때문에 새로운데이턱 흘러간다.
* [SampleCode](http://pluu.github.io/blog/android/2020/02/10/saved-state/)
  * ```kotlin
    class SavedStateViewModelCounterActivity : AppCompatActivity() {

      private lateinit var counterViewModel: SavedStateCounterViewModel

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counterViewModel = ViewModelProvider(
          this,
          SavedStateViewModelFactory(application, this)
        ).get(SavedStateCounterViewModel::class.java)

        Timber.d("ViewModel = ${counterViewModel.hashCode()}")

        counterViewModel.countState.observe(this, Observer {
          binding.counter.text = it.toString()
        })

        binding.fab.setOnClickListener {
          counterViewModel.incCounter()
        }
      }
    }
  * ```kotlin
    class SavedStateCounterViewModel(
      private val handle: SavedStateHandle
    ) : ViewModel() {

      // Get value of SavedStateHandle
      private var counter = handle.get<Int>("counter") ?: 0
        set(value) {
          // Set value of SavedStateHandle
          handle.set("counter", value)
          field = value
        }

      private val _countForm = MutableLiveData<Int>(counter)
      val countState: LiveData<Int> = _countForm

      // Get LiveData of SavedStateHandle
      val countLiveData: LiveData<Int> = handle.getLiveData("count", 0)

      fun incCounter() {
        ++counter
        Timber.d("Inc Counter => $counter")
        _countForm.value = counter
      }
    }
