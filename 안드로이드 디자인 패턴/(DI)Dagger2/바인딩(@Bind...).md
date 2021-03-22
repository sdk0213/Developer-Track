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
### BindsInstance - [이해 잘 안감]
* Component Builder Setter 또는 Factory 매개변수에 붙일수 있다.
* @Inject가 붙은 필드, 생성자, 메서드에 주입될 수 있다.
* **모듈말고 외부로부터 생성된 인스턴스를 빌더 또느 팩토리를 통해 넘겨줘 해당 인스턴스를 바인딩하게 한다.**
* 66~67 page
