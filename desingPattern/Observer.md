Observer
===
Observer
---
* 객체의 상태가 변경되면 그 객체에 의존하는 객체들에게 정보를 알려주는 디자인 패턴이다.
  * 'Youtube'라는 플랫폼에서 사용자가 특정 동영상제작자를 구독하게 될경우 동영상제작자가 동영상을 업로드하거나 변경시 구독한 사용자는 알림을 받게 되는데 이와 같은 시스템을 구독시스템이라고 한다.
  * 구독자 = Observer, 제작자 = Observable 으로 비슷하게 이해해도 괜찮다.
* 예제 코드
  * 아래의 코드는 MyTopic(Subject)에 MyTopicSubscriber(Observer)를 붙히고 MyTopic의 값이 변경될때 모든 옵저버들(구독자들)에게 알리는 형식으로 이루어진 옵저버 패턴입니다.
  * Subject Interface
    ```java
    public interface Subject {
      //methods to register and unregister observers
	    public void register(Observer obj);
	    public void unregister(Observer obj);
	
	    //method to notify observers of change
	    public void notifyObservers();
	
	    //method to get updates from subject
	    public Object getUpdate(Observer obj);
	  }
  * Observer Inteface
    ```java
    public interface Observer {
      // subject가 옵저버를 업데이트하기위한 Method
	    public void update();
	
	    // 옵저빙을 subject에 붙히기
	    public void setSubject(Subject sub);
    }
  * MyTopic
    ```java
    public class MyTopic implements Subject {
        private List<Observer> observers;
  	    private String message;
  	    private boolean changed;
  	    private final Object MUTEX= new Object(); // 동기화를 위한 Object
	   
        public MyTopic(){
          this.observers=new ArrayList<>();
        }
      
        @Override
        public void register(Observer obj) {
          if(obj == null) {
            throw new NullPointerException("Null Observer");
          }
          synchronized (MUTEX) {
            if(!observers.contains(obj)) {
              observers.add(obj);
            }
          }
        }

        @Override
        public void unregister(Observer obj) {
          synchronized (MUTEX) {
            observers.remove(obj);
          }
        }

        @Override
        public void notifyObservers() { 
          List<Observer> observersLocal = null;
          //synchronization is used to make sure any observer registered after message is received is not notified
          synchronized (MUTEX) {
            if (!changed) {
              return;
            }
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
          }
          for (Observer obj : observersLocal) {
            obj.update(); // 모든 옵저버들 업데이트 
          }

        }

        @Override
        public Object getUpdate(Observer obj) {
          return this.message;
        }
	
        //method to post message to the topic
        public void postMessage(String msg){
          System.out.println("Message Posted to Topic:"+msg);
          this.message=msg;
          this.changed=true;
          notifyObservers();  // ★★ 옵저버들에게 알리기 ★★ //
        }

      }
   * Observer
     ```java
      public class MyTopicSubscriber implements Observer {
	
        private String name;
        private Subject topic;
	
        public MyTopicSubscriber(String nm){
          this.name=nm;
        }
        @Override
        public void update() {
          String msg = (String) topic.getUpdate(this);
          if(msg == null){
            System.out.println(name+":: No new message");
          } else {
            System.out.println(name+":: Consuming message::"+msg);
          }
        }

        @Override
        public void setSubject(Subject sub) {
          this.topic=sub;
        }

      }
    * Main
      ```java
      public class ObserverPatternTest {

	      public static void main(String[] args) {
		      //create subject
		      MyTopic topic = new MyTopic();
		
		      //create observers
		      Observer obj1 = new MyTopicSubscriber("Obj1");
		      Observer obj2 = new MyTopicSubscriber("Obj2");
		      Observer obj3 = new MyTopicSubscriber("Obj3");
		
		      //register observers to the subject
		      topic.register(obj1);
		      topic.register(obj2);
		      topic.register(obj3);
		
		      //attach observer to subject
		      obj1.setSubject(topic);
		      obj2.setSubject(topic);
		      obj3.setSubject(topic);
		
		      //check if any update is available
		      obj1.update();
		
		      //now send message to subject
		      topic.postMessage("New Message");
	      }

      }
* 사용하는 이유
  * subject 와 observer 가 느슨하게 결합되어서 의존성이 하락되고 시스템이 유연해진다.
  * 이해하기가 쉽다.
* 대표적인 예
  * Android EventListener
  * node.js event Loop
  * 브라우저 event Handler
* 단점
  * 메모리누수가능성(옵저버 해제안할경우)
  * Thread safe X
  * 너무 많으면 관리가 힘듦
  * 비동기방식으로 원하는순서대로 받지 못할수 있음
  
