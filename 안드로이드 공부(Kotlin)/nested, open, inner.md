### nested - [출처는 thdev님의 tech 블로그](https://thdev.tech/kotlin/2020/11/17/kotlin_effective_11/)
* ||java|kotlin|
  |:--:|:--:|:--:|
  |Nested classes|static class InnerClass|class InnerClass|
  |inner classes|class InnerClass|inner class InnerClass|
* 코틀린은 기본은 Nested Class
* Nested Class 필요할때
  * Outer의 멤버를 참조할 필요가 없다면 굳이 inner 키워드를 사용치 않아야 한다.
  * Inner classes를 보호하고 싶다면 private를 명시해라.
  * 수정 가능한 상태로 두고 싶다면 open 키워드를 명시할 수 있다.
* android에서 ViewHolder는 왠만해서는 nested 로 지정하는것이 좋다.
---
### open
* ||java|kotlin|
  |:--:|:--:|:--:|
  |default|모두 상속가능|모두 상속불가|
  |기본 지정자(final)|X|O|
* 상속이 불가능한 코틀린 언어에서 상속이 가능하도록 하는 키워드
* open -> 상속, 오버라이드 
---
### inner
* 코틀린에서는 **한 클래스안에 다른 클래스를 정의하면 기본적으로는 중첩 클래스**가 되고, **내부 클래스로 만들고 싶다면 inner 키워드**로 클래스를 선언해야 한다
* 주의점
  * Outer 클래스를 참조하기에 메모리 누수가능성이 있다.
* ```kotlin
  // inner class 내부 클래스
  class Outer {
      private val bar: Int = 1
      inner class Inner {
          fun foo() = bar
      }
  }

  val demo = Outer().Inner().foo() // == 1
 
  // Inner 클래스가 Outer 클래스를 참조하기 때문에 bar 를 바라본다.
