### 레아아웃 Id 동적생성
* 레이아웃 Id 를 동적으로 생성 필요할때 랜덤한 값이 필요한데 이때 다음과 같이 해결가능하다.
* idx.xml 파일 생성
  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <resources>
        <item type="id" name="layout1" />
        <item type="id" name="layout2" />
        <item type="id" name="layout3" />
  </resources>
* 다음과 같은 형식으로 동적으로 가져오기
* ```kotlin
  for (i : Int in 1..3) {
    resources.getIdentifier("layout" + i,"id", mContext.packageName)
  }
