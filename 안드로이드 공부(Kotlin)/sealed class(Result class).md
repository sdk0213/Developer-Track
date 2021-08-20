# 봉인된 클래스 즉, sealed class - [출처 - codechacha님의 블로그](https://codechacha.com/ko/kotlin-sealed-classes/)
---
### 특징
* Child 클래스의 종류 제한하는 특성을 갖고 있는 클래스
  * 상속을 허용 안할수 있다.
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
### 그런데 잘 보면 Enum 클래스랑 비슷한것같은데 왜 sealed 클래스를 사용할까?
##### Properties
* Enum 의 단점은? 바로 타입을 맞춰줘야 한다. 즉 다음과 같은 코드에서 String 으로 선언될경우 반드시 String 을 따라줘야 한다.
  * ```kotlin
    enum class DeliveryStatus(val trackingId: String?) {
        PREPARING(null),  <-------- 아무것도 입력하기 싫은데 String 으로 맞춰줘야 하기 때문에 null 이 들어간다.
        DISPATCHED("27211"),
        DELIVERED("27200")
        
        
        // 그리고 또 하나.. 만약 아래와 같이 다른 타입이 들어가고 싶어도 넣을수 없다.
        STOP(9999) <---- String 말고 Integer 넣는거 불가능
    }
* 하지만 sealed 클래스에서는 다음과 같이 여러 타입을 넣을 수 있다.
  * ```kotlin
    sealed class DeliveryStatus
    class Preparing() : DeliveryStatus () <--------- 굳이 필요없다면 파라미터를 안써도 된다. 위에서보면 알겠지만 Enum은 null 을 넣어야됬다.
    class Dispatched(val trackingId: String) : DeliveryStatus()
    class Delivered(val trackingId: String, val receiversName : String) : DeliveryStatus()
##### Functions
* 여러개의 Function 을 사용가능하다.
* ```kotlin
  // ENUM 은 하나의 cancelOrder라는 함수만 사용가능하다.
  enum class DeliveryStatus {
    PREPARING {
        override fun Funcion_A() = println("Cancelled successfully")
    },
    DISPATCHED {
        override fun Funcion_A() = println("Delivery rejected")
    },
    DELIVERED {
        override fun Funcion_A() = println("Return initiated")
    };

    abstract fun Funcion_A()
  }
  
  // sealed 는 여러개의 함수를 사용 가능하다.
  sealed class DeliveryStatus

  class Preparing : DeliveryStatus() {
      fun Funcion_A() = println("Cancelled successfully")
  }

  class Dispatched : DeliveryStatus() {
      fun Funcion_B() = println("Delivery rejected")
  }

  class Delivered : DeliveryStatus() {
      fun Funcion_C() = println("Return initiated")
  }
  
  // 만약에 Enum 처럼 동일한것를 쓰고싶다면 ? 즉, 단순히 다형성을 사용하고싶고 is 를 사용하고 싶지 않다면??
  // 그저 아래처럼 간단히 추상 기능을 만들어주면된다.
  sealed class DeliveryStatus {
      abstract fun cancelOrder()
  } 
##### Inheritance
* enum 클래스는 다른 클래스를 확장불가능하며 오직 interface 만 상속 가능
* sealed 클래스는 다른 클래스를 확장가능하다.
  * ```kotlin
    // enum
    open class OrderStatus { }
    interface Cancellable { }

    enum class DeliveryStatus : OrderStatus() { } <----- 불가능(X)
    enum class DeliveryStatus : Cancellable { }   <----- 가능(O)
    
    // sealed
    open class OrderStatus { }
    interface Cancellable { }

    sealed class DeliveryStatus : OrderStatus() { } <----- 가능(O)
    sealed class DeliveryStatus : Cancellable { }   <----- 가능(O)
##### Number of Instance
* ```kotlin
  sealed class DeliveryStatus
  object C : DeliveryStatus()
  class A(val trackingId: String) : DeliveryStatus()
  data class B(val receiversName: String) : DeliveryStatus()

  // Multiple Instances
  val A1 = A("A") <---- 가능 (객체 여러개 생성 가능)
  val A2 = A("B") <---- 가능 (객체 여러개 생성 가능)
  val B1 = B("A") <---- 가능 (객체 여러개 생성 가능)
  val B2 = B("B") <---- 가능 (객체 여러개 생성 가능)
  val C1 = C <---- 가능 
  val C2 = C() <---- 불가능 (객체 여러개 생성 불가능) 왜냐하면 object 라서 싱글톤
  
  
  // 다른 경우의 수
##### Serializable and Comparable
* enum 은 자동으로 Serializable and Comparable 구현되기에 우리가 정의하지 않아도된다.
* sealed 는 우리가 직접 손수 다 만들어야 한다.
* enum 의 장점인것처럼 보인다.

##### Performance
* enum -> when 에서 속도빠름 근데 크게 차이없음, GC 대상 아님, 프로가드최적화시 속도 더 빠를수있음
* sealed -> when 에서 느림 근데 크게 차이없음, 일반 클래스, 그래서 GC 대상임, 
* Enum 은 가비지 컬렉터에 수집안됨
  * 장점
    * 가비지 컬렉터는 객체 생성만큼 많은 비용이 들기 때문에 장점이 될수도 있다.
  * 단점
    * 메모리에 계속 상주함
  * 그래서 100 ~ 200개 이상 사용하는 경우라면 enum 을 사용해야하는지 결정해야한다.
* enum 은 when 표현식에서 조금 더 빠름
  * 게다가 Android 에서 Proguard 최적화를 할경우 함수와 속성이 없는 열겨형을 정수로 변환하기에 Int 급의 성능을 얻는다.
* sealed 클래스는 그냥 일반클래스이기 떄문에 성능은 일반 클래스랑 동일
  * 그래서 가비지 컬렉션 비용이 들어간다.
  * 메모리가 딸리면 sealed 클래스를 사용하는것이 좋음
  * 하지만 object 로 하면 싱글톤으로 가비지 컬렉터에 수집안됨
* sealed 클래스는 when 표현식에서 enum 보다 느리지만 몇 천개를 비교하지 않는 이상 눈에 띄는 정도는 아님 

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
  
