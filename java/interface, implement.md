참고 : [Limky 삽질블로그](https://limkydev.tistory.com/197)

인터페이스
===

* 동일한 목적 하에 동일한 기능을 보장하게 하기 위한것
  * 과제 제출시 확장자통일(pdf,hwp,doc 등), 목차통일(기승전결) 과 같은것
  
대충만든 대화하는 TALK 인터페이스
---
    public interface Talk{
    
      public int MAX_TIME_MIN = 30; // 최대 대화 가능 시간
    
      void say(int what, int to, int time); // 말하기
    
      void hear(int what, int from, int time); // 듣기
    
      default String findwho(String when){ // 언제 누구랑 대화했는지 찾는 메서드
    
        return "이름은 OOO입니다"
      
      }
    
      static void isheFriend(String name){ // 그사람이 친구인지 구별해주는 메서드
    
        // 대충 name으로 이 사람이 나랑 친구인지 아닌지 구별해주는 메소드가 정의해져있다고 침
       
      }

     }
    
위 인터페이스를 클래스에서 implement해서 인터페이스 규격에 맞게 코드를 작성하면된다.

* 상수 : 인터페이스에서 값을 정해줄테니 함부로 바꾸지 말고 제공해주는 값만 참조해라 **(절대적)**

* 추상메소드 : 가이드만 줄테니 추상메소드를 오버라이팅해서 재구현해라. **(강제적)**

* 디폴트메소드 : 인터페이스에서 기본적으로 제공해주지만, 맘에 안들면 각자 구현해서 써라. **(선택적)**
  * 이미 운영되고 있는 시스템에서 추가 요건으로 인해 불가피하게 반영을 해야할 때 디폴트메소드를 쓰면 효과적

* 정적메소드 : 인터페이스에서 제공해주는 것으로 무조건 사용 **(절대적)**
 





