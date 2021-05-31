# Generic(in, out) - [출처](https://thdev.tech/kotlin/androiddev/2017/10/03/Kotlin-Generics/)
---
### 임시 코드
* ```koltin
  interface Output<T> {
      fun isArgument(argument: T): Boolean
  }
* ```kotlin
  class ExampleUnitTest {

      val items = ArrayList<Output<String>>()

      init {
          items.add(object : Output<String> {
              override fun isArgument(argument: String) = false
          })
          items.add(object : Output<String> {
              override fun isArgument(argument: String) = true
          })
      }
  }
### out T(kotlin) == ? extends T (java)
* **output의 약자로 readonly**
* ```kotlin
  private fun printAll(items: ArrayList<out Output<String>>) {
      // 컴파일 에러 왜냐하면 readonly 이기 때문에 write 불가능
      // items.add(object : Output<String> {
      //    override fun isArgument(argument: String) = false
      // })

      items.indices
              .filter { items[it].isArgument("") }
              .forEach { println("item : " + items[it]) }
      
      // result
      // items.get(0) // Success - print
      // items.get(1) // Success - not print (false라서 filter에서 걸러지기 때문에 출력이 안된다.)
  }
### in T(kotlin) == ? super T (java)
* **input의 약자로 writeonly**
* ```kotlin
  private fun addItem(items: ArrayList<in Output<String>>) {
       items.add(object : Output<String> {
          override fun isArgument(argument: String) = false
       })

      // 컴파일 에러 왜냐하면 write only 이기 때문에 read 불가능
      // items.indices
      //        .filter { items[it].isArgument("") }
      //        .forEach { println("item : " + items[it]) }
      // items.add(null)
  }
