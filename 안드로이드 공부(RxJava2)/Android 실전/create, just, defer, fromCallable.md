### [출처는 softwaree님의 티스토리](https://softwaree.tistory.com/36)
#### 생성하는것이 너무너무 헷갈려서 정리
#### 결론으로는 되도로 fromCallable 을 생성하는것이 효율적
---
### create
* create 연산자를 통하여 옵저버를 생성하고, onNext를 통하여 결과값을 방출(emit)
* 만약, 데이터 방출이 한 개 만을 처리한다면 굳이 Observable를 사용하지 않고, Single이나 Completable를 권고
* 함수
  * public static <T> Observable<T> create(ObservableOnSubscribe<T> source)
* ```java
  Observable.create((ObservableOnSubscribe<String>) emitter -> {
      emitter.onNext("Response!!!"); 
      emitter.onComplete(); 
  }) 
  .subscribeOn(Schedulers.io()) 
  .observeOn(AndroidSchedulers.mainThread()) 
  .subscribe(
    resp -> dispLog(resp),  // onNext
    thr -> dispLog(thr.getMessage()),  // onError
    () -> dispLog("Completed!!")); // onComplete
  }
---
### Just
* just가 호출이 끝난 시점까지 현재 쓰레드가 더 이상 진행되지 않고 펜딩된
  * 메인쓰레드에서 진행되는것처러 동작한다는것인ㄷ 여기다가 데이터르 받아오는데 많은 시간이 소요될경우 ANR 이 발생할수있다는것을 추정
  * 그러니까 조심해서 사용해야한다.
* ```java
  Observable.just(getHeavyData()) // 또는 Obervable.just("Hello")
    .subscribeOn(Schedulers.io())
    ...
    ..
---
### defer
* 옵져버가 구독하기전까지는 Observable를 생성하지 않는다.
* defer는 subscribe가 호출될 때 메모리에 할당
* ```java
  Observable.defer((Callable<ObservableSource<String>>) () -> Observable.just(getHeavyData()))
      .subscribeOn...
      ...
      ..
---
### fromCallable
* defer 가 가져야하느 Observable을 하나 더 생성해야하는 단점이 없다
* ```java
  Observable.fromCallable(() -> getHeavyData())
      .subscribeOn...
      ...
      ..
  
  
  
  
  
