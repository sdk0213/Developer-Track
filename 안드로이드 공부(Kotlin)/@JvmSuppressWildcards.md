### @JvmSuppressWildcards
* 코틀린은 기본적으로 Generic을 선언하고 **자바에서 이 클래스를 상속하면 extends T 로 변환**한다.(? extends를 명시하지 않더라도)
* 이를 방지하기 위해서 나온 annotation 이다.
* ```kotlin
  abstract class Hello<T>{
      abstract fun hello(orderList: List<T>)
      abstract fun hello2(orderList: List<@JvmSuppressWildcards T>)
  }
##### java 클래스에서 상속을 할경우
* ```java
  public class B extends Hello<String>{

      @Override
      public void hello(@NotNull List<? extends String> orderList) {

      }

      @Override
      public void hello2(@NotNull List<String> orderList) {

      }
  }
* 상속을 받을경에 @JvmSuppressWildcards까지도 java에서 상속을 해버리는데 이는 곧 에러메시지로 삭제를 유도한다. 왜 이러는지 잘 모르겠다.
