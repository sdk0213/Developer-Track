espresso Test 기본사항 - [안드로이드 공식문서](https://developer.android.com/training/testing/espresso/basics?hl=ko)
===
* ```java
  // 만약 my_view가 display 되고 있다면 클릭해라

  // withId(R.id.my_view) is a ViewMatcher
  // click() is a ViewAction
  // matches(isDisplayed()) is a ViewAssertion
  onView(withId(R.id.my_view))
      .perform(click())
      .check(matches(isDisplayed()));

> 뷰 찾기
* 다양한 뷰 속성을 검토하다보면 고유하게 식별 가능한 속성을 찾을 수 있다
* 위의 예에서 뷰 중 하나에 "Hello!" 텍스트를 사용하여 조합 매처를 통해 **검색 범위를 좁힐 수 있다.**
