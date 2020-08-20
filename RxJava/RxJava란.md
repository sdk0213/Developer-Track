RxJava의 개념
===
* 명령형 프로그래밍
  * 기존에 우리가 아는 프로그래밍 기법으로 값이 변경되었다는 통보를 받고 새로 계산하는 방식이다.
* 리엑티브 프로그래밍
  * 변경된값을 직접 던져주는것 -> 일종의 Observer 패턴이라고 볼수있다.
  * 내가 어떤 기능을 실행하는것이 아니다. 다만 이벤트가 발생했을때를 처리하는 것이다.
  * 부수효과(sideEffect)가 존재하지 않는다.
    * Callback 또는 Observer Pattern이 ThreadSafe하지 않는 이유는 여러 스레드가 경쟁조건에 빠지기 때문이다.
    * **하지만 리엑티브 프로그래밍은 이러한 sideEffect가 존재하지 않는다.**
* 원론적인 이야기
  * wiki
    * Reactive programs also maintain a continuous interaction with their environment, but at a speed which is determined by the environment, not the program itself. Interactive programs work at their own pace and mostly deal with communication, while reactive programs only work in respond to external demands and mostly deal with accurate interrupt handling.(리엑티브 프로그램은 주변의 환경과 끊임없이 상호작용하며 환경이 변하면 이벤트를 받아 동작하고 외부요구에 반응에 맞춰 일하고 대부분 정확한 인터럽트를 담당한다.)
    * RxJava와 같은 리액티브 프로그래밍을 하려면 누군가 리액티브 프로그래밍을 할 수 있는기 반 시설을 제공해주어야 합니다.즉, 데이스 소스를 정의할 수 있고 그것의 변경 사항을 받아서 내 프로그램에 알려줄push 존재가 필요합니다. **그것을 .NET 환경에서는 리액티브 확장Reactive Extentions이라고 하고JVM 위의 자바 언어로 구현해놓은 라이브러리가 RxJava입니다.**

RxJava 탄생
===
* 2013년 2월 Netflix의 기술블로그에 처음으로 소개되었다. RxJava를 만든이유를 다음과 같이 **세가지**를 뽑았다.
  * 동시성을 적극적으로 끌어안을 필요가있다(Embrace Concurrency).
    * Java는 동시성(Thread) 처리에 번거로움이 크다 --> **클라이언트의 요청을 처리할 때 다수의 비동기 실행 흐름(스레드 등)을 생성하고 그것의 결과를 취합하여 최종 리턴하는 방식으로 내부 로직을 변경**
  * 자바Future를 조합하기 어렵다는점을 해결해야 한다(Java Futures are Expensive to Compose).
    * 2013년 당시 자바 8에서 제공하는 CompletableFuture 같은 클 래스가 제공되지 않았기 때문이다. --> 비동기 흐름을 조합compose할 수 있는 방법을 제공 --> **리액티브 연산자Operators**
  * 콜백방식의 문제점을 개선해야 한다(Callbacks Have Their Own Problems).
    * 콜백 지옥Callback Hell 상황이 코드의 가독성을 떨어 뜨리고 문제 발생 시 디버깅을 어렵게 만들기 때문이다. --> **RxJava는 콜백을 사용하지 않는 방향으로 설계**
