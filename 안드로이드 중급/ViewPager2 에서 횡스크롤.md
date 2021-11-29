# ViewPager2 에서 횡스크롤
### interface class
* ```kotlin
  class PreventTouchEventOnViewPager : RecyclerView.OnItemTouchListener {

      override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
          when (e.action) {
              MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
          }
          return false
  
      }

      override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
      }

      override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
      }

  }
### recyclerview
* ```kotlin
  binding.recyclerviewName.addOnItemTouchListener(PreventTouchEventOnViewPager())
  
##### 다른 것들도 위와 같이 응용
