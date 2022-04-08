# protocol 
---
### 이미 알고있떤 protocol 의 정리
* 프로토콜이란 컴퓨터나 원거리 통신 장비 사이에서 메시지를 주고 받는 양식과 규칙의 체계입니다.

---
### Swift 의 Protocol 
* 사용이유
  * 자바나 코틀린처럼 상속의 한계 극복

##### 기본 형태
* 프로토콜을 따를 때 해당 변수는 { get } 과 { get set } 으로 구현한다.
* protocol
  ```swift
  protocol ComputerScienceStudent{
     var name:String { get }
     var laptop:String? { get set }

     func doDataStructure()
     func doOperatingSystem()
     func doNetwork()
  }
* class
  ```swift
  class Student:ComputerScienceStudent{
 
     ... property 코드들..     
     var laptop:String?{
         get{
             return self._laptop
         }

         set{
             self._laptop = newValue
         }
     }
     ... property 코드들..     

     func doDataStructure(){
         print("I love Data Structure") // <-- 구현
     }
     func doOperatingSystem(){
         print("It's boring") // <-- 구현    
     }
     func doNetwork(){
         print("I like Network") // <-- 구현
     }
  }


---
### [주의점](https://academy.realm.io/kr/posts/understanding-swift-protocol/)
* Swift 프로그래밍이라고 무조건 프로토콜을 사용하기보다는 프로토콜을 필요한 경우에 잘 쓴다면 좀 더 Swift스러운 코드
