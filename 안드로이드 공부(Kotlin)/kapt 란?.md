# KAPT(Kotlin Annotation Processing Tool)
### 코틀린에서는 자바에서 작성한 애노테션 프로세서가 작동하지 않는다.
* 컴파일러 차이로 인하여
  * java -> javac
  * kotlin ->  koltinc
* 이에 대한 해결책으로 나온것이 KAPT 이다.
##### 주의사항
* annotationProcessor를 kapt로 바꾼다고 모든 라이브러리가 정상적으로 동작을 보장하지는 않는다 우선 해당 라이브러리가 kapt를 지원하는지 알아봐야한다
##### 설정
* ```gradle
  apply plugin: 'kotlin-kapt'
  
  annotation processor -> kapt 로 변경하기
