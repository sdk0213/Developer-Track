# Inversion Of Control = 제어의 역전 - [출처는 develogs님의 티스토리입니다.](https://develogs.tistory.com/19)
* Don't call us, we'll call you "연락하지마, 내가 연락할게"
* **클래스 생성을 누가 할것이냐 누가 작업할것이냐고 포인트**
* 라이브러리는 내 코드가 라이브러리를 이용한다. 즉, 제어권이 내 코드에 있다. 반면 프레임 워크는 프레임 워크가 나의 코드를 실행하는 IOC이다.
* 클래스의 생성자를 직접 호출해 인스턴스를 생성하는것을 프레임워크에 맡기는것
* 이해가 잘안가면 출처의 그림 14참고
### 의존성 문제
##### A -> B <- C
* A안에 B를 객체로 가지고 있는 경우 A가 B에 대해 의존을 한다고 한다.
* C도 B에 대해서 의존한다.
##### 발생하는 문제
* B가 바뀌면 A는 B를 의존하므로 다시 바꿔주지 않는이상 사용할수 없다. **즉, B는 코드 하나 변경할려면 여러 경우에 A나 C도 고려해야한다.**
* 만약 A를 다른 포로젝트에서 사용할경우 B와 관련된 부분을 수정해야한다. **즉, 모듈화가 안되어있다.**
---
### Dependency Inversion Principle
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
* factory사용
  ```kotlin
  class SwitchButton { 
      let lamp: SwitchButtonInterface = Factory.getObject()
      ...
  }
---
### 안드로이드에서..
* ```kotlin
  class someActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...
    }

    override fun onResume() {
        super.onResume()
        ...
    }
  }
* Activity 코드를 작성할 때 생명주기 메소드가 호출되었을 때의 **동작만 정의하고, 언제 생명주기 메소드를 호출 할지는 신경쓰지 않는다.
**즉, Activity 의 메인 흐름 제어권은 나의 코드가 아니라 안드로이드 플랫폼에서 쥐고 있다.**
