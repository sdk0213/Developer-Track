# (DIP)Dependency Inversion Principle
* ![image](https://user-images.githubusercontent.com/51182964/172050341-e011707e-8959-43e9-b855-c72c1a7aec78.png)
* "구체화(변동성이 큼)된것에 의존하지 말고 추상적(변동성이 작음)인것에 의존하라"
* 소스 코드 단계에서의 의존을 역전시킨다는 의미이며 곧 개발자에게 의미있는것이며 런타임에서는 의존관계가 역전되지 않는다
* 원칙
  * 첫째, 상위 모듈은 하위 모듈에 의존해서는 안된다. 상위 모듈과 하위 모듈 모두 추상화에 의존해야 한다.
  * 둘째, 추상화는 세부 사항에 의존해서는 안된다. 세부사항이 추상화에 의존해야 한다.
  * 이 원칙은 '상위와 하위 객체 모두가 동일한 추상화에 의존해야 한다'는 객체 지향적 설계의 대원칙을 제공한다.
* **공통인 부분을 변화하지 않는 것에 추상화 시켜 구체화된것말고 추상화된 대상에 의존하라는것이 포인트**
* "고차원 모듈은 저차원 모듈에 의존하면 안된다. 이 모듈 모두 다른 추상화된 것에 의존해야 한다. 추상화 된 것은 구체적인 것에 의존하면 안 된다. 구체적인 것이 추상화된 것에 의존해야 한다."
  * "카드 결제(상위 모듈은)는 신한 카드 결제(하위 모듈)에 의존해서는 안된다. 신한 카드 결제(하위 모듈)은 카드 결제(상위 모듈)에 정의한 카드 결제 인터페이스(추상 타입)에 의존 해야한다."
* A -> Abstract -> B
* 기존
  ```kotlin
  class SwitchButton { 
      let lamp = Lamp() 
      var isOn = false 
      
      fun toggle() { 
          isOn = !isOn 
          if isOn { 
              lamp.lightOn() 
          } else { 
              lamp.lightOff() 
          } 
      } 
  } 
  
  class Lamp { 
      fun lightOn() { 
          print("lamp on") 
      }
      
      fun lightOff() { 
          print("lamp off") 
      } 
  }
* 변경
  ```kotlin
  class SwitchButton { 
      let lamp: SwitchButtonInterface = Lamp()
      var isOn = false 
      fun toggle() { 
          isOn = !isOn 
          if isOn { 
              lamp.on() 
          } else { 
              lamp.off() 
          }
      } 
  } 
  
  Interface SwitchButtonInterface { 
      fun on() 
      fun off() 
  } 
  
  class Lamp: SwitchButtonInterface { 
      fun on() { 
          lightOn() 
      } 
      
      fun off() { 
          lightOff() 
      } 
  }
* factory 사용
  ```kotlin
  class SwitchButton { 
      let lamp: SwitchButtonInterface = Factory.getObject()
      ...
  }
 
