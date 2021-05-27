# invoke - [출처 - wooooooak님의 github 블로그](https://wooooooak.github.io/kotlin/2019/03/21/kotlin_invoke/)
---
### invoke란
* 사전적의미 : 호출하다, 작동시키다, 불러오다.
* 연산자이다. 단 이름없이 호출가능한 연산자이다.
##### 이름이 없다는것이 무슨말일까?
* ```kotlin
  object MyFunction {
      operator fun invoke(str: String) : String { 
          return str.toUpperCase() // To upper case letter
      }
  }
* 그러니까 통상 함수를 호출할때 해당 이름을 호출해야 되지만 이름이 없이 호출하기에 다음과 같이 이름을 명시하지 않고 호출가능하다.
* ```kotlin
  // USING
  MyFunction.invoke("hello one")
  // 위의 코드와 아래코드는 동일하게 취급된다.
  MyFunciotn("hello two")
  
  // result:
  // HELLO ONE
  // HELLO TWO
---
### invoke는 사실 람다에서 쓰인다.
* 결국에느 invoke르 가지 object로 변환된다.
* ```kotlin
  val toUpperCase1 = { str: String -> str.toUpperCase() }
 
  val toUpperCase2 = object: Function1<String, String> {
      override operator fun invoke(str: String): String{
          return str.toUpperCase()
      }
  }
  
  println(toUpperCase1("abcd"))
  println(toUpperCase2("efgh"))
  
  // result:
  // ABCD
  // EFGH
##### 참고: Function1 은 코틀린 표준 라이브러리에 정의된 Function1<P1, R>인터페이스 타입
* ```kotlin
  package kotlin
 
  public interface Function<out R>  
* ```kotlin
  package kotlin.jvm.functions
  
  public interface Function1<in P1, out R> : Function<R> {
    /** Invokes the function with the specified argument. */
    public operator fun invoke(p1: P1): R
  }
