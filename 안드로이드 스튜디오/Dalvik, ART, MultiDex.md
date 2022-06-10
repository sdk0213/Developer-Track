### JIT VS AOT
* |컴파일 방식|배터리 사용량|CPU 사용량|용량|실행 속도|
  |:--:|:--:|:--:|:--:|:--:|
  |JIT|많음|높음|적게 차지|느림|
  |AOT|적음|적음|많이 차지|빠름|
  
### 참고하면 도움이 될만한 사진
* ![](https://i.stack.imgur.com/hpwhy.png)
* ![](https://i.stack.imgur.com/1kLrB.png)
### Dalvik vs ART
* 방식 변화 : JIT(Just In Time) -> Trace JIT (어느정도 컴파일) -> Ahead Of Time (완전 컴파일) -> AOT + JIT = ART 방식 (서로의 단점을 보완)
* Dalvik was the original VM used by Android. It has now been replaced by the Android Runtime.
* Dalvik -> 인터프리터 방식, ART -> 컴파일 방식
* Dalvik
  * ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdRoVPY%2FbtqCUdipBEs%2FKHrPkUQNz7KLktp2X03zd1%2Fimg.png)
* ART
  * ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FL5ziH%2FbtqCWdV5wBw%2FJqkqwvH0kwu4sSPsTvFuvk%2Fimg.png)
* ART 가 디스크 용량은 많이 차지하지만 속도가 빠르며 배터리 사용량이 개선된다.
### [읽어보면 좋은내용 - Dalvik ART GC 비교](https://s2choco.tistory.com/15)
### [읽어보면 좋은내용 - Dalvik ART 컴파일 비교](https://s2choco.tistory.com/16)
---
# MultiDex 
### [About the .dex File - stackoverflow](https://stackoverflow.com/questions/7750448/what-are-dex-files-in-android)
* <img width="774" alt="스크린샷 2022-01-27 오후 7 11 31" src="https://user-images.githubusercontent.com/51182964/151338179-2b129388-7bea-4d4c-920e-7cb64bb663ce.png">
* SDK 21 이상은 기본적으로 true 설정되어있고 그 이하는 수동으로 설정해줘야한다.(ART 가 21이상부터 도입되었고 기본적으로 MultiDex 를 지원한다.)
* 안드로이드는 기본적으로 하나의 Dalvik Executable(Dex)형식의 실행가능한 바이트 코드 파일이 포함 ( ※: ART(Android Runtime)와 Dalvik은 Dex 바이트 코드를 실행하는 호환 가능한 런타임이다.)
* 하나의 Dex 는 메소드 개수가 2^16 까지이며 이를 초과할수없다.
* 그래서 이를 초과할경우에도 실행가능하게 설정해줘야하는데 이는 MutliDex 를 사용해야한다.
---
### [설정법에 관한 링크 - 공식문서](https://developer.android.com/studio/build/multidex?hl=ko)
---
### Primary Dex
* 앱이 성공적으로 start 할 수 있도록 필요한 Class들을 담은 Dex파일이다.
* 기본적으로 build tool 이 결정하여서 자동으로 처리되나 실행중 오류가 발생한다면 수동으로 클래스를 추가해줘야한다.
* 즉 앱이 죽는다면 메인 Dex 파일로 앱 실행과 관련된 요소를 설정해줘야한다.
* txt 또는 프로가드로 방식으로 선택하여서 설정
  ```gradle
  android {
      buildTypes {
          release {
              multiDexKeepFile file('multidex-config.txt')
          }
      }
  }
  
### 단점
* 파일이 커진다.
