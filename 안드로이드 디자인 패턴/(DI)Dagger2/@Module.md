# @Module(모듈)
* 클래스에 @Module 애노테이션으 붙이느 것으로 간단히 모듈 클래스르 만들 수 있다.
---
### @Provides
* 모듈 클래스내 선언되는 메서드에 붙임
##### 반환형을 보고 컴포넌트 내에서 의존성이 관리되어 중복되느 타입이 하나의 컴포넌트 내에 존재하면 안된다
* ```java
  @Module
  class DuplicateModule {
      @Provides
      String provideHelloWorld() {
          return "Hello World";
      }
      
      @Provides
      String ProvideCharles() {
          return "Charles";
      }
  }
##### 컴포넌트 내 바인드된 메서드의 반환형은 @Provides 메서드의 맥 변수로 사용할수 있다.
* ```java
  @Module
  public class MyModule {
      @Provides
      String provideName() {
          return "Charles";
      }
      
      @Provides
      int provideAge() {
          return 100;
      }
      
      @Provides
      Person providePerson(String name, int age) { // 이름, 나이르 제공받아서 name = Charles, age = 100 이 된다.
          return new Person(name, age);
      }
##### @Moduelsㅇ 추상 클래스일경우
* @Provides 메서드느 static 이 되어야 한다.
* ```java
  @Module
  public abstract class MyModule {
      @Provides
      static String provideName() {
          return "Charles";
      }
  }
---
### Null의 비허용
* **@Provides는 null 반환을 기본적으로 제한한다.**
* null 을 허용학 위해서느 @Nullable 추가 <-> 의존성 주입받는 부분도 마찬가지로 @Nullable 추가
  * @Nullable이 쌍방(양쪽)다 존재해야한다.
* ```java
  @Module
  public class MyModule {
      ...
      @Provides
      @Nullable
      Integer provideInteger() {
          return null;
      }
  }
  
  @Component(modules = MyModule.class)
  public class MyComponent {
      @Nullable // <----------- 없다면 에러 발생
      Integer getInteger();
  }
---
### 상속
##### includes
* ModuleB 가 ModuleA 를 상속하는 코드
  * ModuleB를 참조할 경우 ModuleA까지 상속해 A타입의 객체도 바인딩된다.
* 마찬가지로 중복되는 타입이 존재하며 안된다.
* ```java
  @Module
  public class ModuleA {
      @Provides
      A ProvideA() {
          return new A();
      }
  }
  
  @Module(includes = ModuleA.class)
  public class ModuleB {
      @Provides
      B ProvideB() {
          return new B();
      }
  }
  

