ConnectableObservable
===
* Subject 클래스처럼 **차가운 Observable -> 뜨거운 Observable 로 변환**
* **데이터 하나를 여러 구독자에게 동시에 전달할 때 사용**
* **connect()** 함수를 **호출**한 **시점**부터 **subscriber()** 함수를 호출한 **구독자**에게 **발행**
* ConnectableObservable 객체 **생성**을 위해서는 **publish()** 함수를 호출해야 한다.
  * publish()
    * 데이터 발행을 유예하는 역할
* marblediagram(ConnectableObservable.publish())
  * ![](img/marblediagram_connectableobservable.png)

