Spring Contant - [출처 - dydtjr1128.github](https://dydtjr1128.github.io/java/2019/04/23/JAVA-String.html)
---
* 클래스와 같은 Heap의 Permanent area(고정 영역)에 생성되어 Java 프로세스의 종료까지 그 생을 함께 한다.
* 기본적으로 JVM에서 관리하며 프로그래머가 작성한 상수에 대해서 최우선적으로 찾아보고 없으면 상수풀에 추가한 이후 그 주소값을 리턴한다.
* ![](https://t1.daumcdn.net/cfile/tistory/246E2C33566532F115)
* 위 사진은 예전것이고 지금은 heap영역으로 들어갔다 => **GC의 대상이된다.
  * JAVA 7부터 OutOfMemoryException 방지를 위해서 수정됨
* ![출처 - dydtjr1128.github](https://dydtjr1128.github.io/img/Java/String%20literal.png)
* string a, string b 전부 같은 값을 참조한다.왜냐하면 같은 값이라면 같은것을 참조하기 때문이다.
* new로 생성할경우 새롭게 heap영역에 생성되는것이기 때문에 a==c 는 false가 떨어진다.
* 만약 new로 생성한것을 intern()을 사용한다면 해당 문자열이 상수풀에 이미 있는 경우에는 그 문자열의 주소값을 반환하고 없다면 새로 집어넣고 그 주소값을 반환한다.
literal
---
* 자바언어가 실제 처리하는 데이터
* 변수나 상수가 메모리의 할당된 공간이라면 literal은 공간에 할당된 값이다.
* ```java
  int a = 4
  // (a=변수) (4=리터럴)
* ```java
  const intergerNum = 123; // 정수인 number리터럴
  const floatingPointNum = 54.5413; // 실수인(소수점) number리터럴
  const str = '글자'; // 문자 리터럴
  const func = function (a) { return a;} // 함수 리터럴
  const obj = { x: 123, y : 456} // 객체 리터럴
  const regex = /[a-z]/; // 정규표현식 리터럴
  const arr = [1,2,3]; // 배열 리터럴
  const bool = true; // boolean 리터럴
