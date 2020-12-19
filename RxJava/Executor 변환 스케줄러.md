Executor 변환 스케줄러
===
* 자바에서 제공하는 실행자(Executor - java.util.current 패키지에 존재)를 변환하여 스케줄러를 생성할수 있고 이를 변환하여 RxJava에서 사용가능하다.
  * **하지만 Executor 클래스를 재사용할 때만 한정적으로 활용하고 이것 외에는 스케줄러와 동작방식이 다르므로 추천하지 않는다.**
* ```java
  final int THREAD_NUM = 10;
  
  String[] data = {"1", "3", "5"};
  Observable<String> source = Observable.fromArray(data);
  Executor executor = Executors.newFixedThreadPool(THREAD_NUM);
  
  source.subscribeOn(Schedulers.from(executor))
    .subscribe(Log::i);
  source.subscribeOn(Schedulers.from(executor))
    .subscribe(Log::i);
  CommonUtils.sleep(500);
  
  // result:
  // 2020-12-19 15:29:11.516 2273-3152/com.study.rxandroid I/System.out: pool-1-thread-1 | value = 1
  // 2020-12-19 15:29:11.516 2273-3152/com.study.rxandroid I/System.out: pool-1-thread-1 | value = 3
  // 2020-12-19 15:29:11.516 2273-3152/com.study.rxandroid I/System.out: pool-1-thread-1 | value = 5
  // 2020-12-19 15:29:11.517 2273-3153/com.study.rxandroid I/System.out: pool-1-thread-2 | value = 1
  // 2020-12-19 15:29:11.517 2273-3153/com.study.rxandroid I/System.out: pool-1-thread-2 | value = 3
  // 2020-12-19 15:29:11.517 2273-3153/com.study.rxandroid I/System.out: pool-1-thread-2 | value = 5
  
* newFixedThreadPool 이 아닌 newSingleThreadExecutor() 였다면 실행결과는 1개의 스레드에서 전부 실행되었을것이다.
