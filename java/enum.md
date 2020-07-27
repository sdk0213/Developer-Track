enum
===
네 이놈(enum) 누구냐.. - [출처 - opentutorials](https://opentutorials.org/course/2517/14151)
---
* 서로 연관된 상수들의 집합이다.
* ```java
  enum Fruit{
    APPLE, PEACH, BANANA;
  }
  
  if(liked==Fruit.APPLE) System.out.println("사과를 좋아한다");
* **원래는 클래스이다. 편하게 쓰라고 문법만 변경되엇을뿐이다.**
  * enum
    ```java
    enum Fruit{
      APPLE, PEACH, BANANA;
    }
  * 본래 형태
    ```java
    class Fruit{
      public static final Fruit APPLE  = new Fruit();
      public static final Fruit PEACH  = new Fruit();
      public static final Fruit BANANA = new Fruit();
      private Fruit(){} // 생성자로 생성불가능하다.
    }
* 좋은점
  * 서로 다른 상수 그룹에 대한 비교를 **컴파일 시점에서 차단**
    * ```java
      if(Fruit.APPLE == Company.APPLE){ // Fruit, Company 둘다 Enum클래스이다.
        System.out.println("과일 애플과 회사 애플이 같다.");
      }
      
      //결과 : Error발생
  * 편리함
    * ```java
      enum Season {
        봄, 여름, 가을, 겨울
      }
      
      ...
      
      //values()
      for(Season sea : Season.values()){
        System.out.println(sea); // 출력 : 봄, 여름, 가을, 겨울
      }
      
      //ordinal()
      Season seson = Season.여름;
      System.out.println(seson.ordinal()); // 출력 : 1
      
      //valueOf()
      Season seson = Season.valueOf("가을");
      System.out.println(seson); // 출력 : 가을
      
      //name()
      Season seson = Season.가을;
      String name = seson.name();
      System.out.println(name); // 출력 : 가을
      
      //compareTo() - 순번이 빠르면 음수, 느리면 양수
      Season seson1 = Season.가을;
      Season seson2 = Season.겨울;
    	
      int result1 = seson1.compareTo(seson2);
      int result2 = seson2.compareTo(seson1);
    	
      System.out.println("result1 = " +result1); // 가을이 겨울보다 빠르니까 -1 (enum상에서의 순서를 말하는것임)
      System.out.println("result2 = " +result2); // 겨울이 가을보다 느리니까 1 (enum상에서의 순서를 말하는것임) 
      // 출력 : -1, 1
      
      ...

* **데이터들 간의 연간관계 표현 - [배민블로그](https://github.com/jojoldu/blog-code/tree/master/enum-settler)**
    * "Y","1" true를 한곳에 묶어버리기
      * ```java
        Y("1",true), N("0",false);
        
* **상태와 행위 한번에 처리 - [배민블로그](https://github.com/jojoldu/blog-code/tree/master/enum-settler)**
  * 아래 코드 보기전에 function<T,R>에 대하여
    * Function<T, R>
      * 하나의 인자와 리턴타입을 가지며 그걸 제네릭으로 지정해줄수있다. 그래서 타입파라미터(Type Parameter)가 2개다.
      * ```java
        Function<String, Integer> f = str -> Integer.parseInt(str);
        Integer result = f.apply("1");

  * ```java
    public enum CalculatorType {

      CALC_A(value -> value),
      CALC_B(value -> value * 10),
      CALC_C(value -> value * 3),
      CALC_ETC(value -> 0L);

      private Function<Long, Long> expression; //.

      CalculatorType(Function<Long, Long> expression) {
          this.expression = expression;
      }

      public long calculate(long value){
          return expression.apply(value);
      }
    }    
* **데이터 그룹 관리 - [배민블로그](https://github.com/jojoldu/blog-code/tree/master/enum-settler)**
  * PayGroupAdvanced
    ```java
    public enum PayGroupAdvanced {

      CASH("현금", Arrays.asList(PayType.ACCOUNT_TRANSFER, PayType.REMITTANCE, PayType.ON_SITE_PAYMENT, PayType.TOSS)),
      CARD("카드", Arrays.asList(PayType.PAYCO, PayType.CARD, PayType.KAKAO_PAY, PayType.BAEMIN_PAY)),
      ETC("기타", Arrays.asList(PayType.POINT, PayType.COUPON)),
      EMPTY("없음", Collections.EMPTY_LIST);

      private String title;
      private List<PayType> payList;

      PayGroupAdvanced(String title, List<PayType> payList) {
          this.title = title;
          this.payList = payList;
      }

      public static PayGroupAdvanced findByPayType(PayType payType){
          return Arrays.stream(PayGroupAdvanced.values())
                  .filter(payGroup -> payGroup.hasPayCode(payType))
                  .findAny()
                  .orElse(EMPTY);
      }

      public boolean hasPayCode(PayType payType){
          return payList.stream()
                  .anyMatch(pay -> pay == payType);
      }

      public String getTitle() {
          return title;
      }

    }
  * PayType
    ```java
    public enum PayType {

      ACCOUNT_TRANSFER("계좌이체"),
      REMITTANCE("무통장입금"),
      ON_SITE_PAYMENT("현장결제"),
      TOSS("토스"),
      PAYCO("페이코"),
      CARD("신용카드"),
      KAKAO_PAY("카카오페이"),
      BAEMIN_PAY("배민페이"),
      POINT("포인트"),
      COUPON("쿠폰");

      private String title;

      PayType(String title) {
          this.title = title;
      }

      public String getTitle() {
          return title;
      }
    }
  * ```java
    public void PayGroup에게 직접 결제종류 물어보기_PayType () throws Exception {
      PayType paytype = selectPayType();
      PayGroupAdvanced payGroupAdvanced = PayGroupAdvanced.findByPayType(payType);
      
      assertThat(payGroupAdvanced.name(), is("BEMIN_PAY"));
      assertThat(payGroupAdvanced.getTitle(), is("배민페이"));
* **관리 주체를 DB에서 객체로 전환 - [배민블로그](https://github.com/jojoldu/blog-code/tree/master/enum-settler)**
  * 카테고리성 데이터를 Enum으로 전환하고, 팩토리와 인터페이스 타입을 선언하여 일관된 방식으로 관리되고 사용할 수 있도록 진행
  * ```java
    public enum FeeType implements EnumMapperType{
      PERCENT("정율"),
      MONEY("정액");

      private String title;

      FeeType(String title) {
          this.title = title;
      }

      @Override
      public String getCode() {
          return name();
      }

      @Override
      public String getTitle() {
          return title;
      }
    }
* enum 단점
  * 변경이 어렵다.
* 무조건적인 사용보다 적절한 상황에서 잘 사용해야 한다.
  * 변경이 잦은 데이터일 경우 데이터베이스의 테이블로 관리하는 것이 좀 더 좋은 방법
  * 변경이 거의 없는 데이터 그룹의 경우엔 enum이 더 좋은 방법이 될 수 있습니다.
