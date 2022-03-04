# 자료구조_deque
---
* deque 란 Stack + Queue 의 형태로 앞과 뒤에서 넣고 뺄수있는 자료구조이다.
* 기본적인 사용방법은 Queue 매우 비슷하다.
* ```java
  Deque<Integer> dq = new ArrayDeque<>();
  dq.addFirst("Hello"); // 앞에다가 넣기 Excpetion 발생
  dq.offerFirst("Hello"); // 앞에다가 넣기 boolean 반환
  dq.addLast("World"); // 뒤에다가 넣기 Excpetion 발생
  dq.offerLast("World");  // 뒤에다가 넣기 boolean 반환
  dq.add("Hello"); // 앞에다가 넣기
  
  dq.removeFirst(); // 앞에값 삭제
  dq.pollFirst(); // 앞에값 꺼내기 및 삭제
  dq.removeLast(); // 마지막 삭제
  dq.pollLast();  // 마지막 꺼내기 및 삭제
  dq.remove("World6") // 특정 데이터 삭제
  dq.poll() // 앞에값 꺼내기
  dq.remove() // 앞에값 삭제
  dq.clear() // 초기화
