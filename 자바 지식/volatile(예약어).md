# volatile - 휘발성 물질이라는 뜻
* java 의 예약어로서 캐시된 값이 아닌 메모리에서 직접 가져오도록 한다. 가시성의 문제를 해결가능하다.
* 정리표
  |사용|특징|원리|
  |:--:|:--:|:--:|
  |volatile|쓰레드 경쟁상태에서 안전하지 않기 떄문에 하나의 스레드가쓰기 연산을 하고, 다른 스레드에서는 읽기 연산시 사용하면 된다.|캐시된 값이 아닌 메인메모리 사용|
  |Atomic***|여러 스레드에서 읽기 쓰기 모두, synchronized 보다 적은 비용으로 동시성을 보장할|현재 쓰레드에 저장된 값과 메인 메모리에 저장된 값을 비교하여 일치하는 경우 새로운 값으로 교체하고, 일치 하지 않는 다면 실패하고 재시도(CAS(Compare And Swap)), 내부적으로 volatile 을 사용은 한다.|
  |synchronized|여러 스레드에서 읽기 쓰기|Lock기법, synchronized 블락 진입전 후에 메인 메모리와 CPU 캐시 메모리의 값을 동기화|

### 예제
* thread  
  ```java
  package e.thread.volat;
  
  public class VolatileSample extends Thread {
    private double instanceVairable=0;
    
    void setDouble(double value) {
      this.instanceVariable = value;
    }
    
    public void run() {
      while (instanceVariable == 0){
      System.out.println(instanceVariable);
    }
    
* 실행
  ```java
  package e.thread.volat;
  
  public class RunVolatile {
    public static void main(String args[]) {
      RunVolatile sample = new RunVolatile();
      sample.runVolatileSample();
      
    }
    
    public void runVolatileSample() {
      Volatile sample new VolatileSample();
      sample.start();
      try {
        Thread.sleep(1000);
      
      } catch (Exception e) {
        // bula bula 
      }
      
      System.out.println("Sleep Ended !!!");
      sample.setDouble(-1);
      System.out.println("Set value is completed !!!");
      
    }
    
### 위 코드는 끝나지 않는다.
* 반복 참조는 cpu 처리속도 향상을 위해 참조값을 cpu 캐시에 넣어버린다. 그리고 당연히 0 이라고 가정하며 무한히 돌며 끝나지 않는다.
* 그리고 값 변경시 '서로 다른 CPU 캐시' 에 있는 instanceVariable 가 바뀐다.
### 해당 문제를 해결하기위해서 사용하는것이 volatile
* ```java
  private double instanceVairable=0;
  // 아래와 같이 예약어로 설정
  private volatile double instanceVairable=0;
  
### 하지만 volatile 은 최적화를 막을수 있다.
* 쓰레드의 속도 향상을 위해 캐시화 한것이였지만 평균적으로 캐시화 전략은 속도에 매우 중요하다.
* 그러므로 volatile 을 남발하면안된다.

### Thread.sleep(1) 만 걸어도 volatile 을 굳이 사용할 필요가 없다.
* 아래 코드는 예상대로 동작한다.
* ```java
  public void run() {
    try {
      while (instanceVariable == 0){
        Thread.sleep(1);
      }
    } catch (Exception e){
      // exception 처리
    }
    System.out.println(instanceVariable);
  }
    
