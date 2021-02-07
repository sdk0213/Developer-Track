ripple
---
* 사전적 의미
 * 잔물결 모양으로 오글쪼글하게 짠 얇은 바탕의 평직물
* 일반적으로 버튼을 눌렀을때 순식간에 색이 변하는 selector 보다 조금더 자연스러운 ripple 의 형태가 깔끔해보임
* ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <ripple xmlns:android="http://schemas.android.com/apk/res/android"
      android:color="@color/grey500">
      <item
          android:id="@android:id/mask"
         android:top="2dp"
         android:bottom="2dp">
         <shape android:shape="rectangle">
             <solid android:color="@android:color/white" />
             <corners android:radius="25dp" />
         </shape>
     </item>      

      <item
         android:id="@android:id/background"
         android:top="2dp"
         android:bottom="2dp">

          <shape android:shape="rectangle">
              <solid android:color="@android:color/white" />
              <corners android:radius="25dp" />
         </shape>
      </item>
  </ripple>
  
* background 에 해당 ripple 넣으면 된다.
* selector를 따로 설정하지 않아도 색변화를 줄수 있다.
