인텐트 - [출처](https://arabiannight.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9CAndroid-%EC%9D%B8%ED%85%90%ED%8A%B8%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4-%EB%B3%B4%EC%9E%90-intent-filter)
===

* intent를 보내는 방법에는 다음과 같이 두가지가 있다.
  * 명시적 intent
  * 암시적 intent
  
Intent(명시적 Intent)
---
* 특정 대상을 지정 해놓고 보내는 intent
* ![Intent](./img/Intent.png)
* 명령
  * ```java
    Intent i = new Intent(FirstrActivity.this, SecondActivity.class);
    startActivity(i);
    ```  
IntentFilter(암시적 Intent)
---
* 특정 대상을 특정해놓지 않고 보내는 intent
* ![IntentFilter](./img/IntentFilter.png)
* intentFilter를 받으려는 곳은 다음과 같이 코드를 xml로 작성하여야 한다.
  * ```xml
    <intent-filter>
    <action android:name="arabiannight.tistory.com.intentfilter.secondview">
      <category android:name="android.intent.category.DEFAULT">
      </category>
    </action>
    </intent-filter>
    ```
* 명령
  * ```java
    Intent i = new Intent("arabiannight.tistory.com.intentfilter.secondview");
    startActivity(i);
    ```



 
 
