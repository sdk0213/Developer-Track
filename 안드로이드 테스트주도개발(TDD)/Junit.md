# Junit
---
### 정의
* JUnit은 자바용 단위 테스트 도구
---
### 버전
* |비교|JUnit4|JUnit5|
  |:--:|:--:|:--:|
  |required java version|JDK 5|JDK 8(Android Gradle 3.2.0 Gradle 4.7|
  |무시 annotation|@Ignore|@Disable|
  |태킹 annotation|@Category|@Tag|
  |초기화 annotation|@Before|@BeforeEach|
  |1번 초기화 annotation|[@BeforeClass](https://junit.org/junit4/javadoc/4.12/org/junit/BeforeClass.html)|@BeforeAll|
  |결과 annotation|@After|@AfterEach|
  |결과로 인한 초기화 annotation|[@AfterClass](https://junit.org/junit4/javadoc/4.12/org/junit/AfterClass.html)|@AfterAll|
---
### 기본 사용 함수
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
  
  public class Calculator { 
      int a, b;

      public Calculator() { }
      public Calculator(int b, int a) {
      ...
      // 대충 사칙연산 해주는 클래스(이하 생략)
      ... 
  }
* ```java
  import android.support.test.runner.AndroidJUnit4;
  import android.test.suitebuilder.annotation.SmallTest;
  import org.junit.Before; 
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import static org.hamcrest.CoreMatchers.is;
  import static org.junit.Assert.assertThat;

  @RunWith(AndroidJUnit4.class) // 특정 라이브러리 사용
  @SmallTest 
  public class TestSample {
    private Calculator calculator;

    @Before // 테스트 수행전 수행되는 부분
    public void setUp() { 
      calculator = new Calculator(); 
    } 
   
    @Test // 테스트
    public void test() { 
      int result = calculator.add(12, 11);
      assertThat(result, is(20)); 
    } 
   }
### 테스트 수행하기
  * 메뉴 -> Run -> Edit Configurations -> '+'버튼 -> Android Test -> 테스트의 이름을 작성 -> Module을 app선택 -> Test는 Class 선택 -> Class는 테스트 클래스인 TestSample을 선택 -> instrumentation runner는 gradle에서 설정한 AndroidJUnitRunner를 선택 -> **Run(실행)**
  * 예상값과 다르다면 에러 아니면 초록색으로 성공표시됨

