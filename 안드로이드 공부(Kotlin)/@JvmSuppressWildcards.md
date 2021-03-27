### @JvmSuppressWildcards - [출처는 jaejong님의 tistory](https://jaejong.tistory.com/148)
* 코틀린은 기본적으로 Generic을 선언하고 **자바에서 이 클래스를 상속하면 extends T 로 변환**한다.(? extends를 명시하지 않더라도)
* 이를 방지하기 위해서 나온 annotation 이다.
* ```kotlin
  abstract class WildcardTest<T> {

      // @JvmSuprressWildcards 주석을 사용하지 않은 order1 메서드
      abstract fun order1(orderList: List<T>)
    
      // @JvmSuprressWildcards 주석을 사용한 order2 메서드
      abstract fun order2(orderList: List<@JvmSuprressWildcards T>)
  }
##### bytecode 변환시
* ```java
  public class WildcardTestImpl extends WildcardTest<Coffee> {

      // 사용하지 않은 order1 메서드
      @Override
      public void order1(@NotNull List<? extends Coffee> orderList) {	// WildCard가 자동 설정됨
      }
    
      // @JvmSuppressWildcards 사용한 order2 메소드
      @Override
      public void order2(@NotNull List<Coffee> orderList) {
      }
  }
