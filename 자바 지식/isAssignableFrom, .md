### isAssignableFrom
* ```java
  public class AppListAdapter extends BaseAdapter implements Filterable
##### @Test
* ```java
  @Test
  ...
  AppListAdapter.class.isAssignableFrom(BaseAdapter.class) // false
  BaseAdapter.class.isAssignableFrom(AppListAdapter.class) // true
  BaseAdapter.class.isAssignableFrom(Filterable.class) // false
  Filterable.class.isAssignableFrom(AppListAdapter.class) // true
  
### instanceOf
* 자식/부모 관계를 체크 가능하다.
* ```java
  class Car { 
    ...
  }
  
  class FireEngine extends Car { // 소방차 
    ...
  }
  class Ambulance extends Car { // 앰뷸런스
    ...
  }
##### @Test
* ```java
  @Test
  ...  // Debug Mode
  if (c instanceof FireEngine) {        // true
  } else if (c instanceof Ambulance) {  // true
  } else if (FireEngine instanceof c) { // false
  } else if (Ambulance instanceof c) {  // false
  }
