toolbar 와 actiobar
---
* Actionbar는 Object를 상속받는다
* Toolbar(android.support.v7.widget.Toolbar)는 ViewGroup을 상속받는다 -> view 이기 떄문에 변경이 쉽다.




actionbar 그림자 삭제하기
---
* ```java
  getSupportActionBar().setElevation(0);
  
  
actiobar 뒤로가기 방향키 색상 바꾸기
---
* ```java
  final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
  upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
  getSupportActionBar().setHomeAsUpIndicator(upArrow);
