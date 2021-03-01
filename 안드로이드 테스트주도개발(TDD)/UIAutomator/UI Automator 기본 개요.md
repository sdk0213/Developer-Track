UI Automator 기본 개요 - [Android 공식](https://developer.android.com/training/testing/ui-automator?hl=ko)
===
* **※ Android 4.3(API 수준 18) 이상이 필요**
* UI Automator API를 사용하면 테스트 기기에서 Settings 메뉴 또는 앱 런처 열기와 같은 작업을 실행할 수 있다.

> UI Automator 뷰어
* Android 기기에 현재 표시되는 UI 구성요소를 검사하고 분석

> 기기 상태 액세스
* 기기 회전 변경
* '볼륨 업'과 같은 하드웨어 키 누르기
* 뒤로 버튼, 홈 버튼 또는 메뉴 버튼 누르기
* 알림 창 열기
* 현재 창의 스크린샷 찍기

> UI Automator API
* 다음과 같은 API를 사용하여 여러 앱에 걸쳐 UI 구성요소를 캡처하고 조작할 수 있습니다.
  * UiCollection
    * 표시되는 텍스트 또는 콘텐츠 설명 속성별로 하위 요소를 카운트하거나 타겟팅할 목적으로 컨테이너의 UI 요소를 열거합니다.
  * UiObject
    * 기기에 표시되는 UI 요소를 나타냅니다.
  * UiScrollable
    * 스크롤 가능한 UI 컨테이너에 있는 항목을 검색하기 위한 지원 기능을 제공합니다.
  * UiSelector
    * 기기에 있는 하나 이상의 타겟 UI 요소에 관한 쿼리를 나타냅니다.
  * Configurator
    * UI Automator 테스트를 실행하기 위한 주요 매개변수를 설정할 수 있다.
