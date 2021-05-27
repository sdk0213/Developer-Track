# object vs class
---
### class
* 말그대로 클래스를 정의한다.
---
### object
##### class + signleton = object (싱글톤)
* object를 키워드를 사용함으로서 singleton 정의 boilerplate code 를 절약할수있다.
* ```kotlin
  object SomeFactory {
      val somes = mutableListOf<Some>()
  
      fun makeSomething(parameters: Int) : Some {
          val some = Some(parameters)
          somes.add(Some)
          return some
      }
      
  }
  
  class Some(paramters: Int) {}
  
  // USING in somewhere
  fun main() {
      val some1 = SomeFactory.makeSomething(1) // SomeFactory를 아무리 생성해도 싱글톤이기 때문에 같은 객체(val somes = mutable..) 가져온다.
      val some2 = SomeFactory.makeSomething(2)
      val some3 = SomeFactory.makeSomething(3)
      println(SomeFactory.somes.size) // SomeFactory가 싱글톤으로 하나만 생성되었기에 결국에 같은 SomeFactory 클래스에 3개가 생성되었다.
  }
  
  // result:
  // 3
---
##### 번외(companion object 사용을 통한 singleton -> https://codechacha.com/ko/kotlin-object-vs-class/ 에서 확인
---
##### anonymous object (익명객체)
* 한번 사용하고 버릴 객체로서 이름도 필요없느 객체를 익명객체라고 한다.
* interface Some {
      fun function(): String
  }
  
  fun action(some: Some) = println(some.function())
  
  // USING 
  action(object : Some { // 익명 객체로 생성
      override fun function() = "THIS IS SOME FUNCTION"
  }
