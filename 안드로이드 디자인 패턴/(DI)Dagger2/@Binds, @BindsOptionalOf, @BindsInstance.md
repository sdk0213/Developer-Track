### @Binds
* 모듈 내 추상 메서드 + 하나의 매개변수에만 붙일 수 있다.
* 이미 바인드된 SecureRandom을 Random 타입으로 한 번 더 바인드할 수 있다.
* @Provides 메서드보단 좀 더 효율적으로 사용할수 있다.
* ```java
  @Binds
  abstract Random bindRandom(SecureRandom secureRandom)
  
### BindsOptionalOf
* 모듈 내 추상메서드 + 매개변수 X
* 반환타입필요(void는 안됨)
* 예외사항 못 던짐
* ```java
  @Module
  public abstract class CommonModule {
      @BindsOptionalOf
      abstract String bindsOptionalOfString();
  }
* ```java
  @Module
  public class HelloModule {
      @Provides
      String privdesString() {
          return "Hello";
      }
  }
* ```java
  public class Foo {
      @Inject
      public Optional<String> str;
      
      @Inject
      public Optional<Provider<String>> str2;
      
      @Inject
      public Optional<Lazy<String>> str3;
  }
##### 테스트
* ```java
  @Component(modules = {CommonModule.class, HelloModule.class})
  public interface StrComponent {
      void inject(Foo foo);
  }
* ```java
  @Component(modules = CommonModule.class)
  public interface NoStrComponent {
      void inject(Foo foo);
  }
* ```java
  @Test
  public void testFoo() {
      Foo foo = new Foo();
      
      DaggerStrComponent.create().inject(foo);
      System.out.println(foo.str.isPresent());
      System.out.println(foo.str.get());
      
      DaggerNoStrCpomponent.create().inject(foo);
      System.out.println(foo.str.isPresent());
      System.out.println(foo.str.get());
  }
  
  // true
  // Hello
  // false
  // java.util.NoSuchElementException: No Value present
---
### BindsInstance
* Module을 작성하다 보면 객체 생성시 param으로 입력되는 인자를 외부에서 전달받아야 하는 경우
* Component Builder Setter 또는 Factory 매개변수에 붙일수 있다.
* @Inject가 붙은 필드, 생성자, 메서드에 주입될 수 있다.
* **모듈말고 외부로부터 생성된 인스턴스를 빌더 또느 팩토리를 통해 넘겨줘 해당 인스턴스를 바인딩하게 한다.**
##### Component
* ```java
  @Component(modules = {Module_A.class})
  interface Component_A {

      @Named("Person_Master")
      PersonA callPerson();

      @Component.Builder
      interface Builder {
          @BindsInstance
          Builder setAge(int age);
          Component_A build();
      }
  }
##### Module
* 외부에서 받을 값은 @Named 와 같은 annotation을 설정해주지 않는다.
* ```java
  @Module
  public class Module_A {

      @Provides
      @Named("Name")
      String module_Provides_String_Name(){
          return "sudeky";
      }

      @Provides
      @Named("Age")
      int module_Provides_Int_Age(){
        r eturn 29;
      }

      @Provides
      @Named("Person_Master")
      PersonA module_Provides_PersonA_Person(@Named("Name") String name, int age){ // name은 annotation 설정 안함
          return new PersonA(name, age);
      }

  }
##### PersonA
* ```java
  public class PersonA {

    String name;
    int age;

    @Inject
    public PersonA(String name, int age) {
        this.name = name;
        this.age = age;
    }
  }
##### 실제 Dagger 클래스
* setAge로 인자를 넣어주는것을 확인할수 있다.
* DaggerComponent_A.class
  ```java
  ..
  ...
  @Override
  public PersonA callPerson() {
    return Module_A_Module_Provides_PersonA_PersonFactory.module_Provides_PersonA_Person(module_A, Module_A_Module_Provides_String_NameFactory.module_Provides_String_Name(module_A), setAge);}

  private static final class Builder implements Component_A.Builder {
    private Integer setAge;

    @Override
    public Builder setAge(int age) {
      this.setAge = Preconditions.checkNotNull(age);
      return this;
  }
  ..
  .
##### @Test
* ```java
  @Test
  ...
  Component_A component = DaggerComponent_A.builder()
                .setAge(30)
                .build();
