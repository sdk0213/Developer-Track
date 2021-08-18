# Status bar 색 변경
* ```java
  // java
  Window window = getWindow();
  window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
  window.setStatusBarColor(ContextCompat.getColor( 너의 콘텍스트, R.color.지정할색깔));
  
  
