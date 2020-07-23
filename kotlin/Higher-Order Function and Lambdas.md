Higher-Order Function and Lambdas
===
### 출처 : [ devlog of ShinJe Kim](https://shinjekim.github.io/kotlin/2019/09/05/Kotlin-%EA%B3%A0%EC%B0%A8%EC%9B%90-%ED%95%A8%EC%88%98%EC%99%80-%EB%9E%8C%EB%8B%A4/)

Higher-Order Functions
---
* HOF 또는 고차함수라고 부른다.
* **함수를 다루는 함수 == 함수를 인자로 받거나 결과로 반환하는 함수를 고차 함수**
  * 고차 함수는 매개변수와 반환에 함수를 이용할 수 있는 특징이 있다.
* 함수는 값(value)처럼 쓰일 수 있다
* 외부 상태 변경이나 가변(mutable) 데이터를 피하고 불변성(Immutability)을 지향하는 함수형 프로그래밍에 기반을 두고 있다.
* [코드 출처 - kkangsnote.tistory](https://kkangsnote.tistory.com/72)
  ```kotlin
  fun hoFun(x1: Int, argFun: (Int) -> Int){
    val result = argFun(10)
    println("x1 : $x1, someFun1 : $result")
  }
  
  hoFun(10, {x -> x * x }) // 람다식으로 함수 전달

* 
  ```kotlin
  //인수: 숫자, 숫자, 하나의 숫자를 인수하는 반환값이 없는 함수
  fun add(x:Int,y:Int, callback:(sum:Int)->Unit){
     callback(x+y)
  }
 
  //함수는 {}로 감싸고 내부에서는 반환값을 it로 접근할 수있음
  add(5,3,{println(it)}) //8
