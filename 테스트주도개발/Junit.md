Junit
===
> 정의
* JUnit은 자바용 단위 테스트 도구
> 기본 사용 함수
* assertArrayEquals(a, b); 
  * 배열 A와 B가 일치함을 확인
* assertEquals(a, b);
  * 객체 A와 B가 일치함을 확인
* assertEquals(a, b, c);
  * 객체 A와 B가 일치함을 확인
  * a: 예상값, b:결과값, c: 오차범위
* assertSame(a, b); 
  * 객체 A와 B가 같은 객임을 확인
* assertTrue(a); 
  * 조건 A가 참인가를 확인
* assertNotNull(a);
  * 객체 A가 null이 아님을 확인
> 어노테션
* @Test(timeout=5000)
  * 시간단위 : 밀리초
* @Test(expected=RuntimeException.class)
  * RuntimeException이 발생해야 성공
* @Ignore(value=”test”)
* @Before 
  * 해당 테스트 클래스의 객체를 초기화하는 작업
* @After
  * 해당 테스트  실행 수 실행
* @BeforeClass
  * 테스트 클래스 실행 전 한번 실행
* @AfterClass
  * 테스트 클래스 실행 후 한번 실행
> 간단한 예
* ```java
  package test.junit;
  
  public class Calculator {
   
    public int sum(int num1, int num2 ) {
      return num1+num2;
    }
 
  }
