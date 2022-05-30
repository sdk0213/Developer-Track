# SOLID (SRP, OCP, LSP, ISP, DIP)
* 응집도는 높이고 결합도는 낮추자는 고전 원칙을 객체 지향 관점에서 재정립한 것
---
### SRP (Single Responsibility Principle) - 단일 책임 원칙
* **"어떤 클래스를 변경해야 하는 이유는 오직 하나 뿐이여야 한다."**
---
### OCP (Open Closed Principle) - 개방 폐쇄 원칙
* **"자신의 확장에는 열려있고, 주변의 변화에 대해서는 닫혀 있어야 한다."**
* 해당 원칙을 위반할경우 코드 수정 매우 많아진다.
---
### LSP (Liskov Substitution Principle) - 리스코프 치환 원칙
* **"프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다."**
* 상속관계 똑바로 처리하라는 뜻이다. 자식 객체는 부모 객체의 역할을 포함해야한다.
* 직사각형-정사각형 문제
 * 아래는 LSP 를 위반한다. 왜냐하면 해당 기능이 정사각형에서는 가로와 세로 모두를 증가시키는데 해당 기능의 의미와 맞지 않기 때문이다. 즉, Square 클래스는 상위 클래스인 Rectangle 의 기능을 온전히 사용할수없으므로 Rectangle 클래스를 확장해서는 안된다.
 * 만약에 increaseHeight() 와 같은 함수를 사용하지 않을경우에는 LSP 를 위반한다고 볼수는없다.
 * ```java   
   public class Square extends Rectangle 
 * ```java
   public void increaseHeight(Rectangle rec) {
     if(rec.getHeight() <= rec.getWidth()) {
       rec.setHeight(rec.getWidth() + 1);  // <-------- LSP 위반

     }

   }
* 리스코프 위반하면 대표적으로 자바의 instanceof 연산자을 사용하게됨
* 리스코프 치환 원칙이 위반되면 개방 폐쇄 원칙도 못지킴
---
### ISP (Interface Segregation Principle) - 인터페이스 분리 원칙
* **"특정 클라이언트를 위한 인터페이스 여러 개가 범용 인스퍼에스 하나보다 낫다"**
* 인터페이스 설계를 똑바로 하라는 얘기다. 아이폰과 갤럭시 폰의 기본기능 인터페이스에 뜬금없이 아이폰전용 Airplay를 넣거나 두 회사의 특정 년도 이상 제품에서만 가능한 OO페이를 공통으로 넣어버리면 안된다.
* 인터페이스를 각각 쪼개서 사용하는 인터페이스만 구현해준다. 그렇지만 너무 쪼개다 보면 원하지 않게 인터페이스가 어마어마하게 많아 질 수도 있다
  * 잘 설계해야한다.
---
### DIP (Dependency Inversion Principle) - 의존 역전 원칙
* **"추상화에 의존해야지, 구체화에 의존하면 안된다."**
