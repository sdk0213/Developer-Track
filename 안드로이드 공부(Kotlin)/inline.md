# inline - [출처는 thdev님의 tech 블로그](https://thdev.tech/kotlin/2020/09/29/kotlin_effective_04/)
---
### [c언어 에서의 inline ?](https://github.com/sdk0213/Developer-Track/blob/master/프로그래머%20기초/C언어%20기초/inline.md)
* 기존
  * 코드에서 함수를 호출할경우 해당 함수를 들어갔다 라인들을 실행후에 다시 빠져나온다.
* inline
  * 실행시점에는 해당 코드가 아예 호출한 부분에서 처리된다. 즉 함수의 내용 그대로 호출한곳에서 실행된다. 함수를 거치지 않기에 조금 더 빠르다.
---
### kotlin 에서의 inline은 c와 같다.
* ```kotlin
  inline class Password(val value: String)

  val password: String = "Your password"
  // vs(versus)
  val password = Password("Your password")
  
### [활용 1: 동일한 타입에 비슷한 이름일경우 실수 방지](https://thdev.tech/kotlin/2020/09/29/kotlin_effective_04/)
* ```kotlin
  data class CarInfoResponse(
     val index: Long,
     val indexId: Long,
     val indexId2: Long
  )
* ```kotlin
  inline class Index(val index: Long)
  inline class IndexId(val index: Long)
  inline class IndexId2(val index: Long)

  data class UserInfoResponse(
    val index: Index,
    val indexId: IndexId,
    val indexId2: IndexId2
  )
---
### 활용 2: Higher-Order functions 사용시
* 아래와 같이 사용할 경우 anonymous class 생성 하는 코드가 사라지므로 생성비용이 절약되어 코드가 조금더 빨라진다. --> collection 에서 활용시 속도 차이 날수있을 정도..
* ```kotlin
  inline fun String?.notNull(body: String.() -> Unit) {
      this?.body()
  }

  fun test() {
      "aaa".notNull {
          println("this message $this")
      }
  }
---
* ```kotlin
  inline fun loadData(
      loadCache: Boolean,
      noinline onLoaded: () -> Unit, // noinline
      crossinline onCache: () -> Unit // crossinline
  ) {
      if (loadCache.not()) {
          runNetwork("online", onLoaded) // 파라미터로 전달해야하기때문에 noinline 명시
      } else {
          runNetwork("local?") { // block {} 의 형태로 파라미터를 전달하기 때문에 crossline 명시
              onLoaded()
              // cache...
              onCache()
          }
      }
  }

  fun runNetwork(url: String, callback: () -> Unit) {
      // ...
  }
### [noinline](https://thdev.tech/kotlin/2020/09/29/kotlin_effective_04/)
* **noinline은 파라메터로 제3의 함수에 전달할 때 붙인다**
  * 출처 참고
---
### crossline
* 파라미터를 block {} 형태로 전달해줄때 사용 
  * 출처 참고
---
# 결론
* **이름을 명확히 해야할때나 함수 형태의 파라미터로 전달이 필요할때는 되도록 inline을 사용한다. 왜냐하며 성능이 향상되기 때문에... 하지만 이외에 기본 파라미터는 그냥 jvm이 처리해주는것에 의존하도록**
