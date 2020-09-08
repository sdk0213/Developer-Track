Implicit Broadcast Exceptions
===
* API 레벨 26 이상을 타겟팅하는 앱은 암시적 브로드캐스트의 broadcast receiver를 manifest에 더 이상 등록할 수 없습니다 (출처 : https://developer.android.com/guide/components/broadcast-exceptions?hl=ko)
  * 소스 출처 -> [junghun0님의 github](https://junghun0.github.io/2019/05/21/android-broadcastreceiver/)
  * Oreo 이전 -> xml로 등록
    * ```xml
      <receiver
       android:name=".broadcastreceiver.BatteryReceiver"
       android:enabled="true"
       android:exported="true">
      </receiver>
  * Oreo 이후 -> java에서 동적으로 등록
    * ```java
      batteryReceiver = new BatteryReceiver();
      IntentFilter intentFilter = new IntentFilter();
      intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
      intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
      this.registerReceiver(batteryReceiver,intentFilter);
    * ```java
      public class BatteryReceiver extends BroadcastReceiver {
      @Override
      public void onReceive(Context context, Intent intent) {
          if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
              Toast.makeText(context, "전원 연결", Toast.LENGTH_SHORT).show();
          } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
              Toast.makeText(context, "전원 해제", Toast.LENGTH_SHORT).show();
          }
        }
      }
