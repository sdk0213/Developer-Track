# 범위 지정하기
* 컴포넌트의 구현과 함께 가 컴포넌트 인스턴스는 의존성의 제공 방법에 대한 동일성으 보장받을 수 있다.
* 어플리케이션의 생명주기와 별개로 생며 주기를 따로 관리할 수 있다.
  * 안드로이드에서 어플리케이션, 액티비티, 프래그먼트 인스턴스에 대한 범위 지정을 다르게 관리해 오브젝트 그래프의 생성 및 소멸을 각자 관리 가능
---
### @Singleton
* Singleton vs non-singleton
  * singleton
    * Every dependency is provided with annotation @Singleton. That means every time when **Dagger has to use that dependency it will use its single version only.** - 매번 같은 버전을 제공해준다.
  * non-sigleton
    * it will use new version every time. - 매번 다른 버전을 제공해준다.
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
### @CustomScope 
* [사용이유]([출처는 여기](https://crosp.net/blog/software-development/mobile/android/understanding-dagger-2-scopes-under-the-hood/))
  * Scope의 이름은 중요한것이 아니다. 중요한것은 해당 스코프를 가진 모듈들은 같은 생명주기를 같는다는것이다. 예를들어서 모듈 속 A객체와 B객체가 같은 스코프를 가진다면 A라는 객체가 GC처리가 되거나 사라졌다면 
  * @Singleton과 다른점은 전체 application이 아닌 구성요소의 lifecycle(수명주기)와 관련이 있다는 차이점이 있다.
  * 만약에 전부다 같은 안드로이드에서 같은 Scope로 관리를 한다고 가정해보자. 그러면 Application이든 Activity든 Fragment든 간에 DaggerComponent로 바인딩된 모듈들은 항상 동일한 인스턴스를 보장한다. 그런데 Fragment에서 제공받는 모듈중에는 Fragment 파괴되고 재생성되었다면 다시 새로운 객체로 생성될필요가있다. 하지만 모두가 동일한 Scope라고 한다면 Fragment가 파괴되고 다시 생성되었을때 해당 모듈에서 제공받는 객체가 이미 파괴되고 재성성되어야 했지만 그렇지 못해서 계속 그전의 객체로 의미없는 객체를 계속 받게 된다. 즉 어떠한 객체는 다른 객체와 같은 생명주기를 공유해야되는 부분이 있는것이다. A라는 객체와 B라는 객체가 생명주기를 같이 한다는것은 A라는 객체의 생성과 파괴는 B라는 객체의 생성과 파괴에도 영향을 서로 주고 받는 관계이고 이는 두개의 객체를 동일한 Scope(생명주기)에 넣어줘야 되는부분이다. 그렇기 때문에 이때는 @Scope를 사용해서 같은 생명주기를 보장해줘야 한다.
  * 언제 component가 사라지는거지??
    * Activity에서 Dagger...Compoenent 객체를 참조하는데 Activity가 finish()되고 나서 종료되었다. 안드로이드와 자바의 GC의 메커니즘은 거의 똑같다. DaggerComponent를 참조했던 클래스가 사라졌기 때문에 GC는 이를 쓰레기객체로 선정하고 메모리에서 삭제할것이다. 이때 DaggerComponent가 사라지는데 Component의 모듈들은 Component의 Scope를 사용하기 때문에(Module의 Scope를 사용할때에는 반드시 Component에서 선언해줘야함) 해당 모듈들에서 생성된 객체들도 삭제된다.
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
  
