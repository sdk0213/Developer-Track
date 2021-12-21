# Android KTX (Kotlin Android Extension) - [출처는 공식 문서](https://developer.android.com/kotlin/ktx?hl=ko)
* Android 를 지원하는 Kotlin 확장 프로그램 세트
* Jetpack 과 라이브러리에 일부부 포함되어있다.
* KTX 사용 하면 다음과 같이 코드가 간편해진다.
  * ```kotlin
    sharedPreferences
        .edit()  // create an Editor
        .putBoolean("key", value)
        .apply() // write to disk asynchronously
  * ```kotlin
    // Commit a new value asynchronously
    sharedPreferences.edit { putBoolean("key", value) }

    // Commit a new value synchronously
    sharedPreferences.edit(commit = true) { putBoolean("key", value) }
---
### 사용가능한 KTX 모듈들 목록 - [공식에서 직접 확인하기](https://developer.android.com/kotlin/ktx?hl=ko)
* <img width="182" alt="스크린샷 2021-12-21 오후 12 27 32" src="https://user-images.githubusercontent.com/51182964/146866073-957c1427-46c2-4dc9-8bfa-22a41271361e.png">
* 예 
  ```gradle
  implementation androidx.fragment:fragment --> androidx.fragment:fragment-ktx
