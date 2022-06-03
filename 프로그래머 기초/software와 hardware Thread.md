# Software / Hardware Thread 의 차이에 대하여
---
### 같은 말 다른 의미
* Software 에서는 Thread 무한정 만들수 있는것 같아 보인다. 예를들면 java 에서 다음과 같이 설정할경우 ThreadPool 이 100개가 생성된다.
  ```java
  Executors.newFixedThreadPool(100);
* 그런데 Hardware 스펙상에서도 Thread 라는 개념이 등장한다. 그러면 java 에서 선언한 Thread 랑 Hardware 스펙상의 Thread 랑 다른것일까?
### 물리와 가상에서 Thread 개념은 같다.
* 하지만 가상에서 Thread를 무한히 만들어냈다고 해서 모든 쓰레드는 물리 Thread 에서 처리하기 때문에 실제로는 물리 Thread 만큼만 처리된다.
* 처리되지 않는 나머지는 Thread 들은 대기하고 있거나 유휴상태이다.

### 쓰레드가 많다고 능사는 아니다.
* 프로세스 내 동일 Heap 을 공유하기 때문에 이를 보완하기 위해 동기화가 필요하다.
  * 이 때 lock(점유하고있으니 잠금) 의 문제로 OverHead 가 발생할수 있다.
* 멀티 쓰레드로 사용은 난이도가 높은 기술이므로 숙련이 필요하다. 잘못된 멀티 쓰레드의 사용은 안하느니 못하다.
* ~~Context switching 의 문제~~ 
  * ~~Context 는 Thread에 관한 정보이며 이를 교체하며 Thread 를 실행한다.~~
  * ~~이 때 switching 에 대한 시간 비용이 추가되며 이는 곧 속도저하가 될수도 있다.~~
  * 이는 운영체제에서 스케쥴러에 의해 처리되는 영역이므로 굳이 신경쓰지 않아도된다. 운영체제가 지능적으로 context switching 을 판단하여 진행한다. 일반적으로 매우 많은 Thread 를 생성하지 않는이상 Context Switching 으로 인한 성능저하는 없다고 보면된다.
