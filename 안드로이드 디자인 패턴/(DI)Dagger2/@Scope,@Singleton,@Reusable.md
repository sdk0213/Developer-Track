# 범위 지정하기
* 컴포넌트의 구현과 함께 가 컴포넌트 인스턴스는 의존성의 제공 방법에 대한 동일성으 보장받을 수 있다.
* 어플리케이션의 생명주기와 별개로 생며 주기를 따로 관리할 수 있다.
  * 안드로이드에서 어플리케이션, 액티비티, 프래그먼트 인스턴스에 대한 범위 지정을 다르게 관리해 오브젝트 그래프의 생성 및 소멸을 각자 관리 가능
---
### @Singleton
* Dagger에서 제공하는 @Singleton은 java에서의 singleton 개념과는 다르다. 즉 단일객체를 제공하기는 하지만 그 생명주기는 해당 Component에 의존합니다.
  * 예를들어서 다음과 같은 차이가 있다.
    ```java
    Component_A component = DaggerComponent_A.create(); // Non-Singleton

    Component_B component_b = DaggerComponent_B.create(); // Singleton
    Log.i("sudeky",""+System.identityHashCode(component.hello())); // 174949475
    Log.i("sudeky",""+System.identityHashCode(component.hello())); // 109688928
    Log.i("sudeky",""+System.identityHashCode(component_b.hello())); // 152105241
    Log.i("sudeky",""+System.identityHashCode(component_b.hello())); // 152105241
    
    // 하지만 다른 차이점이 있다면 단일객체를 제공하기는 하지만 그 생명주기는 해당 Component에 의존한다.
    Component_B component_b2 = DaggerComponent_B.create(); // 
    Log.i("sudeky",""+System.identityHashCode(component_b2.hello())); // 173464346
    
    // 싱글톤인데도 불구하고 해당 Component를 생성하면 다른 객체로 취급된다. 기본적으로 Singleton의 생명주기가 Component에 의존한다는것을 확인할수있다.
    // 그러니까 싱글톤이지만 객체 생성과 같은것을 보장하는 범위는 컴포넌트내에서만 가능하다.
* **@Singleton은 보통 Component에 붙여서 Component와 생명주기를 같이하도록 할때 사용**
* ```java
  @Singleton
  @Component(modules = MyModule.class)
  public interface MyComponent {
      Object getObject();
  }
* ```java
  @Module
  public class MyModule {
      @Provides
      @Singleton
      Object provideObject() {
          return new Object();
      }
  }
* ```java
  @Test
  public void testObjectIdentity() {
      MyComponent myComponent = DaggerMyComponent.create();
      Object temp1 = myComponent.getObject();
      Object temp2 = myComponent.getObject();
      assertNotNull(temp1);
      assertNotNull(temp2);
      assertSame(temp1, temp2);
  }
    
  // 326549596
  // 326549596
  // true
* [Component와 Module 에 붙은 Singleton의 의미는 다른것인가?](https://stackoverflow.com/questions/35295947/difference-between-scope-in-modules-and-components)
  * Annotating the @Provides method (or the class with an @Inject constructor) tells Dagger to implement the actual scoping functionality whereas annotating the component (which is necessary) doesn't have any functionality, but tells Dagger "I allow this component to contain bindings of this scope". Note that you can still have unscoped bindings in a scoped component, but not the other way around.
  * Component 내부에서 스코프를 사용한다면 붙여주는것이고 만약에 스코프를 사용하는데 안붙히면 에러 나타남. 그거말고는 아무의미 없고 특별한 기능을 하는것은 아님
---
### @Reusable
* 이전객체를 사용가능하다면 재사용하고 아니며 새로 생성한다.
* **동일성을 보장하진 않아도 항상 동일한것을 쓸거 아니면 메모리 측면에서 조금 더 효율적**
---
### @CustomScope [정확히 이해를 하지는 못했음)
* 아마도 추측으로는 싱글톤처럼 역할은 똑같지만 일반적인 싱글톤이 어플리케이션 전체에 걸쳐서 살아있다면 CustomScope로 설정할경우 해당 CustomScope로 설정한 컴포넌트가 살아있는 동안은 싱글톤 유지 되고 만약에 Destory 될경우는 비록 싱글톤이라도 사라진다.
* **해당 클래스의 단일(Single) 인스턴스가 존재하는 범위**
* 생성된 객체의 Lifecycle 범위에 넣는다?
* ```java
  @Scope
  @Retention(RetentionPolicy.RUNTIME)
  public @interface UserScope {
  }
* ```java
  @Module
  public class MyModule {
      @Provides
      @UserScope
      Object provideObject() {
          return new Object();
      }
  }
  
