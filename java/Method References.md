Method References
===
메서드 참조(Method References)
* 필요성
  * 함수형 프로그래밍의 지지자들은 함수가 여러가지 방식으로 조합될 수 있는 이런 스타일을 선호
  * 메소드를 참조해서 매개변수 리턴타입을 알아내어 람다식에서 불필요한 매개변수를 제거하는 것이 목적
  * 
* 람다식이 하나의 메서드만 호출하는 경우에 사용가능하다.
  * 참조할때 소괄호 '()'는 사용하지 않는다.
  * 예시1)
    ```java
    Function<String, Integer> f = (String s) -> Integer.parseInt(s);
    // ▽▽▽                        *생략(s)        *생략(Integer.)
    Function<String, Integer> f = Integer::parseInt; // 좌변의 Function인터페이스에 지정된 지네릭 타입으로부터 쉽게 알아낼 수 있다.
  * 예시2) 
    ```java
    BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);
    // ▽▽▽                                 *생략(s1,s2)     *생략(s1,s2)
    BiFunction<String, String, Boolean> f = String::equals;
  * 예시3)
    ```java
    // 이미 생성된 객체의 메서드를 람다식에서 사용한 경우에는 클래스 이름 대신 그 객체의 참조변수를 적어주어야 한다.
    MyClass obj = new MyClass();
 
    Function<String, Boolean> f = (x) -> obj.equals(x);    // 람다식
    Function<String, Boolean> f2 = obj::equals;    // 메서드 참조
  * 예시4)
    ```java
    String::new
    () -> new String() // 위 코드와 같은 의미
* 형태 세가지
  * 클래스::인스턴스메소드 (public)
    * ```java
      String::compareToIgnoreCase는 (x, y) -> x.compareToIgnoreCase(y)
  * 클래스::정적메소드 (static)
    * ```java
      Object::isNull은 x -> Object.isNull(x)
  * 객체::인스턴스메소드 (new)
    * ```java
      System.out::println은 x -> System.out.println(x)
