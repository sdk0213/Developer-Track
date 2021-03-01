# Companion Object 는 static 과 같을까? - [출처](https://www.bsidesoft.com/8187)
* 사실 코틀린 companion object는 static이 아니며 사용하는 입장에서 static으로 동작하는 것처럼 보일 뿐이다.
* 사실은 companion object는 객체이다.
* 꼭 기억해야 할 것은 클래스 내 정의된 companion object는 클래스 이름만으로도 참조 접근이 가능
* ```kotlin
  class MyClass2{
    companion object{
        val prop = "나는 Companion object의 속성이다."
        fun method() = "나는 Companion object의 메소드다."
    }
  }
  
  fun main(args: Array<String>) {
    println(MyClass2.Companion.prop)
    println(MyClass2.Companion.method())
 
    val comp1 = MyClass2.Companion  //--(1)
    println(comp1.prop)
    println(comp1.method())
 
    val comp2 = MyClass2  //--(2)
    println(comp2.prop)
    println(comp2.method())
  }
### Companion object에 이름을 지을 수 있다.
  * ```kotlin
    class MyClass3{
      companion object MyCompanion{  // -- (1)
          val prop = "나는 Companion object의 속성이다."
          fun method() = "나는 Companion object의 메소드다."
      }
    }
    
    fun main(args: Array<String>) {
      println(MyClass3.MyCompanion.prop) // -- (2)
    ...
    ..
  
    }
### 클래스내 Companion object는 딱 하나만 사용 가능
### 인터페이스 내에도 Companion object를 정의
* ```kotlin
  interface MyInterface{
    companion object{
        val prop = "나는 인터페이스 내의 Companion object의 속성이다."
        fun method() = "나는 인터페이스 내의 Companion object의 메소드다."
    }
  }
  
  fun main(args: Array<String>) {
     println(MyInterface.prop)
  ...
  ..
  
  }
### 상속 관계에서 Companion object 멤버는 같은 이름일 경우 가려짐
* ```kotlin
  open class Parent{
    companion object{
        val prop = "나는 부모"
    }
    fun method0() = prop //Companion.prop과 동일
  }
  class Child:Parent(){
      companion object{
          val prop = "나는 자식"
      }
      fun method1() = prop //Companion.prop 과 동일
  }
  fun main(args: Array<String>) {
      println(Parent().method0()) //나는 부모
      println(Child().method0()) //나는 부모
      println(Child().method1()) //나는 자식
 
      println(Parent.prop) //나는 부모
      println(Child.prop) //나는 자식
 
      println(Parent.Companion.prop) //나는 부모
      println(Child.Companion.prop) //나는 자식
  }
### 다형성 관련 문제는 출처 참고
