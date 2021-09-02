# 애니메이션
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
 
