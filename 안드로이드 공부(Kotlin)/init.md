# init
---
### 생성자와 별도로 객체가 생성될때 실행되는 코드
* 이름 그대로 클래스 초기화과정에서 사용
* ```kotlin
  init{
    //do something
  }
* ```kotlin
  class C(nameParam: String) {

      val name: String

      init {
          if (nameParam.isEmpty()) {
              throw IllegalArgumentException("Error")
          }
          this.name = nameParam
      }
  }
