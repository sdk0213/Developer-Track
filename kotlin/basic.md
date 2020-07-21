Kotlin
===
* 출처 - https://academy.realm.io/kr/posts/kotlin-1/ 
* Kotlin은 클래스 없이 실행이 가능하다.
  * 메서드들은 파일명을 기준으로 자동으로 클래스가 생성되고 스태틱 메서드가 만들어진다.

fun
---
* 함수를 뜻한다. (물론 자바에는 함수라는 개념이없고 전부다 
* 예제 코드
* ![](/img/kotlin1.png)
* 함수 생략가능
  * 함수 짧게하기
    ```kotlin
    fun sum(a: Int, b: Int): Int = a + b
  * 반환값 추론으로 생략가능
    ```kotlin
    fun sum(a: Int, b: Int) = a + b
  * 함수를 바로 생성및 활용 (사실 아래 코드와 같은경우 의미가 없을수도 있지만 예시용으로)
    ```kotlin
    fun main(args: Array<String>) {
      val a = { a: Int, b: Int -> a + b }(3, 5)  // 변수 a = { 매개변수,매개변수 -> return }(값, 값)
      println("Hello Realm! $a")
    }
  * 조건문을 다음과같이 짧게 쓰기
    ```kotlin
    fun max(a: Int, b: Int) = if (a > b) a else b

var,val
---
* var
  * 변수
  * **타입알아서 추론되어서 적을필요없음 -> 매우 편함**
* val
  * 상수
* ```java
  fun sum(a: Int, b: Int): Int {
    return a + b
  }

  fun main(args: Array<String>) {
     val a = sum(5, 3) // 타입적고싶다면 val a: int = sum(5,3)
     println("Hello Realm! ${a}") // 변수랑 상수는 다음과 같이 중괄호 생략가능
     // println("Hello Realm! $a")
  }
  
  
# 출처 - https://academy.realm.io/kr/posts/kotlin-2/ 계속.....................

# 더나아가기 - https://ponyozzang.tistory.com/229?category=792393

