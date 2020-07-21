디자인패턴
===
디자인패턴의 종류
---
* MVC(Model-View-Controller)
* MVP(Model-View-Presenter)
* MVVM(Model-View-View Model)
* 역사적으로 본다면 MVC -> MVP -> MVVM 으로 등장했다.

# 예제코드 GitHUB -> [https://github.com/ericmaxwell2003/ticTacToe](https://github.com/ericmaxwell2003/ticTacToe)
MVC(★) - [출처](https://academy.realm.io/kr/posts/eric-maxwell-mvc-mvp-and-mvvm-on-android/)
---
* ![](/img/MVC.png)
* 구성
  * Model
    * MVC,MVP,MVVM 전부 동일한 의미에서 데이터 + 상태 + 비즈니스 로직을 말하며 앱의 두뇌 역할을 뜻함
  * View
    * 아는것이 아무것도 없는 멍청한 역할 => 종속성 하락 => 변화 용이
  * Controller
    * 모델과 View를 이어주는 역할
* 장점
  * 모델과 뷰를 분리하는것이 용이하고 뷰는 테스트하기가 용이
* 단점
  * 컨트룰러의 문제
    * 테스트 용이성
      * 컨트룰러가 안드로이드 API에 깊게 종속되어 유닛 테스트가 어렵다.
    * 모듈화 및 유연성
      * 뷰와 컨트룰러가 강하게 결합될수 있음
    * 유지 보수
      * 코드가 컨트룰러로 모이면서 문제가 많이 발생
      
MVP(★★) - [출처](https://academy.realm.io/kr/posts/eric-maxwell-mvc-mvp-and-mvvm-on-android/)
---
* ![](/img/MVP.png)
* 구성
  * Model
    * MVC,MVP,MVVM 전부 동일한 의미에서 데이터 + 상태 + 비즈니스 로직을 말하며 앱의 두뇌 역할을 뜻함
  * View
    * Activity와 Fragment가 전부 뷰의 일부로 포함
    * 액티비티가 뷰 인터페이스를 구현해서 프리젠터가 코드를 만들 인터페이스를 갖도록 하는 것이 좋음
  * Presenter
    * 뷰에 직접연결하는것이 아니라 인터페이스로 구성된다 => 테스트 가능성 + 모듈화 + 유연성 해결
    * MVP가 극단적으로 갈경우 어떤 안드로이드 API도 참고하면 안된다는 의견도 있음
* 코드 분석
  * ![](/img/MVPCODE.png)
* 장점
  * API연결이 안되어있어 테스트 용이
* 단점
  * MVC와 동일하게 프레젠터로 코드가 모이는 경향 => 유지보수 매우 힘듬
  
MVVM(★★★ - 매우 중요!!!) - [출처](https://academy.realm.io/kr/posts/eric-maxwell-mvc-mvp-and-mvvm-on-android/)
---
* ![](/img/MVVM.png)
* 구성
  * Model
    * MVC,MVP,MVVM 전부 동일한 의미에서 데이터 + 상태 + 비즈니스 로직을 말하며 앱의 두뇌 역할을 뜻함 
  * View
    * 뷰모델에 의해 보여지는 Observable Variable , Action에 바인딩됨
  * ViewModel
    * 뷰에 필요한 Observable Data를 준비
    * 모델을 래핑
    * 뷰가 모델에 이벤트를 전달할 수 있도록 hook(훅)을 준비
    * 뷰모델에 종속안됨
* 장점
  * 뷰에 대한 의존성이 없어 유닛 테스트가 용이
  * 테스트할때 모델이 변경되는 시점에 옵저버블 변수가 제대로 설정됐는지 확인하면 됨
* 단점
  * 뷰가 변수와 표현식 모두에 바인딩되어 프레젠테이션 로직이 늘어나 XML에 코드를 추가하게 될 수 있다.
    * 뷰 바인딩 표현식에서 값을 계산하거나 파생하지 말고 항상 뷰모델에서 직접 값을 가져오는 것이 중요
