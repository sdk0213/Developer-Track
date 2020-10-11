exam_3_4
===
* 두 함수를 합성하는 함수 값을 만들라.
* 여기서 다음 두 함수를 함수 값으로 다시 정의하고, 이 둘을 합성한 squareOfTiple을 만들어라.
  * 두 함수
    ```kotlin
    fun square(n: Int) = n * n
    fun triple(n: Int) = n * 3
* ```kotlin
  val compose: ((Int) -> Int) -> ((Int) -> Int) -> (Int) -> Int =
    { x -> { y -> { z -> x(y(z)) } } }
    
  // 타입 추론 사용
  
  val compose = { x: (Int) -> Int -> { y: (Int) -> Int ->
    { z: Int -> x(y(z)) } } } 
    
