# &#60;&#42;&#62; (Star-projections) - [출처는 zion830님의 티스토리](https://zion830.tistory.com/71)
### &#60;Any&#62; vs &#60;&#42;&#62;
* Any는 모든것을 받지만 &#42;는 **어떤 타입이 들어올수는 있으나 그 범위를 내가 한정하고 싶을때 사용**한다.
* &#42;를 사용할경우 내가 원하지 않는 타입이 들어왔을때 Error가 발생하기 때문에 조금 더 안전하다고 볼수있다.
* ```kotlin
  open class SuperClass
  class Child : SuperClass()

  open class TestClass

  class GenericClass<out T : SuperClass>() { }
  
  fun acceptStar(value: GenericClass<*>) {} // GenericClass안에 타입이 무엇이 들어갈지는 알수없으나 SuperClass 이하의 자식클래스만 받는것으로 허용해주었기 때문에 이 밖에 클래스가 들어올경우 syntax error가 발생한다.

  fun main() {
      acceptStar(GenericClass<Child>())
      acceptStar(GenericClass<SuperClass>())
      acceptStar(GenericClass<TestClass>()) // syntex error!
  }
