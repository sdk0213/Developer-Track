# MediatorLiveData
---
### 하나의 LiveData가 하나 이상을 관찰하게 할수있게 한다
##### 두 개의 원격저장소에서 데이터를 가져올떄 두개의 원격저장소중 한쪽이라도 통신오류가 났을때에 대한 처리
* ```kotlin
  val observeIsDataComplete = MediatorLiveData<Boolean>()

  val isGetDataCompleteFromServer1 = LiveData<Boolean> ..
  val isGetDataCompleteFromServer2 = LiveData<Boolean> ..
  
  observeIsDataComplete.addSource(isGetDataCompleteFromServer1){ value ->
      result.setValue(value) // == result.setValue(it)
  }
  
  observeIsDataComplete.addSource(isGetDataCompleteFromServer2){ value ->
      result.setValue(value)
  }
  
  ... Thread ... Or Rx ... Or Coroutine ... 에서
  // observeIsDataComplete를 감시한다고 한다면
  In ConnectionThread...
  if(!observeIsDataComplete){
      reportFailedToConnectionToServer()
  }
  
  In UiThread
  if(!observeIsDataComplete){
        ui_reportToUserAboutFailedToConnectionToServer()
  }
---
### addSource 노가다를 막아주는 확장함수
* ```kotlin
  fun <T> MediatorLiveData<T>.addSourceList(vararg liveDataArgument: MutableLiveData<*>, onChanged: () -> T) {
     liveDataArgument.forEach {
          this.addSource(it) { value = onChanged() }
     }
  }
