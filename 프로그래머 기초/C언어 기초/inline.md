inline
===
inline - [소년코딩 블로그](https://boycoding.tistory.com/220)
---

* 아래의 흐름은 min함수를 거쳐서 retun으로 간다. 즉 main -> min -> main으로 돌아오는식으로 진행된다.
  ```c++
  int min(int x, int y) {
      return x > y ? y : x;
  } 
  
  int main() { 
    std::cout << min(5, 6) << '\n'; 
    std::cout << min(3, 2) << '\n'; 
    return 0; 
  }
* 만약 위의 코드에서 inline함수를 사용할경우 다음과 같다.
  ```c++
  inline int min(int x, int y) {
      return x > y ? y : x;
  } 
  
  int main() { 
    std::cout << min(5, 6) << '\n'; 
    std::cout << min(3, 2) << '\n'; 
    return 0; 
  }
* 위의 코드가 실제로 실행될때는 다음과 같이 표현된다. 안에 함수내용이 바로 line 안(in)으로 들어간다. 그래서 inline함수라고 부른다.
  ```
  int main() { 
    std::cout << (5 > 6 ? 6 : 5) << '\n'; 
    std::cout << (3 > 2 ? 2 : 3) << '\n'; 
    return 0; 
  }
* 결론적으로 조금더 빠르게 실행된다. 함수를 거치는 과정이 없기 때문이다.
* 요즘 컴파일러들은 대부분 inline을 자동적으로 처리해준다. java에서는 JVM이 inline을 처리한다.

