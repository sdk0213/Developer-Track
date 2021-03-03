inner, outer, nested
===

Outer Class - [출처 - codecacha.com](https://codechacha.com/ko/kotlin-nested-classes/)
---


Static Nested Class
---
* 정적 중첩 클래스
  ```Java
  class OuterClass {
    ...
    static class StaticNestedClass {
        ...
    }
  }
  ```
* 정적 중첩 클래스는 외부에서 생성이 가능하고 정적이기 때문에 OuterClass의 내부 변수에 접근이 불가능
* Nested는 '중첩'이라는 뜻이다.
* **외부에서 OuterClass.new StaticNestedClass로 생성가능**


Non-static Nested Class, Inner Class
---
* 비정적 중첩 클래스(Non-static nested class)는 Inner class라고 한다.
* ```java
  class OuterClass {
    ...
    class InnerClass {
        ...
    }
  }
  ```
* **외부에서 OuterClass.new InnerClass로 생성불가**
* ![inner out 차이](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20기초/img/innerout.png)
*  InnerClass 내부에서 OuterClass의 내부변수에 접근이 가능하다
  * 즉 OuterClass를 참조할수있음을 뜻하며 이는 메모리누수의 원인이 될수있다.
