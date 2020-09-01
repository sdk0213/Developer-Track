Single
===
* 데이터 무한 발행 가능한 Observable클래스와 다르게 **오직 1개의 데이터만 발행**하도록 한정된다.
* MarbleDiagram
  * ![](img/marblediagram_single.png)
* 발행과 동시에 종료(onSuccess)
  * {onNext(), onComplete()} -> onSucees로 통합
  * 그래서 라이프사이클이 onSuccess(T value)와 onError()로 구성

just()
---
* Observable과 거의 같은 방법으로 활용가능
* ```java
  Single<String> source = Single.just("Hello Single");
  source.subscribe(System.out::println);
  // result:
  // Hello Single
* Observable과 변환가능하다.
  * fromObservable() 사용
    * ```java
      Observable<String> source = Observable.just("Hello Single");
      Single.fromObservable(source)
        .subscribe(System.out::println);
      // result:
      // Hello Single
 
  * single() 함수는 값이 발행되지 않을 때도 기본값(default value)을 갖는 Single객체로 변환 가능하다.
    * ```java
      Observable.just("Hello Single")
        .single("default item")
        .subscribe(System.out::println);
      // result:
      // Hello Single
      
  * first() 함수를 호출하면 Observable이 Single객체로 변환된다.
    * ```java
      String[] colors = {"Red", "Blue", "Gold"};
      Observable.fromArray(colors)
        .first("default value")
        .subscribe(System.out::println);
      // result:
      // Red
      
  * empty() 함수를 통해서 Single객체 생성
    * ```java
      Observable.empty()
        .single("default value")
        .subscribe(System.out::println);
      // result:
      // default value
      
  * take()
    * ```java
      Observable.just(new Order("ORD-1"), new Order("ORD-2"))
        .take(1)
        .single(new Order("default order"))
        .subsribe(System.out::println);
      // result:
      // Order ID: ORD-1
       
       
