FunctionalInterface
===
FunctionalInterface - [출처 - javaplant.tistory](https://javaplant.tistory.com/34)
---
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile30.uf.tistory.com%2Fimage%2F99D0D0425AFAF70324C1D6)
  * JDK 8 이상부터 제공해주는 인터페이스
  * 구현해야 할 추상 메서드가 하나만 정의된 인터페이스 == 람다식으로 사용가능
  * @FucntionalInterface annotation 사용
    * 인터페이스가 Function Interface인지 확실히 하기를 원할 때 사용
* 예시 1)ArithmeticOperator
  * 사칙 연산자를 표상
  * ```java
    @FunctionalInterface
    public interface ArithemticOperator {
      public int operate(int a, int b);
    }
    
    @FunctionalInterface
    public class ArithmeticCalculator {
    /**
     * 실제 계산은 ArithmeticCalculator에 위임한다.
     */
    public static int calculate(int a, int b, ArithmeticOperator operator){
        return operator.operate(a, b);
    }
    
    public class ArithmeticCalculatorTest {
    @Test
      public void testPlus{
          int firstNumber = 5;
          int secondNumber = 94;
          int result = ArithmeticCalculator.calculate(firstNumber, secondNumber, (a, b) -> a + b);
          Assert.assertEquals(firstNumber + secondNumber, result);
      } 
    }
* 수많은 예시들이 있다. - https://beomseok95.tistory.com/277 
  * 사용방법과 사용하는 때 참고
