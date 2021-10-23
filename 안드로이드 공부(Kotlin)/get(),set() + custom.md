# Getter와 Setter를 커스텀
### 자동생성
* 코틀린에서 게터와 세터는 별도의 작업없이 자동으로 생성되어 "." 를 통해 사용
### 커스텀 getter, setter 생성
* get() 을 사용할경우 계속 새로운 객체로 생성된다.
* ```kotlin
  class Person {
    var name: String
    var age: Int // 해당 변수 밑에다가 생성하면 커스텀된것으로 작동함
      get() {
        println("execute getter")
        return field
      }
      set(value: Int) {
        field = value
      }
   }
### 주의점
* ```kotlin
  var age: Int
    get() {
      return this.age
    }
* 위 코드는 무한 루틴이다. 왜냐하면 this.age 자체가 get이기 때문이다.

### field 사용 해야하는 이유
* ```kotlin
  class User{
    var name: String
    get() = field
    set(value) {field = value}
  }

  class User2{
      var name: String
      get() = name
      set(value) {name = value}
  }
###### Kotlin -> JavaCode
* ```java
  public final class User {
     @NotNull
     private String name;

     @NotNull
     public final String getName() {
        return this.name;
     }

     public final void setName(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = value;
     }
  }

  public final class User2 {
     @NotNull
     public final String getName() {
        return this.getName();
     }

     public final void setName(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.setName(value);
     }
  }
  
### val로 선언후 가져올때 값 변경하는 방법
* 코루틴 초기화
  ```kotlin
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job
* 단순히 = 으로 초기화를 할경우에는 job이 변했을경우의 값을 가져오지 못한다.
* 변화되는 값에 대해서 val 로 선언했을때 위와 같이 get()으로 초기화를 해준다.  ->  왜냐하며 get()은 항상 새로운객체를 생성해서 가져오기 때문에 가져올당시의 job을 새롭게 가져온다.
  
  
  
