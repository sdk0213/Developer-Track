# @Component (컴포넌트)
* 바인딩된 모듈로부터 오브젝트 그래프를 생성하는 핵심적이 역할을 한다.
* 최소한 하나의 추상적인 메서드인 abstract, Interface 에만 붙힐수 있다.
* Dagger + @Component 클래스 이름으로 생성된다.
* 메서드 매개 변수오 반환형으 규칙으 엄격하게 따라야 한다.
---
### 오브젝트 그래프
* Component/Module/Object 의 관계를 컨터에너 또는 오브젝트 그래프라고 한다.
* ```java
  ////////Component///////
  /                      /
  /   /////Module/////   /
  /   /              /   /
  /   /   /Object/   /   /
  /   /   /      /   /   /
  /   /   ////////   /   /
  /   /              /   /
  /   ////////////////   /
  /                      /
  ////////////////////////
---
### Provision methods(프로비저 메서드)
* **매개변수가 없는** 반환형은 모듈로부터 제공되건 주입되는 메서드
* **getSomeType() 메서드를 호출하면 SomeModule로부터 제공받거나 주입된 객체를 반환**
* ```java
  @Component(modules = SomeModules.class)
  public interface SomeComponet {
      SomeType getSomeType();
  }
### Member-injection methods(멤버-인젝션 메서드)
* 맴버를 인젝션(주입)하는 메서드
* **하나의 매개변수를 갖는 메서드**
  * void 반환하거나
  * 빌더 패턴(메서드 체이닝)이 가능한 메서드를 만들기 위해서
  * ```java
    @Component(modules = SomeModule.class)
    public interface SomeComponent {
        void injectSomeType(SomeType someType);
        SomeType inejctAndReturnSomeType(SomeType someType);
    }
##### Hello World 예제
* <img width="458" alt="의존성 주입" src="https://user-images.githubusercontent.com/51182964/111732480-ea3a9580-88b8-11eb-818d-1ed1b813e8b1.png">
##### 확인용 테스트코드
* ```java
  @Test
  public void testMemeberInjection() {
      MyClass myClass = new MyClass();
      String str = myClass.getStr();
      assertNotNull("조회 결과 null", str); // check null
      MyComponent myComponent = DaggerMyComponet.create(); // 멤버 - 인젝션 메서드에 의해 필드 주입
      myComponnet.inject(myClass); // 인젝트(주입)
      str = myClass.getStr();
      assertEquals("hello world", str)
  }
##### MemberInjector는 위와 동일한 작업을 수행한다.
* ```java
  @Component(modules = MyModule.class)
  public interface MyComponent {
      ...
      MembersInjector<MyClass> getInjector();
  }
  
  @Test
  public void testMemberInjector() {
      MyClass myClass = new MyClass();
      String str = myClass.getStr();
      System.out.println("result = " + str);
      MyComponent myComponent = DaggerMyComponent.create();
      MembersInjector<MyClass> injector = myComponent.getInjector();
      injector.injectMembers(myClass);
      str = myClass.getStr();
      System.out.println("result = " + str); // str = null
  }
  
  // result = null
  // result = Charles
  
---
# 컴포넌트 객체 만들기
---
### @Component.Builder
* ```java
  @Component(modules = MyModule.class)
  public interface MyComponent {
      ...
      @Component.Builder
      interface Builder {
          Builder setMyModule(MyModule myModule);
          MyComponent build();]
          
  }
  
  // 또는
  
  @Component(modules = {BackendModule.class, FrontendModule.class})
      interface MyComponent {
          MyWidget myWidget();
          
          @Component.Builder
          interface Builder {
              Builder backendModule(BackendModule bm);
              Builder frontendModule(FrontendModule fm);
              @BindsInstance
              Builder foo(Foo foo);
              MyComponent build();
          }
  }
##### 생성 조건
* 반드시 컴포넌트 내 @Component.Builder 선언
* 매개변수 불가
* Build Method 하나 이상 포함
* Build Method 외는 Setter Method 로 부름
  * modules, dependencies는 반드시 Setter Method로 선언해야한다
  * Setter Method는 반드시 하나 이상 매개변수
  * 반환형은 void, Builder, Build Super Type
  * @BindsInstance 는 해당 컴포넌트에 인스턴스를 넘겨 바인드 시킴
---
### @Component.Factory
* ```java
  @Component(modules = {BackendModule.class, FrontendModule.class})
  interface MyComponent {
      MyWidget myWidget();
          
      @Component.Factory
      interface Factory {
          MyComponent newMyComponent(
              BackendModule bm,
              FrontendModule fm,
              @BindsInstance Foo foo);
      }
  
  }
##### 생성 조건
* 반드시 컴포넌트 내 @Component.Factory 선언
* 컴포넌트 타입/슈퍼타입을 반환하는 추상 메서드 하나만 존재
* 팩토리 메서드에는 modules, dependencies로 지정된 속성들을 매개변수로 가져야함
* @BindsIstance 는 해당 컴포넌트에 인스턴스를 넘겨 바인드 시킴
