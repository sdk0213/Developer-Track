# 1. 기본적으로 사용하지 않는 컴포넌트는 삭제
* 서비스, 엑티비티, 프래그먼트, 리시버 등
* 사용하지도 않는데 활성화되어있는 컴포넌트들이 있다면 바로 종료하기
---
# 2. Android 에서 enum 의 사용은 메모리용량을 많이 사용한다?
* [youtube 비디오 (enum의 위험성)- The price of ENUMs (100 Days of Google Dev)](https://www.youtube.com/watch?v=Hzs6OBcvNQE)
* <img width="685" alt="스크린샷 2022-04-01 오후 10 52 36" src="https://user-images.githubusercontent.com/51182964/161277328-d8d5c2aa-596c-4838-b676-418e3d952d9e.png">
* **이는 kotlin 에서도 동일하다. 최대하 지양한다.**
* int 로 열거했을때와 enum 으로 열겨했을때의 메모리 차이는 13배 정도난다.
* enum 으로 티끌모아 태산이 되면 이미 늦었다. 
### 대처법 1
* enum 을 잘짜고 프로가드 최적화..가 잘 되길 기도한다.
### 대처법 2
* enum 말고 typedef 사용해서 컴파일시 값을 찾자. (enum 장점만 사용한것)
##### 기존 
* ```java
    enum Fruit{
      APPLE, PEACH, ORANGE, BANANA;
    }
##### @IntDef
* ```java
  class Fruits { 
    @Retention(RetentionPolicy.SOURCE) 
    @IntDef({APPLE, PEACH, ORANGE, BANANA}) 
    public @interface types {} 

    public static final int APPLE = 0; 
    public static final int PEACH = 1; 
    public static final int ORANGE = 2; 
    public static final int BANANA = 3; 
  }
---
# 3. onTrimMemory callback
* 메모리 사용량에 따라서 작업을 수행할수있다. 가용할수있는 메모리가 떨어질때는 다른작업을 진행할수있게 처리하면 보다 안정적으로 앱을 만들수있다.
* 사용법
  ```java
  class MainActivity : AppCompatActivity(), ComponentCallbacks2 {
  
      @Override 하시면 됩니다...
  }
  
--- 
# 4. 추상화 클래스
* 추상화 클래스는 일반적으로 메모리를 더 소모함. 굳이 만들필요없다면 지향하는편이 좋다.

---
# 5. 메모리 할당 조심하기
* GC 처리 과정자체가 프레임에 영향을 주기 떄문에 과도한 메모리 할당(프레임 연속적으로 그리기) 할때 new 를 지속적으로 하지 말고 기존것을 쓰던가하자

---
# 6. 앱 사이즈 줄이기
* 구글님께서 앱 사이즈를 줄이는것만으로도 메모리 사용량이 줄어든다고 한다. 최대한 줄여보자

---
# 7. Dagger2 쓰자
* 어렵지만 배워둔 의미가 있는것같다. 근데 이게 메모리 최적화까지 잘되는줄은 몰랐다. 아무튼 쓰면 좋다고 하고 무조건 앞으로 써야되니 꼭 쓰자

---
# 8. 라이브러리 남용 금지
* 라이브러리를 잘알고있다. -> 메모리 사용량 파악가능 -> 사용해도됨
* 라이브러리르 모른다 -> 일단써봐야겠다 -> 메모리 사용량 파악불가능 -> 메모리 릭 가능성 상승
* 인증안된 아무 라이브러리나 막 가져다가 쓰지 말자

---
# 9. 일반적으로 비트맵이나 그래픽이 가장 많은 메모리를 사용한다.
* Glide 사용하기
* Glide 최적화
  * 성능이 낮으면 ARGB_8888 대신 RGB_565 사용한다.(2배정도차이난다고함)

---
# 10. Room 사용하기

---
# 11. 정적 객체를 activity 에서 사용할때는 다음과 같이 사용
* ```java
  public class LeakActivityToSingletonActivity extends AppCompatActivity {

    SomeSingletonManager someSingletonManager = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_context_to_singleton);
        
        someSingletonManager = SomeSingletonManager.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        someSingletonManager.unregister(this); //<--------- 필수~~~~~~~~~~~~
    }
  }

---
# 12. Activity 내 내부클래스는 정적으로 생성하기
* ```java
  private static class LeakedThread extends Thread {

    @Override
    public void run() {
        while (!isInterrupted()) {
            SystemClock.sleep(1000);
        }
    }
  }
  
---
# 13. Boxed 말고 Primitive Types 사용
* Integer(16bytes) vs int(4bytes)
* Boolean(16 bytes) vs boolean(4 bytes)
* 당연히 Primitive Types 사용해야함

---
# 14. arrayMap, sparseArray 사용 (데이터 100개미만에서)
* 메모리상에서 약 20~30% 상 이득을본다.
* 다만 속도 측면에서는 느려지기 때문에 데이터 100개 미만에서 사용할경우 HashMap 에 비해 낮은메모리로 사용가능하다.
* |HashMap|->|Array Class|
  |:--:|:--:|:--:|
  |<Integer, Object>|->|SparseArray|
  |<Integer, Boolean>|->|SparseBooleanArray|
  |<Integer, Integer>|->|SparseIntArray|
  |<Integer, Long>|->|SparseLongArray|
  |<Long, Object>|->|LongSparseArray|

---
# 15. 기타 내용 - [출처는 여기용](https://hsc.com/Resources/Blog/Best-Practices-For-Memory-Optimization-on-Android-1)
* Try to avoid static variables or objects as much as possible if they are not final constants. Static variables pose the threat of having references in other classes, which we might forget about and thus cause a memory leak.
* Prefer static methods over virtual methods where any of the member fields of object are not accessed. The static invocations are faster because they save dalvik look up of the method.
* Avoid internal getters and setters. Direct field access is much faster in Android than virtual method lookup. If an app is not exposing APIs as libraries, it should avoid using accessors.memory measuring tools
* A common mistake is to save “Context” objects everywhere in the application. If the developer forgets to free even one reference of a context, it increases the chance of a whole activity leak.
* Match the calls of registration and un-registration of the listeners, receivers in corresponding pairs in activity lifecycle. If a broadcast receiver was registered in onStart method, then it should be unregistered in onStop method only. The same goes for onCreate-onDestroy and onResume-onPause.
* In Eclipse or Android Studio, ADT comes with lint analysis tool. It provides tips and tricks for optimizing the application code at the time of compilation. Always pay attention to these tips and warnings and try to incorporate them if possible.
* For passing information between components of same application, avoid using IPC mechanisms like Intents and ContentProviders if possible. Work with handlers, listener interfaces instead.
