ConstraintLayout - [출처 - dev님의 tistory](https://recipes4dev.tistory.com/158), [안드로이드 공식문서](https://developer.android.com/reference/android/support/constraint/ConstraintLayout)
===
* constraint == 강제, 제약 이라는 뜻을 가진다.
  * 제약(Constraint)"이란, 각 요소들의 최종 위치와 크기를 결정하게 될 조건이다.
* View Widget 의 위치와 크기를 **유연하게** 조절할 수 있게 만들어주는 레이아웃이다.
* layout_constraint[제약조건]의 형식을 가진다.
* **많은 수의 제약조건들을 가지고 있다.**
* |||
  |----|----|
  |Relative positioning|요소 간 상대 위치 지정 (left, right, top, bottom, start, end, baseline)|
  |Margins|요소 간 여백(Margin) 설정을 위한 제약|
  |Centering positioning|뷰를 부모 레이아웃 또는 제약 영역의 중앙에 배치|
  |Circular positioning|대상 뷰를 기준으로 각도(angle)와 반지름(radius)으로 상대 위치 지정|
  |Visibility behavior|뷰의 Visibility 상태에 따른 최종 위치 결정 및 여백|
  |Dimension constraints|뷰에 적용된 제약에 따른 뷰의 크기(Dimension) 결정|
  |Chains|수평 또는 수직 방향(Axis)으로 나열된 뷰에 대한 그룹화. 배치 스타일 지정|
  |Virtual Helpers objects|레이아웃 내 효율적인 뷰 배치에 사용 가능한 몇 가지 Helper 객체들 (Guideline, Barrier, Group)|
  |Optimizer|제약 카테고리에 대한 최적화|

* 만약 모든 자식(Children) 뷰가 **어떠한 제약도 가지지 않는다면**, 컨스트레인트레이아웃에 포함된 뷰들은 모두 레이아웃 영역의 **왼쪽 위를 기준으로 배치된다.** 
  * 그렇기 때문에 나중에 추가된 뷰가 앞서 작성된 뷰를 덮어버리게 된다.
  * 컨스트레인트레이아웃을 사용할 때는 **자식(Children) 뷰에 반드시 하나 이상의 제약(또는 수평, 수직 방향에 대한 양 방향 위치 제약)을 적용**해야 한다.
