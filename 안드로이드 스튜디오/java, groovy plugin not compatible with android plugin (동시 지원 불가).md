* 아래 내용을 참고하면서 동시에 플러그인 적용이 불가능하다는것을 알게되었다.
  * java 와 groovy 는 android plugin 과 동시에 적용이 불가능하다.
* [출처 1](https://youtrack.jetbrains.com/issue/KT-30878)
Since the Android Gradle plugin conflicts with the Gradle Java plugin that is applied as a part of Java support for JVM targets 
introduced as per https://youtrack.jetbrains.com/issue/KT-26256, 
it's impossible to have both in the same multiplatform module, the following error occurs on Gradle project configuration: 
**The 'java' plugin has been applied, but it is not compatible with the Android plugins.**
* [출처 2](https://stackoverflow.com/questions/26861011/android-compile-error-java-plugin-has-been-applied-not-compatible-with-android)
