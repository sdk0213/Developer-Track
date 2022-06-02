# volatile - 휘발성 물질이라는 뜻
* java 예약어이다.

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
    
