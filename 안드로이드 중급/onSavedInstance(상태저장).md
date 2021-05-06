### onSavedInstance(상태저장)
* 안드로이드에서 뷰의 상태를 저장하는데 쓰인다.
* 대부분 화면구성 변경시 사용된다.
* viewmodel을 활용할경우 이를 더 쉽게 활용가능하며 여기서 소개되는것은 기본적인것이다.
---
### Activity
* ```java
  @Override
  protected void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState); 
      outState.putSerializable("foo", this.foo);
      outState.putBoolean("bar", true);
 
      // 추가로 자료를 저장하는 코드는 여기에 작성 하세요. 
  }
 
  @Override
  protected void onRestoreInstanceState(Bundle inState) {
      super.onRestoreInstanceState(inState);
      if(inState!=null) {
          if (inState.getBoolean("bar", false)) {
              this.foo = (ArrayList<HashMap<String, Double>>) inState.getSerializable("foo");
          }
      }
  }
---
### Fragment
* lifecycle 참고
  * ![](https://developer.android.com/images/guide/fragments/fragment-view-lifecycle.png)
  * onSaveInstanceState 에서 값을 가져오고 onViewStateRestored 에서 값을 회복한다.
* ```java
  //store the information using the correct types, according to your variables.
  @Override
  public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      outState.putSerializable("foo", this.foo);
      outState.putBoolean("bar", true);
  }

  @Override
  public void onViewStateRestored(Bundle inState) {
      super.onViewStateRestored(inState);
      if(inState!=null) {
          if (inState.getBoolean("bar", false)) {
              this.foo = (ArrayList<HashMap<String, Double>>) inState.getSerializable("foo");
          }
      }

  }
