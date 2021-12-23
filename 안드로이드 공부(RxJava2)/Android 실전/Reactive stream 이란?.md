# Reactive stream
### [리엑티브 선언문에 따르면...](https://www.reactivemanifesto.org/ko)
##### Reactive Streams is an initiative to provide a standard for asynchronous stream processing with non-blocking back pressure.
* 스트림 지향 라이브러리에 대한 표준 및 사양
  * 잠재적으로 무한한 숫자의 데이터 처리
  * 순서대로 처리
  * 컴포넌트간에 데이터를 비동기적으로 전달
  * backpressure를 이용한 데이터 흐름제어
  * 해당 사양을 만족하는 라이브러리 예
    * Flow, RxJava2, Reactor
* Reactive Stream의 주된 목적
  * 비동기로 데이터를 처리하는 시스템에 어느정도의 data가 들어올 지 예측가능하도록 하는것입니다. 
  * 이를 처리가능하게 BackPressure(배압) 전략을 사용
* 옵저버 패턴과의 차이점
  * Observer Pattern = Publisher -> Data Push -> Subscriber (처리는 알아서 하시고 일단 데이터를 밀음)
  * Reactive Strema = Subscriber -> Data Pull -> Publisher (처리 가능한 만큼만 데이터를 당겨옴 - 이때 사용하는것이 Backpressure 전략)
* Backpressure
  * Subscriber 와 Publisher 에게 Subscriber 가 처리가능한 데이터 개수만 요청하는것
--- 
### 다음 명세서를 따름
* 발행
  ```java
  public interface Publisher<T> {
      public void subscribe(Subscriber<? super T> s);
  }

* 구독자
  ```java
  public interface Subscriber<T> {
      public void onSubscribe(Subscription s);
      public void onNext(T t);
      public void onError(Throwable t);
      public void onComplete();
  }
* 구독
  ```java
  public interface Subscription {
      public void request(long n); // Subscriber가 Publisher에게 데이터를 요청하는 갯수
      public void cancel(); // 구독 취소
  }
