stream api - [jeong-pro님의 tistory 블로그](https://jeong-pro.tistory.com/165)
===
* 컬렉션, 배열등의 저장 요소를 하나씩 참조하며 함수형 인터페이스(람다식)를 적용하며 반복적으로 처리할 수 있도록 해주는 기능
* **스트림을 이용하면 for, if 문 없이 한 줄의 코딩만으로 count 값을 구할수있다**
* Stream의 특징
  * 재사용 불가능
    * 다음 코드는 에러를 뱉는다.
      ```java
      Stream<String> a = names.stream().filter(x -> x.contains("o"));
      count = a.count();
      
      List<String> lists = a.collect(Collectors.toList());
  * 병렬 스트림은 여러 쓰레드가 작업한다.
      * parallelStream()으로 병렬 스트림을 만들수 있다.
      * **하지만 스트림의 요소수가 많지 않거나 기존에 쓰레드가 과부화상태인 Application이라면 오버헤드(토끼잡으려고 탱크가져오는것과 비슷)가 더 클수도 있다.**
  * 중개 연산은 미리하지 않고 지연 연산을 한다.
      * 다음과 같은 코드에서 filter와 map함수는 미리 하지 않고 forEach와 같은 최종연산이 적용될때 중개연산 실행된다.
        ```java
        Stream<String> a = names.stream().filter(x -> x.contains("o")).map(x -> x.concat("s"));
        a.forEach(x -> System.out.println(x)):
* Stream이 구조
  * 스트림생성
  * 중개 연산
  * 최종 연산
  * Collections/Arrays.스트림생성().중개연산().최종연산()
> 스트림샘성()
  * ```java
    List<String> names = Arrays.asList("sung", "daekyoung", "java" , "android");
    names.stream(); //Collection에서 Stream 생성
  
    Double[] dArray = {3.1, 3.2, 3.3};
    Arrays.stream(dArray); // 배열로 Stream 생성
  
    Stream<Integer> str = Stream.of(1, 2); // 직접 생성
> 중개연산()
  * Filter
    ```java
    List<String> names = Arrays.asList("sung", "daekyoung", "java" , "android");
    Stream<String> a = names.stream().filter(x -> x.contains("o"));
  * Map
    ```java
    List<String> names = Arrays.asList("sung", "daekyoung", "java" , "android");
    names.parallelStream()
         .map(x -> x.concat("s");
         .forEach(x -> System.out.println(x));
       
    // "sung", "daekyoung", "java" , "android"
  * Peek
    * 연산할때 쓰임 
  * Limit
    ```java
    List<Integer> ages = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    ages.stream()
        .filter(x -> x > 3)
        .limit(3);
    // 4, 5, 6
  * Distinct, Skip mapToInt................................... 기타 등등 많음
> 최종 연산
  * count(), min(), max(), sum(), average()
  * DB 에서 연산된 결과를 어떻게 처리할지 명령하는것과 비슷한느낌을 준다.
  * reduce
    ```java
    List<Integer> ages = new ArraysList<Integer>();
    ages.add(1); // 1, 2, 3
    ages.add(2); // 1, 2, 3
    ages.add(3); // 1, 2, 3
    
    System.out.println(ages.stream().reduce((b,c) -> b + c).get()); // 1+2+3=6
  * forEach.collcect, iterator.............. 기타 등등 많음
