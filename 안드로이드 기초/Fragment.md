# Fragment
---
### 생성 및 사용. 
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
### xml 로 정의가능
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
* 상태를 잃어버릴수 있는 경우
  * onSaveInstanceState() 이후의 FragmentManager 의 상태, 추가하거나 제거된 fragment의 상태
* commit는 항상 activity의 onSaveInstanceState()가 호출 되기 전에 수행되어야한다
  * 위 과정을 따르지 않을경우 Illegal..Exception 발생
  * 위 과정을 따르기 싫다면 commitAllowingStateLoss 를 사용
  * 참고사항
    * onPause() -> **onSavedInstance()** -> onStop() -> maybe call onDestory()
  
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
