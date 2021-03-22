# SRP(Single Responsibility Principle) - 단일 책임 원칙
* **"어떤 클래스를 변경 해야 하는 이유는 오직 하나뿐 이어야 한다 - 로버트 C 마틴"**
* SOLID 원칙중 첫번째 원칙
---
### 책임을 나누어야한다.
* 하나의 클래스가 모든 책임을 지면안된다. 즉, 어떤 일을 처리해야하는데 해당 일을 분업하지 않고 하나의 클래스에 모든 클래스에 책임을 맡기면 나중에 엉터리가 될수있다.
* "식당 카운터에서는 돈을 계산해야지 요리를 하면 안된다."
##### 변경 전
* ```java
  public class BookService() {
    
      public void findBookById(String query) {
          // DB에서 찾아오기..
          // Log 파일에 찾은 정보 쓰기..
      }
  }
##### 변경 후
* ```java
  public class BookService() {
      
      public void findBookById(String query) {
          // DB에 찾아오기..
      }
      
      public void WriteLog(String log) {
          // Log 파일에 찾은 정보 쓰기..
      }
  }
  
---
### 공통인 부분이 있다면 상위 클래스를 상속받아서 사용하고 각각 차이점을 구현한다.
* 남자는 무조건 군대를 간다. 여자는 가지 않는다 라고 가정을 했을 때 사람 클래스가 정의되어 있다. 
이렇게 되면 남자 클래스는 군대를 가기 때문에 괜찮지만, 여자 클래스일 경우에는 문제의 소지가 있다. 
문제 해결을 위해 남자 클래스와 여자 클래스를 분할 하고 공통인 부분이 있다면 사람 클래스를 상속받아서 사용하고 각각 차이점을 구현하면 된다.

---
### if 문이 나오는 이유(메서드 분기처리)
* 단일 책임원칙을 지키지 않았기 떄문에
##### 변경 전
* ```java
  class Man {
      private boolean sex;
      
      public void setSex(boolean sex) {
      
          this.sex = sex;
      }
      
      public void pee() {
          
          if(sex == true) {
              System.out.println("서서 봅니다.");
          } else {
              System.out.println("앉아서 봅니다.");
          }
      }
  }
##### 변경 후
* ```java
  abstract class Man {
      abstract void pee();
  }
  
  class Male extends Man {
  
    @Override
    void pee() {
        System.out.println("서서 봅니다.");
    }
  }
  
  class Female extends Man {
  
    @Override
    void pee() {
        System.out.println("앉아서 봅니다.");
    }
  }
