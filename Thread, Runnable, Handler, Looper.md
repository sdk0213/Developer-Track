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
* ![](https://t1.daumcdn.net/cfile/tistory/232EB335577C080F21)
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


Looper - [출처 - IT 마이닝](https://itmining.tistory.com/5)
---
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
    
* **하지만 많은 소스를 보면 Handler를 Looper없이 단독 사용한다.**
  * **왜냐하면** 안드로이드에서는 편리성을 제공하기 위해 Handler의 기본 생성자를 통해 Handler 단독으로 사용할수 있게 해주기 때문!!!
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

