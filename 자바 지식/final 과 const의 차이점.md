# const vs final - [출처](https://blog.naver.com/PostView.nhn?blogId=codingspecialist&logNo=221516528734&categoryNo=131&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=&from=postList)
* 자바에서 const와 static final은 다음과 같은 관계를 가진다고 표현이가능하다.
  * const == static final
* const는 상수의 개념으로 final은 상속의 개념으로 접근하면 된다.
### const
* 상수로 만들어준다.
* 코틀린을 예를들자면 val(읽기전용), var(읽기/쓰기) 에서 val 와 const의 차이점을 확인가능하다.
* ```kotlin
  // 작동
  class Test{
      val name:String = "홍길동"
          get(){
              return field+" 천재"
          }
  }
* ```kotlin
  // Error
  class Test{
      val const NAME:String = "홍길동"
          get(){
              return field+" 천재"
          }
  }
### koltin 에서 val 와 const val 의 차이는?
* 결론부터 보자면 const 는 CompileTime 에 검증가능한 Immutable 한 값이고 val 는 런타임에 검증가능한 immtuable 한 값이다.
* 왜냐하면 [진정한 의미의 Immutable 한 variable 은 실제로는 compile time 에 검증할수 있어야한다. 예를들어서 다음과 같은 예는 val 로 선언되어있지만 과연 이것이 진정한 immutable 인지는 runtime 에 따라 달라질수 있다.](https://stackoverflow.com/questions/37595936/what-is-the-difference-between-const-and-val)
  * ```kotlin
    class Test {
        var x: Int = 2
        val y
            get() = x
    }

    fun main(args: Array<String>) {
        val test = Test()
        println("test.y = ${test.y}") // prints 2
        test.x = 4
        println("test.y = ${test.y}") // prints 4
    }
### final
* 상수처럼 사용하는것처럼 보이지만 사실상 그런 기능으로 만들어진것이 아니다.
* 상속을 불가능하게 만들어준다.
  * ```kotlin
    final class Animal {
      final val name:String = "홍길동"
    }
    
    class Dog:Animal() {     // Error - 상속 불가능
    
    }
* final은 변수의 오버라이딩을 불가능하게 만들어준다.
  * ```kotlin
    open class Animal{
      final val name:String = "홍길동"
    }
      
    class Dog:Animal() {
      override val name:String = "강아지" // Error - override 불가능
    }
* final은 함수의 오버라이딩을 불가능하게 한다.
  * ```kotlin
    open class Animal{
        final fun sound(){
        
        }
    }
    
    class Dog:Animal() {
        override fun sound() {
    
        }
    }

