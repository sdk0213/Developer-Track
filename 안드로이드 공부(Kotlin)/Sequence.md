# Sequence
---
### List가 가지는 단점
* List는 map()과 filter()를 이용해서 쉽게 원하는 결과를 얻을수 있지만 단점이 하나 있는데 연산을 하고 다시 list를 생성하는 방식이기 때문에 list 아이템이 많아질수록 연산과정에서 부하가 일어난다.
---
### sequence가 가지는 특징 - [출처 - 조용한 담장 - tistory](https://iosroid.tistory.com/79)
* lazy하게 최종 메서드가 실행될때 만들어진다.
* Sequences는 Lazy evaluation으로 연산을 수행하기 때문에 Collections보다 적은 연산으로 동일한 결과를 출력할 수 있습니다. **하지만 리스트의 개수가 적은 경우 Collections가 좋을 수 있습니다. 규모가 어느정도 있다고 생각이 되면 Sequences를 고려해보는 것이 좋을 것 같습니다.**
##### List로 생성
* ```kotlin
   val list = listOf("one", "two", "three")
   val sequenceFromList = list.asSequence()
   val sequence = sequenceOf("four", "three", "two", "one")
##### 직접 생성
val oddNumbers = generateSequence(1) { it + 2 } // `it` is the previous element
println(oddNumbers.take(5).toList()) // [1, 3, 5, 7, 9]
##### 유한한 개수 
generateSequence() 함수로 유한한 sequence 생성시에는 반드시 else null 을 처리해줘야 한다.
val oddNumbersLessThan10 = generateSequence(1) { if (it < 10) it + 2 else null }
println(oddNumbersLessThan10.count()) // 6
##### Chunks
* ```kotlin
  val oddNumbers = sequence { 
      yield(1) 
      yieldAll(listOf(3, 5)) 
      yieldAll(generateSequence(7) { it + 2 }) 
  } 
  
  println(oddNumbers.take(5).toList()) // [1, 3, 5, 7, 9]

### [List와 Sequence의 실제 수행 과정 비교는 여기서 확인](https://iosroid.tistory.com/79)
* 결론은 Sequence가 더 효율적이다.
