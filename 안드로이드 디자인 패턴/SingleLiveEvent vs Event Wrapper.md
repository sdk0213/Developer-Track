# SingleLiveEvent vs Event Wrapper
### 일반적인 LiveData의 문제
* 단일로 끝나느 이벤트시 다음과 같은 의도치 않는 동작이 발생한다.
* 문제 발성 과정
  * Activity의 onCreate()에서 LiveData를 구독
  * LiveData 값 변경된다.
  * LiveData 값의 변경으로 인해 토스트 혹은 다이얼로그 출력한다.
  * 화면 회전 (onPause -> onStop -> onDestroy -> onCreate -> onStart -> onResume)
  * Activity의 onCreate()에서 다시 LiveData를 구독
  * LiveData의 상태가 InActive -> Active로 바뀌었으므로 옵저버가 다시 이전에 저장한 값으로 콜백을 호출한다.
  * 토스트 혹은 다이얼로그를 다시 출력한다.
* 이에 따라서 다른 방법이 필요하고 SingleLiveEvent와 Event Wrapper가 있다.
### SingleLiveEvent
* MutableLiveData를 상속받아 새로운 SingleLiveEvent르 등록한다.
* ```kotlin
  class SingleLiveEvent<T> : MutableLiveData<T>() {
      private val mPending = AtomicBoolean(false)
      @MainThread
      override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
          if (hasActiveObservers()) {
              Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
          }
          // Observe the internal MutableLiveData
          super.observe(owner, object : Observer<T> {
              override fun onChanged(t: T?) {
                  if (mPending.compareAndSet(true, false)) {
                      observer.onChanged(t)
                  }
              }
          })
      }
      @MainThread
      override fun setValue(t: T?) {
          mPending.set(true)
          super.setValue(t)
      }
      /**
       * Used for cases where T is Void, to make calls cleaner.
       */
      @MainThread
      fun call() {
          setValue(null)
      }
      companion object {
          private val TAG = "SingleLiveEvent"
      }
  }
  
  ..
  ...
  // 선언
  private val uploadData = SingleLiveEvent<String>()
  fun getUploadData(): SingleLiveEvent<String> {
      return uploadData
  }
  ..
  ...
  // 사용
  viewModel.getUploadData().observe(this, Observer {
      //Snackbar
  })

##### SingleLiveData의 한계점
*  SingleLiveEvent (코드를 보면) 여러개의 옵저버를 등록시 어떤 것이 실행 될지는 보장되지 않는다.
### Event Wrapper
* SingleLiveEvenet의 단점을 커버하는 방법
* ```kotlin
  open class Event<out T>(private val content: T) {
      var hasBeenHandled = false
          private set

      fun getContentIfNotHandled(): T? {
          return if (hasBeenHandled) { // 이벤트가 이미 처리 되었다면
              null // null을 반환하고,
          } else { // 그렇지 않다면
              hasBeenHandled = true // 이벤트가 처리되었다고 표시한 후에
              content // 값을 반환합니다.
          }
      }

      /**
       * 이벤트의 처리 여부에 상관 없이 값을 반환합니다.
       */
      fun peekContent(): T = content
  }
* ```kotlin
  class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
      override fun onChanged(event: Event<T>?) {
          event?.getContentIfNotHandled()?.let { value ->
              onEventUnhandledContent(value)
          }
      }
  }
##### 사용
* ```kotlin
  val eventToast = MutableLiveData<Event<String>>()
  val eventNavigation: SingleLiveEvent<Event<Any>> = SingleLiveEvent()
* ```kotlin
  viewModel.eventNavigation.observe(this, EventObserver {
	    val intent = Intent(this@MainActivity, EventWrapperActivity::class.java)
  	  startActivity(intent)
  })
        
  viewModel.eventToast.observe(this, { event: Event<String> ->
      Toast.makeText(this, event.peekContent(), Toast.LENGTH_SHORT).show()
  })
