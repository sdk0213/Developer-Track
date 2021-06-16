### this@[class]
---
* **해당 위치에서 가장 가까운 범위의 클래스를 의미**
* 함수 내부에서 외부에 접근할 수 있는 스코프를 나타내는데 Kotlin에서는 implicit label 을 이용해 기존 Java에서 접근하기 힘들었던 부분까지 this 키워드로 접근이 가능
* 장점
  * this가 무엇을 말하는지 쉽게 알수있다는 장점이 있는것같기도 하다.-> 확실치 않음
* 사용상 주의점
  * 과도한 사용은 코드르 읽는데 혼란을 가져온다.
* ```kotlin
  class OuterClass { 
    fun alphabet() = with(StringBuilder()) { 
      for (letter in 'A'..'Z') { 
        append(letter) // this = stringBuilder() 생략가능 = 원래는 this.append
      } 
      append("\nNow I know the alphabet!") // this = stringBuilder() 생략가능 = 원래는 this.append
      println(this@OuterClass.toString()) toString()  // @this 사용 
    }
  }
  
