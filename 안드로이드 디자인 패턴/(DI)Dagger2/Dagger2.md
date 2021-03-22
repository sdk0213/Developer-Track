# Dagger - [참고는 maryangmin님의 medium](https://medium.com/@maryangmin/di-기본개념부터-사용법까지-dagger2-시작하기-3332bb93b4b9)
### Dagger 란?
* Dagger1 -> Sqaure 에서 제작
* Dagger2 -> Google 에서 관리
* 자동으로 종속 항목을 삽입해준다.
  * --> 보일러코드가 줄어든다.
* Annotation Processing으로 reflection 사용없이 컴파일 타임에 코드를 생성하기 때문에 빠르다.
* 런타임에 바이트코드를 생성하지 않는다.
##### 장점
* 자원 공유의 단순화(지정된 범위의 생명 주기 내에서 동일 인스턴스를 제공)
* 복잡한 의존성을 단순하게 설정
* 유닛 테스트를 도움
* 자동 코드 생성 -> 생성된 코드는 디버깅 가능
* Dagger2 는 Dagger1 에서 발생했던 난독화문제가 없다.
* 라이브러리 크기가 작다
* [여기에서](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20디자인%20패턴/(DI)종속%20항목%20삽입.md) DI 안드로이드 코드를 작성시 발생하는 다음과 같은 문제를 해결해준다.
  * AppContainer 자동생성
  * Factory 클래스 생성
  * 설정에 따라 의존성을 재활용할건지 새로만들것인지 결정할수있다.
  * 특정기능을 위한 Cotainer를 사용후 메모리에서 해제할수 있다.
---
### Dagger2 에서 컴포넌트와 모듈
* ```java
  interface Factory { // Factory
     <T> create()
  } 
  public final class BClassFactory implements Factory{ // --> BClass Factory
      private BClass bclass;
      BClassFactory(BClass bclass){
          this.bclass = bclass;
      }
 
      public static BClassFactory create(BClass bclass) {
          return new BClassFactory(bclass);
      }
 
      public static void print(BClass bclass){
          bclass.print();
      }
  }

  interface AInterface { // ---> Component
     void print();
  }
  
  class AClass implements AInterface{ // --> DaggerPersonComponent
  
     private BClass bclass;
     AClass(BClass bclass){
         bclass = BClassFactory,create(bclass);
     }
    
     @Override
     void print(){
         bclass.print();     
     }
  }
  
  class BClass { // -> Module
  
     void print(){
         System.out.println("Hello world");
     }
  }
  
  ..main..{
      AClass aClass = new AClass(new BClass()); // Module 넣기
      aclass.print();
   }
### Dagger2가 의존성 주입을 해준다는것의 의미
* 컴포넌트
  ```java
  @Component(modules = PersonModule.class)
  public interface PersonComponent {

      int a();
      String b();
      PersonA c();
  }
* 모듈
  ```java
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

      @Provides
      PersonA getPersonA(){
          return new PersonA("sudeky",3174);
      }
  }
* 이를 컴파일 할경우 DaggerPersonComponent가 생성이 되는데 이를 확인해보면 Dagger가 의존성을 자동으로 주입해주었다는것을 알수 있다.
  * ```java
    public final class DaggerPersonComponent implements PersonComponent {
        private final PersonModule personModule;

        private DaggerPersonComponent(PersonModule personModuleParam) {
          this.personModule = personModuleParam;
        }

        public static Builder builder() {
          return new Builder();
        }

        public static PersonComponent create() {
          return new Builder().build();
        }

        @Override
        public int a() {
          return personModule.provideAge();}

        @Override
        public String b() {
          return PersonModule_ProvideNameFactory.provideName(personModule);}

        @Override
        public PersonA c() {
          return PersonModule_GetPersonAFactory.getPersonA(personModule);}

        public static final class Builder {
          private PersonModule personModule;

          private Builder() {
          }

          public Builder personModule(PersonModule personModule) {
            this.personModule = Preconditions.checkNotNull(personModule);
            return this;
          }

          public PersonComponent build() {
            if (personModule == null) {
              this.personModule = new PersonModule();
            }
            return new DaggerPersonComponent(personModule);
          }
        }
    }
* 위 코드를 확인해보면은 a()에서 module에 들어있는 객체를 사용하고 b(),c() 또한 같은 타입을 가진 객체를 사용하는것을 확인할수가 있다.
* 만약 위를 Dagger2 라는것 없이 자기가 직접 클래스를 만들고 의존성을 주입하고 어떤 경우는 팩토리 패턴을 사용해 위와 같이 코드를 직접 작성해야한다.
  * 내가 어떤 객체를 만들고 해다 객체에서 다른 객체가 필요할경우 즉, 의존성이 필요한경우 위와 같은 코드를 작성해야 하는경우가 많다. 이를 자동으로 작성해준다? 그게 바로 Dagger 이다.
* 하지만 Dagger2를 사용하고 어노테션 프로세서로 @Component와 @Module를 지정해주며 타입을 설정해준것만으로 내가 직접 작성했어야할 코드를 자동으로 작성주고 관리해주는것이다.
* 추가적으로 Dagger2는 객체의 생성은 펙토리 패턴으로 관리되는것을 확인할수있다.
  * ```java
    // 위 코드에서 c() 를 확인해보면은 다음과 같이 코드가 작성되어있다.
    @Override
    public PersonA c() {
      return PersonModule_GetPersonAFactory.getPersonA(personModule);}
    ....
    ..
    .
    // 그리고 위의 코드중 GetPersonAFactory의 클래스이다.(펙토리 패턴)(Dagger2가 자동으로 작성함)
    public final class PersonModule_GetPersonAFactory implements Factory<PersonA> {
        private final PersonModule module;

        public PersonModule_GetPersonAFactory(PersonModule module) {
          this.module = module;
        }

        @Override
        public PersonA get() {
          return getPersonA(module);
        }

        public static PersonModule_GetPersonAFactory create(PersonModule module) {
          return new PersonModule_GetPersonAFactory(module);
        }

        public static PersonA getPersonA(PersonModule instance) {
           return Preconditions.checkNotNull(instance.getPersonA(), "Cannot return null from a non-@Nullable @Provides method");
        }
    }
     
* Dagger2 가 코드를 작성해주기때문에 우리는 단지 의존성이 필요한것이 있을때 컴포넌트와 모듈로 구분해주고 형식만 맞추어주면 된다. 이는 엉청난 보일러코드를 줄여주며 개발자가 직접 작성해야할 코드량 또한 매우 줄어드므로 당연히 필요한 도구임에 틀림없다.
---
### HelloWorld 예제
* ```java
  @Module // 의존성을 제공하는 클래스에 붙인다
  public class MyModule {
      @Provides // 의존성 주입에 필요한 파일들을 생성
      String provideHelloWorld() {
          return "Hello World!";
      }
  }

  // 위의 코드만으로는 클래스 파일이 생성하지 않는다. Component가 있어야 한다.
  @Component(modules = MyModule.class) // 참조된 Module 클래스로부터 의존성을 제공받는다
  public interface MyComponent {
      String getString(); // 컴포넌트의 메서드의 반환형을 보고 모듈과 관계를 맺으므로 해당 반환형을 갖는 메서드
  }
* Dagger는 컴파일 타임에 @Component를 구현한 클래스를 생성한다. 이때 Dagger라는 접두사가 붙는다.
  * MyComponent --> DaggerMyComponent
##### 테스트코드 작성 및 결과
* ```java 
  pulbic class ExampleUnitTest {
      @Test
      public void testHelloWorld(){
          MyComponent mycomponent = DaggerMyComponent.create();
          System.out.println("result = " + mycomponent.getString());
      }
  }
  
  // result = Hello World
