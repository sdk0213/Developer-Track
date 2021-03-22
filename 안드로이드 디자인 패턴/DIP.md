# (DIP)Dependency Inversion Principle
* 원칙
  * 첫째, 상위 모듈은 하위 모듈에 의존해서는 안된다. 상위 모듈과 하위 모듈 모두 추상화에 의존해야 한다.
  * 둘째, 추상화는 세부 사항에 의존해서는 안된다. 세부사항이 추상화에 의존해야 한다.
  * 이 원칙은 '상위와 하위 객체 모두가 동일한 추상화에 의존해야 한다'는 객체 지향적 설계의 대원칙을 제공한다.[
* **실체말고 추상화에 의존하라는것이 포인트**
* "고차원 모듈은 저차원 모듈에 의존하면 안된다. 이 모듈 모두 다른 추상화된 것에 의존해야 한다. 추상화 된 것은 구체적인 것에 의존하면 안 된다. 구체적인 것이 추상화된 것에 의존해야 한다."
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
