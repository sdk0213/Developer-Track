# 난독화 및 앱 축소
## 역사
* progaurd + dx -> jack & jill -> progaurd + d8 -> r8
* 예전 방식
  * <img width="992" alt="스크린샷 2022-04-19 오후 6 30 28" src="https://user-images.githubusercontent.com/51182964/163974660-1f77e51f-f6cf-4c12-aa7e-691e65e362f3.png">
* 통합된 방식
  * <img width="991" alt="스크린샷 2022-04-19 오후 6 30 51" src="https://user-images.githubusercontent.com/51182964/163974744-d63627c2-ff31-4934-8875-a9454007bd53.png">
* 다시 분리된 방식 + D8
  * <img width="1011" alt="스크린샷 2022-04-19 오후 6 30 54" src="https://user-images.githubusercontent.com/51182964/163974818-77e8b113-5b12-43b6-97ea-dbfe10b52f12.png">
* R8 을 사용한 방식
  * <img width="1011" alt="스크린샷 2022-04-19 오후 6 32 40" src="https://user-images.githubusercontent.com/51182964/163974868-e8b37d78-2ae5-4606-8430-682eeab15ac3.png">
---
### d8 (r8 의 초기버전)
* dex 파일로 변환해주는것
* 위 사진 보면 이해됨
---
### progaurd
* Android Studio 3.4 이전 기본 컴파일러
* **Proguard is free Java class file** shrinker, optimizer, obfuscator, and preverifier. It detects and removes unused classes, fields, methods, and attributes. Mobile app development companies use proguard in android , it optimizes bytecode and removes unused instructions. It renames the remaining classes, fields, and methods using short meaningless names.
* Android Applications are quite easy to reverse engineer
---
### R8
* Android 스튜디오 3.4 또는 Android Gradle Plugin 3.4 이후에 적용됨
  * 3.4 이전버전에서 사용하고싶을경우 다음과 같이 사용
  * gradle.properties 에 다음과 같이 선언
    ```gradle
    android.enableR8=true 
* R8 is backward compatible with ProGuard.
* 단점
  * R8 doesn't yet automatically recognize and handle simple cases of reflection
  * R8 can't yet rename class names in strings, in resource files and in resource file names.
  * R8 doesn't yet rename inner classes according to the Java standard, which can trigger subtle compatibility issues.


    
  
