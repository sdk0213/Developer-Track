# 컴포넌트 
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
* [Builder 와의 차이점 - 출처](https://proandroiddev.com/dagger-and-the-shiny-new-component-factory-c2234fcae6b1)
  * 코드가 더 깔끔하다.
  * 반드시 의존성을 넣어줘야 하는것에 대해서 까먹지 않을수 있다.(it’s not possible anymore to forget to provide a mandatory dependency to the component)
    * 그러므로 컴파일로부터 Builder보다 더 안전하다.(compile time safety)
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
