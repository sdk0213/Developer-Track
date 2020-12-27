리플렉션(reflection) - [출처 madplay.github](https://madplay.github.io/post/java-reflection)
===
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
* 
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
    
