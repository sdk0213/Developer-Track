* stroke 방향에 따라 다르게 값 주기
* 오른쪽이나 아래쪽에만 stroke 값을 주고싶은경우 아래와 같이 사용
```java
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:top="-1dp" <----- 아래 width 만큼 값 내려야함
        android:left="-1dp" <----- 아래 width 만큼 값 내려야함
        android:right="0dp"
        android:bottom="0dp">
        <shape>
            <solid android:color="@color/white" />
            <stroke
                android:width="1dp"
                android:color="@color/footer_color" />
        </shape>
    </item>

</layer-list>
