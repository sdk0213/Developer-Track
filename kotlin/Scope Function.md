Scope Function
===
범위지정함수 - [출처 - 커니의안드로이드](https://www.androidhuman.com/lecture/kotlin/2016/07/06/kotlin_let_apply_run_with/) - [출처 - @limgyumin](https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29)
---
* ![](https://miro.medium.com/max/700/1*qgUKSwzEicuHwaQBgN5UFw.png)
* Scope(범위, 영역) 를 일시적으로 만들어서 속성(property)나 함수를 처리하는 용도로 사용되는 함수
* 만들어진 이유
  * 특정 객체에 있는 함수를 연속해서 사용 
  * 다른 함수의 인자로 전달하기 위해 변수를 선언하고 이를 다른 곳에서는 사용하지 않는 경우
* 구성요소
  * 요소
    * 수신 객체
    * 수신 객체 지정 람다 (lambda with receiver)

let
---
* **객체가 null이 아닌 코드를 실행하는 경우 사용**
* ```java
  val padding = TypedValue.applyDimension(
  TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics).toInt()
  // 왼쪽, 오른쪽 padding 설정
  setPadding(padding, 0, padding, 0)
* 위의 자바코드를 아래의 코틀린코드로 옮기면 다음과 같다.
* ```kotlin
  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f,
  resources.displayMetrics).toInt().let { padding ->  // |padding ->| <== 인자가 하나이므로 생략가능하다.
  // 계산된 값을 padding 이라는 이름의 인자로 받음
  setPadding(padding, 0, padding, 0)
* safeCall과 함께 사용해서 null체크가능
* ```kotlin
  // obj가 null이 아닐 경우 작업 수행 (기존 방식)
  if (null != obj) {
      Toast.makeText(applicationContext, obj, Toast.LENGTH_LONG).show()
  }
  // obj가 null이 아닐 경우 작업 수행 (Safe calls + let 사용)
  obj?.let {
      Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
  }
  
with
---
* with() 함수는 사실상 run()함수와 기능이 거의 동일
* run() 함수 = with() + let(), 즉, 편하게 사용하기 위해서 만든것이라고 봐도 무방
* 수신 객체이며 결과가 필요하지 않은 경우 유용
* **safeCalls 지안하기때문 run함수 더 자주사용할듯**
* ```kotlin
  supportActionBar?.let {
  with(it) {
      setDisplayHomeAsUpEnabled(true)
      setHomeAsUpIndicator(R.drawable.ic_clear_white)    
    }
  }
      
run
---
* apply()와 적용 예가 유사하지만, apply()는 새로운 객체를 생성함과 동시에 연속된 작업이 필요할 때 사용하고 run()은 이미 생성된 객체에 연속된 작업이 필요할 때 사용한다는 점이 다르다.
* 블록 내에서 여러 작업을 할때 사용
* ```kotlin
  override fun onCreate(savedInstanceState: Bundle?) {
  ...
  supportActionBar?.run {
     setDisplayHomeAsUpEnabled(true)
     setHomeAsUpIndicator(R.drawable.ic_clear_white)
     }
     ...
  }
    
apply
---
* 특정 객체를 생성하면서 함께 호출해야 하는 초기화 코드가 있는 경우 사용\
* 프로퍼티에 값을 할당할때 유용
* ```kotlin
  val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
  param.gravity = Gravity.CENTER_HORIZONTAL
  param.weight = 1f
  param.topMargin = 100
  param.bottomMargin = 100
  // ▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽
  val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
      gravity = Gravity.CENTER_HORIZONTAL
      weight = 1f
      topMargin = 100
      bottomMargin = 100
  }
        
also
---
* 데이터를 할당하기 전에 유효성 검사등을 할 때 유용
* ```kotlin
  //also 적용 X
  var validData: Customer? = 
  if (jungwoon.name != null &&
      jungwoon.age != null && 
      jungwoon.phone != null && 
      jungwoon.address != null) {
        jungwoon
      } 
      else {
        null
      }
   // also 적용 O    
   val validData = jungwoon.also {
      requireNotNull(it.name)
      requireNotNull(it.age)
      requireNotNull(it.phone)
      requireNotNull(it.address)
   }
       
