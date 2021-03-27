# Generic_wildcard - [출처는 joongwon님의 medium](https://medium.com/@joongwon/java-java의-generics-604b562530b3)
---
### 용어 설명
* 무공변(invariant) <T>
  * 나
* 공변 (covariant) <? extends T>
  * 나 + 자식 [구체적 방향 허용]
* 반공변 (contravariant) <? super T>
  * 나 + 부모 [추상적 방향 허용]
---
### Wildcard and subtyping
##### 동작할것같은데 동작을 안함
* Integer는 Number의 서브타입이기 때문에 동작할것같지만 동작하지 않는다.
* ```java
  List<Integer> list = new ArrayList<>();
  addAll(list); // compile error

  public void addAll(List<Number> list) { /* ... */ }
##### 이를 해결하기 위해서는 다음과 같이 와일드카드 선언
* List<Integer>, List<Float>, List<Double> 를 허용해준ㄷ.
* ```java
  List<Integer> list1 = new ArrayList<>();
  List<Float> list2 = new ArrayList<>();
  addAll(list1);  // ok
  addAll(list2);  // ok

  public void addAll(List<? extends Number> list) { /* ... */ }
---
### PECS : Producer(생산자)-extends, Consumer(소비자)-super (중요!!!)
##### extends => 읽기전용
##### super => 쓰기 전용
* 올바른 케이스
  ```java
  // ? extends => 읽기 전용
  public void doSomething(List<? extends MyClass> list) {
      for (MyClass e : list) { // 읽기 -> 컴파일 성공
          System.out.println(e);
      }
  }

  // ? super => 쓰기 전용
  public void doSomething(List<? super MyClass> list) {
      list.add(new MyClass()); // 쓰기 -> 컴파일 성공
  }
* 잘못된 케이스
  ```java
  // ? extends => 읽기 전용
  public void doSomething(List<? extends MyClass> list) {
      list.add(new MyClass()); // 쓰기 -> 컴파일 에러
  }

  // ? super => 쓰기 전용
  public void doSomething(List<? super MyClass> list) {
      for (MyClass e : list) { // 읽기 -> 컴파일 에러
          System.out.println(e);
      }
  }
