AlarmManager,JobScheduler
===
AlarmManager - [출처 superwony님의 블로그](https://superwony.tistory.com/99), [출처](https://doraeul.tistory.com/73)
---
* 모든 버젼에서 사용가능하다.
* 배터리 최적화 예외를 사용설정할때 Jobshceduler보다 매우 매우 정확하게 작동한다. - **[**출처**](https://wooyeol.github.io/2019/03/19/Android-Repeat-Background-Service/)**
  * ![](https://user-images.githubusercontent.com/9836231/54588301-ea072580-4a65-11e9-9e98-d0c0d239e971.png)
* 알람매니저에 Action을 취하는 방법
  * Service 호출
  * BroadCast Receiver 호출
* Alarm은 App을 강제적으로 kill하여도 작동되며 이는 **AlarmManager의 생명주기가 Application이 아닌 OS와 함께하기 때문이다.**
* **반복알람의 장단점** - [출처 - 안드로이드 공식문서](https://developer.android.com/training/scheduling/alarms?hl=ko)
  * 네트워크 작업을 트리거하는 앱에는 최선이 아닐수 있다.
  * 잘못 설계시 배터리소모에 악영향을 끼칠수 있다.
  * 호스팅이 존재한다면 동기화 어뎁터와 함께 GCM 또는 FCM을 사용하는것이 좋을수도있다.
* 단말이 재부팅되면 알람들이 모두 사라진다. **재부팅할 경우 ACTION_BOOT_COMPLETED 액션을 사용해 알람 재등록**을 하면 된다.
* Service 알람처리를 사용할경우 잠들어있을경우 깨우지 못한다.
	* 그러므로 디바이스를 깨워야한다.
	* **디바이스를 깨울려면 PowerManager와 WakeLock을 사용**해 전력관리를 해야 한다. - [출처 - programmingfbf7290.tistory](https://programmingfbf7290.tistory.com/entry/7-AlarmManager-%EC%9E%91%EC%97%85-%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81)
  * 메니페스트에 다음을 등록한다.
    * ```xml
      <uses-permission android:name="android.permission.WAKE_LOCK"/>
      ```
  * WakeLock 설정
    * ```java
      PowerManager pm = (PowerManager)ctx.getSystemService(Context.POWER_SERVICE);
      WakeLock lock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "my_app");
      lock.acquire();
      ```
  * WakefulBroadcastReceiver 클래
    * 이 클래스는 부분적인 WakeLock을 획득하고 해제하는 두 메서드를 가지고 획득과 동시에 서비스 시작도 할 수 있다.  
      * ```java
        WakefulBroadcastReceiver.startWakefulService(context, serviceIntent);
        WakefulBroadcastReceiver.completeWakefulService(serviceIntent);
        ```
  * onReceive()
    * ```java
      @Override
      public void onReceive(Context context, Intent intent){
	         Intent serviceIntent = new Intent(context, MyService.class);
	         WakefulBroadcastReceiver.startWakefulService(context, serviceIntent);
	     }
  * 해제 - 그렇지 않으면 CPU가 불필요하게 작동되 배터리를 소모
    * ```java
      @Override
      protected final void onHandleIntent(Intent intent){
	      try{
		
      	}finally{
		      WakefulBroadcastReceiver.completeWakefulService(serviceIntent);	
	      }
      }
      ```
* 예약된 알람은 **잠자기 모드를 종료할때**까지 **지연** 된다.
  * 대안 
    * **setAndAllowWhileIdle()**
    * **setExactAndAllowWhileIdle()**
    * **WorkManager API 사용 - [WorkManager Link](https://developer.android.com/topic/libraries/architecture/workmanager?hl=ko)**
* | |Don’t wake up Device|Wake up Device|
  |:---:|:---:|:---:|
  |RTC|RTC|RTC_WAKEUP|
  |ELAPSED|ELAPSED_REALTIME|ELAPSED_REALTIME_WAKEUP
  * RTC : 일반 표준시간을 기준으로 설정
  * RTC_WAKEUP : UTC 표준시간을 기준으로 하는 명시적인 시간에 intent를 발생, 장치를 깨움
  * ELAPSED_REALTIME : 안드로이드가 부팅된 후 시간을 기준으로 설정
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
    * set() 은 API 19 이전에만 정확한 동작 보장
    * ```java
      manager.set(AlarmManager.ELAPSED_REALTIME, triggerTime, sender);
      ```
    * setExact()
      * API 19 이상에서 set() 처럼 정확한 시간에 동작 (중요알림에만 사용하도록 권장)
    * setExactAndAllowWhileIdle()
      * API 23 이상에서 Doze 모드일경우에도 setExact() 처럼 정확한 시간에 동작
      * 9분에 1번
  * setRepeat() 메소드 
    * 반복적으로 실행되며 위의 설명처럼 OS의 생명주기를 따름
    * ```java
      manager.setRepeating(AlarmManager.ELAPSED_REALTIME, time, 1000*60, sender);
      ```
    * doze 모드에도 동작을 보장하지 않기 때문에 재등록하는방법으로 setExactAndAllowWhileIdle() 을 반복적으로 편법적으로 사용  
  * service/broadcast/intent action/pendingIntent 등은 의도에 따라서 각자 정해서 넣으면된다.
  
JobScheduler
---
* 개발자가 정의한 작업을 스케쥴링해주는 서비스이다.
* **API 21 이상**부터 동작
* 구현은 JobService에서 자세한스케쥴링 정보는 JobInfo에 정의된다.(**※ : JobInfo -> JobScheduler -> JobService**)
* 기본적으로 **MainThread**에서 동작한다.
* id
	* job을 구분해주는 역할
* Job의 조건이 복잡할수록 실행과 더불어 유지가 어렵다.
* 권한등록
	* 메니페스트 등록
	  ```xml
	  <service
  	    android:name=".SyncJobService"
  	    android:permission="android.permission.BIND_JOB_SERVICE"
  	    android:exported="true"/>
	  ```
* JobService
	* onStartJob()
		* Job이 시작될 시점에 시스템에 의해 호출되는 콜백
		* 종료시 지속할 동작이 있다면 true
			* finishJob()의 호출을 통해 명시적으로 작업 종료를 선언
		* 종료시 여기에서 동작이 완료되면 false
	* onStopJob()
		* Job이 종료되기 이전에 취소될 필요가 있을 경우 시스템에 의해 호출되는 콜백입니다.
		* 갑작스럽게 중지되어서 다시 등록하여 수행할필요가있다면 true를 반환하면 된다.
		* false를 반환하면 스케쥴링을 방지한다.
	* jobFinished
		* Job이 완료되었을 때 호출하여 JobManager로 하여금 해당 Job을 종료하도록 한다.
* JobIntentService 
	* 자세한 설명 링크 참고 - [링크](https://medium.com/til-kotlin-ko/android-o%EC%97%90%EC%84%9C%EC%9D%98-%EB%B0%B1%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C-%EC%B2%98%EB%A6%AC%EB%A5%BC-%EC%9C%84%ED%95%9C-jobintentservice-250af2f7783c)
	
	
