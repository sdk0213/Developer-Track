Singletone
===
Singletone
---
* 인스턴스가 남용되는 것은 바람직하지 않고 하나의 자원으로 모두가 공유해서 사용해야할때 필요
  * 하나의 객체만을 생성해 이후에 호출된 곳에서는 생성된 객체를 반환하여 **프로그램 전반에서 하나의 인스턴스만을 사용**하게 하는 패턴
* 예제 코드
  * ```java
    public class Printer {
      private static Printer printer = null;
    
      private Printer(){} // 기본생성자 불가능으로 변경
    
      public static Printer getInstance() { // 여기서만 생성가능 -> 다만 오직 하나만 생성가능
        if(printer == null) {               // static으로 선언해서 어디서든 가져올수 있도록 해야됨
          printer = new Printer();
        }
        return printer;
      }
    
      public void print(String input) {     
        System.out.println(input);
      }
    }
* 문제점은 아래코드와 같이 count의 값이 쓰레드로부터 완전히 보장받지 못하며 멀티쓰레드에서 우연히 getInstance가 여러개 생성될수있다.
  ```java
    public static Printer getInstance() {
      if(printer == null) {
        printer = new Printer();
      }
      return printer;
    }
    
    public void print(String input) {
      count++;
      System.out.println(input + "count : "+ count);
    }
    
* 해결책
  * ```java
    public synchronized static Printer getInstance() { // Synchronized 사용
      if (printer == null)
        printer = new RealPrinter();
      return printer;
    }
  * ```java
    public synchronized static void print(String input) {  // Synchronized 사용
      count++;
      System.out.println(input + "count : "+ count);
    } 
  * ```java
    public class Printer {
      private static Printer printer = new Printer(); // 하나만 일단 생성후
      private static int count = 0;
    
      private Printer(){}
    
      public static Printer getInstance() { // 오로지 가져오기만 하기
        return printer;
      }
      
* 그래도 사용하기 어려운이유
  * 결합도가 너무 높아짐 -> 유연성 매우 하락
  * 멀티쓰레드 대처하기에는 고려사항이 너무 많음
* 그래도 사용하는 이유
  * 대부분의 소프트웨어는 프로그램 동작중에 반드시 필요한 한 개정도는 메모리상에 하나씩 존재해야하고 이럴 때 싱글톤이 필요하기 때문
  * ~~메모리 낭비 없음~~
  * 다른 클래스에서 사용하기 쉬움
  * 성능면에서 더 나은 선택일수 있음
