selector
---
* 특정 변화에 따라서 다른 이미지나 효과를 주고 싶을때 사용한다.
* 보통 다음과 같은 형식으로 되어있다.
  * ```java
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item
            android:state_checked="false"
            android:state_enabled="false"
            android:drawable="@drawable/switch_track_off" />

        <item
            android:state_checked="true"
            android:state_enabled="true"
            android:drawable="@drawable/switch_track_on" />

    </selector>
