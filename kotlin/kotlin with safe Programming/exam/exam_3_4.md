exam_3_4
===
* 두 함수를 합성하는 함수 값을 만들라.
* 여기서 다음 두 함수를 함수 값으로 다시 정의하고, 이 둘을 합성한 squareOfTiple을 만들어라.
  * 두 함수
    ```kotlin
    fun square(n: Int) = n * n
    fun triple(n: Int) = n * 3
* 위 두함수는 (Int) -> Int로 가고 이를 만약 T라고 치환하였다면 전체적인 구조는 다음과 같이 표현할수 있다.
  * (T) -> (T) -> T
  * **쉽게 말해서 Int를 받아 Int를 반환하는 함수가 반환한값이 결과적으로 다시 인자가 되어서 들어가는 헝태다**
* ```kotlin
  val compose: ((Int) -> Int) -> ((Int) -> Int) -> (Int) -> Int =
    { x -> { y -> { z -> x(y(z)) } } }
    
  // 타입 추론 사용
  val compose = { x: (Int) -> Int -> { y: (Int) -> Int ->
    { z: Int -> x(y(z)) } } } 

  // 타입 별명
  typealias T = (Int) -> Int
  val compose: (T) -> (T)  -> T = { x -> { y -> { z -> x(y(z)) } } }
  val square: T = { it * it }
  val triple: T = { it * 3 }
  // triple이 먼저 적용되고 square가 적용된다.
  val squareOfTriple = compose(sqaure)(triple) 
  println(squareOfTriple(2))
  // result:
  // 36
