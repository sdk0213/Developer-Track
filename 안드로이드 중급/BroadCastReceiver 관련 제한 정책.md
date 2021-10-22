# 브로드캐스트와 프로세스
* 백그라운드 관련 또는 전력관련테스트 해보기 
  * [테스트 해보기](https://developer.android.com/topic/performance/power/test-power?hl=ko#adb-copmmands)
### 브로드캐스트 제한
* class 로 만든 브로드캐스트는 activity 나 service 에서 만든것과 다르게 행동하는것같다.
* 문제점
  * onReceive() 이후에 코드는 포어그라운드로 취급되기 됨
    * onReceive() 이후 시스템은 언제든지 프로세스를 종료하여 메모리를 회수할 수 있으며 이 과정에서 프로세스에서 생성되어 실행 중인 스레드를 종료할 수 있습니다. 
* 해결책
  * JobService 또는 Workmanager 사용
  * [goAsync() 호출](https://developer.android.com/guide/components/broadcasts?hl=ko#effects-process-state)
### 지오펜싱과 브로드캐스트
  * 백그라운드 제한이 걸리며 브로드캐스트가 작동하지 않는다.
