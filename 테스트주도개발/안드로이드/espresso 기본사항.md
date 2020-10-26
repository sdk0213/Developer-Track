espresso Test 기본사항 - [안드로이드 공식문서](https://developer.android.com/training/testing/espresso/basics?hl=ko)
===
* Espresso API는 테스트 **작성자에게 사용자**가 애플리케이션과 상호작용(UI 요소를 찾고 상호작용)하는 동안 **실행하는 작업의 관점에서 생각하도록 권장**
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
  * ```java
    onView(allOf(withId(R.id.my_view), withText("Hello!")));

    // 매처를 역전시키지 않도록 선택
    onView(allOf(withId(R.id.my_view), not(withText("Unwanted"))));
    
> 뷰에 작업 실행
* ```java
  onView(...).perform(click());
  // OR
  onView(...).perform(typeText("Hello"), click());
  // 작업 중인 뷰가 ScrollView(세로 또는 가로) 내부에 있다면
  onView(...).perform(scrollTo(), click());
    
    


