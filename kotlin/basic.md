Kotlin
===
* 출처 - https://academy.realm.io/kr/posts/kotlin-1/ 
* Kotlin은 클래스 없이 실행이 가능하다.
  * 메서드들은 파일명을 기준으로 자동으로 클래스가 생성되고 스태틱 메서드가 만들어진다.
* 변수
  * 코틀린은 변수의 변경 가능성을 중요하게 생각하고 만들어진 언어다.
  * 코틀린은 변수의 Null 가능성을 중요하게 생각하고 만들어진 언어다.
  
주의사항
---
* 코틀린을 자바처럼 코딩하면 코틀린을 할 이유가 없다. 코틀린은 코틀린처럼 코딩해야한다.

변경점
---
* **세미콜론(;) 사용 금지 - 최고!!!!**
* import.* <- 불가능
  * 사용안하는 import문 금지!
* 삼항연산자가 없다.
   * ```java
     //java 삼항연산자
     num2 = num1 ? 100 : 200;\
   * 왜냐하면 kotlin은 if문이 삼항연산자를 수행가능할만큼 잘 되어있다.
* import한 클래스의 이름을 as 키워드를 이용해서 별명으로 쓸 수 있다.
   * ```kotlin
     import com.test.dksung.Info as Computer
     fun main(){ 
       val bestSeller = Computer("Intel", "i5", "2.80ghz")
     }
* 타입을 추론할 수 있는 경우 타입을 생략가능하다.
   * ```kotlin
     var name = "hello"
     // 하지만 단순히 아래와같은 코딩은 불가능하다.
     var name
     // 타입이 없다면 선언을 해주어야한다.
* 변수 선언할때 값이 할당되어있지 않으면 안된다. (null 방지)
* 자동으로 현변환 안해줌
  * ```java
    int a = 3; 
    double b = a; //3.0으로 자동변환
  * ```kotlin
    val intValue:Int = 3 
    val doubleValue:Double = intValue.toDouble()
* elvis operator
  * ?: <-- kotlin , or OR || <-- another language
* safe Call기능
  * 물음표를 이용해서 null이 아니라면 다음 변수를 참조할 수 있도록 체이닝하는 기능
* **★★Scope Functions★★** - [그림출처 - @limgyumin 블로그](https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29)
 
class
---
* [코드 출처 - 박상권님의 블로그](https://gun0912.tistory.com/81)
* 코드가 상당히 줄어들었다. 예를들어서 자바에서 getter와 setter를 설정해줘야 하는데 다음과 같이 길어진다.
  * ```java
    public class Dealer { 
       @Nullable 
       private String name; 
       @Nullable 
       private String address; 
       public Dealer(@Nullable String name, @Nullable String address) {
          ...
       } 
       @Nullable 
       public final String getName() {
          ...
       } 
       public final void setName(@Nullable String var1) {
          ...
       } 
       @Nullable 
       public final String getAddress() {
          ...
       } 
       public final void setAddress(@Nullable String var1) {
          ...
       } 
    }
  * ```kotlin
     // 코드가 혁명적으로 줄어들었다.
     data class Dealer(var name: String?, var address: String?) // data클래스인경우 toString(), hashCode(), equals(), copy()까지 구현해줌
* 오버로딩 매우쉬움
  * 원래는 경우의수마다 만들어줘야 하지만 코틀린에서는 값이 비어있을경우를 명시해주면 생성자가 전부 만들어진다.
  * ```kotlin
    class TedCustomView @JvmOverloads constructor(
      context: Context,
      attrs: AttributeSet? = null,
      defStyleAttr: Int = 0,
      defStyleRes: Int = 0 
    ) :
      FrameLayout(context, attrs, defStyleAttr, defStyleRes) { 
         ... 
      }
fun
---
* 함수를 뜻한다. (물론 자바에는 함수라는 개념이없고 전부다 
* 예제 코드
* ![](/img/kotlin1.png)
* 함수 생략가능
  * 함수 짧게하기
    ```kotlin
    fun sum(a: Int, b: Int): Int = a + b
  * 반환값 추론으로 생략가능
    ```kotlin
    fun sum(a: Int, b: Int) = a + b
  * 함수를 바로 생성및 활용 (사실 아래 코드와 같은경우 의미가 없을수도 있지만 예시용으로)
    ```kotlin
    fun main(args: Array<String>) {
      val a = { a: Int, b: Int -> a + b }(3, 5)  // 변수 a = { 매개변수,매개변수 -> return }(값, 값)
      println("Hello Realm! $a")
    }
  * 조건문을 다음과같이 짧게 쓰기
    ```kotlin
    fun max(a: Int, b: Int) = if (a > b) a else b

var,val
---
* var
  * 변수
  * **타입알아서 추론되어서 적을필요없음 -> 매우 편함**
* val
  * 상수
* ```java
  fun sum(a: Int, b: Int): Int {
    return a + b
  }

  fun main(args: Array<String>) {
     val a = sum(5, 3) // 타입적고싶다면 val a: int = sum(5,3)
     println("Hello Realm! ${a}") // 변수랑 상수는 다음과 같이 중괄호 생략가능
     // println("Hello Realm! $a")
  }

as
---
* Kotlin에서는 캐스팅을 as로 한다.
* 실패시 ClassCastException 처리된다.
  * 이를 그냥 넘기고 싶을경우 as?를 사용한다.

null
---
* kotlin에서는 null이 가능한 변수와 불가능한 변수로 나누어서 타입을 설정할수있다.
  * null이 가능한 타입은 뒤에 **'?'** 을 붙힌다.
    * String?
    * Int?
    * Long
  * ```java
    //JAVA                   //Kotlin
    @NotNull  String a  -->  a: String
    @Nullable String b  -->  b: String?
* 그러므로 다음과 같이 명시적으로 null을 인자로 넣을수 
  * ```kotlin
    var word: string = "apple"
    word = null
   
    >> error: null can not be a value of a non-null type kotlin.String
    >> word = null
* null체크
  * [코드 출처 - 박상권님의 블로그](https://gun0912.tistory.com/81)
    ```Kotlin
    //JAVA 에서 다음과 같이 null체크를 해주는것을
    private String getSelectedDealerName(@Nullable Car car) { 
        if (car == null) { 
            return null;
        } 
        Auction auction = car.getAuction(); 
        if (auction == null) { 
            return null; 
        } 
        Bid selectedBid = auction.getSelectedBid(); 
        if (selectedBid == null) { 
            return null; 
        } 
        Dealer dealer = selectedBid.getDealer(); 
        if (dealer == null) { 
            return null;
        } 
        return dealer.getName(); 
    }
    
    //Kotlin에서는 이렇게 간단하게 작성이 가능하다.
    private fun getSelectedDealerName(car: Car?): String? {
        return car?.auction?.selectedBid?.dealer?.name 
    }


  * ```kotlin
    // kotlin
    val l = if (hello2 != null) (hello2 as String).length else -1  // as
    val size = if (temp != null) temp.length else -1
  * ```kotlin
    // temp를 null로 초기화
    var temp: String? = null
    // ?는 null을 인가해줌 그러므로 length를 구할수있음
    val size = temp?.length
    // ?가 없다면 null을 인가불가. 그러므로 length는 NPE 발생
    val size = temp?.length
    
   
    
    
### ?
 * 
 
when()
---
* ```kotlin
  // break 없음, 가독성이 더 좋다.
  when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> {
        print("x is neither 1 nor 2")
    }
  }
* ```kotlin
  // perseInt가 불가능하다면 else로
  when (x) {
      parseInt(s) -> print("s encodes x")
      else -> print("s does not encode x")
  } 
  
  
# 출처 - https://academy.realm.io/kr/posts/kotlin-2/ 계속..................... 0721
# 출처 - https://jepark-diary.tistory.com/69 계속..... 0722

# 더나아가기 - https://ponyozzang.tistory.com/229?category=792393

