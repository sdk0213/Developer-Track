# Generic 런타임중 구별하기
#### is 사용
* ```kotlin
  fun <T> printWhatAmI(t: T) {
        if (t is String) {
            println("i'm String")
        } else if (t is Int) {
            println("i'm Int")
        }
  }
 
#### type 사용
* ```kotlin
  fun <T> printWhatAmI(t: T, type: Class<T>) {
        when (type) {
            String::class -> {
                println("i'm String") 
            }
            Int::class -> {
                println("i'm Int")
            }
        }
  }

#### reified 사용 - 추천
* ```kotlin
  inline fun <reified T> printWhatAmI() {
        when (T::class) {
            String::class -> {
                println("i'm String")
            }
            Int::class -> {
                println("i'm Int")
            }
        }
  }
