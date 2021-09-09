# doOn
### doOn 으로 시작하는것들에 대하여
* doOn~~ 은 해당 상태일때 콜백을 등록할수 있다.
* 예)
  ```java
  Observable<Integer> source = Observable.just(1)
      .subscribeOn( () -> System.out.println("doOnSubscrbie"));
      
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
### doOnSubscribe
* 옵저버들이 구독할때 상태 처리 
