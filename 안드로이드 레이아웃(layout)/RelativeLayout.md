### Relative 단어 그대로 뷰의 위치를 다른 뷰의 상대적 위치로 결정하는것
* 부모랑 자식 관련 값 동시 사용가능
* 기준선을 맞추려면 맨 아래 align 속성 (맞춤정렬) 사용하는것이 더 현명한 방법
##### 자식 관련
* android:layout_toLeftOf
  * 기준 뷰(View)의 왼쪽(Left)에 배치.
* android:layout_above
  * 기준 뷰(View)의 위(Above)에 배치.
* android:layout_toRightOf
  * 기준 뷰(View)의 오른쪽(Right)에 배치.
* android:layout_below
  * 기준 뷰(View)의 아래(Below)에 배치.
* android:layout_toStartOf
  * 기준 뷰(View)의 시작(Start)에 배치.
* android:layout_toEndOf
  * 기준 뷰(View)의 끝(End)에 배치.
##### 부모 관련 
* android:layout_alignParentLeft
  * 부모(Parent) 영역 내에서 왼쪽(Left)에 배치
* android:layout_alignParentTop
  * 부모(Parent) 영역 내에서 위쪽(Top)에 배치
* android:layout_alignParentRight
  * 부모(Parent) 영역 내에서 오른쪽(Right)에 배치
* android:layout_alignParentBottom
  * 부모(Parent) 영역 내에서 아래쪽(Bottom)에 배치
* android:layout_centerHorizontal
  * 부모(Parent) 영역의 가로(horizontal) 방향 가운데(center) 배치
* android:layout_centerVertical
  * 부모(Parent) 영역의 세로(vertical) 방향 가운데(center) 배치
* android:layout_centerInParent
  * 부모(Parent) 영역의 정 중앙(center)에 배치
* android:layout_alignParentStart
  * 부모(Parent) 영역 내에서 시작 지점(Start)에 배치
* android:layout_alignParentEnd
  * 부모(Parent) 영역 내에서 끝 지점(End)에 배치
##### 맞춤정렬
* android:layout_alignLeft
  * 뷰(View)의 왼쪽(Left)을 기준 뷰(Anchor View)의 왼쪽(Left)에 맞춤.
* android:layout_alignTop
  * 뷰(View)의 위(Top)을 기준 뷰(Anchor View)의 위(Top)에 맞춤.
* android:layout_alignRight
  * 뷰(View)의 오른쪽(Right)을 기준 뷰(Anchor View)의 오른쪽(Right)에 맞춤.
* android:layout_alignBottom
  * 뷰(View)의 아래(Bottom)를 기준 뷰(Anchor View)의 아래(Bottom)에 맞춤.
* android:layout_alignBaseline
  * 뷰(View)의 폰트 기준선(Baseline)을 기준 뷰(View)의 폰트 기준선(Baseline)에 맞춤.
* android:layout_alignStart
  * 뷰(View)의 시작(Start)을 기준 뷰(Anchor View)의 시작(Start)에 맞춤.
* android:layout_alignEnd
  * 뷰(View)의 끝(End)을 기준 뷰(Anchor View)의 끝(End)에 맞춤.
