# Fragment
---
### 생성 및 사용
* ```java
  private FragmentManager mFragmentManager;
  private FragmentA fragmentA;
  private FragmentTransaction mTransaction;
  
  ...init(){
      mFragmentManager = getSupportFragmentManager();
  
  }
  
  ...onClick(){
  
     if(R.id...){
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.fragment_container, mImagePickFragment).commitAllowingStateLoss();
        mTransaction.addToBackStack(null);
     }
  }
### event 처리방식
* Fragment 에 전달되는 event 들은 사실 activity 가 먼저 받고, 그 다음에 fragment 에 전달이 되는 형태이다. 물론 activity 에서 다 처리하고, fragment 로 전달하지 않도록 하는 방법도 있다.
### xml 로 정의가 가능하다.
--- 
* ```xml
  <fragment>
      ...
  </fragment>
---
### commit, commitAllowingStateLoss
* commit
  * commit은 다음 번 스레드가 준비될 때 메인 스레드에 대한 작업으로 예약된다
  * 기다리기 싫다면 ??
    * commitNow()
* 그러니까 상태을 언제 잃어버린다는건가?
  * 백그라운드에서 replace 또는 add 를 진행할경우
  * 백그라운드에서 시스템이 앱을 강제로 메모리로 종료시키고 다시 commit을 강제로 진행시킬경우
  * 그렇다면 왜 안드로이드는 commit을 통해 데이터 상태를 보장해주기는 커녕 그저 exceptipon 만 떨어트릴거면 이거를 왜 만든것인가?

상태를 잃어버릴수 있는 경우
  * onSaveInstanceState() 이후의 FragmentManager 의 상태, 추가하거나 제거된 fragment의 상태
* commit는 항상 activity의 onSaveInstanceState()가 호출 되기 전에 수행되어야한다
  * 위 과정을 따르지 않을경우 Illegal..Exception 발생
  * 위 과정을 따르기 싫다면 commitAllowingStateLoss 를 사용
  * 참고사항
    * onPause() -> **onSavedInstance()** -> onStop() -> maybe call onDestory()
* 특징
  * commit() 된 transaction 은 바로 실행되지 않고, main thread 에 schedule 되어 처리된다. 
  * Fragment 에 전달되는 event 들은 사실 activity 가 먼저 받고, 그 다음에 fragment 에 전달이 되는 형태이다. 물론 activity 에서 다 처리하고, fragment 로 전달하지 않도록 하는 방법도 있다.
  * fragment 를 UI 가 없는 녀석으로 만들어 background 작업을 하도록 사용할 수도 있다. 이때는 add( Fragment fragment, String tag ) 를 통해 등록을 한다. 이렇게 등록을 하면 onCreateView() 가 호출되지 않는다. 물론 구현할 필요도 없다. 등록시의 tag 는 findFragmentByTag() 를 통해 해당 fragment 에 접근하는데 쓰인다.

---
### fragmentManager - [출처는 selfish-developer 님의 티스토리 블로그 - FragmentManagers Android](https://selfish-developer.com/entry/FragmentManagers-Android)
* supportFragmentManager (SFM)
  * Activity랑 상호작용하는(interacting) Fragment를 관리
* parentFragmentManager (PFM)
  * 부모 UI 컴포넌트의 manager
* childFragmentManager (CFM)
  * Fragment 고유의 FragmentManager
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbB23Hk%2FbtqDeUBILbJ%2FctpsLKfK8oqL0Xf3hSAITk%2Fimg.png)
* childFragmentManager 가 아닌 parentFragmentManager 로 생성할경우 다음과 같은 경우가 생기기 때문에 fragmentManager 를 잘 선택해야한다.
  * ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbvxWjo%2FbtqDebw9kA9%2F7jpEbOkQuyPxx9LNg041G0%2Fimg.png)
* 아무 Manager 나 골라써서 만든다고 하여서 문제가되지는(메모리 릭) 않는듯보이나 의도치 못한 버그가 생성될것으로 보이기 때문에 위 사항을 따라서 개발하여야함
---
### 애니메이션
* setCustomAnimations(enter, exit, popEnter, popExit)
* fromX(or Y)Delta -> toX(or Y)Delta
  * x1 -> x2 또는 y1 -> y2 로 이동
* duration
  * 슬라이드 속도를 뜻하면 보통은 값이 200
* ```java
  mTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down,R.anim.slide_up, R.anim.slide_down);
* slide_down.xml
  * ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate
            android:duration="@integer/slide"
            android:fromYDelta="0"
            android:toYDelta="100%" />
    </set>
* slide_in_left.xml
  * ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate android:fromXDelta="-100%" android:toXDelta="0%"
            android:fromYDelta="0%" android:toYDelta="0%"
            android:duration="@integer/slide"/>
    </set>
* slide_in_right.xml
  * ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate android:fromXDelta="100%" android:toXDelta="0%"
            android:fromYDelta="0%" android:toYDelta="0%"
            android:duration="@integer/slide"/>
    </set>
* slide_out_left.xml
  * ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate android:fromXDelta="0%" android:toXDelta="-100%"
            android:fromYDelta="0%" android:toYDelta="0%"
            android:duration="@integer/slide"/>
    </set>
* slide_out_right.xml
  * ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate android:fromXDelta="0%" android:toXDelta="100%"
            android:fromYDelta="0%" android:toYDelta="0%"
            android:duration="@integer/slide"/>
    </set>
* slide_up.xml
  * ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate
            android:duration="@integer/slide"
            android:fromYDelta="100%"
            android:toYDelta="0" />
    </set>
