# Optional - [출처](https://advenoh.tistory.com/15) - kotlin 에서는 더 이상 사용하지 않음
* Optional 클래스는 null이나 null이 아닌 값을 담을 수 있는 클래스
* ```java
  @Test
  test(){
     String str = getFromNetworkOrOther();
     if(str != null) {
        System.out.println(str.charAt(0);
     }
  }
* ```java
  @Test
  test(){
    String str = getFromNetworkOrOther();
    Optional<String> optStr = Optional.ofNullable(str);
    optStr.ifPresent(s -> System.out.println(s.charAt(0)));
  }
---
### 생성
* Optional.empty()
  * 빈 Optional 객체
  * ```java
    Optional<String> optStr = Optional.empty();
* Optional.of(value)
  * non-null 
  * like '!!' (in kotlin)
  * ```java
    String str = null;
    Optional<String> optStr1 = Optional.of(str); // NPE Occured
* Optional.ofNullable(value)
  * Null or Non-null 확실치 않을때
  * ```java
    String str = null;
    Optional<String> optStr1 = Optional.ofNullable(str); // empty Optional 반환
---
### 접근
* IfPresent
  * non-null 일 경우 실행
  * ```java
    public void test_1_otional_usage_ifPresent() {
      String str = "test";
      Optional<String> optStr1 = Optional.ofNullable(str);
      optStr1.ifPresent(s -> System.out.println(s.charAt(0))); //t 프린트

      Optional<String> optStr2 = Optional.ofNullable(null);
      optStr2.ifPresent(s -> System.out.println(s.charAt(0))); //print 안됨
    }
* orElse
  * null 일경우 다른 동작
  * ```java
    @Test
    public void test_2_otional_usage_orElse() {
        Optional<String> optStr = Optional.ofNullable(null);
        String result = optStr.orElse("test”); //null이면 test를 반환함
        System.out.println(result); //test
    }
* orElseGet
* orElseThrow
.....
[기타 더 필요한 내용은 이곳을 참고하세요](https://advenoh.tistory.com/15)
