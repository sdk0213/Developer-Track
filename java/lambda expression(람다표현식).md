lambda expression
===
lambda expression
---
* 람다 표현식
* **람다 함수는 프로그래밍 언어에서 사용되는 개념으로 익명 함수(Anonymous functions)를 지칭하는 용어이다.**
* 클래스를 작성하고 객체를 생성하지 않아도 메소드를 사용할 수 있음
* 메서드
  * **메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없어지므로 람다식을 익명함수(anonymous function)라고도 한다.**
  * [코드출처 - ryan-han.com](https://ryan-han.com/post/java/java-lambda/)
  * ```java
    //기존
    int max(int a, int b) {
        return a > b ? a : b;
    }
    // ▽ 변신 ▽
    //람다식
    (int a, int b) -> {
        return a > b ? a : b;
    }

    // ▽ 변신 ▽
    //return문 대신 expression 사용
    (int a, int b) -> a > b ? a: b
  
    // ▽ 변신 ▽
    //매개변수 타입 생략
    (a, b) -> a > b ? a : b
  * **매개변수 한개일경우**
    * ```java
      //매개변수 1개일 경우 괄호 생략
      a -> a*a     //OK
      int a -> a*a //에러
      
      //본문 문장 1개일 경우 중괄호 생략
      (String name, int i) -> System.out.println(name+"="+i)
     
  * 아래코드처럼 가능한 이유는 ** 람다식도 실제로는 익명 객체이기 때문이다. **
  * ```java
    // 람다식으로 구현하기
    // 기존 인터페이스의 메서드 구현
    List<String> list = Arrays.asList("abc", "aaa", "bbb", "ccc");
    Collections.sort(list, new Comparator<String>() {
    public int compare(String s1, String s2)  {
        return s2.compareTo(s1);
    }
    });

    // 람다식으로 구현
    List<String> list = Arrays.asList("abc", "aaa", "bbb", "ccc");
    Collections.sort(list, (s1, s2) -> s2.compareTo(s1));
  
* Java8에서 함수형 인터페이스(단, 하나의 메서드만이 선언된 인터페이스로 Java8에서 도입되었음)의 경우, 람다식으로 표현이 가능할 수 있게 제공되었음
* ```java
  (int a, int b) -> {return a + b} // 매개변수 -> 함수 로직 (+@ 리턴)
* 차이점
  * ```java
    new Thread(new Runnable() { 
       public void run() {
          System.out.println("Annoymous Thread");
       }
    }).start();
  * 가 다음과 같이 간편하다.
  * ```java
    new Thread(()->System.out.println("Lambda Thread")).start();
* **쉽게 말해서 interface를 구현해야 하는부분에서 매개변수랑 구현부분만 작성해야되는것이 간편하기 때문에 쓴다.**
* 모든 람다 예시
  ```Java
  () -> {} // No parameters; result is void
  () -> 42 // No parameters, expression body
  () -> null // No parameters, expression body
  () -> { return 42; } // No parameters, block body with return
  () -> { System.gc(); } // No parameters, void block body
  () -> { 
    if (true) return 12; 
    else {
      int result = 15;
      for (int i = 1; i < 10; i++) 
        result *= i; return result;
    }
  } // Complex block body with returns 
  (int x) -> x+1 // Single declared-type parameter 
  (int x) -> { return x+1; } // Single declared-type parameter 
  (x) -> x+1 // Single inferred-type parameter 
  x -> x+1 // Parens optional for single inferred-type case 
  (String s) -> s.length() // Single declared-type parameter 
  (Thread t) -> { t.start(); } // Single declared-type parameter 
  s -> s.length() // Single inferred-type parameter 
  t -> { t.start(); } // Single inferred-type parameter 
  (int x, int y) -> x+y // Multiple declared-type parameters 
  (x,y) -> x+y // Multiple inferred-type parameters 
  (final int x) -> x+1 // Modified declared-type parameter 
  (x, final y) -> x+y // Illegal: can't modify inferred-type parameters 
  (x, int y) -> x+y // Illegal: can't mix inferred and declared types
