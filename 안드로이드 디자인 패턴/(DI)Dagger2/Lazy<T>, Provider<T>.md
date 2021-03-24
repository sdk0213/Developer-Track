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
