### ActionBar
* Object 상속받음
* 하위호환성 X - 기기의 버전에 따라 다르게 동작
* 액티비티 내부에 기본적으로 포함된 요소라서 style 이 .NoActionBar 가 아니라면 자동으로 표시됨
---
### Toolbar
* ViewGroup 상속받음
  * View 이기 때문에 애니메이셔 적용이 쉽고 변경이 용이함
* 하위호환성 O - 기기 버전 상관없이 일관성을 보장
* .NoActionBar 스타일을 지정하고 xml 로 만들어야한다. 
