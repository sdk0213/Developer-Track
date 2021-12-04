# SharedViewModel
---
### Activity ViewModel 공유하기
* 단순하게 Activity viewmodel 을 공유하는 방식으로 단순하다.
* fragment 단에서 사용하는 기능을 activity 에서 가지고 있기 때문에 activity 의 viewModel 이 무거워진다는 단점이있다.
* ```kotlin
  class MainFragmentOne : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    // View 초기화
  }

  class MainFragmentTwo : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    // View 초기화
  }
---
### 상위 Fragment ViewModel 공유하기
* requireParentFragment을 이용하여 내 상위 Fragment의 ViewModel을 공유
* A 의 프래그먼트 ViewModel 을 사용
* Fragment 구조도
  * Fragment A
    * Fragment B
    * Fragment C
* ```kotlin
  // B Fragment
  private val viewModel: FragmentA_ViewModel by viewModels(
    ownerProducer = { requireParentFragment() }
  ) 
* ```kotlin
  // C Fragment
  private val viewModel: FragmentA_ViewModel by viewModels(
    ownerProducer = { requireParentFragment() }
  ) 
* 단순히 상위 Fragment 를 사용하는 방식이다.
---
### [Navigation Component 사용시 Shared ViewModel 구성하기](https://stackoverflow.com/questions/55137338/android-navigation-component-with-shared-view-models)
* Navigation 2.1.0-alpha02 이상 필요
* Fragment 구조도
    * Fragment B
    * Fragment C
* ```kotlin
  // Fragment B
  // 해당 코드는 dagger2 를 통한 주입 (factory 를 코드에 따라 알아서 구현)
  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val viewModel : sharedViewModel by navGraphViewModels(R.id.nav_graph){
      viewModelFactory
  }
* ```kotlin
  // Fragment C
  // 해당 코드는 dagger2 를 통한 주입 (factory 를 코드에 따라 알아서 구현)
  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val viewModel : sharedViewModel by navGraphViewModels(R.id.nav_graph){
      viewModelFactory
  }
  
  
