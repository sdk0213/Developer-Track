# sealed class - [출처 - codechacha님의 블로그](https://codechacha.com/ko/kotlin-sealed-classes/)
---
### 특징
* Child 클래스의 종류 제한하는 특성을 갖고 있는 클래스
* **동일 파일에 정의된 하위 클래스 외에 다른 하위 클래스는 존재하지 않는다는 것을 컴파일러에게 알려주는 것**
* sealed 키워드를 붙힐경우 abstract 클래스가 된다. -> 객체로 생성불가능하다.
* 생성자는 private로 된다. -> public 불가
* 하위 클래스는 class, data class, object class으로 정의가능
* ```kotlin
  sealed class Color
  
  object: Red: Color()
  object: Green: Color()
  object Blue: Color()
  
  val color : Color = Red
  
  // 또는
  
  sealed class Color {
    object Red: Color()
    object Green: Color()
    object Blue: Color()
  }
  
  val color : Color = Color.Red
---
### 사용
##### when을 사용할때 조금 더 안전하게 사용가능하다.
* sealed class는 **컴파일 시점에 존재할 수 있는 클래스 타입이 정해져 있기 때문에** when을 사용할 때 **else를 사용하지 않아도 된다.** 
  * else를 사용하지 않았기 때문에 기능을 확장할 때 실수로 잘못된 경우의수를 코드로 추가하지 않는 일이 발생하지 않게 된다. -> 조금 더 안전하게 컴파일 가능
* ```kotlin
  sealed class Color {
    object Red: Color()
    object Green: Color()
    object Blue: Color()
  }

  val color : Color = Color.Red
  val font = when (color) {
      is Color.Red -> {
          "Noto Sans"
      }
      is Color.Green -> {
          "Open Sans"
      }
      // compile error!
  }
  
##### Enum vs Sealed
* Enum -> 싱글톤으로 생성됨
* Sealed -> object를 사용하지 않는다면(data, class) 싱글톤이 아닌 여러 객체 생성가능
* [자세한 내용은 codechacha님 블로그 참고](https://codechacha.com/ko/kotlin-sealed-classes/)

##### Result class
* 데이터 요청에 대한 결과를 처리하는 클래스
* General
  ```kotlin
  sealed class Result {
      data class Success(val data: String) : Result()
      data class Error(val exception: Exception) : Result()
      object InProgress : Result()
  }
  
  var parsedResult : Result = Result.Success("Sealed classes are used for representing...")
  var parsedResult2 : Result = Result.Error(Exception("Got error while parsing this url"))
  var parsedResult3 : Result = Result.InProgress
  
  showResult(parsedResult)
  showResult(parsedResult2)
  showResult(parsedResult3)

  fun showResult(result: Result) {
      when (result) {
          is Result.Success -> {
              println("Success: ${result.data}")
          }
          is Result.Error -> {
              println("Error: ${result.exception}")
          }
          is Result.InProgress -> {
              println("In progress")
          }
      }
  }
* Generic
  ```kotlin
  sealed class Result<out T : Any> {
      data class Success<out T : Any>(val data: T) : Result<T>()
      data class Error<out T : Any>(val exception: Exception) : Result<T>()
      object InProgress: Result<Nothing>()
  }
  
  var parsedResult : Result<String> = Result.Success("Sealed classes are used for representing...")
  var parsedResult2 : Result<String> = Result.Error(Exception("Got error while parsing this url"))
  var parsedResult3 : Result = Result.InProgress
  
  showResult(parsedResult)
  showResult(parsedResult2)
  showResult(parsedResult3)

  fun showResult(result: Result) {
      when (result) {
          is Result.Success -> {
              println("Success: ${result.data}")
          }
          is Result.Error -> {
              println("Error: ${result.exception}")
          }
          is Result.InProgress -> {
              println("In progress")
          }
      }
  }
  
