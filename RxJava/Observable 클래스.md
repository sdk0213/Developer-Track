Observable
===
* **Observable은 현재는 관찰되지 않았 지만 이론을 통해서 앞으로 관찰할 가능성을 의미하며 RxJava에서 가장 중요한것이다.**
* Observable은 데이터 흐름에 맞게 알림을 보내 구독자가 데이터를 처리할 수 있도록 해준다.
* 라이프사이클이 없다.
* **상태 변화가 있을 때마다 메서드를 호출**하여 객체가직접 목록의 각 **옵서버에게 변화를 알려줌**
* Rxjava 2.x 부터 세개의 클래스로 세분화되었다.
  * Observable
  * Maybe
  * Flowable
* ![](img/observableclassification.png)
* 세가지 알리미
  * onNext
    * 데이터 발행을 알림
  * onComplete
    * 모든 데이터 발행 완료
  * onError
    * Error 발생 Observable 종료
    
* |Factory Fuction|Function|
  |---------------|--------|
  |Base In RxJava 1.x|create(), from(), just()|
  |Added In RxJava 2.x|fromArray(), fromIterable(), fromCallable(), fromFucture(), fromPublisher()|
  |etc..|Interval(), range(), timer(), defer() ..|
  
  
* just()
  * MarbleDiagram
    * ![](img/marble_Just.png);
