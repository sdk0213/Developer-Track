# CoordinatorLayout (coordinate: 조정하다)
* 예제코드는 Android Studio -> new -> New Project -> Scrolling Actitivty 로 확인
##### Behavior
* 자식뷰에 Behavior가 지정되어 있으면, CoordinatorLayout은 그것을 토대로 부모뷰 - 자식뷰 / 자식뷰 - 자식뷰 의 소통을 구현
* Child View의 Behavior를 수신하여 다른 Child View에 Behavior를 전달한다. 각 Child View는 미리 정의된 Behavior를 사용하거나 새롭게 만든 Behavior를 사용하여 수신된 Behavior로 특정 작업을 수행할 수 있다.
##### Anchor (Anchor: 닻)
* layout 이 변화하니 상대적으로 고정이 필요한 view 의 경우가 있기 때문에 필요함
* 다른 임의의 화면의 상대위치에 뷰를 띄우는 용도로 사용될 수도 있다.
* app:layout_anchor
  * 겹치거나 위치시킬 View의 ID
  * coordinator 내부 에 있는 다른 뷰여야한다.
* layout_anchorGravity
  * 위치를 지정할 수 있다. |(파이프)를 통해 여러개 선택가능 
  * layout_anchor id 에 대한 나의 상대위치
* 예)
  ```xml
  app:layout_anchor="@id/app_bar"
  app:layout_anchorGravity="bottom|end"

##### app:layout_scrollFlags
* scroll
  * 스크롤 이벤트와 직접 연관되어 스크롤 됩니다.
* snap
  * 스크롤이 종료될 때 View가 부분적으로만 표시되면, View가 스냅되어 가장 가까운 가장자리로 스크롤됩니다.
* enterAlways	
  * 아래로 스크롤 할 때마다 이 View가 아래로 보여지게 됩니다.
* enterAlwaysCollapsed	
  * enterAlways 속성과 비슷하지만 스크롤 뷰가 맨 위에 도달했을 때 전체뷰가 보여지게 됩니다.
* exitUntilCollapsed	
  * 스크롤을 아래, 위로 이동 시 View의 minHeight 만큼만 보여지고 스크롤이 최상단에 도착 시 나머지 View의 전체가 보여지게 됩니다.

##### coordinatorLayout
* CoordinatorLayout은 자식뷰의 스크롤의 변화 상태를 다른 자식뷰에게 전달하여 view 를 변경해주는 layout
* **View에 Behavior를 등록**
  * NestedScrollView 나 RecyclerView 등에 스크롤의 상태를 판단
* ```xml
  <androidx.coordinatorlayout.widget.CoordinatorLayout
      <..Appbar...
        <.. CollapsingToolbarLayout 
            ..
            ..
            app:layout_scrollFlags="scroll|exitUntilCollapsed" // <-- 스크롤시 변경됨
            ..
        </.. CollapsingToolbarLayout>
            
      </ Appbar>
      
      <androidx.core.widget.NestedScrollView
        ..
        ..
        app:layout_behavior="@string/appbar_scrolling_view_behavior" // Behavior 설정
        
      </ ... NestedScrollView>

      <com.google.android.material.floatingactionbutton.FloatingActionButton
          android:id="@+id/fab"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginEnd="@dimen/fab_margin"
          app:layout_anchor="@id/app_bar"  // <-- app_bar 를 앵커(닻)으로 지정
          app:layout_anchorGravity="bottom" // <-- app_bar 에 대한 해당뷰의 상대적위치
          app:srcCompat="@android:drawable/ic_dialog_email" />
    
  </ ... CoordinatorLayout>
