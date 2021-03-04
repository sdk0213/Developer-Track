# by
### 사용 이유 - [출처](https://velog.io/@jojo_devstory/코틀린-Kotlin-by-by-the-way-what-is-this)
* **데코레이터 패턴을 쉽게 활용할수있다.**
* 상위 클래스와 하위 클래스의 서로 간의 결합도가 높아져 상위 클래스의 변화가 하위 클래스에 주는 영향을 예측하기 어렵다.
* 불필요한 상위 클래스의 메서드까지 구현해야 한다.
* final클래스의 경우 상속이 불가능하다.
* 상속 구조가 복잡해질수록 그 영향에 대한 예측이 힘들어진다.
* 하위 클래스로 내려갈수록 기능이 더해져 파악하기 어렵다.
---
### 코틀린
* 코틀린에서는 자바와는 달리 상속으로 인한 종속성, 의존성 문제를 방지하기 위해 기본적으로 클래스는 final
* 상속을 원한다면 상속될 클래스에 open 접근자를 명시
---
### 기능
##### 보일러코드 생략 가능
* by 키워드 X
  ```kotlin
  interface IWindow {
      fun getWidth() : Int
      fun getHeight() : Int
  }

  open class TransparentWindow : IWindow {
      override fun getWidth(): Int {
          return 100
      }

      override fun getHeight() : Int{
          return 150
      }
  }

  class UI(window: IWindow) : IWindow {
      val mWindow: IWindow = window

      override fun getWidth(): Int {
          return mWindow.getWidth()
      }

      override fun getHeight(): Int {
          return mWindow.getHeight()
      }
  }
* by 키워드 O
  ```kotlin
  interface IWindow {
    fun getWidth() : Int
    fun getHeight() : Int
  }

  open class TransparentWindow() : IWindow {
      override fun getWidth(): Int {
          return 100
      } 

      override fun getHeight() : Int{
          return 150
      }
  }

  class UI(window: IWindow) : IWindow by window {

  }
* 사용
  ```kotlin
  fun main(args: Array<String>) {
    val window: IWindow = TransparentWindow()
    val ui = UI(window)
    System.out.println("Width : ${ui.getWidth()}, height: ${ui.getHeight()}")
  }
