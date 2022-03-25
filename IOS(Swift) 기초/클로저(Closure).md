
# Closure(클로저)
---
### kotlin 에서의 람다표현식과 많이 비슷하면서도 조금씩 다르다.
* closure 중에 trailling closure 는 trailling lambda 라는 [kotlin 문법 - [Developer-Track -> 안드로이드 공부(Kotlin) -> 고차함수와 람다함수.md 파일 참고]](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20공부(Kotlin)/고차함수와%20람다함수.md)과 동일한다. 이를 통해서 swift closure는 lambda 의 swift 식 표현이라고 봐도 될것같다.
---
### 중괄호({})로 감싸진 '실행 가능한 코드 블럭' Closure
* 이름이 없이 함수를 선언한것이 java, kotlin 의 람다와 매우 비슷하다.
---
### 주의점
* ```swift
  // closure 사용시 argu label 사용 불가함 -> Error 발생
  closure("Sodeul")
  closure(name: "Sodeul")  //error!
* java, kotlin 람다와 비슷하게 코드가 심플해지는반면 지나친 사용은 가독성을 떨어트릴것으로 예상됨
---
### 실사용 표현식
* 표현방법 1
  ```swift
  func helloGenerator(c: String) -> (String, String) -> String {
    return { (a: String, b: String) -> (String) in
      return a + b + c
    }
  }
* 표현방법 2 (타입추론으로 타입 생략 가능)
  ```swift
  func helloGenerator(c: String) -> (String, String) -> String {
    return { a, b in
      return a + b + c
    }
  }
* 표현방법 3
  ```swift
  func helloGenerator(message: String) -> (String, String) -> String {
    return {
      return $0 + $1 + message
    }
  }
* 표현방법 4
  ```swift
  func helloGenerator(message: String) -> (String, String) -> String {
    return { $1 + $0 + message }
  }
* 표현방법 5
  ```swift
  // 사실상 아무 의미없는 함수로 func(){ return; } 이다.
  let closure = { () -> () in
      print("Closure")
  }
* 표현방법 6
  ```swift
  let closure = { (name: String) -> String in
      return "Hello, \(name)"
  }
---
### 호출
* 호출방법 1 (= inline closure)( = 일반적인 java, kotlin 람다형태)
  ```swift
  func fetchData(success: () -> (), fail: () -> ()) {
    //do something...
  }
  
  // 호출 부분
  fetchData(success: { () -> () in
      print("Success!")
  }, fail: { () -> () in
      print("Fail!")
  })
* 호출방법 2 (= trailling closure)
  * 마지막 파라미터 closure 는 함수뒤에 붙혀서 연속적으로 코드 작성가능하다. 
    ```swift
    
    fetchData(success:  { () -> () in
      print("Success!")
    }) { () -> () in // <--- 함수가 닫히고 마지막 파라미터는 클로져형태로 전달해줌
        print("Fail!")
    }
---  
### 경량화
* 개인적인 생각으로 파라미터 넘버링화부터는 가독성이 너무 안좋아서 진짜 명확한거 아니거나 협업이라면 사용 지양하는게 좋을듯싶다.
* ```swift
  func doSomething(closure: (Int, Int, Int) -> Int) {
    closure(1, 2, 3)
  }
  
  // 호출 부분 경령화
  doSomething(closure: { (a: Int, b: Int, c: Int) -> Int in
    return a + b + c
  })
  
  // ▽▽▽▽▽ 타입추론(?)
  
  doSomething(closure: { (a, b, c) in
    return a + b + c
  })

  // ▽▽▽▽▽ 파라미터 넘버링화
  doSomething(closure: {  
      return $0 + $1 + $2
  })
  
  // ▽▽▽▽▽ return 단일인경우 생략
  doSomething(closure: {
     $0 + $1 + $2
  })

  // ▽▽▽▽▽ trailling closure
  doSomething {  
     $0 + $1 + $2
  }

---
### @autoclosure [출처는 eunjin3786님의 티스토리이며 여기서 보면 자세하게 설명되어있음](https://eunjin3786.tistory.com/468)
* 클로저 함수에 대한 지연동작 보장
* 파라미터로 함수 + 직접값을 넣는 두가지로 형태로 전달이 가능하게 하고싶을때 사용한다.
* ```swift
  func goodMorning(morning: Bool, whom: String) {
    if morning {
        print("Good morning, \(whom)")
    }
  }

  func giveAname() -> String {
      print("giveAname() is called")
      return "Robert"
  }

  goodMorning(morning: true, whom: giveAname())

  // 출력결과
  // giveAname() is called
  // Good morning, Robert
 
 
  goodMorning(morning: false, whom: giveAname())

  // 출력결과
  // giveAname() is called // <---------중요Point!!------------ 무조건 한번 실행된다. 
  
* 무조건 한번 실행되기보다는 지연되게 동작하고싶다면? -> 클로저로 사용
  * ```swift
    func goodMorning(morning: Bool, whom: () -> String) {
      if morning {
          print("Good morning, \(whom())")
      }
    }

    func giveAname() -> String {
        print("giveAname() is called")
        return "Robert"
    }

    goodMorning(morning: true, whom: giveAname)
    // 출력결과
    // giveAname() is called
    // Good morning, Robert

    goodMorning(morning: false, whom: giveAname)
    // 출력결과 없음
    
* 그런데 이렇게하면 값만 넘겨주에 컴파일 에러가 뜨는데.. 하지만 그냥 값 그 자체를 넘겨주고 싶은경우도 있을수있으니까 @autoclosure 사용한다.
  * ```swift
    func goodMorning(morning: Bool, whom: @autoclosure () -> String) {
      if morning {
          print("Good morning, \(whom())")
      }
    }

    func giveAname() -> String {
        print("giveAname() is called")
        return "Robert"
    }

    goodMorning(morning: false, whom: giveAname())
    goodMorning(morning: true, whom: "Pavel")

    // 출력결과
    // Good morning, Pavel

