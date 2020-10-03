exam3_1
===
> Int에서 Int로 가는 함수의 합성을 허용하는 compose 함수를 작성하라.(fun을 사용하라)
* ```kotlin
  fun compose( f: (int) -> Int, g: (Int) -> Int): (Int) -> Int = {
    x -> f(g(x))
    // 또는
    f(g(it))
  }
* ```kotlin
  // 추후에 이해 
  val squareOfTriple = compose(::square, ::triple)
  
  println(squareOfTriple(2))
  
  // 36
