Observable
===
* Observable 클래스는 데이터의 변화가 발생하는 데이터 소스data source

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

