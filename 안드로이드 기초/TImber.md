# Timber
---
### Timber란
* 개발을 할때는 로그를 남기고 아닐때는 릴리즈 시점에서는 Log를 출력 하고 싶지 않을때 사용
##### 사용
* ```gradle
  implementation "com.jakewharton.timber:timber:version
* ```kotlin
  Log.d("tagging","log is required for the string format $message")
* ```kotlin
  Timber.d("This is $message")
##### TAG는 필요에 따라서 다음과 같이 추가가능하다.
* ```kotlin
  Timber.tag("sdk0213").d("THIS IS THE BEST LOG")
##### 초기화하기
* 초기화가 필요하다. Application 에서 구현되면 될것같다.
* ```kotlin
  class BlurApplication() : Application() {
      override fun onCreate() {
          super.onCreate()
          if (BuildConfig.DEBUG) {
              Timber.plant(DebugTree())
          }
      }
  }
---
### 커스텀 DebugTree - [출처: https://jhdroid.tistory.com/9 [공부는 내일부터]](https://jhdroid.tistory.com/9)
* ```kotlin
  class TimberDebugTree : Timber.DebugTree() {
      override fun createStackElementTag(element: StackTraceElement): String? {
          return "${element.fileName}:${element.lineNumber}#${element.methodName}"
      } 
  }
