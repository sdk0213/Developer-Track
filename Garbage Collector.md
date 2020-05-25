Garbage Collector
===

GC - [출처 - @litien ](https://velog.io/@litien/%EA%B0%80%EB%B9%84%EC%A7%80-%EC%BB%AC%EB%A0%89%ED%84%B0GC)
---
* Garbage Collector
* 가비지 컬렉터는 사용되지 않는 메모리를 정리해준다.
* 아래와 같은 코드에서 garbage가 힘을 발휘힌다.
* ```c
  String[] array = new String[2];

  array[0] = '0';
  array[1] = '1';

  array = new String[] {'G', 'C' };
  ```
  **위와 같은 코드에서 String으로 선언되기전 0과 1은 array를 참조하다가 주소를 잃어버리게 되는데 이때 메모리낭비가 되지 않게 Garbage Collector가 정리해준다.**
* Java에서는 GC가 메모리가 낭비되지 않게 꾸준히 관리해주므로 굉장히 편리하다
* 하지만 GC가 메모리 누수의 100%를 책임지지는 않는다.

Reachablity
---
* GC는 어떤 객체의 유효한 참조가 있는 기준에 따라서 다음과 같이 나눈다.
  * reachable
  * unreachable -> Garbage
* Weak Reference는 new로 할당된 객체의 유효 참조를 인위적으로 설정 할 수 있게 해주기 때문에,원래의 데이터가 삭제되면 이 객체에 Weak Refrence가 걸려있는 객체들은 모두 가비지로 인식된다 - [WeakReference 란 ? ](https://github.com/sdk0213/Android-Develop-Info/blob/master/WeakRefrence%2C%20Strong%20Reference%2C%20SoftReference.md)
