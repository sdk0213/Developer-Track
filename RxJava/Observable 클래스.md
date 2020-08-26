Observable
===
* **RxJava에서 가장 중요한 것**
* Observable 클래스는 데이터의 변화가 발생하는 데이터 소스(data source
* Observable은 데이터 흐름에 맞게 알림을 보내 구독자가 데이터를 처리할 수 있도록 해준다.
* Rxjava 2.x 부터 세개의 클래스로 세분화되었다.
  * Observable
  * Maybe
  * Flowable
* ![](img/observableclassification.png)

예제
---
* ```java
  import io.reactivex.Observable; 

  public class FirstExample { 
    public void emit() { 
      Observable.just("Hello", "RxJava 2!!") 
      .subscribe(System.out::println);  // <==> data -> System.out.println(data)
    } 
  
    public static void main(String args[]) {
      FirstExample demo = new FirstExample();
      demo.emit(); 
    }
  }

just()
---
* Observable을 선언하는 방식이다.
* 발행하는것이다.

subscribe()
---
* 데이터를 수신한 구독자가 subscribe()을 호출해야 Observable에서 데이터가 발행된다.

