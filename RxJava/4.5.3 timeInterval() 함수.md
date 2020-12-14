timeInterval() 함수
===
* 값을 발행후 이전 값을 발행한 이후 얼마나 시간이 흘렀는지 알려준다.
* marblediagram
  ![](img/marblediagram_timeinterval.png)
* @SchedulerSupport(SchedulerSupport.NONE)
  public final Observable<Timed<T>> timeInterval()
  public T value()
  public TimeUnit unit()
  public long time()
  public long time(TimeUnit unit)
  
* ```java
  String[] data = {"1", "3", "7"};
  
  CommonUtils.exampleStart():
  Observable<Timed<String>> source = Observable.fromArray(data)
    .delay(item ->
        CommonUtils.doSomething();
        return Observable.just(item);
    })
    .timeInterval();
    
  source.subscribe(Log::it);
  CommonUtils.sleep(1000);
