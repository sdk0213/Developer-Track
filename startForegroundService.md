startForegroundService
===
startForegroundService
---
* 백그라운드 실행제한
  * OS 8.0 부터 백그라운드 실행제한으로 인하여서 **startService 사용불가**
    * 만약사용하게 된다면 IllegalStateException 발생한다.
  * context.startForegroundService(intent)
    * startService말고 위의 코드를 사용한다.
    * **위의 코드를 실행후에 반드시 해당 서비스에서 startForeground()를 반드시 코드를 실행**
    * ```java
      if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
          context.startForegroundService(intent);
      }
      else {
          context.startService(intent);
      }
  * Manifest.xml
    * <uses-permission android:name="android.permission.FOREGROUND_SERVICE" /> 추가
