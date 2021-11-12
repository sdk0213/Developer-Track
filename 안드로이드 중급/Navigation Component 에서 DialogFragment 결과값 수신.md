# Navigation Component 에서 DialogFragment 결과값 수신 
* [자세한 내용은 공식 홈페이지 참고 - 꼭 영어로 봐야함, 한국어로 설정할경우 해당내용 보이지 않음](https://developer.android.com/guide/navigation/navigation-programmatic#additional_considerations)
* 2.3 버전의 패치로 가능한 방법
  * In Navigation 2.3 and higher, NavBackStackEntry gives access to a SavedStateHandle.
* <img width="758" alt="스크린샷 2021-11-12 오후 6 48 28" src="https://user-images.githubusercontent.com/51182964/141446838-3d83bba0-e725-47f4-9221-3cf871c68534.png">
* 전달하기
  * DialogFragment 에서 전 이전 백스택의 savedStateHandle 에 값을 저장
* 전달받기
  * DialogFragment 를 호출할경우 onStop 이 호출되고 DialogFragment 가 끝나고 다시 복구되었을때 onResume 이 되었을때를 구독하는것이 포인트
  * DialogFragment 를 호출한 Fragment 에서는 자기 자신의 라이프사이클이 onResume 될때를 항상 구독하고 onResume 이 되었을 경우 savedStateHandle 에서 값을 확인한다.
  * onDestory 가 호출될경우 구독 종료
* Extension Kit
  ```kotlin
  // DialogFragment --> Fragment 데이터 전달
  fun <T> Fragment.setNavigationResult(key: String, value: T) {
      findNavController().previousBackStackEntry?.savedStateHandle?.set(
          key,
          value
      )
  }

  // DialogFragment --> Fragment 데이터 전달
  fun <T> Fragment.getNavigationResult(@IdRes id: Int, key: String, onResult: (result: T) -> Unit) {
      val navBackStackEntry = findNavController().getBackStackEntry(id)

      val observer = LifecycleEventObserver { _, event ->
          if (event == Lifecycle.Event.ON_RESUME
              && navBackStackEntry.savedStateHandle.contains(key)
          ) {
              val result = navBackStackEntry.savedStateHandle.get<T>(key)
              result?.let(onResult)
              navBackStackEntry.savedStateHandle.remove<T>(key)
          }
      }
      navBackStackEntry.lifecycle.addObserver(observer)
  
      viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
          if (event == Lifecycle.Event.ON_DESTROY) {
              navBackStackEntry.lifecycle.removeObserver(observer)
          }
      })
    }
