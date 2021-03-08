# Kotlin 에서 자주 쓰이는 Annotation 정리
---
### @JvmName
##### 역할
* 바이트코드로 변환할 때 JVM 시그니쳐를 변경할 때 사용
* 즉 자바로 변환할때 이름을 바꿀수 있다.
##### 에러
* ```kotlin
  // compile error
  fun foo(a : List<String>) {
      println("foo(a : List<String>)")
  }

  fun foo(a : List<Int>) {
      println("foo(a : List<Int>)")
  }
##### 동작
* ```kotlin
  @JvmName("fooListString")
  fun foo(a : List<String>) {
      println("foo(a : List<String>)")
  }
  
  @JvmName("fooListInt")
  fun foo(a : List<Int>) {
      println("foo(a : List<Int>)")
  }
---
### @JvmStatic
##### 역할	
* static 변수의 get/set 함수를 자동으로 만든다. 
* @JvmStatic는 Companion에 등록된 변수를 자바의 static처럼 선언하기 위한 annotation
##### 기존 companion object 사용
* 본래 companion object 는 자바에서의 static 과는 다르다. 만약에 아래 코틀린 코드를 자바코드로 변경할경우 다음과 같이 변경된다.
* ```kotlin
  class Bar {
 	    companion object {	
   	      var barSize : Int = 0
   	  }
  }
* ```java
  public final class Bar {
     private static int barSize;
     public static final class Companion {
        public final int getBarSize() {
           return Bar.barSize;
        }
        public final void setBarSize(int var1) {
           Bar.barSize = var1;
        }
     }
  }
##### JvmStatic 사용
* barSize가 static으로 직접 변경이 되지는 않았고 Companion 클래스가 static으로 선언되어서 getBarSize로 barsize에 접근하도록 변경되었다.
* 이를 annotation과 함께 선언해서 변경할경우 자바코드로 변경시 다음과 같이 표현이 된다. 진짜 static 처럼말이다.
* ```kotlin
  class Bar {
      companion object {
          @JvmStatic var barSize : Int = 0
      }
  }
* ```java
  public final class Bar {
     private static int barSize;
     public static final int getBarSize() {
        return barSize;
     }

     public static final void setBarSize(int var0) {
        barSize = var0;
     }

     public static final class Companion {
        public final int getBarSize() {
           return Bar.barSize;
        }
        public final void setBarSize(int var1) {
           Bar.barSize = var1;
        }
     }
  }

	
	
	
	
	
	
  
  
