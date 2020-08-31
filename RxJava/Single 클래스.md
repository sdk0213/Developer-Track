Single
===
* 데이터 무한 발행 가능한 Observable클래스와 다르게 **오직 1개의 데이터만 발행**하도록 한정된다.
* MarbleDiagram
  * ![](img/marblediagram_single.png)
* 발행과 동시에 종료(onSuccess)
  * {onNext(), onComplete()} -> onSucees로 통합
  * 그래서 라이프사이클이 onSuccess(T value)와 onError()로 구성

just()
---
