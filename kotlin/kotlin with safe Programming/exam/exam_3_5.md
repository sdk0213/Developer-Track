exam_3_5
===
> 다형적 highercompose 함수를 작성하라.
* ```kotlin
  val <T, U, V> highercompose:  ((U) -> V) -> ((T) -> U) -> (T) -> V = { f -> { g -> { x -> f(g(x)) } } }
  
  // 다형적 프로퍼티를 정의불가능 -> val 는 상수를 의미한다. 아마 제네릭타입을 사용불가능하다는 뜻 같다.
  // 그러므로 fun을 사용하여야 한다.
  
  fun <T, U, V> higherCompose(): ((U) -> V) -> ((T) -> U) -> (T) -> V = { f -> { g -> { x -> f(g(x)) } } }
  // 'fun'이라는 함수로 정의했지만 이것은 아무 파라미터도 받지 않기 때문에 항상 같은 값을 반환한다.
  // 따라서 이 함수는 상수다.
  // 단지 함수를 합성하는 함수 값을 반환해주는 fun함수일뿐이다.
  fun <T, U, V> higherCompose() = { f: (U) -> V -> { g: (T) -> U -> { x: -> f(g(x)) } } }

  // 그리고 이제 완성한 higherCompise를 활용해 triple과 square를 합성하면 된다.
  val squareOfTriple = higherCompose()(square)(triple)

  // result:
  // Error:(79, 24) Kotlin: Type inference failed:
  // Not enough information to infer parameter T in 
  // fun <T, U, V> higher.................. -> V
  // please specify it explicitly

  // T U V가 진짜 타입을 추론할수 없다.. 이유는 나도 모르겠다. 그래서 타입을 직접 넣어줘야한다.
  val squareOfTiple = higherCompose<Int, Int, Int>()(triple)(square)
