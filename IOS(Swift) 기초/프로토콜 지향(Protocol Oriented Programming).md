# POP(Protocol Oriented Programming)
### "Swift 는 프로토콜 지향 언어이다."
* Swift 2.0 과 함꼐 Swift 는 Protocol 지향언어 라고 발표하였다.
### [Protocol Oriented Programming 의 시작](https://www.raywenderlich.com/6742901-protocol-oriented-programming-tutorial-in-swift-5-1-getting-started)
##### POP 가 필요한 이유
* 여러가지 탈것들을 만드는 게임을 만든다고 치자. OOP 로 자동차탈것과 비행가능한탈것을 생성했다. 근데 이게임의 배경이 2300년도라서 날라가는 자동차도 있다. 그렇다면 이를 특수한 베이스클래스로 하나 더 생성해서 만들어야할까? 자동차탈것과 비행기탈것의 super class 를 말하는것이다. 아니면 자동차와 비행기를 둘다 상속받을까? 근데 swift 도 그렇고 대부분의 언어에서 다중상속을 불가능하다.
* 다중상속이 불가능하다? -> POP 에서는 하나의 오브젝트가 여러개의 Protocol 을 가질수있다.
##### vs java inteface
* wiki 에서는 protocl 과 다른언어의 interface 는 동일하다고 말한다.
  * [Protocol is a term used by particular object-oriented programming languages with a variety of specific meanings, which other languages may term interface or trait.](https://en.wikipedia.org/wiki/Protocol_(object-oriented_programming)) - wiki -
* 초기 구현 선택적으로 받을수있으나 초기 값 설정이 불가능 (java inteface 는 이와 반대)
* mutating 을 사용한다.
 * ```swift
  struct Meeting {
    var name: String
    var state: MeetingState
    var reminderDate: Date?
    ...

    mutating func cancel(withMessage message: String) {
        state = .cancelled(message: message)
        reminderDate = nil
    }
  }
* 이 외 많은 사소한 차이가 있지만 POP 의 시작은 protocol Extension 부터 온다고 함
---
### OOP 단점
* superClass 가 뭔지를 알아야 한다.
* 굳이 필요없는 기능까지 상속받아야 한다.(물론 이 경우는 잘못 코드를 짠 경우지긴 하지만..)
---
### POP 장점
* 굳이 알려주지 않아야 할 것은 알려주지 않아도 되고 그로 인해 필요한 것만 쓸 수 있다.
* 값 타입도 공통된 기능을 쉽게 구현할 수 있다.
* Protocol은 마치 (레고의)블럭처럼 기능을 추가할 수 있다.
  * Class 는 기능 추가할라면..... 하.. 그냥 새롭게 만드는게 더 편할정도이다. -> 하지만 이것 또한 매우 복잡하다.
* 제네릭의 활용성이 매우 높아짐
---
### Protocol ?
* 프로토콜은 단순히 설계도일뿐 구현은 못한다. **그래서 Extension**이 등장하였다.
* 구현을 미리 할수있고 이에 대해서는 또 다시 구현할필요가없어진다.
* Extension
  ```swift  
  protocol Person {
      var name: String { get }
      var age: Int { get }

      func getAge() -> Int
  }

  // 프로토콜 초기구현
  extension Person {
      func getAge() -> Int {
          return self.age
      }
  }

  struct FireFighter: Person {
      var name: String
      var age: Int

      func extinguish() {
      }
  }
* Generic
  ```swift
  protocol Box {
    // 연관값으로 선언
    associatedtype Item
    
    var items: [Item] { get set }
    mutating func addItem(item: Item)
  }

  extension Box {
      mutating func addItem(item: Item) {
          items.append(item)
      }
  }

  // 제네릭으로 선언
  struct StructBox<Element>: Box {
      typealias Item = Element

      var items: [Element]
  }
* Protocol 이 추가된다면? -> Extension
  ```swift
  
  protocol Printable {
      func printSelf()
  }

  // where로 Self가 Box 프로토콜을 따르는 경우에만 초기 구현이 되도록 제약.
  extension Printable where Self: Box {
      func printSelf() {
          print(items)
      }
  }

  extension StructBox: Printable {
  }

  // StructBox는 Box 프로토콜을 따르므로 초기 구현된 것을 바로 사용 가능.
  stringBox.printSelf()
  // ["Alpaca", "Cattle"]
  
