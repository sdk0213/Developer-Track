### Lazy
* 객체가 초기화되는 데 시간이 필요하다면 Lazy<T> 사용
* ```java
  @Component(modules = CounterModule.class)
  public interface CounterComponent {
      void inject(Counter counter);
  }
* ```java
  @Module
  public class CounterModule {
      int next = 100;
  
      @Provides
      Integer provideInteger() {
          System.out.println("computing ... ");
          return next++;
      }
  }
* ```java
  public class Counter {
      @Inject
      Lazy<Integer> lazy;
  
      public void printLazy(){
          System.out.println("printing...");
          System.out.println(lazy.get());
          System.out.println(lazy.get());
          System.out.println(lazy.get());
      }
  }
* ```java
  @Test
  public void testLazy() {
      CounterComponent component = DaggerCounterComponent.create();
      Counter counter = new Counter();
      component.inject(counter);
      counter.printLazy();
  
  // 출력결과
  // printing...
  // computing...
  // 100
  // 100
  // 100
          
---
### Provider<T>
* 매번 새로운 인스턴스를 주입하고 싶을때 Provider<T> 사용
* get() 메서드를 호출할때마다 새로운 객체를 제공받는다.
  * get() 메서드를 실행할때마다 다시 Inject 된다고 볼수 있다. 자동완성된 모듈을 잘 살펴보면 get이 어떻게 사용되는지 확인할수있다.
  * ```java

    // 1. 반환값으로 @Provides 된것을이 Provider 객체로 만들어진다.
    private final Provider<String> nameProvider; 
    private final Provider<Integer> ageProvider;
    
    ...
    ..
  
    // 2. 여기서 보면 만들어진 Provider 객체를 get을 통해 가져오는것으 확인가능한데 이를 보면은 새로운 객체를 만들때마다 get으로 가져오는것을 문맥상 가능하며 이는 새로운 객체를 만든다는것을 유추 가능하다.
    @Override
    public PersonA get() {  
        return module_Provides_PersonA_Person(module, nameProvider.get(), ageProvider.get());
    }
  

* 하지만 Singleton으로 범위가 지정되어있다면 같은 인스턴스로 지정되어서 효과가없다.
* ```java
  public class Counter {
    
      @Inject
      Provider<Integer> provider;
      
      public void printProvider() {
          System.out.println("printing...");
          System.out.println(provider.get());
          System.out.println(provider.get());
          System.out.println(provider.get());
      }
  }
* ```java
  @Test
  public void testProvider() {
      CounterComponent component = DaggerCounterComponent.create();
      Counter counter = new Counter();
      component.inject(counter);
      counter.printProvider();
  }
