# protocol 
---
### 이미 알고있떤 protocol 의 정리
* 프로토콜이란 컴퓨터나 원거리 통신 장비 사이에서 메시지를 주고 받는 양식과 규칙의 체계입니다.

---
### Swift 의 Protocol 
* 사용이유
  * 자바나 코틀린처럼 상속의 한계 극복
* 특정 역할을 하기 위한 메서드, 프로퍼티, 기타 요구사항 등의 청사진을 정의
* 자바 인터페이스보다는 조금 더 넓은 의미를 가지며 객체를 만들 때 스케치를 하는 느낌이 강한것같다.

##### property
* 항상 var 키워드를 쓰는 변수 프로퍼티로 사용하며 아래코드 처럼 get set 으로 접근 수정 처리
* ```swift
  protocol ComputerScienceStudent{
     var name:String { get }
     var laptop:String? { get set }
     
     ..
     .
  }
  
##### method
* static 혹은 class 키워드 사용
* ```swift
   protocol Animal {
     var name: String {get set} //읽기, 쓰기 모두 가능
     var age: Int {get set} //읽기만

     func sayNameAndAge()

      mutating func addAge(amount: Int) // <----------- 인스턴스 내부의 값을 변경하는 가변 메서드의 경우 mutating(struct 한정, class 는 안해도됨)
  }

  struct Human: Animal {
      var name: String = "Park"
      var age: Int = 15

      func sayNameAndAge() {
          print(name)
          print(age)
      }

      mutating func addAge(amount: Int) {
          age += amount
      }
  }
##### initializer
* ```swift
  protocol Animal {
     var name: String {get set} //읽기, 쓰기 모두 가능
     var age: Int {get set} //읽기만

     init(name: String, age: Int)    // <----------------- initializer


     func sayNameAndAge()

     mutating func addAge(amount: Int)
  }

  class Human: Animal {
      var name: String
      var age: Int

      required init(name: String, age: Int) {
          self.name = name
          self.age = age
      }


      func sayNameAndAge() {
          print(name)
          print(age)
      }

      func addAge(amount: Int) {
          age += amount
      }
  }
  
  // var p1: Human = Human(name: "Park", age: 15)
  // p1.addAge(amount: 5)
  // p1.sayNameAndAge()

##### 선택적인 구현을 하고싶을때는 @objc
* ```swift
  @objc protocol ComputerScienceStudent{
     var name:String { get }
     var laptop:String? { get set }

     func doDataStructure()
     func doOperatingSystem()
     func doNetwork()

     @objc optional func doGraphics()
  }
---
### [주의점](https://academy.realm.io/kr/posts/understanding-swift-protocol/)
* Swift 프로그래밍이라고 무조건 프로토콜을 사용하기보다는 프로토콜을 필요한 경우에 잘 쓴다면 좀 더 Swift스러운 코드
