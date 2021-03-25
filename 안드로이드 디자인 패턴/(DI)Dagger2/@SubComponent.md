# @Subcomponent - [참고는 투덜이님의 리얼블로그](https://tourspace.tistory.com/330?category=797357#recentComments)
### 특징
* Component간의 부모-자식 관계로 처리해줄수 있게 해주는 어노테션
* 상위 Component 내에 구현이 된다.
* SubComponent의 모듈을 상위 Component에서 사용할수 없다. 단지 상위 컴포넌트의 모듈을 하우 컴포넌트에서 사용가능하다.
* **Builder를 정의해 놓지 않는다면, 외부에서 접근 할 수가 없습니다.** --> Build failed --> **외부**외부**외부**!!에서 접근하기위해서 반드시 필요!!!!**
---
### 코드
##### 부모 자식관계 표시
* 자식-부모 관계 표시는 **Component에 해야할것같지만 module 에다가 표시**한다.
  * [투덜이님의 리얼블로그 왈 - Component는 단순히 외부에서 사용하기 위한 interface 역할만 하고 객체 생성을 담당하는 것은 모듈의 역할이기 때문에 아마도 모듈에서 subcomponent를 정의해주는것으로 추측](https://tourspace.tistory.com/330?category=797357#recentComments)
##### Component_A -> Module_A -> Component_B -> Module_B
* Module_A 가 Component와 SubComponent의 중간다리 역할을 한다.
  * Component = Component_A
  * Subcomponent = Component_B
##### Component_A
* ```java
  @Component(modules = {Module_A.class})
  interface Component_A {

      @Named("Me")
      PersonMe callPersonMe();

      Component_B.Builder componentb();

  }
##### Module_A
* ```java
  @Module(subcomponents = Component_B.class)
  public class Module_A {

      @Provides
      @Named("MyName")
      String MyName(){
          return "Sung Dae Kyoung";
      }

      @Provides
      @Named("MyAge")
      int MyAge(){
          return 29;
      }

      @Provides
      @Named("Me")
      PersonMe Me(@Named("MyName") String name, @Named("MyAge")int age){
          return new PersonMe(name, age);
      }

  }
##### Component_B
* ```java
  @Subcomponent(modules = {Module_B.class})
  public interface Component_B {

      @Named("You")
      PersonYou callPersonYou();

      @Subcomponent.Builder
      interface Builder{
          Component_B build();
      }

  }
##### Module_B
* ```java
  @Module
  public class Module_B {

      @Provides
      @Named("YourName")
      String YourName(){
          return "Jung Sun Yea";
      }

      @Provides
      @Named("YourAge")
      int YourAge(){
          return 53;
      }

      @Provides
      @Named("You")
      PersonYou You(@Named("YourName") String name, @Named("YourAge") int age){
          return new PersonYou(name, age);
      }
  }
##### PersonMe, PersonYou
* ```java
  //PersonMe
  public class PersonMe {

      String name;
      int age;

      @Inject
      public PersonMe(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }
  
  // PersonYou
  public class PersonYou {

      String name;
      int age;

      @Inject
      public PersonYou(String name, int age) {
          this.name = name;
          this.age = age;
      };

  }
--- 
### 테스트 결과
* ```java
  @Test
  ...
  Component_A component = DaggerComponent_A.create();
  PersonMe personMe = component.callPersonMe();
  PersonYou personYou = component.componentb().build().callPersonYou();

  Log.i("sudeky","personMe.name: " + personMe.name + " personMe.age: " + personMe.age);
  Log.i("sudeky","personYou.name: " + personYou.name + " personYou.age: " + personYou.age);
  
  // result
  // personMe.name: Sung Dae Kyoung personMe.age: 29
  // personYou.name: Jung Sun Yea personYou.age: 53
