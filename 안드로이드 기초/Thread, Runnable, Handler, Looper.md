Thread, Runnable, Handler, Looper
===

Thread - [출처](https://aileen93.tistory.com/105)
---
* ```java
  class ThreadEx01{
      public static void main(String args[]){
          ThreadEx2 t1 = new ThreadEx2();
          t1.start()
      }
  }
  
  class ThreadEx2 extends Thread{
      public void run(){
          for(int i=0; i<5; i++)
              System.out.println("쓰레드가 실행되었습니다.");
      }
  }
  ```      
* java는 **다중상속을 지원하지 않는다.** 그래서 extends는 확장성이 떨어질수있다.

Runnable - [출처](https://aileen93.tistory.com/105)
---
* ```java
  class RunnableEx01{
    public.....args[]){
      Runnable r = new ThreadEx1();
      Thread t1 = new Thread(r);
      t1.start();
    }
  }
  
  class ThreadEx1 implements Runnable{
    private boolean stopped = false;
    public void run(){
      while(!stopped){
      ....
      }
    }
    public void stop(){
      System.out.println............;
    }
  }
* implement를 사용하기 때문에 'extends Thread'보다 확장성이 있다고 말할수 있다.
  
필요성
---
* 안드로이드의 UI는 MainThread에서 주축으로 싱글 스레드 모델로 동작하므로 **메인 스레드에서 긴 작업은 피해야 한다.**
* 긴 작업을 하고싶을때는?
  * Handler클래스 사용
    * Message, Runnable객체를 받아와 다른곳으로 전달해주는것
    
Handler - [출처](https://itmining.tistory.com/5)
---
* 핸들러는 쓰레드가 작업할 내용을 순차적으로 대기시켜주는 역할
* ![](/img/handlerThread.png)
* Message는 handleMessage()메서드를 이용해 처리하는데 위의 그림처럼 handler가 handlemessage | sendMessage()를 통해 처리한다.
  * handleMessage()
    * Looper에서 받은 Message 처리
  * sendMessage()
    * Thread로부터 받은 Message를 MessageQueue에다가 넣기
  * Looper
    * MessageQueue에서 Message를 꺼내어 Handler에다가 전달
* Handler는 **의존적**이다.
* [코드 출처 - IT마이닝](https://itmining.tistory.com/16)
* ```java 
  onCreate(...){
    ....
    Thread.start();
    ...
    
  }
  // 대충 쓴 코드
  final Handler handler = new Handler(){
    public void handleMessage(Message msg){
      swtich(msg.what){
        case SEND_INFORMATION:
          textView.setText(Integer.toString(msg.arg1) + msg.obj);
          break;
          ...
          ...
        
      }
    }
  }
  
  Thread(){
    run(){
      String information = new String("hello");
      message.obj = information;

      // 메시지 전
      handler.sendMessage(message);
    }
  }
  ```
  * Handler 주요함수 - [출처 - mystoryg](https://brunch.co.kr/@mystoryg/84)
    * Handler.sendMessage(Message msg) 
      * Message 객체를 message queue에 전달하는 함수
    * Handler.sendEmptyMessage(int what)
      * Message의 wha 필드를 전다랗는 함수
    * Hnadler.post(new Runnable())
      * Message객체가 아닌 Runnable 객체를 Message Queue에 전달하는 함수


Looper - [반드시봐야 하는 출처](https://recipes4dev.tistory.com/166), [출처 - IT 마이닝](https://itmining.tistory.com/5) , [출처 - what-is-the-purpose-of-looper-and-how-to-use-it](https://qastack.kr/programming/7597742/what-is-the-purpose-of-looper-and-how-to-use-it)
---
* **필요성**
  * 메인 쓰레드는 유일하게 UI를 조정한다. 그런데 사용자가 순서대로 UI를 클릭한다고 가정한다고 가정을 해보자. 그런데 만약에 작업이 오래걸린다면 어떻게 해야할까?
  * 예를들어서 ui상에 버튼이 5개가 있다고 하자. 편의상 1번,2번,3번,4번,5번으로 부르자. 1번버튼을 누르면 메인 쓰레드는 작업을 시작한다. 그리고 곧 바로 2번을 누르고 3번을 누르고 4번,5번까지 누를것이다. 그런데 1번작업이 오래걸렸다고 치자. 그러면 메인쓰레드는 우선 1번을 마무리하고 2번부터 5번까지 순서대로 작업을 진행하여야한다. 하지만 이러한 기능을 해줄수 있는것이 안드로이드에서는 구현되지 않았었고 그래서 만들어진것이 루퍼(Looper)라는 개념이다. 즉 메시지를 동시에가 아닌 순서대로 처리하기위해서 만들어진 개념이다. 그리고 메인쓰레드는 루퍼를 안드로이드환경에서 자동으로 생성해준다. 결론적으로 말하자면 루퍼가 필요한 이유는 GUI 프레임 워크에서 발생하는 문제를 해결하기 위해서이고 해당 목적을 달성하기위해 만들어진 클래스이다.
  메인쓰레드는 메인루퍼가 있지만 그밖에 경우는 없어서 단지 run 메서드만 실행한 후 종료하기 때문에 메시지를 받을 수 없다.
  * 루퍼가 없다면 멀티 스레드 프로그래밍에서 문제점이 발생하게 된다. 대표적으로 컴퓨터공학과를 졸업했다면 한번쯤 들어본 용어인 DeadLock(교착)이 있다. 관련내용 자세한 사항은 : [godkyu Tistory 블로그](https://godkyu.tistory.com/6)를 참고하면 된다.
* 루퍼는 또한 쓰레드간 통신을 위해 사용될수있다.
  * 예를들어서 ThreadA 와 ThreadB가 있을때 ThreadA가 ThreadB에게 메세지를 전달하고 싶다면 같은 루퍼안에서 메시지를 전달하면된다. 메인루퍼가 아니라면 루퍼는 따로 생성해야한다.
    * ```java
      //- SubThread2에서 만들 쓰레드
		  HandlerThread hThread = new HandlerThread();
		  hThread.start();

		  //- SubThread1에서 만들 핸들러와 루퍼
		  Handler handler = new Handler( hThread.getLooper() );
* Looper는 하나의 Thread만을 담당할수 있다.
  * **또한** 하나의 Thread도 오직 하나의 Looper만을 가진다.
* MessageQueue가 비어있다면 Looper는 놀다가 Message가 들어오면 Handler에 전달한다.
  * **이 짓을 계속 반복하기 때문에 루퍼(Loop + er)라는 이름이 붙었다.
* Looper 생성법
  * ```java
    Thread t = new Thread(new Runnable(){
      @Override
      public void run() {
        Looper.prepare(); 
        handler = new Handler();
        Looper.loop();
      }
    });
    t.start();
    ```
  * Looper.prepare()
    * MessageQueue 준비
  * handler = new Handler()
    * Handler 생성
  * Looper.loop()
    * Message 전달을 기다리는 작업이 시작
  * onDestory()
    * **Activity에서 handler.getLooper().quit()를 이용해 꼭 종료 시켜야 된다.**
  * 코드 흐름
    * ![](/img/Looper.png)
    
* **메인스레드는 MainLooper가 있기 때문에 루퍼를 따로 생성하지 않아도된다.**
* 다음 코드를 보면 알수있다. Looper클래스에 의해 guarded 되고있다.
  ```java
  @UnsupportedAppUsage
  private static Looper sMainLooper;  // guarded by Looper.class
* 또한 주석에 안드로이드 환경에서 만들어지니 prepareMainLooper를 호출하지 말라고 한다.
  ```java
  /**
  * Initialize the current thread as a looper, marking it as an
  * application's main looper. The main looper for your application
  * is created by the Android environment, so you should never need
  * to call this function yourself.  See also: {@link #prepare()}
  */
  public static void prepareMainLooper() {
     prepare(false);
     synchronized (Looper.class) {
         if (sMainLooper != null) {
             throw new IllegalStateException("The main Looper has already been prepared.");
         }
         sMainLooper = myLooper();
     }
  }
* quit() , quitSafely()
  ```java
  public void quit() {
     mQueue.quit(false);
  }
  
  public void quitSafely() {
      mQueue.quit(true);
  }
*    
  * quit()
    * 모든 메시지큐 제거
  * quitSafely() - API 18 이상
    * 지연된 메시지는 루프가 종료되기전에 전달되고 나머지 메시지 제거


  
* ```java
  public class MainActivity extends AppCompatActivity {  
    Handler mHandler = null; 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
      super.onCreate(savedInstanceState); 
      mHandler = new Handler(); 
      Thread t = new Thread(new Runnable(){ 
          @Override 
           public void run() { 
              // UI 작업 수행 X 
          mHandler.post(new Runnable(){ 
             @Override 
             public void run() { 
               // UI 작업 수행 O
             } 
          }); 
        } 
      }); 
      t.start(); 
      } 
    }
    ```
    * 메인 스레드에서 Handler를 생성하면 해당 Handler는 호출한 스레드의 메시지큐와 루퍼에 자동 연결 되므로 다른 스레드에서 Handler를 통해 메시지를 전달하면 메인 스레드(UI 스레드)에서 UI 작업을 가능
    
HandlerThread
---
* Thread를 상속하고 자동으로 Looper.prepare()와 Looper.loop()를 실행하여 개별 Looper를 가지는 내부에서 무한루프를 도는 백그라운드 스레드
* ```java
  HandlerThread handlerThread = new HandlerThread("myHandlerThread");
  handlerThread.start();


