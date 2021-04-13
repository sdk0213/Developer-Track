# @Inject
* 어떻게 클래스를 생성하는것인지 방법을 Dagger에게 알려주는것
  * @Inject가 최소한 하나이상 생성자(매개변수가없더라도)에는 붙어있어야 Component에서 생성가능하다. 왜냐하면 아무 @Inject가 없는 클래스는 어떠한 방법으로 생성을 해야하는지 Dagger알수 없기 때문이다.
* 필드, 메서드 또는 생성자 인스턴스를 주입해준다. 
* ```java
  public class PersonB {

    @Inject
    String name;
    @Inject
    int age;

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
  }
  
  ...
  
  @Component(modules = PersonModule.class)
  public interface PersonComponent {
      void inject(PersonB personB);
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
  
* @Inject 처리된것은 Module의 같은 타입이 있다면 매치되어서 값을 가져온다.
* DaggerComponent 를 보면 확인가능하다.
  ```java
  public final class DaggerPersonComponent implements PersonComponent {
    ,,,
    ,,
    
      @Override
      public void inject(PersonB personB) {
          injectPersonB(personB);}
       
      }
    
      private PersonB injectPersonB(PersonB instance) {
        PersonB_MembersInjector.injectName(instance, PersonModule_ProvideNameFactory.provideName(personModule));
        PersonB_MembersInjector.injectAge(instance, personModule.provideAge());
        return instance;
      }
  }
  
  ...
  
  @InjectedFieldSignature("com.turtle.amatda.PersonB.name")
  public static void injectName(PersonB instance, String name) {
    instance.name = name;
  }
* injectPerspnB 에서 PersonB_MemberInjector 의 injectName에서 집어넣은 PersonB객체에 모듈에서 제공받은 이름을 집어넣는것을 확인가능하다.
* private에 @Inject는 Exception 이 떨어지며 컴파일이 안된다.
### Inject 에 따른 변화
* 단순히 메서드에서 get을 할때는 아무 효과가 없는듯싶다. 그래서 그냥 필드에다가 Inject를 하는가 추측이된다.
* set으로 값을 넣어주는 형태일경우는 모듈이 주입되어 작동한다.
* ```java
  public class PersonB {

    @Inject
    String name;
    int age;

    @Inject
    public void setAge(int age) {
        this.age = age;
    }

    @Inject
    public String getName() {
        return name;
    }
    @Inject
    public int getAge() {
        return age;
    }
  }
* ```java
  public final class DaggerPersonComponent implements PersonComponent {
  ...
  ..
  
      @Override
      public void inject(PersonB personB) {
        injectPersonB(personB);}

     private PersonB injectPersonB(PersonB instance) {
       PersonB_MembersInjector.injectName(instance, PersonModule_ProvideNameFactory.provideName(personModule));
       PersonB_MembersInjector.injectSetAge(instance, personModule.provideAge());
       PersonB_MembersInjector.injectGetName(instance);
        PersonB_MembersInjector.injectGetAge(instance);
        return instance;
      }
  ....
  ...
