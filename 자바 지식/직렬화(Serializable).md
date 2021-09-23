[참고 사항 - 우아한 형제들 기술블로그 직렬화에 관하여](https://techblog.woowahan.com/2551/)
### 직렬화
* 하는이유
  * 메모리를 디스크에 저장하거나 네트워크 통신에 사용하기 위한 형식으로 변환
  * 그냥 있는그대로 넣어버리면 나중에 해석을 못하니 그러한 부분들을 포함하여 변환하다고 보면된다
* 역직렬화는 그 반댓말이다

### Serializable
* Serializable의 목적은 자바 Object를 byte array로 변경
* JVM 메모리 안에 상주되어있는 데이터를 Bytes 형태로 직렬화하여 타 Java 시스템에서 역직렬화하여 사용가능하도록 변경
* 자바에서 지원해주는 직렬화해주는 클래스라고 생각하면 된다
##### 사용
* ```java
  class Article implements Serializable {
   
      private static final long serialVersionUID = 1L; // <----------- 관리해야하는 SUID 이다. 명시적으로 선언하고 관리해야한다.

      private String title;
      private String pressName;
      private String reporterName;

      // ... 이하 생략
  } 

### 주의사항
* 역직렬화시 예외사항이 발생할수 있다.
* 오래 저장해야한다면 사용 지양
* 자주 변경된다면 사용 지양
* **serialVersionUID 명시해줘야함**
  * 똑같은 객체라도 버전이 다르면 다른객체로인식되어서 Exception 떨어진다. 주의필요!! 
  * 자동으로 부여되긴 하나 자동으로 부여되기에 변경되었을때 버전정보도 바뀌어서 역직렬화시 InvalidClassException 발생할수 있다.
  * [더 자세한 내용](https://madplay.github.io/post/java-serialization-advanced)
* 타입이 엄격하다.(변경시 유의사항)
  * int -> double 등 원시타입 변경시 ClassCastException 발생
  * [변경시 호환이 가능한지 여부 확인하기](https://docs.oracle.com/javase/8/docs/platform/serialization/spec/version.html?fbclid=IwAR21VL-mmCuIT50Ntv_g-1Noc7HkchUrBGkCQYcLBazQgWrJDoPnq1ymfAM)

### 단점
* 데이터 크기가 JSON보다 큼
* 보안상 문제
  * [직렬화는 취약점의 원인이 될 수 있다.](https://madplay.github.io/post/prefer-alternatives-to-java-serialization)
    * 바이트 스트림을 역직렬화하는 과정에서 readObject 메서드는 그 타입들 안의 모든 코드를 수행할 수 있다. 즉, 그 타입들의 코드 전체가 악의적인 공격 범위에 들어갈 수 있다는 뜻이 된다.
  * [역직렬화시 필터링을 사용하여 취약점을 제거해야한다.](https://madplay.github.io/post/why-java-serialization-is-bad)
