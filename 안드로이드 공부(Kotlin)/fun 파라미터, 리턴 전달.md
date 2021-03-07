# fun 파라미터, 리턴 전달
### Default Arguments
* ```kotlin
  fun checkCustomer(name: String, phone: String = "NA", age: Int = 30){
      ...
  }
 
  checkCustomer("Gildong")
##### 람다에서 사용안하는 파라미터 처리
* ```java
  mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
      @Override
      public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
          ...
          ..
          .
         
      }
  });
* ```kotlin
  NestedScrollView.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                    ...
                    ..
                    .
                }
  }
---
### Name Arguments
* ```kotlin
  reformat(str,
          normalizeCase = true,
          upperCaseFirstLetter = true,
          divideByCamelHumps = false,
          wordSeparator = '_')
  reformat(str, wordSeparator = '_')
* ```kotlin
  fun foo(a: Int = 1, b: Int = 2, c: Int = 3) {}
  
  //using
  foo(c=33)
---
### Unit-returning Functions
* return Unit 이나 return 은 선택적
* ```kotlin
  fun printHello(name: String?): Unit {
      if (name != null)
          println("Hello ${name}")
      else
          println("Hi there")
  }
---
### Single-Expression functions
* 괄호 생략 => 함수형 프로그래밍같은 표현
* ```kotlin
  fun double(x: Int): Int = x * 2
---
### Variable number of arguments(Varargs)
* 변수를 여러개 들어올수 있도록 표시 - 가변인자
* ```koltin
  fun <T> asList(vararg ts: T): List<T> {
      val result = ArrayList<T>()
      for(t in ts) // ts is an Array
          result.add(t)
      return result
  }
 
  val list = asList(1, 2, 3)
* Decomplied to java
* ```java
  public final List asList(@NotNull Object... ts) {
     Intrinsics.checkNotNullParameter(ts, "ts");
     ArrayList result = new ArrayList();
     Object[] var5 = ts;
     int var6 = ts.length;
     for(int var4 = 0; var4 < var6; ++var4) {
        Object t = var5[var4];
        result.add(t);
     }

     return (List)result;
  }
  
  ...
  this.list = this.asList(1, 2, 3);

