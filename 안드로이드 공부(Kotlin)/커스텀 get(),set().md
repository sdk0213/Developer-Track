# Getter와 Setter를 커스텀
#### 자동생성
* 코틀린에서 게터와 세터는 별도의 작업없이 자동으로 생성되어 "." 를 통해 사용
#### 커스텀 getter, setter 생성
* ```kotlin
  class Person {
    var name: String
    var age: Int // 해당 변수 밑에다가 생성하면 커스텀된것으로 작동함
      get() {
        println("execute getter")
        return field
      }
      set(value: Int) {
        field = value
      }
   }
#### 주의점
* ```kotlin
  var age: Int
    get() {
      return this.age
    }
* 위 코드는 무한 루틴이다. 왜냐하면 this.age 자체가 get이기 때문이다.
