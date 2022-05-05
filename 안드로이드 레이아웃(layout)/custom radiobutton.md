# Radio button Customization
* selector 를 사용
* background 속성이 아닌 button 속성을 사용
* background 속성은 @null 로 제거해버림
---
### Result
* normal
  * <img width="51" alt="스크린샷 2022-05-05 오후 9 55 40" src="https://user-images.githubusercontent.com/51182964/166927301-e41ad115-b981-4e6d-904c-80e151dffe82.png">
* checked
  * <img width="49" alt="스크린샷 2022-05-05 오후 9 55 52" src="https://user-images.githubusercontent.com/51182964/166927333-1fab1d91-91e0-44af-ab6a-3739e4991731.png">

---
### RadioButton 
* ```xml
  <androidx.appcompat.widget.AppCompatRadioButton
      android:id="@+id/rb_hello"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:background="@null"
      android:button="@drawable/selector_radiobutton"
      android:text="Test Radio Button"/>
      
### Selector
* ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <selector xmlns:android="http://schemas.android.com/apk/res/android" >
      <item
          android:state_checked="true"
          android:drawable="@drawable/radio_checked" />
      <item
          android:state_checked="false"
          android:drawable="@drawable/radio_normal" />
  </selector>
---
### radio_checked.xml
* ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <layer-list xmlns:android="http://schemas.android.com/apk/res/android" >
      <item>
          <shape>
              <corners android:radius="24dp" />
              <solid android:color="@color/test_color" />
          </shape>
      </item>
      <item
          android:drawable="@drawable/shape_oval_white"
          android:top="6dp"
          android:right="6dp"
          android:bottom="6dp"
          android:left="6dp" />
  </layer-list>
### radio_noraml.xml
* ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <layer-list xmlns:android="http://schemas.android.com/apk/res/android" >
      <item>
          <shape>
              <stroke android:color="@color/samsunglife_primary_color_grey" android:width="2dp" />
              <corners android:radius="24dp" />
              <solid android:color="@color/white" />
          </shape>
      </item>
      <item
          android:drawable="@drawable/shape_oval_white"
          android:top="6dp"
          android:right="6dp"
          android:bottom="6dp"
          android:left="6dp" />
  </layer-list>
