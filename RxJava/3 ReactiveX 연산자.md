ReactiveX 연산자
===
* **연산자**가 **버전**이 높아지면서 **계속 늘어나**고 있다.
  * **하지만** 모두 **알아야** 할 **필요**가**없다**.
* **for, if, while** 키워드 **없이** **RxJava** 연산자로 비동기 프로그래밍 **주요 로직 만들**수 있다.
  * Reactive 연산자는 **언어 특성**과 크게 **상관없다**는 뜻이다.
* Reactive 연산자는 부수 효과가 없는 **순수 함수(pure function)** == "**함수라고** 하는것이 **자연**스럽다"
* |연산자|설명|
  |:---|:---|
  |**생성(Creating)** 연산자|Observable, Single 클래스 등으로 **데이터의 흐름을 만들어**내는 함수다. create(), just(), fromArray(), interval(), range(), timer(), defer() 등이 있으며 **RxJava는 생성 연산자에서 시작**한다.|
  |**변환(Transforming)** 연산자|어떤 입력을 받아서 원하는 출력 결과를 내는 **전통적인 의미의 함수**로 map(), flatMap() 등이 있다.|
  |**필터(Filter)** 연산자|입력 데이터 중에 **원하는 데이터만 걸러**내며 filter(), first(), take() 등이 있다.|
  |**합성(Combining)** 연산자|생성, 변환, 필터 연산자가 주로 단일 Observable을 다룬다면 합성 연산자는 여러 Observable을 조합하는 역할을 한다. **한 개 뿐만 아니라 여러개의 Observalbe을 생성 조합**하는것이 Rxjava의 묘미다.|
  |**오류 처리(Error Handling)** 연산자|**onErrorReturn(), onErrorResumeNext(), retry()** 등이 있다.|
  |**유틸리티(Utility)** 연산자|subscribeOn(), observeOn() 등이 있고 **비동기 프로그래밍을 지원**한다.
  |**조건(Conditional)** 연산자|**Observable 흐름 제어**역할을 한다.|
  |**수학과 집합형(Mathematical and Aggregate)** 연산자|**수학** 함수와 연관 있는 **연산자**다.|
  |**배압(Back pressure)** 연산자|**배압 이슈 대응 연산자**다.|
  
