# AAC - LiveData
##### [출처 - gus0000123](https://gus0000123.medium.com/android-jetpack-livedata-사용법-1-개념편-931e6884decf)
##### [출처 - readystory](https://readystory.tistory.com/101)
### 특징
* **LiveData는 Activity, Fragment의 생명 주기를 인식하며, 생명주기가 활성화 되어있는 상태에서만 Observer를 업데이트 합니다. 또한 생명주기가 종료되면 자동으로 메모리를 반환합니다.**
##### 장점
* UI와 데이터 상태의 일치 보장 
* 메모리 누추 없음
  * 생명주기를 인식하기 때문에 Rx 와의 가장 큰 차이점으로 볼수 있다.  
* 최신 데이터 유지
  * pause -> resume 될때 다시한번 받아옴
* 리소스 공유
  * UI와 데이터 상태의 일치 보장 
* 생명주기에 대한 추가적인 handling 불필요
  * **하지만 onResume()에 하게될 경우 pause()나 stop()에 의해서 잠시 비활성화된 앱이 다시 활성화가 되면서 LiveData에 대한 코드가 중복 호출될 수 있다.**
* 화면 구성이 변경되어도 데이터를 유지
* Data의 변경을 관찰 할 수 있는 Data Holder 클래스로 컴포넌트의 LifeCycle을 인식하여 상태에따라(STARTED, RESUMED) 데이터를 업데이트 하는 DataType이다.
* Observer 객체에 변화를 알려주고, Observer의 onChanged() 메소드가 실행
##### 단점
* MVVM 디자인 패턴 강제화 필요
* 잘못 이용할 경우 Data가 여러번 Observe 되는 현상을 겪을수 있다.
##### 주의점
* LiveData는 UI 감시라는 큰 장점에서 사용하는것이기 때문에 UI에 영향을 미치지 않는 곳을 구현할 때에는, LiveData가 필요하지 않을 수 있다.
* Singleton이 아닐경우 버그가 발생할 여지가 있으므로 소비자간에 LiveData 인스턴스를 여러군데서 공유하기 전에 신중하게 생각해야한다. -> 최대한 새로운 인스턴스를 생성해서 감시하고 하나의 LiveData 인스턴스를 여러곳에서 가지지 말자.
---
### 코드 작성
* **컴포넌트의 onCreate() 메소드 내에 위치하는 것이 바람직**
* **하지마 LiveData를 통해서 UI를 업데이트 하고 싶을때는 ViewModel에 선언하는것이 데이터 간 결합도와 MVVM 지향이기에 바람직**
* onActivityCreated()안에서 LiveData를 observe 할 때, getVIewLifecycleOwner() 를 사용
* 만약 onCreate()에서 LiveData를 observe 하고 싶으면 Fragment가 recreate 될 때 view와 value를 수동으로 업데이트 해줘야한다.
##### ViewModel
* ```kotlin
  class ItemViewModel : ViewModel() {
    
    // Create LiveData
    val currentItem : MutableListData<String> by lazy {
        MutableLiveData<String> ()
    }
  }
##### View - Activity Or Fragment 
* ```kotlin
  class ItemActivity : AppCompatActivity() {
    
     private lateinit var model : ItemViewModel
    
     override fun onCreate(savedInstanceState : Bundle?)
         super.onCreate(savedInstanceState)
        
         model = ViewModelProvider(this).get(ItemViewModel::class.java)
        
         val itemObserver = Observer<String> { newItem ->
             itemTextView.text = newItem
         }
        
         model.currentName.observer(this, itemObserver)
  }
##### LiveData()
* 변경할 수 없고 오로지 데이터의 변경값만을 소비
##### MutableLiveData()
* 변경할 수 있는 LiveData 형으로 데이터를 UI Thread와 Background Thread에서 선택적으로 변경
* setValue()
  * background Thread
* postValue()
  * ui Thread 에서 처리
---
### ProgressBar 추적하기
* ```kotlin
  // ui
  viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(@Nullable Boolean aBoolean) {
          aBoolean ? pds.show() : pd.dismiss();
      }
  }

  // viewmodel
  pirvate final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
  public LiveData<Boolean> getIsLoading() {
      LiveData<Boolean> isLoading = yourRepository.getIsLoading();
      return isLoading;
  }
  
  public LiveData<Boolean> getIsLoading(){
    LiveData<Boolean> isLoading=yourRepository.getIsLoading();
    return isLoading;
  }
