WeakRefrence, SoftReference
===
접근성
---
* GC는 Reference 강약에 따라 쓰레기로 처리할지 말지 판단을 한다.
Strong Reference
---
* **GC에서 제외됨**
* 누가 지우지않는 이상 절대 안없어짐
  * **메모리 누수** 가능성

Soft Reference
---
* 메모리가 충분하다면 GC에서 내비두고 **메모리 없으면 GC에서 삭제**

WeakRefrence
---
* GC발생하면 무조건 없애버림
* 짧은시간 자주 쓰이는 객체를 위해 
