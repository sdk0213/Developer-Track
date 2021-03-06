# class -> interface 형변환
##### 예
* ```java
  interface D{
      public void a();
      public void b();
  }

  class ABC implements D{
      @Override
      public void a() {

      }

      @Override
      public void b() {
      }

      public void c(){
      }
  }


  class Exam1 {
     private D d;
     
     public Exam1(D d){ // 인터페이스를 객체로 받기
        this.d = d;
     }
  }
  
  
  class Exam2 {
     private ABC abc;
     
     public Exam1(ABC abc){ // 클래스를 객체로 받기
        this.abc = abc;
     }
  }
##### 사용시 에러 및 형변환시 다른점
```java
...somewhere main(){
    Exam1 exam1 = new Exam1(new ABC()); // ABC class -> interface로 형변한 되었기 때문에 ABC 클래스가 구현한 D의 인터페이스 메서드만 호출가능
    exam1.a();
    exam1.b();
    exam1.c(); // 형변환 되었기에 호출 불가능
    
    // 임시로 익명 인터페이스 구현
    Exam2 exam2 = new Exam2(new D(){ // Error - interface 를 class 형변환 불가능
      public void a();
      public void b();
    });

  }
