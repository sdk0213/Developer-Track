exam_3_5
===
> 다형적 compose 함수를 작성하라.
* ```kotlin
  val <T, U, V> highercompose:  ((U) -> V) -> ((T) -> U) -> (T) -> V = 
                                   { f -> { g -> { x -> f(g(x)) } } }
