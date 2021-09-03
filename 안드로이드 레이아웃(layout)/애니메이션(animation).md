# 애니메이션
---
### 애니메이션 구현은 어렵고 비용이 많이들지만 몇 개의 포인트만 애니메이션으로 설정하면 앱이 동적으로 살아난다.
##### 1. 단순한것 -> ViewPropertyAnimator 사용 - [출처는 crocus님](https://www.crocus.co.kr/1690)
* ```java
  View.animate()
    .alpha(0.5f)
    .scaleX(0.5f)
    .scaleY(0.5f)
    .setDuration(3000)
    .withStartAction(() -> { Write something to do when the animation starts })
    .withEndAction(() -> { Write something to do when the animation starts })
* 단점으로는 일회용이고 반복적인 애니메이션은 불가능
##### 2. 반복적 -> ObjectAnimator 사용 - [출처는 crocus님](https://www.crocus.co.kr/1690)
* ```java
  ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 300f);
    anim.setDuration(5000);
    anim.setRepeatCount(ValueAnimator.INFINITE);
    anim.setRepeatMode(ValueAnimator.REVERSE);
    anim.start();
* 또는 XML
  ```xml
  <?xml version="1.0" encoding="utf-8"?> 
  <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
                  android:interpolator="@android:interpolator/accelerate_decelerate"
                  android:repeatCount="infinite"
                  android:repeatMode="reverse"
                  ...
                  />
##### 3. 복합적 -> PropertyValueHolders 사용 - [자세한 내용은 crocus님 블로그 참고](https://www.crocus.co.kr/1690)
##### 4. 복합 + 순차 애니메이션 -> AnimatorSet - [자세한 내용은 crocus님 블로그 참고](https://www.crocus.co.kr/1690)
* 반복 불가능
##### 5. 커스터마이징이 너무 많다면 -> ValueAnimator 사용 - [자세한 내용은 crocus님 블로그 참고](https://www.crocus.co.kr/1690)
---
### 애니메이션 속성
##### interpolator 속성
* 아래 의미는 어떤 애니메이션인가에 따라서 달라질수 있음
* linear_interpolator
  * 등속
* accelerate_interpolator
  * 천천히 가속
* decelerate_interpolator
  * 천천히 감속
* accelerate_decelerate_interpolator
  * 과속후 급정거
* overshoot_interpolator
  * 기준점 넘어서까지 가다가 다시 돌아옴...
* bounce_interpolator
  * 기준점에 부딪혀서 통통 튕김 ㅋㅋ
* cycle_interpolator
  * 기준점에 갔다가 다시 돌아옴
##### 반복 설정 속성
* ```xml
  android:fillAfter="true" <-- 기본은 false 임...
##### 지연 설정
* ```xml
  android:startOffset="2000"
---
### 애니메이션 종류 - [출처는 tkddlf4209님의 네이버 블로그](https://m.blog.naver.com/tkddlf4209/220700530627)
##### 회전(rotate)
* ```xml
  <rotate
      xmlns:android="http://schemas.android.com/apk/res/android"
      android:interpolator="@android:anim/accelerate_interpolator" <------- interpolator 속성
      android:duration="1500"     <------ 1.5 초동안
      android:pivotX="50%"        <------ 애니메이션 시작 기준점 x 어디로 (50%는 가운데지점)
      android:pivotY="50%"        <------ 애니메이션 시작 기준점 y 어디로 (50%는 가운데지점)
      android:fromDegrees="0"     <------ 0 도부터
      android:toDegrees="360">    <------ 360 도까지
  </rotate>

##### 크기 (scale)
* ```xml
  <scale xmlns:android="http://schemas.android.com/apk/res/android"
      android:duration="4000"  <------ 4초 동안
      android:fromXScale="0.0" <------ x 시작 크기 배수 (부터)
      android:fromYScale="0.0" <------ y 시작 크기 배수 (부터)
      android:toXScale="2.0"   <------ x 끝 크기 배수 (까지)
      android:toYScale="2.0"   <------ y 끝 크기 배수 (까지)
      android:pivotX="50%"     <------ 애니메이션 시작 기준점 x 어디로 (50%는 가운데지점)
      android:pivotY="50%"     <------ 애니메이션 시작 기준점 y 어디로 (50%는 가운데지점)
    >
  </scale>
##### 투명도 (alpha)
* ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <alpha xmlns:android="http://schemas.android.com/apk/res/android"
      android:duration="3000" <----- 3초 동안
      android:fromAlpha="0.0" <----- 시작 투명도 값
      android:interpolator="@android:anim/accelerate_interpolator" <---- interpolator 속성
      android:toAlpha="1.0" /> <----- 끝 투명도 값
##### 이동 (translate) 
* ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <translate
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="3000"     <------- 3초 동안
        android:fromXDelta="-100%"  <------- 시작 x 지점 ( -100% 는 기준점으로 x 크기만큼 마이너스 )
        android:toXDelta="0">       <------- 끝 x 지점
  </translate>
##### 투명 + 크기 확대 (복합 애니메이션)
* **set은 복합적으로 애니메이션을 설정할때 사용**
* 투명 + 크기 증가 후 다시 크기는 원상으로 복구하는 애니메이션
* ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <set xmlns:android="http://schemas.android.com/apk/res/android" android:interpolator="@android:anim/accelerate_interpolator">
      <alpha
          android:fromAlpha="0.0"
          android:toAlpha="1.0"
          android:duration="2000" />
      <scale
          android:fromXScale="0.5" android:toXScale="1.5"
          android:fromYScale="0.5" android:toYScale="1.5"
          android:pivotX="50%" android:pivotY="50%"
          android:duration="2000" />
      <scale 
          android:fromXScale="1.5" android:toXScale="1.0"
          android:fromYScale="1.5" android:toYScale="1.0"
          android:pivotX="50%" android:pivotY="50%"
          android:startOffset="2000"
          android:duration="2000" />
  </set>                                  
