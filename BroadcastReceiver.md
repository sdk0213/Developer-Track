BroadcastReceiver
===

BroadcastReceiver - [출처](https://limkydev.tistory.com/49)
---
* 4대 컴포넌트중 하나
* 브로드캐스터 **정적** 등록
  * Manifest.xml에 등록하는 방법
  * ```xml
    <receiver android:name=".receiver.MyBroadCastReceiver">
      <intent-filter>
        <action android:name="com.example.limky.broadcastreceiver.gogo"></action>
      </intent-filter>
    </receiver>
    ```
  * ```java
    public class MainActivity extends AppCompatActivity{
      oncreate()
      clickmethod(){
        sendBroadCast(new Intent("com.example.sdk0213.broadcastreceiver"));
      }
    }
    ```
  * ```java
    HelloBroadCastReceiver extends BroadcastReceiver{
      public void onReceive(Context context, Intent intent) {
        String actionName = intent.getAction();
        Toast.makeText(context, "받은 액션 : "+actionName , Toast.LENGTH_SHORT).show();
      }
    }
* 브로드캐스터 **동적** 등록
  * 코드상에서 BroadCastReceiver를 등록하는 방법이다.
  * Manifest.xml에 따로 Receiver를 등록할 필요없다.
  * IntentFilter 등록
    ```java
    final IntentFilter theFilter = new IntentFilter();
    theFilter.addAction(BROADCAST_MESSAGE); // private final String BROADCAST_MESSAGE = "com.example.limky.broadcastreceiver.gogo"; 
    ```
  * 익명클래스 구현
    ```java
    this.mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int receviedData = intent.getIntExtra("value",0);
            if(intent.getAction().equals(BROADCAST_MESSAGE)){
                Toast.makeText(context, "recevied Data : "+receviedData, Toast.LENGTH_SHORT).show();
            }
        }
    ```
  * 등록(register)
    ```java
    this.registerReceiver(this.mReceiver, theFilter);
    ```
  * 해체(unregister)
    ```java
    if(mReceiver != null){
      this.unregisterReceiver(mReceiver);
      mReceiver = null;
    }
    ```
