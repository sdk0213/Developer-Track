AlarmManager,JobScheduler
===
AlarmManager - [출처](https://doraeul.tistory.com/73)
---
* 모든 버젼에서 사용가능하다.
* 배터리 최적화 예외를 사용설정할때 Jobshceduler보다 매우 매우 정확하게 작동한다. - [출처](https://wooyeol.github.io/2019/03/19/Android-Repeat-Background-Service/)
  * ![](https://user-images.githubusercontent.com/9836231/54588301-ea072580-4a65-11e9-9e98-d0c0d239e971.png)
* 알람매니저에 Action을 취하는 방법
  * Service 호출
  * BroadCast Receiver 호출
* Alarm은 App을 강제적으로 kill하여도 작동되며 이는 **AlarmManager의 생명주기가 Application이 아닌 OS와 함께하기 때문이다.**
* | |Don’t wake up Device|Wake up Device|
  |:---:|:---:|:---:|
  |RTC|RTC|RTC_WAKEUP|
  |ELAPSED|ELAPSED_REALTIME|ELAPSED_REALTIME_WAKEUP
  * RTC : UTC표준시간을 기준으로 하는 명시적인 시간에 intent를 발생
  * RTC_WAKEUP : UTC 표준시간을 기준으로 하는 명시적인 시간에 intent를 발생, 장치를 깨움
  * ELAPSED_REALTIME : 부팅 후 시간을 기준으로 지정된 시간에 intent를 발생
  * ELAPSED_REALTIME_WAKEUP : 부팅 후 시간을 기준으로 지정된 시간에 intent를 발생, 장치를 깨움
* Alarm 등록 - [출처](https://doraeul.tistory.com/73#recentEntries)
  * 예제 - [출처 doraeul.tistory](https://doraeul.tistory.com/73#recentEntries)
  ```java
  public static void registerAlarm(Context context)
  {
    Log.e(TAG, "registerAlarm");
      
    Intent intent = new Intent(context, NotificationReceiver.class);
    intent.setAction(NotificationReceiver.INNER_PUSH);
    PendingIntent sender 
          = PendingIntent.getBroadcast(context, mCrouteCode, intent, 0);
          
    AlarmManager manager 
          = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE); 
          
    long triggerTime = SystemClock.elapsedRealtime() + 1000*60;
    manager.set(AlarmManager.ELAPSED_REALTIME, triggerTime, sender);
  }
  ```
  
  * ```java
    PendingIntent sender = PendingIntent.getBroadcast(context, mCrouteCode, intent, 0);
    // 또는 
    PendingIntent sender = PendingIntent.getService(context, mCrouteCode, intent, 0); 
    ```
  * requestCode는 나중에 Alarm을 해제 할때 어떤 Alarm을 해제할지를 식별하는 코드이다.
  * set() 메소드
    * 한번만 실행
    * ```java
      manager.set(AlarmManager.ELAPSED_REALTIME, triggerTime, sender);
      ```
  * setRepeat() 메소드 
    * 반복적으로 실행되며 위의 설명처럼 OS의 생명주기를 따름
    * ```java
      manager.setRepeating(AlarmManager.ELAPSED_REALTIME, time, 1000*60, sender);
      ```
  * service/broadcast/intent action/pendingIntent 등은 의도에 따라서 각자 정해서 넣으면된다.
