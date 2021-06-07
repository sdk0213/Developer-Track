# reified - [출처는 [boilerplate님]](https://boilerplate.tistory.com/57)[[sungjk님]](https://sungjk.github.io/2019/09/07/kotlin-reified.html)
### reified 사전적의미 : 구체화 된 -> 개념을 구체화하다.
### 무엇을 할까?
* |시간|Compile|Runtime|
  |:--:|:--:|:--:|
  |Genric Type T 접근|불가능|가능|
* 런타임에 타입 T에 접근가능 -> 인스턴스 체크 가능(someVar is T)
### 주의 : reified 타입 파라미터로 작성된 인라인 함수는 자바 코드 불가능
### reified 가 없다면?? 
* ```kotlin
  // 컴파일 에러  
  fun <T> whoAmI(genericT: T){
      when (T::class) {
          String::class -> {
              println("i'm a string")
          }
          Int::class -> {
              println('i'm a Integer")
          }
      }
  }
  
  
  // 컴파일 에러가 나지 않을려면 다음과 같이 사용해야한다.
  fun <T> printWhatAmI(t: T) {
          if (t is String) {
              println("i'm String")
          } else if (t is Int) {
              println("i'm Int")
          }
   }
 
  fun <T> printWhatAmI(t: T, type: Class<T>) {
          when (type) {
              String::class -> {
                  println("i'm String") 
              }
              Int::class -> {
                  println("i'm Int")
              }
          }
  }
  
### reified 사용할경우는
* ```kotlin
  inline fun <reified T> printWhatAmI() {
        when (T::class) {
            String::class -> {
                println("i'm String")
            }
            Int::class -> {
                println("i'm Int")
            }
        }
  }
* 아주 깔끕하게 표현할수있다.
