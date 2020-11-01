espresso 테스트 설정 - [안드로이드 공식문서](https://developer.android.com/training/testing/espresso/recipes?hl=ko#java)
===
> 뷰를 옆의 다른 뷰와 일치
* ![](https://developer.android.com/images/training/testing/repeated-view.png?hl=ko)
* 위에처럼 7이 연속될때 특정한것을 어떻게 찾을까?
* **hasSibling()**
* ```java
  onView(allOf(withText("7"), hasSibling(withText("item: 0"))))
        .perform(click());
