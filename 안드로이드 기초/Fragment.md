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
  
* ```java
  
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
