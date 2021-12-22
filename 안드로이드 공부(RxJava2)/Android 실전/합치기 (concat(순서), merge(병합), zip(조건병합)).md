[출처는 남갯,YTS의 개발,일상블로그](https://namget.tistory.com/entry/RxKotlinRxJava-merge-zip-알아보기)
### concat
* 첫번째 Observable을 발행 한 뒤 두번째 Observable을 발행
* 순서 보장된다.
* 두번째거를 먼저 구독하여도 첫번째것이 완료되지 않으면 발행되지 않음
  * **그래서 이는 메모리 누수 가능성이 있음**
* ```kotlin
  val test1 = Observable.just("1", "2", "3")
  val test2 = Observable.just("apple", "banana", "car")

  disposable.add(Observable.concat(test1, test2).subscribe({
  ..
  }
##### concatWith
* ```kotlin
  Observable<String> mySource1 = Observable.just("a", "b", "c");
        Observable<String> mySource2 = Observable.just("x", "y", "z");
        mySource1.subscribeOn(Schedulers.newThread())
                 .concatWith(mySource2)
                 .subscribe(System.out::println);
  
---
### merge
* 단순히 합친다.
* A가 발행하는것과 B가 발행하는것을 전부다 합쳐서 결과물로 내어준다.
* ```kotlin
  val test1 = Observable.just("1", "2", "3")
  val test2 = Observable.just("apple", "banana", "car")

  disposable.add(Observable.merge(test1, test2).subscribe({
  ...
  ..
---
### zip
* 여러개의 Observable 을 합쳐서 전송
* 두 개가 발행될경우 무조건 두개를 합쳐서 보낸다. **만약에 발행되는 시간이 서로 차이가 나도 무조거 합쳐질수 있는 타이밍에 합쳐서 보내진다.**
* A,B 의 Observable 을 합칠경우 A가 B보다 늦게 발행된다면 A의 시간에 맞추어서 합쳐서 발행이된다.
* ```kotlin
  val test1 = Observable.just("1", "2", "3").delay(2, TimeUnit.SECONDS)
  val test2 = Observable.just("apple", "banana", "car")
  val test3 = Observable.interval(2, TimeUnit.SECONDS)

  Observable.zip(test3, test2, BiFunction { t1: Long, t2: String -> t1.toString() + t2 })
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
          Log.e("zip observable ${System.currentTimeMillis()}", it)
      }, {

      }, {
          Log.e("zip observable ", "complete")
      })
