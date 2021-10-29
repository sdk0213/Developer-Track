# timber
---
### [Timber Github - JakeWharton's timber](https://github.com/JakeWharton/timber)
---
### 사용이유
* 개발시에는 보고 릴리즈때는 보고 싶지 않는 로그를 만들때
* 태그는 호출한 클래스명으로 자동으로 넣어준다.(태그 또한 커스텀가능)
* ```gradle
  implementation 'com.jakewharton.timber:timber:4.7.1'
---
### 사용
##### 초기화
* ```kotlin
  ..activity .. application
  
  .. oncreate(){
      Timber.plant(Timber.DebugTree())
  }
##### 사용
* ```kotlin
  Timber.d("my log only working in debug mode $myvar")
##### TAG 수정
* ```kotlin
  Timber.tag("sdk0213").d("my log")
---  
### 커스텀 하기
* ```kotlin
  class MyCustomDebug : Timber.DebugTree() { 
      override fun createStackElementTag(element: StackTraceElement): String? { 
          return "${element.fileName}:${element.lineNumber}#${element.methodName}" 
      } 
  }
* ```kotlin
  ..activity .. application
  
  .. oncreate(){
      Timber.plant(MyCustomDebug) // Custom DebugTree로 초기화
  }


  // Result in Log

  // 2021-01-06 13:06:30.688 21677-21677/com.android.test D/MainActivity.kt:15#onCreate: some log
  // 여기에서 D/MainActivity.kt:15#onCreate 에 해다 하는 부분이 위에서 설정한 형식으로 출력됨
  // [element.fileName]:[element.lineNumber]: [element.methodName]
                                                    
  

