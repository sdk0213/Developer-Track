리플렉션(reflection) - [출처 madplay.github](https://madplay.github.io/post/java-reflection)
===
* 사용하게 되는 이유
  * 다음처럼 최상위 클래스인 Object에 담을수는 있지만 사용은 불가능하다. 왜냐하면 Object의 메서드와 변수만 사용가능하기 때문이다. 그러니까 car의메 메서드는 사용하지 못한다.
  * .java
    ```java
    public class Car{
      public void drive(){
        //do Something
      }
    }
  
    public class Main{
      public static void main(String[] args){
        Object car = new Car();
        car.drive(); // 컴파일 에러
      }
    }
  * 그런데 위의 상황같이 구체적인 타입의 클래스를 모를때 사용하는것이 리플랙션이다.
  * 그런데 자기가 작성하는 코드인데 어떻게 모를수 있을까?
    * 코드를 작성할 시점에는 어떤 타입인지 모를때가 있을수 있음 그런데 런타임에서 지금 실행되고 있는 클래스를 가져와야됨
> 리플렉션(reflection)은 구체적인 클래스 타입을 알지 못해도, 그 클래스의 메소드, 타입, 변수들을 접근할 수 있도록 해주는 Java PI
* **즉, 작성 시점에는 어떤 클래스를 사용해야 할지 모를때 사용**
  * **Heap Memory** 영역에 올라가있는 Class 정보를 통해 가져온다.
  * 컴파일 시간(Compile Time)이 아니라 실행 시간(Run Time)에 동적으로 특정 클래스의 정보를 객체화를 통해 분석 및 추출해낼 수 있는 프로그래밍 기법이라고 표현할 수 있다.
* .java
  ```java
  import java.lang.reflect.Method; // 리플렉션의 Method 사용하기

  public class MadPlay {

    public void reflectionTest() {
        try {
            Class vectorClass = Class.forName("java.util.Vector");

            Method[] methods = vectorClass.getDeclaredMethods();

            for (Method method : methods) {
                System.out.println(method.toString());
            }

        } catch (ClassNotFoundException e) {
            // Exception Handling
        }
    }

    public static void main(String[] args) {
        new MadPlay().reflectionTest();
    }
  }
* 메서드 이름으로 호출
  * .java
    ```java
    class MadLife {
      public void sayHello(String name) {
        System.out.println("Hello, " + name);
      }
    }

    public class MadPlay {

      public void reflectionTest() {

        try {
            Class myClass = Class.forName("MadLife");
            Method method = myClass.getMethod("sayHello", new Class[]{String.class}); //sayHello 호출
            method.invoke(myClass.newInstance(), new Object[]{new String("Kimtaeng")});
    ...
    ..
    .
* private도 Field.setAccessible() 사용으로 접근이 가능하기 때문에 주의해야한다.
---   
# 내용추가 (21/03/17)
### Reflection - [출처는 codechacha](https://codechacha.com/ko/reflection/)
* 자바의 reflection은 클래스, 인터페이스, 메서드들을 찾을 수 있고, 객체를 생성하고 변경 그리고 메서드를 호출할수 있다.
* 자바에서 기본으로 제공해주는 API 이다.
* Hidden Method(숨겨진 함수)를 호출할 때 Reflection을 사용할 수 있다.
---
### 예제코드
* ```java
  package test;

  public class Parent {
      private String str1 = "1";
      public String str2 = "2";

      public Parent() {
      }
  
      private void method1() {
          System.out.println("method1");
      }

      public void method2(int n) {
          System.out.println("method2: " + n);
      }

      private void method3() {
          System.out.println("method3");
      }
  }
* ```java
  package test;

  public class Child extends Parent {
      public String cstr1 = "1";
      private String cstr2 = "2";

      public Child() {
      }

      private Child(String str) {
          cstr1 = str;
      }

      public int method4(int n) {
          System.out.println("method4: " + n);
          return n;
      }

      private int method5(int n) {
        System.out.println("method5: " + n);
          return n;
      }
  }
---
### Class(클래스) 찾기
* ```java
  Class clazz = Child.class;
  Sytem.out.println("Class name: " + clazz.getName());
  // 출력결과:
  // Class name: test.Child
  
  // 만약에 클래스를 참조할수 없다면??? 다음과 같이 사용
  Class clazz2 = Class.forName("test.Child"); // 참고로 Child 클래스는 test패키지에 있음
  System.out.println("Class name: " + clazz2.getName());
  
  // 출력결과:
  // Class name: test.Child
---
### Constructor(생성자) 찾기
##### .getDeclaredConstructor()
* 인자 없는 생성자 가져오기
* ```java
  Class clazz = Class.forName("test.Child");
  Constructor constructor = clazz.getDeclaredConstructor();
  System.out.println("Constructor: " + constructor.getName()); 
  
  // result:
  // Constructor: test.Child
##### .getDeclaredConstructor(Param)
* Param의 타입과 일치하는 생성자를 찾는다.
* ```java
  Class clazz = Class.forName("test.Child");
  Constructor constructor2 = clazz.getDeclaredConstructor(String.class);
  System.out.println("Constructor(String): " + constructor2.getName());
  
  // result:
  // Constructor(String): test.Child
##### .getDeclaredConstructors()
* 모든 생성자를 가져오기
* ```java
  Class clazz = Class.forName("test.Child");
  Constructor constructors[] = clazz.getDeclaredConstructors();
  for (Constructor cons : constructors) {
      System.out.println("Get constructors in Child: " + cons);
  }
  
  // result:
  // Get constructors in Child: private test.Child(java.lang.String)
  // Get constructors in Child: public test.Child()
##### .getConstructors()
* public 생성자만 리턴
* ```java
  Constructor constructors2[] = clazz.getConstructors();
  for (Constructor cons : constructors2) {
      System.out.println("Get public constructors in Child: " + cons);
  }
  
  // result:
  // Get public constructors in both Parent and Child: public test.Child()
---
# [(클릭)reflection에서 더 알고싶은 아래 정보는 codechacha님의 블로그를 들어가서 추가적으로 확인하기(클릭)](https://codechacha.com/ko/reflection/)
### Method(메서드) 찾기
### Filed(변수) 찾기
### Method 호출
### Filed(변수) 변경
### Static Method 호출 또는 필드 변경
