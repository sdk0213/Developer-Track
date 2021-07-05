selector
---
* 특정 변화에 따라서 다른 이미지나 효과를 주고 싶을때 사용한다.
* 보통 다음과 같은 형식으로 되어있다.
  * ```xml
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item
            android:state_checked="false" // 해당 상태일때 아래 레이아웃 으로 draw
            android:state_enabled="false"
            android:drawable="@drawable/switch_track_off" />

        <item
            android:state_checked="true" // 해당 상태일때 아래 레이아웃 으로 draw
            android:state_enabled="true"
            android:drawable="@drawable/switch_track_on" />

    </selector>
* ex) switch_track_on And off
 ```xml
  <shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="@color/material_50" />
  </shape>
* ex) switch_track_on
 ```xml
  <shape xmlns:android="http://schemas.android.com/apk/res/android">
      <solid android:color="#E0F7FA" />
      <stroke
          android:width="3sp"
          android:color="#000000" />
  </shape>
