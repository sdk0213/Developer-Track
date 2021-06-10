# Executor
---
### Executor는 무엇일까? - [emong님의 블로그](https://emong.tistory.com/221)
* 스레드를 다루는 최상위 API
* Thread 다음 버전이라 생각하면 된다
* Executors는 작업(task)들을 비동기적으로 실행시킬 수 있으며 기본적으로 스레드 풀을 운영한다
* 생성되는 쓰레드를 제한(쓰레드풀을 운영하기 때문에..)하고 싶거나 이를 한번에 관리하고 싶을경우 Executor 를 사용한다.
* Java의 Executor 인터페이스
  ```java
  public interface Executor {

    /**
     * Executes the given command at some time in the future.  The command
     * may execute in a new thread, in a pooled thread, or in the calling
     * thread, at the discretion of the {@code Executor} implementation.
     *
     * @param command the runnable task
     * @throws RejectedExecutionException if this task cannot be
     * accepted for execution
     * @throws NullPointerException if command is null
     */
    void execute(Runnable command);
  }
---
### Executor를 관리하는 ExecutorService
##### 1. 기능
* void shutdown(); 
  * 진행중인 작업은 유지하되 새로운 작업은 불가능
* boolean isTerminated(); 
  * void shutdown() 처리중/진행완료 여부 파악
* List< Runnable > shutdownNow(); 
  * 현재 + 대기 작업 중지 및 대기 작업 반환
* boolean isShutdown(); 
  * Executor 종료여부 파악
* boolean awaitTermination(long timeout, TimeUnit unit); 
  * void shutdown()후 지정시간동안 종료 완료 -> ture, 아니면 -> false 반환
* [기타 추가적인 메서드는 여기 블로그에서 참고!](https://4urdev.tistory.com/104)
---
### execute() vs submit()
* |실행|execute()|submit()|
  |:--:|:--:|:--:|
  |반환값|X|O|
  |Exceptin 발생|해당 쓰레드 종료후 새롭게 생성|해당 쓰레드를 멈추고 그냥 다시 재활용|
* 오버헤드를 줄이기위해서는 submit()을 사용하는것이 좋다.
---
### 코드
* ```java
  ExecutorService executorService = Executors.newSingleThreadExecutor();
  // 2 개로 지정하고 싶을때는 아래와 같이 사용
  // 2 개로 지정하고 2개이상을 쓰레드로 
  // ExecutorService executorService = Executors.newFixedThreadPool(2); 
  executorrService.submit( () -> {
    String threadName = Thread.currentThread().getName();
    System.out.println("Hello I'm " + threadName);
  })
  
  
  // 여기서부터 아래는 일반적인 executor 종료 방법
  // shutdown 시도
  // 5초 내 종료안될시 강제종료
  // 아직도 종료안됬으면 현재 진행중인작업까지 전부다 종료
  try {
      System.out.println("attempt to shutdown executor");
      executor.shutdown();
      executor.awaitTermination(5, TimeUnit.SECONDS);
  }
  catch (InterruptedException e) {
      System.err.println("tasks interrupted");
  }
  finally {
      if (!executor.isTerminated()) {
        System.err.println("cancel non-finished tasks");
      }
      executor.shutdownNow();
      System.out.println("shutdown finished");
  }
  
  // => Hello I'm pool-1-thread-1 
* 
* **executor.shutdown() 처럼 명시적으로 종료하지 않으면 절대로 종료되지 않는다.**

