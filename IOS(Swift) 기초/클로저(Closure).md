
# Closure(클로저)
### 중괄호({})로 감싸진 '실행 가능한 코드 블럭' Closure
* 이름이 없이 함수를 선언한것이 java, kotlin 의 람다와 매우 비슷하다.
* 1번 방법
  ```swift
  func helloGenerator(c: String) -> (String, String) -> String {
    return { (a: String, b: String) -> (String) in
      return a + b + c
    }
  }
* 2번 방법 (타입추론으로 타입 생략 가능)
  ```swift
  func helloGenerator(c: String) -> (String, String) -> String {
    return { a, b in
      return a + b + c
    }
  }
* 3번 방법
  ```swift
  func helloGenerator(message: String) -> (String, String) -> String {
    return {
      return $0 + $1 + message
    }
  }
* 4번 방법
  ```swift
  func helloGenerator(message: String) -> (String, String) -> String {
    return { $1 + $0 + message }
  }
  
# https://devxoul.gitbooks.io/ios-with-swift-in-40-hours/content/Chapter-3/functions-and-closures.html
# 클로즈 관련 추가내용 필요 
