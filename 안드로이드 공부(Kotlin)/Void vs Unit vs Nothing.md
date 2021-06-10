# Void vs Unit vs Nothing - [출처는 [kumgo1d님]](https://kumgo1d.tistory.com/61)[[readystory님]](https://readystory.tistory.com/143)
---
### Void
* 반환 값 생략
---
### Unit
* 반환 값 생략
* 코틀린 특성상 모드 참조형이기에 void(기본형) + Void(참조형) 두 가지 형태를 묶음으로 사용가능
---
### Nothing
* 차이점
  * Any -> 모든 타입의 **조상**
  * Nothing -> 모든 타입의 **자식**
* Nothing을 반환시 호출한 코드로 반환도 하지 않음
* 리턴 -> 안함
##### 1. TODO
* ```kotlin
  fun shouldReturnSomething() {
      TODO("make your code")
      println("hello") // --> Unreachable Code 에러
  }
##### 2. infinite Loop
* ```kotlin
  fun infiniteLoop(): Nothing {
       while (true) {
          println("Hi there!")
      }
  }
##### 3. Throw Exception
* ```kotlin
  fun throwException(): Nothing {
      throw IllegalStateException()
  }
##### 4. Nothing + ? -> can return Null
* ```kotlin
  fun mayThrowAnException(throwException: Boolean): Nothing? {
      return if (throwException) {
          throw IllegalStateException()
      } else {
          println("Exception not thrown :)")
          null
      }
  }
##### 5. 모든 타입의 자식
* nullableString -> String 타입
* throw IllegalStateException() -> Nothing 타입
* nullableValue가 Null 이기에 throw IllegalStateException() 의 반환값인 Nothing이 nullableString으로 들어가는데 
Nothing이 String 클래스를 포함하여 모든 타입의 자식이기 때문에 위 코드가 컴파일 에러없이 작동하는것이다.
* ```kotlin
  val nullableValue: String? = null
  val value = nullableString ?: throw IllegalStateException() 
