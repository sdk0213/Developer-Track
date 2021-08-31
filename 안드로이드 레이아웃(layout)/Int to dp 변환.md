### dp to Int 변환
* 동적으로 layout 형성시 Int 값으로 처리해야하는경우가 있는데 이를 dp 형식으로 사용하고 싶을경우 다음과같이 변환가능하다.
* ```java
  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, <<DP 값을 여기다가 입력 >> ,getResources().getDisplayMetrics());
* ```java
  // 마진 - 35
  ((LinearLayout.LayoutParams) imageView.getLayoutParams()).topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,35,getResources().getDisplayMetrics());
  // 가로 - 250
  imageView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,250,getResources().getDisplayMetrics());
  // 세로 - 300
  imageView.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,300,getResources().getDisplayMetrics());
