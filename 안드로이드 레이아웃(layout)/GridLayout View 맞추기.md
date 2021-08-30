* dp 로 설정할경우 기기에 따라서 다르게 보이는 경우가 있어서 이 비율을 조절해야되는데 이를 다음과 같은 코드로 가능하다.
* ```java
  public class MyCustomLayout extends RelativeLayout { // <----------- Relative 또는 Linear 또는 기타 등등...

    public SquareRelativeLayout(Context context) {
        super(context);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareRelativeLayout(Context context, AttributeSet attrs,         int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Set a square layout.
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

  }
* 그리고 xml 에서는 위를 사용한다.
  * ```xml
    <RelatvieLayout .......... 
    
    // 을 아래로 수정
    
    <com.my.app.view.myCustomRelativeLayout
