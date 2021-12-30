stream api - [jeong-pro님의 tistory 블로그](https://jeong-pro.tistory.com/165)
===
* 컬렉션, 배열등의 저장 요소를 하나씩 참조하며 함수형 인터페이스(람다식)를 적용하며 반복적으로 처리할 수 있도록 해주는 기능
* Stream의 특징
  * vs Collection
    * **컬렉션의 주제는 데이터이고 스트림의 주제는 계산이다.**
    * 컬렉션은 데이터를 선 계산하고 스트림은 후 계산한다. 즉, 언제 계산하느냐의 차이이다.
    * 컬렉션은 모든 값을 메모리에 저장하는 자료구조 스트림은 비디오 스트리밍처럼 그때그때 필요한 부분을 불러 계산
    * 컬렉션은 외부 반복(external iteration) 스트림은 내부 반복(internal iteration)이다.
      * 외부 반복
        * 반복을 외부(사용자가 직접 요소를 반복)에서 진행해야함
      * 내부 반복
        * 반복을 내부에서 알아서 처리해줌
  * 선언적인 스타일 (함수형)
    * 불필요한 코딩(for, if 문법)을 걷어낼 수 있고 직관적이기 때문에 가독성이 좋아진다
    * **스트림을 이용하면 for, if 문 없이 한 줄의 코딩만으로 count 값을 구할수있다**
  * 원본의 데이터를 변경하지 않는다.
    * 정렬하여서 출력이 필요할경우 굳이 실제 정렬을 하지 않아도 변경가능하다.
  * 데이터의 흐름
  * 필요한 데이터만 메모리에 로드해 처리
  * 병렬 스트림은 여러 쓰레드가 작업한다.
      * parallelStream()으로 병렬 스트림을 만들수 있다.
      * **하지만 스트림의 요소수가 많지 않거나 기존에 쓰레드가 과부화상태인 Application이라면 오버헤드(토끼잡으려고 탱크가져오는것과 비슷)가 더 클수도 있다.**
  * 중간연산이라는 개념이 있다.
    * 중간연산
      * filter, map, limit, sorted, distinct, peek ...
    * 최종연산
      * forEach, count, collect, match ...
    * 중개 연산은 미리하지 않고 지연 연산(최종연산때 진행)을 한다.
        * 다음과 같은 코드에서 filter와 map함수는 미리 하지 않고 forEach와 같은 최종연산이 적용될때 중개연산 실행된다.
          ```java
          Stream<String> a = names.stream().filter(x -> x.contains("o")).map(x -> x.concat("s"));
          a.forEach(x -> System.out.println(x)):
  * 병렬처리를 하다보니 성능적인 이슈가 발생할 확률이 높아진다.
  * 디버그가 힘들다.
  * 재사용이 불가능하다.
    * 한번쓰면 close 된다. 다음 코드는 에러를 뱉는다.
      ```java
      Stream<String> a = names.stream().filter(x -> x.contains("o"));
      count = a.count();
      
      List<String> lists = a.collect(Collectors.toList());
  * [stream api 는 무조건 좋을까?](https://madplay.github.io/post/mistakes-when-using-java-streams)    
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
