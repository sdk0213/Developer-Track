# 범위 지정하기
* 컴포넌트의 구현과 함께 가 컴포넌트 인스턴스는 의존성의 제공 방법에 대한 동일성으 보장받을 수 있다.
* 어플리케이션의 생명주기와 별개로 생며 주기를 따로 관리할 수 있다.
  * 안드로이드에서 어플리케이션, 액티비티, 프래그먼트 인스턴스에 대한 범위 지정을 다르게 관리해 오브젝트 그래프의 생성 및 소멸을 각자 관리 가능
---
### @Singleton
* Dagger에서 제공하는 @Singleton은 java에서의 singleton 개념과는 다르다. 즉 단일객체를 제공하기는 하지만 그 생명주기는 해당 Component에 의존합니다.
* Component에 붙여서 Component와 생명주기를 같이하도록 할때 사용
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
---
### @Reusable
* 이전객체를 사용가능하다면 재사용하고 아니며 새로 생성한다.
* **동일성을 보장하진 않아도 항상 동일한것을 쓸거 아니면 메모리 측면에서 조금 더 효율적**
---
### @Scope
* 커스텀 스코프
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
  
