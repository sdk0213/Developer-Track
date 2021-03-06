# DI(Dependency Injection)(의존성주입) = 종속 항목 삽입 - [출처는 안드로이드 공식문서](https://developer.android.com/training/dependency-injection?hl=ko)
### 종속 항목 삽입이란?
* 클래스를 만들고 사용할때는 거의 원 헌드래드 퍼센트까지는 아니여도 많은경우에 다른 클래스가 필요하다. 이는 잘못 설계된 코드이거나 아키텍쳐가 개판일경우 더욱 심해진다. 
**여하튼 이런 많은경우에 다른클래스에 종속되는 다른 클래스를 삽입하는것을 종속 항목 삽입이라고 한다.**
---
### 종속 항목 삽입을 넣지 않고 코딩할경우
* 코드가 개판이 될수 있는 두가지 경우를 알아보자
##### 그냥 자체 인스턴스를 가지고 초기화하기
* ```kotlin
  class Car {

    private val engine = Engine()

    fun start() {
        engine.start()
    }
  }

  fun main(args: Array) {
      val car = Car()
      car.start()
  }
##### 다른 곳에서 객체를 가져오기
* ```kotlin
  class Car {
  
    fun start()
        Main.getEngine.start() // Main, getEngine 은 아마도 static 
    }
    
  }
  
  fun main(args: Array) {
      val.car =  Car()
      car.start()
  }
---
### 위 세가지 코드의 문제점
* 서브클래스 또는 다른 클래스로 대체 구현을 쉽게 사용할 수 없다.
* 테스트 매우매우 힘들어짐
---
### 종속 항목 삽입 사용하기
##### 생성자 삽입
* ```kotlin
  class Car(private val engine: Engine) {

    fun start() {
        engine.start()
    }
  }

  fun main(args: Array) {
      val engine = Engine()
      val car = Car(engine)
      car.start()
  }
##### 필드 삽입(또는 setter 삽입)
* ```kotlin
  class Car {
    lateinit var engine: Engine

    fun start() {
        engine.start()
    }
  }

  fun main(args: Array) {
      val car = Car()
      car.engine = Engine()
      car.start()
  }
##### 장점
* 결합도 하락
* FakeEngine이라는 Engine의 테스트 더블을 생성하여 다양한 테스트에 맞게 구성할 수 있다.
* DI를 사용한다면 업데이트된 ElectricEngine 서브클래스의 인스턴스를 전달하기만 하면 되며 Car는 추가 변경 없이도 계속 작동
* 코드 변경이 용이해진다.
* 클래스 재사용 가능 및 종속 항목 분리: 종속 항목 구현을 쉽게 교체할 수 있습니다. 컨트롤 반전으로 인해 코드 재사용이 개선되었으며 클래스가 더 이상 종속 항목 생성 방법을 제어하지 않지만 대신 모든 구성에서 작동합니다.
* 리팩터링 편의성: 종속 항목은 API 노출 영역의 검증 가능한 요소가 되므로 구현 세부정보로 숨겨지지 않고 객체 생성 타임 또는 컴파일 타임에 확인할 수 있습니다.
* 테스트 편의성: 클래스는 종속 항목을 관리하지 않으므로 테스트 시 다양한 구현을 전달하여 다양한 모든 사례를 테스트할 수 있습니다.
  
