
# Closure(클로저)
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


 
 

# https://devxoul.gitbooks.io/ios-with-swift-in-40-hours/content/Chapter-3/functions-and-closures.html
# 클로즈 관련 추가내용 필요 
