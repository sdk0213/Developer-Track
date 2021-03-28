# Set MultiBinding
* Dagger의 멀티 바인딩을 사용하면 **여러 모듈에 있는 같은 타입의 객체를 하나의 Set 형태로 관리할 수 있다.**
---
### @IntoSet
* IntoSet을 사용 하는것으로 Set<String> 타입으로 멀티 바인딩이 구현된다.
* IntoSet을 하면 Set 반환형으로 자동으로 삽입해준다.
##### Component
* ```java
  @Singleton
  @Component(modules = {Module_A.class})
  interface Component_A {

      Set<Person> callMyFamiliy();
  
  }
##### Module
* ```java
  @Module
  public class Module_A {

      @Provides
      @IntoSet
      Person person(){
          return new Person("sung dae kyoung",29);
      }


      @Provides
      @IntoSet
      Person person2(){
          return new Person("jung sun yae",54);
      }

  }
##### Person(객체)
* ```java
  public class Person {

      String name;
      int age;

      @Inject
      public Person(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }
##### Test
* 자동으로 Set으로 삽입이 되었다.
* ```java    
  @Test
  public void testSingletonComponent() {
      Component_A component = DaggerComponent_A.create();      Set<Person> setperson = component.callMyFamiliy();

      for (Person person : setperson) {
          System.out.println("person.name: " + person.name + " person.age: " + person.age);
      }
  }
 
  // 출력결과 :
  // person.name: jung sun yae person.age: 54
  // person.name: sung dae kyoung person.age: 29
  
---
### @ElementsIntoSet
* 객체를 하나씨 Set에 추가하지 않고 Set<T>의 일부분을 한꺼번에 추가할때 사용
##### Component
* ```java
  @Singleton
  @Component(modules = {Module_A.class})
  interface Component_A {

      Set<Person> callMyFamiliy();

  }
##### Module
* ```java
  @Module
  public class Module_A {
  
      @Provides
      @ElementsIntoSet
      Set<Person> Person(){
          return new HashSet<>(Arrays.asList(
              new Person("sung dae kyoung",29),
              new Person("sung nak su",55),
              new Person("jung sun yae", 54),
              new Person("sung tae kyoung",32))
          );
      }
      
  }
##### Person(객체)
* ```java
  public class Person {

      String name;
      int age;

      @Inject
      public Person(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }
##### @Test
* ```java
  Component_A component = DaggerComponent_A.create();
  Set<Person> setperson = component.callMyFamiliy();

  for (Person person : setperson) {
      Log.i("sudeky", "person.name: " + person.name + " person.age: " + person.age);
  }
  
  // result:
  // person.name: jung sun yae person.age: 54
  // person.name: sung nak su person.age: 55
  // person.name: sung dae kyoung person.age: 29
  // person.name: sung tae kyoung person.age: 32
