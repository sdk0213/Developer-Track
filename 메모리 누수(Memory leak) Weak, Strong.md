메모리 누수(Memory leak)
===

GC알고리즘 - [출처 - @joongwon](https://medium.com/@joongwon/android-memory-leak-%EC%82%AC%EB%A1%80-6565b817a8fe)
---
* 안드로이드
  * activity instance 에 대한 누수
    * 자바의 일반적인 객체의 생명주기와 다르다.
      * 왜냐하면 activity와 같은 컴포넌트가 자신만의 생명주기를 가지고 있기 때문이다.
    * 그래서 onDestory() 이후에도 activity의 instance는 계속해서 heap 에 남는 상황이 발생할 수 있다.
      * GC가 이 부분을 처리함
        * **하지만 만약에 activity에 대한 참조가 남아있다면? --> 메모리 누수**
* Java의 일반적인 객체는 Strong reference이다.
* 다음과 같은 상황에서는 GC가 작동하지 않는다.
* ![](/img/GCException.png)
* inner Class는 Non-static Nested Class 라고도 불리며 OuterClass가 까지 참조
* 여기서는 OuterClass를 참조한 상태에서 main thread에서 생성하였기 때문에 루퍼와 메시지큐가 메인스레드에 바인드된다.
* non-static inner class의 경우 outer class에 대한 reference를 가지게 된다. non-static inner class로 선언된 Handler는 LeakActivity에 대한 reference를 가지고 main thread에서 생성하였기 때문에 main thread의 Looper 및 Message Queue에 바인딩 된다. 
* handler에서는 LeakActivity에 대한 reference가 남아 있기 때문에 LeakActivity는 GC의 대상이 되지 않는다.
* 이러한것을 방지하기위해서는 static inner class의 사용과 WeakReference를 사용하여야한다.
* ```java
  public class NonLeakActivity extends Activity {
  private NonLeakHandler handler = new NonLeakHandler(this);
  
  private static final class NonLeakHandler extends Handler {
    private final WeakReference<NonLeakActivity> ref;
    
    public NonLeakHandler(NonLeakActivity act) {
      ref = new WeakReference<>(act);  
    }
    
    @Override
    public void handleMessage(Message msg) {
      NonLeakActivity act = ref.get();
      if (act != null) {
        // do work  
      }
    }
  }
  
  private static final Runnable runnable = new Runnable() {
    @Override
    public void run() {}
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    handler.postDelayed(runnable, 60000);
    finish();
  }
  }
  ```
