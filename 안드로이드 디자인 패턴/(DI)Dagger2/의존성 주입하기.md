# 의존성 주입
* 의존성 주입의 세가지
  * 필드 -> 주로 실무에서 사용
  * 생성자 -> 주로 실무에서 사용
  * 메서드
##### 예
* ```java
  @Component(modules = PersonModule.class)
  public interface PersonComponent {
    
      PersonA getPersonA();
      void inject(PersonB personB)
  }
  
  ...
  @Module
  public class PersonModule {
  
      @Provides
      String provideName() {
          return "Charles";
      }
      
      @Provides
      int provideAge() {
          return 100;
      }
  }
  
  ...
  public class PersonA { // 생성자 주입
      private String name;
      private int age;
      
      @Inject // 생성자 주입
      public PersonA .. 생성자
          this.name = na...
          this.age ...
      }
      
      ...String getName 메서드
      ...Int getAge 메서드
  }
  
  ...
  public class PersonB { // 필드 주입, 메서드 주입
      @Inject // 필드 주입
      String name;
      private int age;
      
      @Inject // 메서드 주입
      public void setAge(int age) {
          this.age = age;
      }
      
      ..String getName 메서드
      ..Int getAge 메서드
  }
##### 테스트 결과
* ```java
  @Test
  public void testInjection() {
      PersonComponent personComponent = DaggerPersonComponent.create(); // 생성자 주입
      PersonA personA = personComponent.getPersonA();
      System.out.println(personA.getName() + " : " + personA.getAge());
      
      PersonB personB = new PersonB();
      DaggerPersonComponent.create().inject(personB);
      assertEquals("Charles", personB.getName());
      
      assertEquals(100, personB.getAge());
  }
  
  // result :
  // Cahrles:100
  // Cahrles:100
---  
### Module에서 값을 가져오는 우선순위는 ?
* 상위 객체가 있다면 상위 객체부터 처리를 하는것같다.
* 예를들어서 아래 예제에서 String과 int를 Provides해주기 때문에 Person에 String과 name을 처리해주지만 직접적으로 Person을 모듈에서 제공할경우에는 해당 Provides를 우선순위로 처리를 해주는것같다.
* ```java
  // 경우의 수 1
  @Module
  public class Module_A {

     @Provides
     String module_Provides_String_Name(){
         return "sudeky";
     }

     @Provides
     int module_Provides_Int_Age(){
         return 29;
     }
  }
 
  @Module
  public class Module_B {

     @Provides
     String module_Provides_String_Name(){
         return "sudeky";
     }

     @Provides
     int module_Provides_Int_Age(){
         return 29;
     }

     @Provides
     PersonA module_Provides_PersonA_Person(){
         return new PersonA("sunyea",45);
     }
  }
 
  ...
 
  @Component(modules = {Module_A.class})
  public interface Component_A {
     PersonA callPersonA();
   }
  
  @Component(modules = {Module_B.class})
  public interface Component_B {
     PersonA callPersonA();
   }
  
   ...
   @Test
   ...
  Component_A component = DaggerComponent_A.create();
   Component_B component_b = DaggerComponent_B.create();
   PersonA a = component.callPersonA();  
   PersonA a2 = component_callPersonA();  
  
   Log.d(TAG,a.getName());
   Log.d(TAG,a.getAge());
   Log.d(TAG,a2.getName());
   Log.d(TAG,a2.getAge());
   // 결과
   // sudeky
   // 29
   // sunyea
   // 45
# 상속된 클래스에 의존성 주입
* ```java
  public class PersonC {
      @Inject
      String C;
  }
 
  public class PersonD extends PersonC {
      @Inject
      String D;
  }

  public class PersonE extends PersonD{
     String E;
  }
* ```java
  @Component(modules = PersonModule.class)
  public interface PersonComponent {
     void inject(PersonE personE);
  }
* DaggerPersonComponent.class
  ```java
  ...
  @Override
  public void inject(PersonE personE) {
    injectPersonE(personE);}

  private PersonE injectPersonE(PersonE instance) {
    PersonC_MembersInjector.injectC(instance, PersonModule_ProvideNameFactory.provideName(personModule));
    PersonD_MembersInjector.injectD(instance, PersonModule_ProvideNameFactory.provideName(personModule));
    return instance;
  }
* @Inject 들어가있는것만 작동한다.
---
### 커스텀 컴포넌트 빌더 또느 팩토리 만들기 ....추가내용은 51Page 참고
* 컴포넌트 빌더
  ```java
  @Component(modules = {BackendModule.class, ForntendModule.class})
      interface MyComponet {
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
  

 


