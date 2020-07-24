Wrapper type
===
Kotlin 안에서의 구분
---
* 자바
  * 원시타입
    * int, float, double..
  * Wrapper type
    * Integer, String ....
* 코틀린
  * 구분이 없다.
    * int + Integer -> Int
  * **컴파일시 자동으로 구분되어서 빌드된다. --> 매우 편하다.** 
  * 원시타입은 null이 불가능하기 때문에 null이 가능한것은 Wrapper type으로 변환된다.
    * B: int

Any
---
* 자바에서의 최상의 타입인 object가 있듯이 코틀린에는 Any가 있다.

Unit
---
* 자바에서의 void값은것

Nothing
---
* Nothing type은 Nothing type은 함수가 **정상적으로 끝나지 않는다**라는걸 명시적으로 표현하는 함수
* ```kotlin
  fun fail(message: String): Nothing {  // 정상적으로 끝나지 않는다
    throw IllegalStateException(message)  // throw
  }

  fun main(args: Array) {
    fail("Error occurred")
  }

  //Nothing 을 반환하는 함수를 엘비스 연산자의 우항에 사용해서 전제 조건을 검사할 수 있다.
  fun getCompany(person: Person) {
    val comp = Person.company ?: fail("No company info.") // fail호출
    return comp.name
  }
