stream
===
stream
---
* **컬렉션, 배열등의 저장 요소를 하나씩 참조**하며 함수형 인터페이스(람다식)를 적용하며 **반복적으로 처리**할 수 있도록 해주는 **기능** 
### Collections같은 객체 집합.스트림생성().중개연산().최종연산();
* **for,if 문 생략 및 람다식으로 인하여 가독성 뛰어남**
  * [출처 - jeong-pro.tistory](https://jeong-pro.tistory.com/165)
    ```java
    List<String> names = Arrays.asList("jeong", "pro", "jdk", "java");
    // 기존의 코딩 방식
    long count = 0;
    for (String name : names) {
        if (name.contains("o")) {
            count++;
        }
    }
    System.out.println("Count : " + count); // 2
 
    // 스트림 이용한 방식
    count = 0;
    count = names.stream().filter(x -> x.contains("o")).count();
    System.out.println("Count : " + count); // 2
  * ```java
    List<String> names = Arrays.asList("jeong", "pro", "jdk", "java");
    names.parallelStream().map((x) ->{return x.concat("s");}).forEach(x -> System.out.println(x));
    //jeongs, pros, jdks, javas

