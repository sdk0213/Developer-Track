[참고링크 : 상상속동물](http://www.todayhumor.co.kr/board/view.php?table=programmer&no=11661)

callback
---
* 어떤 일 X를 B에게 명령하고 나는 할것다하다가 B가 일이 끝나면 나를 다시 호출하는것을 얘기한다.
  * **명령당한사람(노예)가 일 다 끝났다고 나를 다시부른다 **
* 예를들어서 마우스클릭이벤트 같은것들도 이놈이 나를 다시 호출하는것과 비슷하게 나를 호출하는것과 비슷해서 이와같이 쓰는것같다

개요
---
* mouse click이 발생했다고 하면, 이를 변화시키는건 back-end 단이고
  + middleware framework 쪽에서 객체의 mouse-click 관련 속성 값을 변화시키면
  + **observer**가 이를 보고 listener에게 통지하여
  + **listener**에 등록된 **callback**들을 호출하는 형식이 됩니다

* listener도 callback과 동작 원리는 같다.
  * callback이 어떤 작업이 종료되는 시점에 되호출( 호출한 쪽이 지정한 함수를 호출 ) 하는 것이라면,
listener는 등록된 callback들을 호출하는 역할을 합니다.


우선순위
---
1. View의 리스너
2. View의 콜백 메서드
3. Activity의 콜백 메서드

 
