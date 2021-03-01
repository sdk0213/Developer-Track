Lombok 라이브러리
===
* Java에서 기계적으로 작성하는 VO, DO, ENTITY 관련작업을 쉽게 해주는 것
* Getter, Setter,ToString, hashCode 메서드관련 클래스 작업을 **아주 아주 간단하게 해준다**
* **Kotlin을 사용한다면 필요가 없는 라이브러리이다.**
* ```java
  import lombok....

  @Getter
  @Setter
  @ToString
  @EqualsAndHashCode
  @Builder
  public class lombokEx {
      private @NonNull String name;
      private @NonNull String age;
      private @NonNull String sex;
  }
  
  
  ....
  ...
  .. main Class
  
  .... main(){
    lombokEX lmb = new LombokModelBuilder()
        .name("sungDaekyoung")
        .age("28")
        .sex("men")
        .build();
    
    System.out.println("lombok : " + lmb);
    System.out.println("lombok : " + lmb.toString());
    System.out.println("lombok : " + lmb.hashCode());
  }
