# Callable And Future 클래스 - [출처는 emong(에몽이)님의 블로그](https://emong.tistory.com/221)
### 반환값을 받는 스레드
##### Runnable vs Callable
* ||Runnable|Callable|
  |:--:|:--:|:--:|
  |반한값|X|O|
  |반환형태|N/A|Future<T>|
  
##### Callable
* ```java
  Callable<Integer>> task = () -> {
      try {
          TimeUnit.SEOCNDS.sleep(1);
          return 123;
      } catch(InterruptedException e) {
          throw new IllegalStateException("task interrupted", e);
      }
  }
---
### Future  
##### 기본
* 근데 쓰레드에서 반환값을 주면 머하나 어짜피 미래에 끝나는뎨 -> 그래서 나온것이 Future
* ```java
  ExecutorService executor = Executors.newFixedThreadPool(1);
  Future<Integer> future = executor.submit(task);

  System.out.println("future done? " + future.isDone());

  Integer result = future.get();

  System.out.println("future done? " + future.isDone());
  System.out.print("result: " + result);
  
* Future.get()
  * 리턴 값 줄때까지 무한정 기다린다.
* Future.get(1, TimeUnit.SECONDS);
  * 리턴 값 줄때까지 대기시간 한정하기
##### 리스트 형태
* ```java
  ExecutorService executor = Executors.newWorkStealingPool();

  List<Callable<String>> callables = Arrays.asList(
          () -> "task1",
          () -> "task2",
          () -> "task3");

  executor.invokeAll(callables)
      .stream()
      .map(future -> {
          try {
              return future.get();
          }
          catch (Exception e) {
              throw new IllegalStateException(e);
          }
      })
      .forEach(System.out::println); 
---
### [기타 Callable에 대한 추가적인 기법은 emong(에몽이)님의 블로그 참고](https://emong.tistory.com/221)
