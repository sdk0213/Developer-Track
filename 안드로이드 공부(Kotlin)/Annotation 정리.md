# Kotlin 에서 자주 쓰이는 Annotation 정리 - [출처](https://codechacha.com/ko/kotlin-annotations/)
# [안드로이드에서 자주쓰이는 어노테션은 여기로 이동](https://medium.com/hongbeomi-dev/android-annotation-정리-8d0b5b6845c3)
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
### @JvmField
* get/set 생성 없애기
##### 기존
* ```kotlin
	class Bar {
	    var barSize = 0
	}
* ```java
	public final class Bar {
	   private int barSize;
	   public final int getBarSize() {
	      return this.barSize;
	   }
	   public final void setBarSize(int var1) {
	      this.barSize = var1;
	   }
	}
##### @JvmField
* ```kotlin
	class Bar {
	    @JvmField
	    var barSize = 0
	}
* ```java
	public final class Bar {
	   @JvmField
	   public int barSize;
	}
### @Throws
* Exception throw
* ```kotlin
	@Throws(NumberFormatException::class)
	fun convertStringToInt(str: String) {
	  ....
	}
* ```java
	public static final void convertStringToInt(@NotNull String str) throws NumberFormatException {
  	....
	}
### @JvmOverloads
* 코틀린 함수의 오버로딩 메소드들을 생성해주는 annotation
* 자바랑 섞어서 사용하지 않으며 필요없는 어노테션
##### [자세한 설명](https://codechacha.com/ko/kotlin-annotations/)
---
###  @OptIn / @RequiresOptIn
* 사전적 설명
  * @OptIn
    * 어노테이션이 연결된 파일, 선언, 식에서 opt-in API를 사용할 수 있습니다.
	* @RequiresOptIn
		* 해당 어노테이션이 연결된 요소가 OptIn API의 마커임을 나타냅니다. 불안정하거나 비표준 API의 일부로 간주되며 해당 요소를 호출하려면 OptIn을 사용하거나 @OptIn 어노테이션을 달아야합니다.
* 쉽게말해서 해당 API 가 실험적으로 사용될수있다는것을 사용자에게 알려주기 위해서 어노테이션을 붙히는것이다. 즉 OptIn 을 사용함으로써 라이브러리 개발자가 사용자한테 명시적 동의르 받는셈이다. 그렇게 함으로써 해당 API 는 현재 실험적이며 향후에 사라질수있다는것을 사용자가 알 필요가있을때 붙혀준다. 보통 실험적인 API 를 추가하고 이를 공개할때 해당 어노테션을 사용할것으로 추측한다.
	
	
	
	
  
  
