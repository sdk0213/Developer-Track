# Dagger - [출처는 maryangmin님의 medium](https://medium.com/@maryangmin/di-기본개념부터-사용법까지-dagger2-시작하기-3332bb93b4b9)
### Dagger 란?
* Dagger1 -> Sqaure 에서 제작
* Dagger2 -> Google 에서 관리
* 자동으로 종속 항목을 삽입해준다.
  * --> 보일러코드가 줄어든다.
* Annotation Processing으로 reflection 사용없이 컴파일 타임에 코드를 생성하기 때문에 빠르다.
* 런타임에 바이트코드를 생성하지 않는다.
##### 장점
* 자원 공유의 단순화(지정된 범위의 생명 주기 내에서 동일 인스턴스를 제공)
* 복잡한 의존성을 단순하게 설정
* 유닛 테스트를 도움
* 자동 코드 생성 -> 생성된 코드는 디버깅 가능
* Dagger2 는 Dagger1 에서 발생했던 난독화문제가 없다.
* 라이브러리 크기가 작다
* [여기에서](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20디자인%20패턴/(DI)종속%20항목%20삽입.md) DI 안드로이드 코드를 작성시 발생하는 다음과 같은 문제를 해결해준다.
  * AppContainer 자동생성
  * Factory 클래스 생성
  * 설정에 따라 의존성을 재활용할건지 새로만들것인지 결정할수있다.
  * 특정기능을 위한 Cotainer를 사용후 메모리에서 해제할수 있다.
---
### HelloWorld 예제
* ```java
  @Module // 의존성을 제공하는 클래스에 붙인다
  public class MyModule {
      @Provides // 의존성 주입에 필요한 파일들을 생성
      String provideHelloWorld() {
          return "Hello World!";
      }
  }

  // 위의 코드만으로는 클래스 파일이 생성하지 않는다. Component가 있어야 한다.
  @Component(modules = MyModule.class) // 참조된 Module 클래스로부터 의존성을 제공받는다
  public interface MyComponent {
      String getString(); // 컴포넌트의 메서드의 반환형을 보고 모듈과 관계를 맺으므로 해당 반환형을 갖는 메서드
  }
* Dagger는 컴파일 타임에 @Component를 구현한 클래스를 생성한다. 이때 Dagger라는 접두사가 붙는다.
  * MyComponent --> DaggerMyComponent
##### 테스트코드 작성 및 결과
* ```java 
  pulbic class ExampleUnitTest {
      @Test
      public void testHelloWorld(){
          MyComponent mycomponent = DaggerMyComponent.create();
          System.out.println("result = " + mycomponent.getString());
      }
  }
  
  // result = Hello World
---
### Module
##### Inject @Inject
* 의존성 주입을 요청한다.
##### @Provides
* 의존성을 제공하는 메서드에 붙인다
##### @Module
* 의존성을 제공하는 클래스에 붙인다.
* Component에 연결되어 의존성 객체를 생성합니다. 생성 후 Scope에 따라 관리
##### @Component
* 연결된 Module을 이용하여 의존성 객체를 생성하고 Inject로 요청받은 인스턴스에 생성한 객체를 주입한다.
##### SubComponent
* Component는 계층관계를 만든다. Dagger의 중요한 컨셉인 그래프를 형상한다. 의존성을 검색후에 없으면 부모로 올라가면서 검색하는 방식으로 작동한다.
##### Scope
* 객체의 LifeCycle 범위이다. 안드로이드의 Activitiy, Fragment와 맞추어 사용한다.
* Module은 이 Scope를 보고 객체를 관리한다.
---
### Flow
##### @Inject -> SubComponet -> Module -> Scope에 있으면 return
##### Subcomponet에서 Module에서 맞는 타입을 못찾을경우는 상위 Componet -> Module -> Scope에 있으면 return
---
