메모리 누수(Memory leak)
===

GC알고리즘 - [출처 - @joongwon](https://medium.com/@joongwon/android-memory-leak-%EC%82%AC%EB%A1%80-6565b817a8fe)
---
* Java의 일반적인 객체는 Strong reference이다.
* 다음과 같은 상황에서는 GC가 작동하지 않는다.
* ![](/img/GCException.png)
* inner Class는 Non-static Nested Class 라고도 불리며 OuterClass가 까지 참조
* 여기서는 OuterClass를 참조한 상태에서 main thread에서 생성하였기 때문에 루퍼와 메시지큐가 메인스레드에 바인드된다.
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
