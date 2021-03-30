# Map MultiBinding
* [Set](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20디자인%20패턴/(DI)Dagger2/SetMultiBinding(%40IntoSet%2C%20%40ElementsIntoSet).md)에 이어서 Map 형태로 관리 
---
### @IntoMap
* Map 에 넣을거라는것을 명시
### @StringKey, @IntKey, @LongKey
* 키값 넣기
### @ClassKey
* 키를 클래스형태로 관리할경우
---
##### Component
* ```java
  @Singleton
  @Component(modules = {Module_A.class})
  interface Component_A {

      Map<String, Person> callMyPerson();

      Map<String, Dog> callMyDog();

      Map<Class<?>, Family> callMyNewFamily(); // Class 안 '?' 은 wildcard 이다.

  }
##### Module
* ```java
  @Module
  public class Module_A {

      @Provides
      @IntoMap
      @StringKey("Me")
      Person person(){
          return new Person("sung dae kyoung",29);
      }

      @Provides
      @IntoMap
      @StringKey("Mom")
      Person person2(){
          return new Person("jung sun yae",54);
      }

      @Provides
      @IntoMap
      @StringKey("Dog")
      Dog dog(){
          return new Dog("cally",2);
      }

      @Provides
      @IntoMap
      @ClassKey(Person.class)
      Family Person3(){
          return new Person("sung nak su", 55);
      }
  
      @Provides
      @IntoMap
      @ClassKey(Dog.class)
      Family dog2(){
          return new Dog("selly",5);
      }
}
##### Family
* ```java
  abstract class Family {

      abstract int hasLove();
  }
##### Person
* ```java
  public class Person extends Family{

      String name;
      int age;

      @Inject
      public Person(String name, int age) {
          this.name = name;
          this.age = age;
      }

      @Override
      int hasLove() {
          return age*10;
      }
  }
##### Dog
* ```java
  public class Dog extends Family{

      String name;
      int age;

      @Inject
      public Dog(String name, int age) {
          this.name = name;
          this.age = age;
      }

      @Override
      int hasLove() {
          return age*10;
      }
  }
### @Test
* ```java
  public class ComponentTest {

      @Test
      public void testSingletonComponent() {
          Component_A component = DaggerComponent_A.create();
          Map<String, Person> personMap = component.callMyPerson();
          Map<String, Dog> dogMap = component.callMyDog();
          Map<Class<?>, Family> familyMap = component.callMyNewFamily();

          System.out.println("person.name: " + personMap.get("Me").name + " / person.age: " + personMap.get("Me").age);
          System.out.println("person.name: " + personMap.get("Mom").name + " / person.age: " + personMap.get("Mom").age);
          System.out.println("dogMap.name: " + dogMap.get("Dog").name + " / person.age: " + dogMap.get("Dog").age);
          System.out.println("Person's Love: " + familyMap.get(Person.class).hasLove());
          System.out.println("Dog's Love: " + familyMap.get(Dog.class).hasLove());
        
      }

  }
  
  // result
  person.name: sung dae kyoung / person.age: 29
  person.name: jung sun yae / person.age: 54
  dogMap.name: cally / person.age: 2
  Person's Love: 550
  Dog's Love: 50

---
# [@Multibinds](https://www.charlezz.com/?p=1315)
