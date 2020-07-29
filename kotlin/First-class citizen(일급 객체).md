First-class citizen
===
일급 객체
---
* 일급 객체가 되기위한 조건
  * 변수나 데이타에 할당 할 수 있어야 한다.
    * ```kotlin
      object Main {
        @JvmStatic
        fun main(args: Array<String>) {
          val a = test
        }

        val test: () -> Unit = { println("kotlin") }
      }
  * 객체의 인자로 넘길 수 있어야 한다.
    * ```kotlin
      object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            function(test)
        }

        fun function(f: () -> Unit) {
          f.invoke()
        }

        val test: () -> Unit = { println("kotlin") }
      }
      
  * 객체의 리턴값으로 리턴 할수 있어야 한다.
    * ```kotlin
      object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            function()
        }

        fun function(): () -> Unit {
            return { println("kotlin") }
        }

      }
* **kotlin의 함수는 위의 조건들을 다 만족하니까 1급 객체라고 할 수 있다.**
* **하지만 Java의 함수는 위 조건들을 만족하지 못하기 때문에 1급 객체라고 할수 없습니다.**
