
동일성체크 - [출처 - namget.tistory](https://namget.tistory.com/entry/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9E%90%EB%A3%8C-%EC%9E%90%EB%A3%8C%ED%98%95%EC%9D%98-%ED%99%95%EC%9D%B8-%EB%B0%8F-%EB%B3%80%ED%99%98-is-as-%EC%8A%A4%EB%A7%88%ED%8A%B8-%EC%BA%90%EC%8A%A4%ED%8A%B8)
---
* 객체가 동일한지 확인할때 자바에서는 equal()을 사용했었음
* **kotlin은 == 을 사용하면 객체 + null 까지 확인가능 => 매우매우 편함**
* ```kotlin
  val aEqualsB: Boolean = a == b //a와 b 의 값은 동일 하기 때문에 true
  val aEqualsC: Boolean = a == c //a와 c 의 값은 동일 하기 때문에 true
  val aB: Boolean = a === b   //a와 b 의 값은 동일한 객체기 때문에 true
  val aC: Boolean = a === c   //a와 c의 값은 동일한 객체가 아니기 때문에 true

is (≒instanceOf in Java) - [출처 - namget.tistory](https://namget.tistory.com/entry/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9E%90%EB%A3%8C-%EC%9E%90%EB%A3%8C%ED%98%95%EC%9D%98-%ED%99%95%EC%9D%B8-%EB%B0%8F-%EB%B3%80%ED%99%98-is-as-%EC%8A%A4%EB%A7%88%ED%8A%B8-%EC%BA%90%EC%8A%A4%ED%8A%B8)
---
* java에서의 instanceOf 기능을 대체함
* ```kotlin
  fun isTypeName(obj: Any) {
      if (obj is Int) {
          Log.e("Type", "Type = Integer")
      } else if (obj is Float) {
          Log.e("Type", "Type = Float")
      } else if (obj is String) {
          Log.e("Type", "Type = String")
      }
  }


  fun notIsTypeName(obj: Any) {
      if (obj !is Int) {
          Log.e("Type", "Not Type = Integer")
      } else if (obj !is Float) {
          Log.e("Type", "Not Type = Float")
      } else if (obj !is String) {
          Log.e("Type", "Not Type = String")
      }
  }
  }

as - [출처 - namget.tistory](https://namget.tistory.com/entry/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9E%90%EB%A3%8C-%EC%9E%90%EB%A3%8C%ED%98%95%EC%9D%98-%ED%99%95%EC%9D%B8-%EB%B0%8F-%EB%B3%80%ED%99%98-is-as-%EC%8A%A4%EB%A7%88%ED%8A%B8-%EC%BA%90%EC%8A%A4%ED%8A%B8)
---
* ```java
  //java 변수명(원하는변수명)
  int a = 4;
  System.out.println(a(char))
* ```kotlin
  //kotlin as사용
  class As {
    fun asInt(number: Number) {
        val int: Int = number as Int
    }
  }


출처: https://namget.tistory.com/entry/Kotlin-코틀린-자료-자료형의-확인-및-변환-is-as-스마트-캐스트 [남갯,YTS의 개발,일상블로그]
 
smartcast - [출처 - namget.tistory](https://namget.tistory.com/entry/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9E%90%EB%A3%8C-%EC%9E%90%EB%A3%8C%ED%98%95%EC%9D%98-%ED%99%95%EC%9D%B8-%EB%B0%8F-%EB%B3%80%ED%99%98-is-as-%EC%8A%A4%EB%A7%88%ED%8A%B8-%EC%BA%90%EC%8A%A4%ED%8A%B8)
---
* 추론이 가능한 경우 캐스팅 없이 해당하는 자료형으로 객체가 변환되도록 스마트 캐스트 기능을 지원
* ```kotlin
  class SmartCast {
    fun smartCast(number: Number) {
        if (number is Int) { // 변환되는것을 확인한후 as로 확인을해야하지만 (자바같은경우는 instanceOf를 사용하여서)
            val a: Int = number //스마트 캐스트가 지원되어서 as 연산자를 거치지 않고 사용할 수 있습니다.
        }
    }
  }
