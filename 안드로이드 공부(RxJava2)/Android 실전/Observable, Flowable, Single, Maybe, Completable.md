### Observable
* 세 가지 상태를 구독자에게 전달
  * onNext
  * onComplete
  * onError
* Complete 또는 Error 후에는 자동으로 dispose 된다. 
### Flowable
* Observable 처럼 대량의 데이터를 다루나 1000개이상의 데이터를 다룰때 사용
* 배압이라는 개념이 포함되어있음
* Reactive Stream 으로 Reactive Stream Interface 를 포함한다.
  * ```java
    public abstract class Flowable<T> implements Publisher<T> {
        ...
        ..
    }
    
    ...
    ..
    package org.reactivestreams;
    
    public interface Publisher<T> {
        public void subscribe(Subscriber<? super T> s);
    }
---
### 아래는 Observable, Flowable 과 다르게 데이터를 최대 1건만 발행하는 생산자들이다.
### Single
* 1개 데이터
* 두 가지 상태를 구독자에게 전달
  * onSuccess
  * onError
---
### Maybe
* 0 또는 1개 데이터
* 데이터 개수빼고 나머지는 Observable과 동일
---
### Completable
* 그냥 백그라운드 사용이 필요할때(ex) room) 에서 사용
* 리턴값이 없음
* 두 가지 상태를 구독자에게 전달
  * onComplete
  * onError
