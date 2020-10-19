모르면 손해보는 Android 디버깅/분석 꿀팁 대방출
===
에뮬레이터
---
* 폴더블처럼 고가의 기기가 없더라도 실제기기에서 엑티비티가 어떻게 동작하는지 파악이 가능하다.
* 예전에는 많이 느렸지만 지금은 많은 기능들이 구현되어있어서(폰 뒤집기 같은 기능까지) 매우 효과적이다.
* 복/붙 가능

테스트 전용 기기 설정 권장 옵션들
---
> 항시 ON 하면 좋은것들
* Usb debugging
* Stay awake(화면 켜짐 상태 유지)
* Enable view attribue inspection(보기 속성 검사 사용)
  * 기기마다 다르지만 attribute가 감춰진것을 볼수가 있음
* show taps(탭한 항목 표시)
* Strict mode enable(엄격 모드 사용)
  * 비용이 많이 드는 작업할때 표시
* Always show crash dialog(충돌 다이얼로그 항상 표시)
  * 예전에는 문제가 발생하면 다이얼로그가 떳지만 사용자경험 향상을 위해 반복적으로 발생하는것이 아니라면 표시 하지 않도록 하는 경향으로 바뀜
  * **하지만, 개발자 입장에서 이것은 중요한 정보를 놓치는 상황이 발생할수 있음**
  * 이러한 부분을 보완하기 위한 옵션
* Show background ANRs (백그라운드 ANR 표시)
* Show notification channel warnings (알림 채널 경고 표시)
* Logger buffer size (로거 버퍼 크기) : 16M
  * 로거버퍼크기가 작으면 로거를 놓칠수 있는 상황이 발생가능
* 안드로이드 11
  * 개발자옵션으로 앱 상호작용 변화 테스트할수 있도록 제공될 예정이라고함


검증 모듈
---
* LeakCanary
  * Memory Leak
* Android Studio Memroy Profiler
  * 코드레벨까지 확인가능
* Systrace
* Lint
* Thread Dump
  * 더 자세히볼려면 shell로 찍어서 확인해야함
  * adb shell ps -T | grep <PID>
  * adb shell ps -T | grep <PID> | wc -l
    *  
  * 간혹 비동기 작업을 하다보면 쓰레드가 쌓일수 있는데, 물론 요즘에는 Rx나 코루틴이 사용해서 덜하긴 하지만... 그래도 누적되어서 문제가 발생하는 경우 이런 명령어를 통해 확인 가능
* Stetho
  * 갈수록 필요성이 조금 줄어들고있음
* Fill RAM Memory(APK-구글플레이)
  * 3td party app
  * 시스템의 가용 메모리를 억지로 점유하는 앱(포어그라운드일떄 
  * 
