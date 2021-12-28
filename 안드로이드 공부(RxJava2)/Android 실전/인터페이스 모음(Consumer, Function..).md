### Function< T, R >
* 연관
  * map
    ```java
    public final <R> Flowable<R> map(Function<? super T, ? extends R> mapper) {
* T 를 R 로 반환
* ```java
  public interface Function<T, R> {
    R apply(@NonNull T t) throws Exception;
  }
---
### Consumer< T >
* 연관
  * subscribe
    ```java
    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
* T 를 받아서 수행
* ```java
  public interface Consumer<T> {
    void accept(T t) throws Exception;
  }
--- 
### Predicate< T >
* 연관
  * <img width="450" alt="스크린샷 2021-12-28 오전 9 56 01" src="https://user-images.githubusercontent.com/51182964/147516464-b2fe2bc1-6dd4-4ce4-9d21-f5db612f972a.png">
* ```java
  public interface Predicate<T> {
    boolean test(@NonNull T t) throws Exception;
  }
--- 
### Callable< T >, Supplier< T >
* Callable
  * 2.x 버전
* Supplier
  * 3.x 버전
* 연관
  *  다른 스레드에 의해 잠재적으로 실행될 수 있는 Exception
* ```java
  @FunctionalInterface
  public interface Callable<V> {

      V call() throws Exception;
  }

  @FunctionalInterface
  public interface Supplier<@NonNull T> {
  
      T get() throws Throwable;
  }
---
### 이 외
* ```java
  public interface BiPredicate<T1, T2> {
  
      boolean test(@NonNull T1 t1, @NonNull T2 t2) throws Exception;
  }
* ```java
  public interface BiConsumer<T1, T2> {
      void accept(T1 t1, T2 t2) throws Exception;
  }
* ```java
  public interface BiFunction<T1, T2, R> {

      @NonNull
      R apply(@NonNull T1 t1, @NonNull T2 t2) throws Exception;
  }
* ```java
  public interface UnaryOperator<T> extends Function<T, T> {
     static <T> UnaryOperator<T> identity() {
          throw new RuntimeException("Stub!");
     }
  }
* ```java
  @FunctionalInterface
  public interface BinaryOperator<T> extends BiFunction<T, T, T> {
      static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
          throw new RuntimeException("Stub!");
      }

      static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
          throw new RuntimeException("Stub!");
      }
  }
