# Flow
---
### Flow 란
* 비동기 스트림
  * 기존 kotlin 개발자들이 Rxjava를 사용해서 비동기를 처리했던 와중에 Flow API 가 공개되면서 RxJava의 대안으로 떠올랐다.
* 연산도 되고 쓰레드를 정할수도 있다.
* coroutine builder 이다.
* java의 stream과 굉장히 똑같다.
* sequence 처럼 cold stream의 형태를 가져가기에 flow operators가 실행되기 전까지는 수행되지 않는다.
* 여러개의 중간 operator로 조합된 경우 코틀린에서의 sequence처럼 순차적으로 진행된다.
##### Room Flow
* 클린 아키텍쳐의 일환으로서 repository는 flow 흐름으로 두고 Livedata는 android viewmodel의 수준으로 나누어서 관리하는것이 좋다고 한다. 정확히 왜? 인지는 클린아키텍처가 더 공부가 필요
* 기본 데이터가 변경될 때 UI가 자동으로 업데이트되도록 하려면 Flow 객체를 반환하는 쿼리 메서드를 작성하세요.
* https://stackoverflow.com/questions/58890105/kotlin-flow-vs-livedata
  * So its recommended to keep flow in the repository level , and make the livedata a bridge between the UI and the repository !
* https://developer.android.com/training/data-storage/room/accessing-data?hl=ko#query-flow
* https://stanleykou.tistory.com/entry/httpsproandroiddevcomshould-we-choose-kotlins-stateflow-or-sharedflow-to-substitute-for-android-s-livedata-2d69f2bd6fa5
---
### [중요한것만 적어놓음 나머지 필요한 연산 정보는 전부 투덜이님의 coroutine 정리를 참고](https://tourspace.tistory.com/260?category=797357)
##### builder
* ```kotlin
  fun main(args: Array) = runBlocking {
      println("main start!")
      
      val flow1 = flowOf(1,2,3)
      flow1.collect( value -> println("flow1: $value)}
      
      println("////////////////")
      
      (1..3).asFlow().collect { value -> println("flow2: $value")}
      
      println("main end!")
      
      // result
      // main start!
      // flow1:1
      // flow1:2
      // flow1:3
      // /////////////////
      // flow2:1
      // flow2:2
      // flow2:3
      // main end!
##### Flow cancellation
* flow 자체에는 cancel 함수를 지원하지 않는다.
* ```kotlin
  fun foo(): Flow<Int> = flow { 
      for (i in 1..3) {
          delay(100) 
          println("Emitting $i") 
          emit(i) 
      } 
  } 
  
  fun main() = runBlocking { 
      println("main start!") 
      withTimeoutOrNull(250) { // Timeout after 250ms 
          foo().collect { value -> println(value) } 
      } 
      println("main end!") }
  }
* 또는 다음과 같은 방법으로 취소 가능
  ```kotlin
  fun main(args: Array) = runBlocking { 
      println("main start!") 
      val fooLaunch = launch { // Timeout after 250ms 
          foo3().collect { value -> println(value) } 
      } 
      delay(250) 
      fooLaunch.cancel() 
      println("main end!") 
  }
  
  // 위 코드와 아래 코드 결과는 아래처럼 동일
  // result : main start!
  // Emitting 1
  // 1
  // Emitting 2
  // 2
  // main end!
##### flowOn operator
* ```kotlin
  fun foo(): Flow<Int> = flow { 
     for (i in 1..3) { 
         Thread.sleep(100) // pretend we are computing it in CPU-consuming way 
         log("Emitting $i") 
         emit(i) // emit next value 
     } 
  }.flowOn(Dispatchers.Default)
   
  fun log(msg: String) = println("[${Thread.currentThread().name}] $msg") // result

---
### StateFlow
* 현재 상태와 새로운 상태 업데이트를 수집기에 내보내는 식별 가능한 상태 홀더 흐름
