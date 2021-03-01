exam_3_3
===
> 두 Int 값을 더하는 함수를 작성하라.
* ```kotlin
  val add: (Int) -> (Int) -> Int = { a -> { b -> a + b } }
  
  // using typealias 
  typealias IntBinOp = (Int) -> (Int) -> Int // 이런것을 정수 이항 연산(Integer Binary Operation) 이라고 한다.
  
  val add: IntBinOp = { a -> { b -> a + b} }
  val mult: IntBinOp = { a -> { b -> a * b } }
