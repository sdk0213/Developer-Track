# by
### 사용 이유 - [출처](https://velog.io/@jojo_devstory/코틀린-Kotlin-by-by-the-way-what-is-this)
* ** 사용 하는 방법에 따라 이유가 달라진다.**
* **데코레이터 패턴 또는 위임패턴 을 쉽게 활용할수있다.**
* 상위 클래스와 하위 클래스의 서로 간의 결합도가 높아져 상위 클래스의 변화가 하위 클래스에 주는 영향을 예측하기 어렵다.
* 불필요한 상위 클래스의 메서드까지 구현해야 한다.
* final클래스의 경우 상속이 불가능하다.
* 상속 구조가 복잡해질수록 그 영향에 대한 예측이 힘들어진다.
* 하위 클래스로 내려갈수록 기능이 더해져 파악하기 어렵다.
---
### 코틀린 사전 정보
* 코틀린에서는 자바와는 달리 상속으로 인한 종속성, 의존성 문제를 방지하기 위해 기본적으로 클래스는 final
* 상속을 원한다면 상속될 클래스에 open 접근자를 명시
---
### 설명 - [출처](https://medium.com/til-kotlin-ko/kotlin의-클래스-위임은-어떻게-동작하는가-c14dcbbb08ad)
##### by delegate()
* ![](https://i.stack.imgur.com/iI6hs.png)
* 사진과 같은 형태로 클래스를 생성해 호출시 행할 동작들을 위임할수 있다.
##### by lazy
* 클래스 생성과 변수를 초기화하면 좋지만 메모리 낭비를 방지하기위해 first access 시에 초기화를 이루어지게 하는 행위
* lateinit의 특징
  * lateinit은 꼭 변수를 부르기 전에 초기화 시켜야 하는데 아래와 같은 조건을 가지고 있다.
  * var(mutable)에서만 사용이 가능하다
  * var이기 때문에 언제든 초기화를 변경할 수 있다.
  * null을 통한 초기화를 할 수 없다.
  * 초기화를 하기 전에는 변수에 접근할 수 없다.
  * lateinit property subject has not been initialized
  * 변수에 대한 setter/getter properties 정의가 불가능하다.
  * lateinit은 모든 변수가 가능한 건 아니고, primitive type에서는 활용이 불가능하다(Int, Double 등)
* by lazy의 특징 
  * 호출 시점에 by lazy 정의에 의해서 초기화를 진행한다.
  * val(immutable)에서만 사용이 가능하다.
  * val이므로 값을 교체하는 건 불가능하다.
  * 초기화를 위해서는 함수명이라도 한번 적어줘야 한다.
  * lazy을 사용하는 경우 기본 Synchronized로 동작한다
* **Nullable이 필요치 않은 곳에서는 lazy 패턴을 걸어서 주로 사용**
* **lateinit의 경우도 Nullable 일 필요 없고, 언제든 값을 교체하는 게 필요한 경우 사용**
* ```kotlin
  class ExampleUnitTest {

      private val subject: String by lazy {
          println("subject initialized!!!!")
          "Subject Initialized"
      }

      @Test
      fun test() {
          println("Not Initialized")
          println("subject one : $subject")
          println("subject two : $subject")
          println("subject three : $subject")
      }
  }
  
  // result
  // Not Initialized
  // subject initialized!!!!
  // subject one : Subject Initialized
  // subject two : Subject Initialized
  // subject three : Subject Initialized
* 초기화는 접근할때 한번만 이루어지고 이후로는 저장된값을 받아오기만 한다.
##### class delegate (클래스 위임)
* 자바에서 위임 패턴(delegation pattern)을 작성할때 발생하는 보일러코드를 상당부분 작성하지 않아도 된다.
* ```kotlin
  interface Base {
      fun printX()
  }

  class BaseImpl(val x: Int) : Base {
      override fun printX() { print(x) }
  }
  val baseImpl = BaseImpl(10)
  class Derived(baseImpl: Base) : Base by baseImpl
* kotlin decomplied to .java
  ```java
  public interface Base {
     void printX();
  }
  
  public final class BaseImpl implements Base {
    private final int x;

    public void printX() {
       int var1 = this.x;
       boolean var2 = false;
       System.out.print(var1);
     }

     public final int getX() {
        return this.x;
     }

     public BaseImpl(int x) {
       this.x = x;
     }
  }
  
  public final class Derived implements Base {
     // $FF: synthetic field
     private final Base $$delegate_0;

     public Derived(@NotNull Base baseImpl) {
        Intrinsics.checkNotNullParameter(baseImpl, "baseImpl");
        super();
        this.$$delegate_0 = baseImpl;
     }

    public void printX() {
       this.$$delegate_0.printX();
    }
  }
---
### by map
* Json파싱시 사용하면 유용
* ```kotlin
  class Person(map: MutableMap<String, Any>) {
      var name: String by map
      var age: Int by map
  }

  fun main() {
      val person = Person(mutableMapOf())
      person.name = "jin"
      person.age = 31
  
      println("person name is ${person.name} and age is ${person.age}") 
      
      // result:
      // person name is jin and age is 31
  }
