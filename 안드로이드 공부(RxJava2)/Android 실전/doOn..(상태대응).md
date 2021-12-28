# doOn
### doOn 으로 시작하는것들에 대하여
* 중간에 확인하기위함이지 결과적으로 값에 영향을 미치지는 않는다.
  * 디버깅을 하기위함
* 또는 side-effect 를 위함
* doOn~~ 은 해당 상태일때 콜백을 등록할수 있다.
* 예)
  ```java
  Observable<Integer> source = Observable.just(1)
      .doOnUnsubscribe( () -> System.out.println("doOnSubscrbie"));
      
  source.subscrbie(
    { i -> System.out.println("Next " + i)   },
    { e -> System.err.println("Error: " + e) },
    { () -> System.out.println("Completed")  }
  )
  
  // 결과
  doOnSubscribe
  Next: 0
  Completed
---
##### doOnSubscribe
* 옵저버들이 구독할때 상태 처리 
##### doOnUnscribe
* 옵저버들이 구독 해제할때 상태 처리
##### doOnNext
* 아이템을 발행할때 상태 처리
* doOnNext() allow you to see what is going on into the Observable (often really long) chains 
##### doOnComplete
* 아이템 발행 완료되었을때 상태 처리
##### doOnError
* 아이템 발행중 에러가 발생했을때 상태 처리
##### doOnEach
* onNext, onError, onComplete 에서 상태 처리
##### doOnTerminate
* onError, onComplete 에서 상태 처리
##### doAfterTerminate
* onNext, onError 에서 상태 처리

