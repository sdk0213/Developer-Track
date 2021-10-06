# gradle
* 객체 지향 프로그래밍 언어인 Groovy 를 기반으로 한 빌드 도구
* JAVA 6 이상에서 사용가능
---
### 구조
##### build.gradle
* 빌드 스크립트라고 하며 엄밀히 말하면 빌드 구성 스크립트(Build Configuration Script)라고 한다.
* 의존성이나 플러그인 설정 등과 같은 빌드에 필요한 설정을 하게 된다.
##### settings.gradle
* 빌드할 프로젝트 정보 설정(프로젝트 구성 정보 기록)
* **트리형태로 멀티프로젝트 구성시 사용한다.** -> 싱글프로젝트일경우 생략가능
##### gradlew / gradlew.bat
* gradlew - UNIX 용 실행스크립트
* gradlew.bat -> WINDOWS 용 실행 스크립트
##### .idea
* 에디터 관련 파일
##### .gradle / gradle
* gradle 버전별 엔진 및 설정 파일
##### Gradle wrapper
* Gradle을 설치하지 않았어도 Gradle tasks를 실행할 수 있도록 도움 -> 프로젝트 생성자랑 사용자가 동일한 버전의 gradle 사용가능
---
### api / compile / implementation
* api 와 compile은 동일한 동작이나 api 권장
* |api|implementation|
  |:--:|:--:|
  |프로젝트에 의해 노출되는 API의 일부인 프로젝트 소스를 컴파일하는데 필요한 종속성.|프로젝트에 의해 노출되는 API의 일부가 아닌 프로젝트 소스를 컴파일하는데 필요한 종속성|
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F8Ba6G%2Fbtq2MltTvbA%2Fo66DJU8D0nkd5yKZy0YriK%2Fimg.png)
  * api -> 모듈이 lib2 코드 뭔지 알음
  * implementation -> 모듈이 lib2 코드 뭔지 모름
---
### provideRuntime / provideCompile / testImplementation
##### provideRuntime
* runtime 중에만 필요한 의존성 설정
##### provideCompile
* compile 중에만 필요한 의존성 설정
##### testImplementation
* test 시에만 필요한 의존성 설정
##### [그룹명][이름][버전] 으로 보통 의존성을 정의
---
### plugin
* 미리 구성해 놓은 task들의 그룹이며, 특정 빌드과정에서 필요한 기본정보를 포함한다.
* apply plugin: 'java' 와 같은 형식으로 설정한다.
* 안드로이드에서는 apply plugin: 'com.android.application' 사용하여 안드로이드 플로그인을 적용한다.
---
### sourceCompatibility / targetCompatibility
* 자바소스를 컴파일시키는 역할
* sourceCompatibility -> 자바 1.6 이상에서 사용하는 컴파일 메서드 
  * 1.5 이하에서 할경우 MethodNotFoundException이나 ClassNotFoundException 발생함
* targetCompatibility -> 자바 1.5 이하에서 사용하는 컴파일 메서드
---
### task
* Gradle의 실행 작업 단위
* 예) 문자열 추출
  * ```gradle
    task sayHello {
        println 'Hello Taeng'
    }
* 예) for 문
  * ```gradle
    task sayHi {
        def loopCount = count.toInteger()
        for(def i in 1..loopCount) {
           println('LoopCount: ' + i)
        }
    }
* 예) 사용자 정의 메서드
  * ```gradle
    task methodTask {
       printMessage('say Hi')
    }
 
    String printMessage(String msg) {
        println msg
    }
