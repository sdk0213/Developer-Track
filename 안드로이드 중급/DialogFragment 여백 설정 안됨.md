# DialogFragment
### 문제점
* DialogFragment 는 기기마다 각각 여백을 가지고 있어서 사이즈 조절이 쉽지 않음
### 해결
* 디바이스 사이즈를 구하여서 설정 params 값 변경
* 아래와 같이 설정후 match_parent 로 설정하여야함
* 아래설정은 width 를 조절하는값, height 설정시에는 height 를 match_parent 로 설정 및 params 값을 맞추어 변경
* ```kotlin
  override fun onResume() {
    super.onResume()
    val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
    val windowsManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val deviceWidth = windowsManager.currentWindowMetricsPointCompat().x
    params?.width = (deviceWidth * 0.9).toInt() // 여백을 90 퍼센트로 설정
    dialog?.window?.attributes = params as WindowManager.LayoutParams 
  }
* ```kotlin
  // Extension.kt
  // 윈도우 사이즈 계산
  fun WindowManager.currentWindowMetricsPointCompat(): Point {
      return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
          val windowInsets = currentWindowMetrics.windowInsets
          var insets: Insets = windowInsets.getInsets(WindowInsets.Type.navigationBars())
          windowInsets.displayCutout?.run {
              insets = Insets.max(
                  insets,
                  Insets.of(safeInsetLeft, safeInsetTop, safeInsetRight, safeInsetBottom)
              )
          }
          val insetsWidth = insets.right + insets.left
          val insetsHeight = insets.top + insets.bottom
          Point(
              currentWindowMetrics.bounds.width() - insetsWidth,
              currentWindowMetrics.bounds.height() - insetsHeight
          )
      } else {
          Point().apply {
              defaultDisplay.getSize(this)
          }
      }
  }
