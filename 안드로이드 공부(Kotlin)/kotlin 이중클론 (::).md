코틀린에서의 이중클론(::)
===
> 자바에서의 이중클론(::)
* [자바 이중클론 요약 링크](https://github.com/sdk0213/Knowledge-Storage/blob/master/java/%EC%9D%B4%EC%A4%91%ED%81%B4%EB%A1%A0(::)%20.md)
> 코틀린에서의 이중클론(::)
* 더블클론(::)은 코틀린에서는 리플렉션을 위한것이다. 자바처럼 메서드 참조를 위한것은 아니다.
* 코틀린은 Object::class를 쓸수있고 이것은 KClass를 준다.
  * KClass
    * KClass는 자바에서 우리가 알고있는 Class랑 동일한것은 아니다.
    * 그러니까 자바에서는 .class 일경우 class를 리턴하지만 코틀린에서는 KClass를 리턴한다.
    * 그래서 자바랑 다르기 때문에 :: 을 사용해서 클래스를 호출한다고 생각하면된다.
    * 이중클론이라는 의미에 얽매일필요가없는것이 언어마다 동일한 syntex를 사용하는것일뿐 의미는 언어마다 제각각이다.
    * 만약 자바 클래스의 참조를 얻으려면 KClass 인스턴스에 .java 프러퍼티를 사용해야 한다.
* 변수앞에 :: 을 명시하면 **변수를 객체로 액세스 하여 객체애 대한 속성을 참조** 할수 있다.
* SomeClass:Method를 사용해서 클래스 내의 메서드를 참조 가능
* **람다식을 활용하면 메소드를 전달받고, 그 메소드를 호출할 수 있다. 하지만 넘기려는 메소드가 이미 함수 형태로 파라미터도 동일하다면 어떻게 할 수 있을까? 또 함수를 호출하는 게 맞을까?? 다행이도 코틀린과 자바 8에서는 이중 콜론 ::을 제공한다.**
* .kt
  ```kotlin
  fun add(num1:Int, num2:Int): Int {
    return num1 + num2
  }

  fun sub(num1:Int, num2:Int): Int {
      return num1 - num2
  }

  fun calc(func:(Int,Int)->Int, num1: Int, num2: Int) {
      var ret = func(num1, num2)
      println("Result $ret")
  }

  fun main(){
      calc(::add, 5, 3); // 코드를 작성하는 시점에는 런타임 시점의 컴파일된 바이트코드중에서 add라는 함수의 위치를 알 수 없기 때문에 런타임시의 바이트 코드를 이용해 add함수의 값을 찾기 위해 사용한다.
      calc(::sub, 5, 3);
  }
* fun일경우 ::Function형식의 함수도 참조 가능
  * .kt
    ```kotlin
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))
    //출력 [1,3]
  * .kt
    ```kotlin
    inline fun IntArray.filter(
      predicate:(Int) -> Boolean
    )
  * 동일한 형식이기 때문에 함수를 참조하여 동일한 형식인 isOdd 사용가능하다.
> 클래스::멤버
* ```kotlin
  Person:age // {person: Person -> person.age} 동일
> ::최상위함수
* ```kotlin
  fun showName() = println("코틀린")
  >>> run(::showName)
> ::함수
* ```kotlin
  val action = {person: Person, message: String -> sendMail(person, message)}
  val action = ::sendMail
* FusedLocation을 사용하여 Location 을 성공하였을때 처리
  ```kotlin
  // 1차 변환
  fusedLocationClient.lastLocation.addOnSuccessListener(object: OnSuccessListener<Location>{
            override fun onSuccess(location: Location?) {
                setLocation(location!!)
            }

        })
        
  // 2차 변환
  fusedLocationClient.lastLocation.addOnSuccessListener { location -> setLocation(location!!) }
  
  // 3차 변환
  fusedLocationClient.lastLocation.addOnSuccessListener(::setLocation)
  
> ::클래스
* ```kotlin
  data class Person(val name: String, val age: Int)
  
  >>> val createPerson = ::Person
  >>> val p = createPerson("코틀린", 30)
  >>> println(p)
  Person(name=코틀린, age=30)
> 클래스::확장 함수
* ```kotlin
  fun Person.isadult() = age >= 20
  val predicate = Person::isAdult
 
