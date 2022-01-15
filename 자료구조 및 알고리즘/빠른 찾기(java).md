# 빠른 찾기
* 문제 똑바로 읽기
  * 오래 걸리더라도 천천히 읽기
    * 잘못 이해해서 문제를 생각보다 어렵게 풀수도 있음
* 예외 케이스 작성 ( 스페이스바, 양 끝단, 특수문자 등.. )
* 문제의 범위 정확히 파악하기
* 프로그래밍은 위에서 아래로 순서대로 실행되니 이에 따라서 논리 전개식이 변경될수있음을 인지해야한다. 가령 for 문을 돌리는 와중에 flag 값을 설정시 A 조건 B 조건을 체크하여 변경할경우 A -> B, B -> A 의 순서에 따라서 플래그값이 다르게 변경되어 다른 결과를 나오게 할수있다. 초기설정값이 제일 중요하지만 이러한 사실또한 중요하다
* String 의 contains 로 C 와 C# 을 찾을때 C 만찾아도 C# 까지 C 라고 찾았다고 판단하는 문제 파악 필요
------------
* ```java
  // 필수적으로 하기
  import java.util.*;
------------

# INDEX 
### 클릭 & 이동
* [아스키](#ascii)
* [비교](#comparison)
* [for](#for)
* [Array 출력하기](#print-array)
* [배열](#array)
  * 고정/가변 배열
  * 얕은/깊은 복사
  * 배열 거꾸로 (String / int)
  * Array <-> List 변환
  * 배열사이즈 구하기
* [stack](#stack)
* [queue](#queue)
* [comparator](#comparator)
  * 문자 정렬
  * 숫자 정렬
* [compareTo](#compareto)
  * a.compareTo(b)
  * sort
* [collection - stream](#collection-stream)
  * 합계 - sum
  * 평균 - average
  * 최대값 - max, min
  * forEach
  * findFirst() - Optional<T>
  * filter - return Stream<T>
  * removeIf
* [string](#string)
  * 문자열
  * 자르기
  * 공백이 무수히 있어도 완전히 제거
  * 정수 짜르기(스트링 변환없이)
  * 문자열 대체
  * 문자열 확인
* [hash](#hash)
* [수학](#math)
  * 최솟값, 최댓값, 절대값, 제곱
  * 조합 + 중복조합
  * 순열 + 중복순열
  * 소수
  * 약수
  * 최대공약수
  * 최소공배수
  * 소인수분해
  * 팩토리얼
  * 피보나치
  * 진법 변환
* [기타](#etc)
  * 윤년 계산 (+달의 일 계산법)
* [자료구조 전체사진, 컬렉션포함](#data-structure)

  
------------
# ASCII
[맨 위로](#index)
<details>
  <summary>아스키</summary>
  
### 아스키
### char <-> ascii 전환
* ```java
  int ascii = 65;
  char character = 'A';
  System.out.println((char)ascii);
  System.out.println((int)character);
* 0	<-> 48
* 9	<-> 57
* A	<-> 65
* Z	<-> 90
* a	<-> 97
* z	<-> 122
<details>
<summary>아스키 코드 표(펼치기/접기)</summary>
  
<div markdown="1">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkDTO6%2Fbtq1ca8DHu6%2FbRY6sqRipuwtTRsEokfw1K%2Fimg.png">
</div>
</details>
</details>

  
------------
# comparison
[맨 위로](#index)
<details>
  <summary>comparison (객체 비교)</summary>

### 비교
* ```java
  String st1 = new String("aaa");
  String st2 = new String("aaa");
 
  System.out.println(st1==st2);   // false
  System.out.println(st1.equals(st2)); // true
* .eqauls() (동등성 (identity)) 비교
  * 값을 비교
  * 두 오브젝트가 같은 정보를 같고 있을 경우를 의미
* == (동일성 (equality)) 비교
  * 주소르 비교 (즉,객체를 비교)
  * 두 오브젝트가 같은 정보를 같고 있을 경우를 의미  
</details>
  
  
  
------------
# for
[맨 위로](#index)
<details>
  <summary>for</summary>
  
### for
* ```java
  // int
  int[] array = {5,2,89,423,111};
  for(int number : array){
      System.out.println(number);
  }
  
  // String
  String[] temp = { "aa", "bb", "cc" };
  for (String el : temp) {
      System.out.println(el);
  }
  
  // Object
  Object[] objArray = ...someObject
  for (Object obj : objArray) {
      System.out.println(obj.toString());
  }
  
</details>
  
  
  
------------
# print array
[맨 위로](#index)
<details>
  <summary>Array 출력하기</summary>
  
### Array 출력하기
* ```java  
    int[] array = new int[]{3,1,5,37}; 
    System.out.println(Arrays.toString(array)); // 리스트의 경우는 그냥 toSring() 하면됨
    // [3, 1, 5, 37]
</details>
  
  
  
------------
# array
[맨 위로](#index)
<details>
  <summary>array (배열)</summary>
  
### 고정/가변 배열
* 고정
  * [][]
* 동적
  * ArrayList (기존 + 기존/2 만큼 추가적으로 가변됨)
### 얕은 복사
* 주소를 복사
* ```java
  int[] a = new int[];
  int[] b = a;
  // b는 a의 주소를 가짐
### 깊은 복사 = .clone()
* ```java
  int[] a = new int[];
  int[] b = a.clone();
  // b는 a의 값을 가짐
### 배열(Array) 또는 리스트(List) 거꾸로 (String)
* ```java
  // 가장 쉽게
  String[] array = {"5","2","89","423","111"};
  Arrays.sort(array, (a, b) -> -1);

  System.out.println(Arrays.toString(array));
  
  String[] array = {"5","2","89","423","111"};
  List<String> list = Arrays.asList(array); // convert to ArrayList
  Collections.reverse(list);
  System.out.println(list);
  // [111, 423, 89, 2, 5]
  
### 배열 거꾸로 (int)
* ```java
  // 가장 쉽게
  // 원시타입인 경우는 다음과 같은 사용 불가능 int[] 는 원시타입이므로 불가능, 즉 객체만 sort 가능하다.
  // int[][] 의 경우는 2차원부터 객체로 판단되기 때문에 비교 가능
  Integer[] array = {"5","2","89","423","111"};
  Arrays.sort(array, (a, b) -> -1);

  System.out.println(Arrays.toString(array));
      
  int[] array = {5, 2, 89, 423, 111};
  // 단순한게 재일 좋은듯 싶다.
  int count = 0;
  for(int i = array.length - 1 ; i >= 0 ; i--){
      reverse[count] = array[i];
      count++;
  }
  
  // Collection 사용
  // int[] -> Integer[] -> list -> reverse
  Integer[] newArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
  List<Integer> list = Arrays.asList(newArray);
  Collections.reverse(list);
  System.out.println(list.toString());  
  // [111, 423, 89, 2, 5]
  ```

### Array -> List 변환
* ```java
  // Immutable
  Arrays.asList() // return List<T> 
  
  // Mutable
  List<Integer> list = new ArrayList<>(Arrays.asList(array)):
  
### List -> Array 변환
* ```java
  List.stream().mapToInt(Integer::intValue).toArray()
 
 
### 배열사이즈 구하기
* ```java
  int[][][] array = new int[3][4][5];

  int x = array.length;
  int y = array[0].length;
  int z = array[0][0].length;

  System.out.println("x: " + x + " / y:" + y + " / z :" + z);
  // x: 3 / y:4 / z :5
</details>
  
  
  
------------
# stack
[맨 위로](#index)
<details>
  <summary>스택 (stack)</summary>
  
### 스택
* ```java
  import java.util.*
  
  Stack<Integer> stack = new Stack<>();

  stack.push(5) // 쌓기
  stack.pop() // 맨 위에값 가져오기 동시에 빼기
  stack.peek() // 맨 위에값 가져오기
  stack.isEmpty() // 비어있다면
  stack.isNotEmpty() // 비어있지 않다면
  stack.size(); // 크기
  
  // toArrayList
  Stack<Integer> stack = new Stack<Integer>();
  stack.push(1);
  stack.push(3);
  stack.push(6);
  stack.push(19);

  ArrayList<Integer> list = new ArrayList<>(stack);

  System.out.println("toString: " + list.toString());
  System.out.println("toString: " + Arrays.toString(stack.toArray()));
  // [1, 3, 6, 19]
  // [1, 3, 6, 19] 
</details>  
  
  
  
------------
# queue
[맨 위로](#index)
<details>
  <summary>큐 (queue)</summary>
  
### 큐
* ||예외 발생|값 리턴|
  |:--:|:--:|:--:|
  |enqueue|add(e)|offer(e)|
  |dequeue|remove(e)|poll(e)|
  |peek|element()|peek(), 없을경우 null 반환|
* ```java
  Queue<Integer> queueInteger = new LinkedList<>();

  int get = 0;

  queueInteger.add(5); 
  queueInteger.add(8);
  queueInteger.add(22);
  queueInteger.add(63);
  queueInteger.offer(33);
  queueInteger.offer(2);
  queueInteger.offer(1);
  queueInteger.offer(13);

  System.out.println(queueInteger.toString());
  // [5, 8, 22, 63, 33, 2, 1, 13]
  
  int peek = queueInteger.peek();
  System.out.println("참조(peek()) : " + peek + "(값 변환없음)\n" + queueInteger.toString());
  // 참조(peek()) : 5(값 변환없음)
  // [5, 8, 22, 63, 33, 2, 1, 13]
  
  get = queueInteger.poll();
  System.out.println("꺼냄(poll()): " + get + "\n" + queueInteger.toString());
  // 꺼냄(poll()): 5
  // [8, 22, 63, 33, 2, 1, 13]
  
  get = queueInteger.poll();
  System.out.println("꺼냄(poll()): " + get + "\n" + queueInteger.toString());
  // 꺼냄(poll()): 8
  // [22, 63, 33, 2, 1, 13]
  
  queueInteger.remove();
  System.out.println("remove()" + "\n" + queueInteger.toString());
  // remove()
  // [63, 33, 2, 1, 13]
  
  queueInteger.remove();
  queueInteger.remove();
  System.out.println("remove() + remove()" + "\n" + queueInteger.toString());
  // remove() + remove()
  // [2, 1, 13]
  
  queueInteger.clear();
  System.out.println("clear()" + "\n" + queueInteger.toString());
  // clear()
  // []
  
  int pollnull = queueInteger.poll(); // NullPointerException 발생 // 만약 String Queue<String> 일경우 발생하지 않음
  System.out.println("poll()" + "\n" + queueInteger.toString());
  // java.lang.NullPointerException 발생함


  // --------------------------------------
  // [5, 8, 22, 63, 33, 2, 1, 13]
  // 참조(peek()) : 5(값 변환없음)
  // [5, 8, 22, 63, 33, 2, 1, 13]
  // 꺼냄(poll()): 5
  // [8, 22, 63, 33, 2, 1, 13]
  // 꺼냄(poll()): 8
  // [22, 63, 33, 2, 1, 13]
  // remove()
  // [63, 33, 2, 1, 13]
  // remove() + remove()
  // [2, 1, 13]
  // clear()
  // []

  java.lang.NullPointerException 발생함 // 만약 String Queue<String> 일경우 발생하지 않음
</details>


  
------------
# comparator
[맨 위로](#index)
<details>
  <summary>comparator</summary>
  
### Comparator
* ```java
  Comparator<Integer> comp = (a, b) ->
              {
                  // 오름차순  // 아주 쉽게는 list.sort(Integer::compareTo);
                  if(a > b) return 1; // a 가 앞에
                  else if(a < b) return -1; // a 가 뒤에 
                  else return 0; // 변화 없음
                  // 내림차순
                  // if(a > b) return -1; // a 가 앞에
                  // else if(a < b) return 1; // a 가 뒤에 
                  // else return 0; // 변화 없음
              };
                      
<details>
  <summary>Array 코드 (배열에서의 정렬시 주의할점) </summary>
  
* ```java
  int[] arr = new int[]{1,0,2,3,5,6,7,8,9,36};
  Arrays.sort(arr);
  System.out.println(Arrays.toString(arr));
  [0, 1, 2, 3, 5, 6, 7, 8, 9, 36]
  // 배열해서는 자료형일경우 Comparator 사용불가
  // 즉, 다음과같으 사용불가
  // Arrays.sort(arr, (a,b) -> {
      ...
  });
  
  // 하지만 2차원 배열의 경우에는 가능
  int[][] arr = new int[][]{{77,0},{2,3},{16,6},{7,8},{9,36}};
  Arrays.sort(arr, (a,b) -> {
       if(a[0] > b[0]) return 1;
       else if(a[0] < b[0]) return -1;
       else return 0;
  });
  for(int[] i: arr){
      System.out.print(Arrays.toString(i) + " ");
  }
  // [2, 3] [7, 8] [9, 36] [16, 6] [77, 0]
  
</details>
  
<details>
  <summary>리스트 코드</summary>
  
### 숫자 정렬
* ```java                                   
  List<Integer> list = new ArrayList<>();
  list.add(5);
  list.add(1);
  list.add(7);
  list.add(8);
  list.add(4);

  // 첫번째 방법
  list.sort(Integer::compareTo);
  
  // 두번째 방법
  list.sort((a, b) ->
              {
                  if(a > b) return -1;
                  else if(a < b) return 1;
                  else return 0;
              }
  );
  
  // 세번째 방법
  Comparator<Integer> comp = (a, b) ->
              {
                  if(a > b) return -1;
                  else if(a < b) return 1;
                  else return 0;
              };
  Collections.sort(list, comp);

  System.out.println(list.toString());
  // [1, 4, 5, 7, 8]
                                
### 문자 정렬
* ```java
  List<String> list = new ArrayList<>();
  list.add("가");
  list.add("자");
  list.add("마");
  list.add("나");
  list.add("다");

  // 첫번째 방법
  list.sort((a, b) -> Integer.compare(a.compareTo(b), 0));
  
  // 두번째 방법
  list.sort((a, b) -> {
      // 오름차순
      return a.compareTo(b);
      // 내림차순
      if(a.compareTo(b) > 0) return -1;
      else if(a.compareTo(b) < 0) return 1;
      else return 0;
  });
  
  // 세번째 방법
  Comparator<Integer> comp = (a, b) ->
              {
                    if(a.compareTo(b) > 0) return 1;
                    else if(a.compareTo(b) < 0) return -1;
                    else return 0;
              };
  Collections.sort(list, comp);
  
  
  System.out.println(list.toString());
  // [가, 나, 다, 마, 자]
</details>
</details>
  

  
------------
# compareTo
[맨 위로](#index)
<details>
  <summary>compareTo</summary>

### a.compareTo(b)
* 문자열 비교시에는 아스키코드 값 차이만큼 반환함
  * ```java
    String numStr1 = "756";
    String numStr2 = "719";
    int rtn = numStr1.compareTo(numStr2); // '7'같으니까 비교안함 -> '5' '1' 서로 다르니까 비교 -> 서로의 아스키값 차이는 4임 -> 4 반환 -> 더이상 진행하지않음
    // compareToIgnoreCase(numStr2) -> 대소문자를 무시하고 비교함
    System.out.println(rtn);
      
    // 4
      
### sort - [출처 notepad96님의 티스토리](https://notepad96.tistory.com/entry/Kotlin-8)
* ```java
  // 오름차순, 내림차순 위 쪽 배열 참고
  int[][] array = { {5,2}, {1,2}, {7,7}, {8, 9}, {1, 9} };
  Arrays.sort(array, (a, b) -> {
      // 여기 안에서 전부 조정하면됨
      // 두 번째를 기준으로 오름차순 정렬
      if(a[1] > b[1]) return 1;
      else if(a[1] < b[1]) return -1;
      else return 0;
  });
  // 또는 아래와 같이 간단한 코드로 구현 가능, 하지만 구현상 편의를 위해 위의 방법을 권장함
  // Arrays.sort(array, Comparator.comparingInt(a -> a[0]));

  for (int[] i : array){
      for(int j : i){
          System.out.print(j + " ");
      }
      System.out.println();
  }
</details>
  

  
------------
# collection-stream
[맨 위로](#index)
<details>
  <summary>collection-stream</summary>

### 합계 - sum
* Stream
  * ```java
    int[] array = new int[]{1,2,5,3,6};
    int sum = Arrays.stream(array).sum();
    System.out.println(sum);
    
    // 17
  
* Collection -> stream()
  * ```java
    ArrayList<Long> longArray = new ArrayList<>();
    ArrayList<Double> doubleArray = new ArrayList<>();

    long longSum = longArray.stream().mapToLong(Long::longValue).sum(); // or average()
    double doubleSum = doubleArray.stream().mapToDouble(Double::doubleValue).sum(); // or average()
  
### 평균 - average
* Stream
  * ```java
    int[] array = new int[]{1,2,5,3,6};
    OptionalDouble avg =  Arrays.stream(array).average();
    avg.ifPresent(System.out::println);
    // 3.5
    
    int[] array = new int[]{1,2,5,3,6};
    int avg =  Arrays.stream(array).sum() / array.length;
    System.out.println(avg);
    // 3

* Collection -> stream()
  * ```java
    ArrayList<Long> longArray = new ArrayList<>();
    ArrayList<Double> doubleArray = new ArrayList<>();

    long longSum = longArray.stream().mapToLong(Long::longValue).average()
    double doubleSum = doubleArray.stream().mapToDouble(Double::doubleValue).average();

### 최대값 - max, min
* Stream
  * ```java
    // 왠만해서는 알고리즘 문제에서 ifPresent 로 체크해서 존재하지 않는 경우를 확인하기보다는
    // 가독성과 편의를 위해 위와 같이 그냥 가져오기 
    int or double or long max = Arrays.stream(array).max().getAsDouble() // getAsLong(), getAsInt();
   
    // max
    double[] array = new double[]{1,2,5,3,6};
    OptionalDouble max =  Arrays.stream(array).max(); // int 일경우 OptionalInt
    max.ifPresent(System.out::println);
    
    
    // min
    int[] array = new int[]{1,2,5,3,6};
    OptionalInt min = Arrays.stream(array).min();
    min.ifPresent(value -> {
        System.out.println(value);
    });
   
* Collections
  * ```java
    ArrayList<Integer> array =new ArrayList<>();
    array.add(3);
    array.add(1);
    array.add(5);
    array.add(7);
    array.add(6);

    // 밥법 - 1 
    // int max = Collections.max(array);
    // int min = Collections.min(array);
    // System.out.println("min: " + min + " / max: " + max);
    // min: 1 / max: 7
    
    // 방법 - 2
    Collections.sort(array);
    System.out.println("min : " + array.get(0) + " / max: " + array.get(array.size() - 1));
    // min : 1 / max: 7
    
### forEach
* Collecitons - return void
  ```java
  list.forEach(a -> {
            System.out.println(a);
        }); 
  // 156 32 20
* Stream
  ```java
  String[] array = new String[]{"asdf","bsdfg"};
  Arrays.stream(array).forEach( a -> {
      System.out.println(a);
  });
  
### findFirst() - Optional<T>
* Stream
  ```java
  ArrayList<Integer> list = new ArrayList<>();
  list.add(156);
  list.add(32);
  list.add(20);

  Optional<Integer> data  = list.stream().filter(a -> {
      return a.equals(20);
  }).findFirst();

  data.ifPresent( a -> System.out.println(a));

### filter - return Stream<T>
* Stream
  ```java
  // 마찬가지로 stream 
  int[][] array = { {5,2}, {1,2}, {7,7}, {8, 9}, {1, 9} };

  Arrays.stream(array).filter( a -> a[1] == 2).forEach( a-> {
     System.out.println(Arrays.toString(a));
  });
  
### removeIf
* Collections - return boolean
  ```java
  ArrayList<Integer> list = new ArrayList<>();
  list.add(156);
  list.add(32);
  list.add(20);

  list.removeIf(a -> {
      return a == 20;
  });

  System.out.println(list.toString());
  // [156, 32]
</details>
  
  
------------
# string
[맨 위로](#index)
<details>
  <summary>string</summary>
  
### 문자열
* 대문자
  * str.toUpperCase()
* 소문자
  * str.toLowerCase()
* string
  * ```java
    String x = "Java Complete";
    System.out.print(x.toCharArray()[0] + " ");
    System.out.print(x.toCharArray()[3] + " ");
    System.out.print(x.length());
    // J a 13 
    
### 자르기
* ```java
  String s = "hello, my friend. hello kotlin. hello world.";
  String[] splitedString = s.split(", ");
  
### 공백이 무수히 있어도 완전히 제거
* ```java
  // 공백제거 1
  " a  3people  unFollowed   me  ".replaceAll("\\s{1,}","");
  // 공백제거해서 첫항이 "" 이 안나오게 분리
  String[] array = Arrays.stream("  a  3people   unFollowed    me   ".split("\\s{1,}")).filter( a -> !a.equals("")).toArray(String[]::new);
  
### 정수 짜르기(스트링 변환없이)
* ```java
  int remain = 0;
  int value = 84716;
  while(value != 0){
      System.out.print(value % 10 + " ");
      value /= 10;
  }
  // 6 1 7 4 8
### 문자열 뒤집기
* ```java
  StringBuilder sb = new StringBuilder("ewrwer");
  sb.reverse();
  System.out.println(sb.toString());
 
### 대체
* ```java
  String s = "hello, my friend. hello kotlin. hello world.";
  String new_s = s.replace(' ', '+');
  
### 확인
* ```java
  String s = "hello, my friend. hello kotlin. hello world.";
  boolean start = s.startsWith("hello"); // true
  boolean end = s.endsWith("world2"); // false
  boolean contains = s.contains("kotlin"); // ture
  System.out.println(start + " / " + end + " / " + contains);
  // true / false / true

</details>
  
  

------------
# Hash
[맨 위로](#index)
<details>
  <summary>Hash</summary>

### 해쉬
### 해쉬 생성
  * ```java
    Map<String, Integer> hashMap = new HashMap<>();
    hashMap.put("A",1);
    hashMap.put("A",2);
    hashMap.put("B",2);
    
    System.out.println(hashMap.get("A"));
    System.out.println(hashMap.get("B"));
    // 2
    // 2
### getOrDefault()
  * 가져왔는데 없다면 기본값으로 적용  
  * ```java
    map.getOrDefault("A", "B"); 
### 데이터 순회 - Map 은 Iterator 가 없기 때문에 entrySet() 을 활용하여 Iterator 를 사용한다.
* ```java
  Map<Integer, String> map = new HashMap<>();
  map.put(23, "Y");
  map.put(67, "A");
  map.put(99, "ZH");
  map.put(103, "UU");
  map.put(111, "QT");
     
  // 방법 1 - keySet()
  for(int i : map.keySet()){
      System.out.println(map.get(i));  // 이런식으로 한번더 순회를 해야하기 때문에 entrySet() 보다 느리다.
  }
  
  // 방법 2 - for(Map.Entry<K, V> entry : map.entrySet()) 
  for(Map.Entry<Integer, String> entry : map.entrySet()){
  	  System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue()); // 이미 값을 가져왔기때문에 keySet() 보다 빠르다.
  }
  
  // 방법 3 - iterator()    
  Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
  while(iterator.hasNext()){
      Map.Entry<Integer, String> entry = iterator.next();
      System.out.println("entry.getKey(): " + entry.getKey() + " / entry.getValue(): " + entry.getValue());
  }
### 이미 데이터 있는지 확인
* 키가 있나요? - containsKey(key) == true
* 키가 있나요? - Map.get(key) != null
* 벨류가 있나요? - containsValue(value) == true
    
</details>
  
  
------------  
# Math
[맨 위로](#index)
<details>
  <summary>Math (수학)</summary>
 
### 최댓값 / 최솟값 / 제곱값 / 절댓값
<details>
    <summary>코드 보기</summary>
  
* ```java
  Math.max(a,b) // 최댓값
  Math.min(a,b) // 최솟값
  Math.pow(a,b) // 제곱값 (a의 b 제곱)
  Math.abs(a,b) // 절댓값
</details>
  
### 일반적으로 경우의수는 순열이 조합보다 많다.
### 조합
<details>
    <summary>펼치기 + 코드 보기</summary>
  
* 3개 중에 2 개를 선택 근데 이미 선택한건 **못 선택함**
  ```java
  1 2
  1 3
  2 3
* ```java
  /**
    * r : 뽑고자 하는 개수
    * temp : r개를 뽑는 결과값을 저장해놓는 배열
    * current : 현재 개수를 저장해 놓는 값
    * start : 그다음 반복문을 시작하는 값
   **/
  void someMethod() {
    Comp(3, new int[3], 0, 0, new int[]{1,2,6,3,3});
  }
  
  private void Comp(int r, int[] temp, int current, int start, int[] array){
      if(r == current){
          System.out.println(Arrays.toString(temp));
      } else{
          for(int i = start ; i < array.length ; i++){
              temp[current] = array[i];
              Comp(r, temp, current + 1, i + 1, array);
          }
      }
  }

</details>
  
### 중복조합
<details>
    <summary>펼치기 + 코드 보기</summary>
  
* 3개 중에 2 개를 선택 근데 이미 선택한거 **또 선택됨**
  ```java
  1 1 
  1 2 
  1 3 
  2 2 
  2 3 
  3 3
* ```java
  // 조합에서 Comp(... i + 1 ) --> Comp(... i, ) 로 변경하면 끝남
  /**
    * r : 뽑고자 하는 개수
    * temp : r개를 뽑는 결과값을 저장해놓는 배열
    * current : 현재 개수를 저장해 놓는 값
    * start : 그다음 반복문을 시작하는 값
   **/
  void someMethod() {
    Comp(3, new int[3], 0, 0, new int[]{1,2,6,3,3});
  }
  
  private void Comp(int r, int[] temp, int current, int start, int[] array){
      if(r == current){
          System.out.println(Arrays.toString(temp));
      } else{
          for(int i = start ; i < array.length ; i++){
              temp[current] = array[i];
              Comp(r, temp, current + 1, i, array);
          }
      }
  }
    ```
</details>

### 순열
<details>
    <summary>펼치기 + 코드 보기</summary>
  
* 3개 중에 2 개를 선택해서 나열함 근데 이미 선택한건 **못 선택함**, 쉽게말해서 뽑는 순서까지 포함해서 내가 1 이라는 값을 첫번째에 뽑고 2 라는값을 두번째에 뽑은거랑 2라는 값을 첫번째에 뽑고 1이라는 값을 두번째에 뽑은것은 다른 경우의수로 취급함
  ```java
  1 2 
  1 3 
  2 1 
  2 3 
  3 1 
  3 2
* ```java
  // 중복순열에서 visited 만 추가
  /**
    * r : 뽑고자 하는 개수
    * temp : r개를 뽑는 결과값을 저장해놓는 배열
    * current : 현재 개수를 저장해 놓는 값
    * visited : 방문 여부를 확인하는 배열
   **/
  public void Test() {
      Comp(3, new int[3], 0, new boolean[5], new int[]{1,2,6,3,3});
  }
  
  private void Comp(int r, int[] temp, int current, boolean[] visited, int[] array){
      if(r == current){
          System.out.println(Arrays.toString(temp));
      } else{
          for(int i = 0 ; i < array.length ; i++){
              if(!visited[i]) {
                  visited[i] = true;
                  temp[current] = array[i];
                  Comp(r, temp, current + 1, visited, array);
                  visited[i] = false;
              }
          }
      }
  }
  ```
</details>
  
### 중복순열
<details>
    <summary>펼치기 + 코드 보기</summary>

* 3개 중에 2 개를 선택해서 나열함 근데 이미 선택한거 **또 선택가능**
  ```java
  1 1 
  1 2 
  1 3 
  2 1 
  2 2 
  2 3 
  3 1 
  3 2 
  3 3
* ```java
  // 조합에서 start 제거 및 int i = start 가 아닌 0 부터 시작
  /**
    * r : 뽑고자 하는 개수
    * temp : r개를 뽑는 결과값을 저장해놓는 배열
    * current : 현재 개수를 저장해 놓는 값
   **/
  public void Test() {
      Comp(3, new int[3], 0, new int[]{1,2,6,3,3});
  }
  
  private void PermutDup(int r, int[] temp, int current, int[] array){
      if(r == current){
          System.out.println(Arrays.toString(temp));
      } else{
          for(int i = 0 ; i < array.length ; i++){
              temp[current] = array[i];
              Comp(r, temp, current + 1, array);
          }
      }
  }
  ```
</details>
  
### 소수 (반댓말 : 합성수 = 소수가 아닌수 (1보다 큰수))
<details>
      <summary>펼치기 + 코드 보기</summary>
  
* O(N√N) - 소수 판별
  * <details>
      <summary>코드 보기</summary>
         
      ```java
      private boolean isPrimeNumber(int number){
          if(number < 2){
              return false;
          }
          if(number == 2){
              return true;
          }
          if(number % 2 == 0){
              return false;
          }

          for(int i = 2; i <= Math.sqrt(number) ; i++ ){
              if(number % i == 0){
                  return false;
              }
          }
  
          return true;
      }
  
      ```
    </details>
* O(Nlog(log N)) - N 이하 소수 구하기 - 에라토스테네스의 체
  * 2는 소수 -> 2빼고 2의 배수 삭제 -> 남아있는 수들중 가장작은 수 -> 3은 소수? -> 3빼고 3의 배수 삭제 ......... <= number의 루트까지
  * <details>
      <summary>코드 보기</summary>
         
      ```java
      // 체로 거르기
      private boolean[] getPrime(int number){
          boolean[] prime = new boolean[number+1];
          if(number < 2){
              return new boolean[]{true};
          }
          prime[0] = prime[1] = true;

          for(int i = 2 ; i <= Math.sqrt(number) ; i++){

              // 이미 걸렀다면 넘어가기
              if(prime[i] == true){
                  continue;
              }

              for(int j = i*i ; j < prime.length ; j+=i){
                  prime[j] = true; // 체로 거르기
              }
          }

          return prime;
      }
  
      // 사용
      ... someMethod(){
          boolean[] prime = getPrime(10000);
          for(int = 0 ; i < prime.length ; i++){
              if(prime[i] == false){ // 걸러지지 않았다면 소수
                  System.out.println(prime[i]);
              } 
          }
      }
      ```
    </details>
</details>
  
### 약수 - divisor
* <details>
    <summary>코드 보기</summary>
  
    ```java
    // 약수둘의 특징인 하나의 약수 A 를 구하면 B 도 자연스레 알수있는것을 사용 (예를들어서 30의 약수중 3을 알경우 10도 자연스레 약수인것을 알수있다)
    private int[] getDivisor(int number){
        List<Integer> temp = new ArrayList<>();
        for(int i = 1 ; i <= Math.sqrt(number) ; i++){
            if(number % i == 0){
                temp.add(i);
                if(number / i != i){
                    temp.add(number / i);
                }
            }
        }

        Collections.sort(temp);

        return temp.stream().mapToInt(Integer::intValue).toArray();
    }
    ```
                                                                 
  </details>
  
### 최대공약수 - GCD(the **G**reatest **C**ommon **D**enominator
* ```java
  private int Gcd(int a, int b) { return b == 0 ? a : Gcd(b, a % b);}
### 최소공배수 - LCM(the **L**east[lowest] **C**ommon **M**ultiple) 
* ```java
  private int Lcm(int a, int b) { return (a*b) / Gcd(a,b); }
### 소인수분해
* <details>
    <summary>코드 보기</summary>
  
    ```java
    private int[] Pf(int number){
        List<Integer> list = new ArrayList<>();
      
        for(int i = 2; i <= Math.sqrt(number) ; i++){
            while(number % i == 0){
                list.add(i);
                n /= i;
            }
        }
        if(number != 1){
            list.add(number);
        }
                                                               
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    ```                                                                 
  </details>
### 팩토리얼 ( 1 ~ N 까지의 곱 )
* <details>
    <summary>코드 보기</summary>
  
    ```java
    private long factorial(int number){
        long result = 1;
        for(int i = 1 ; i <= number ; i++ ){
            result *= i;
        }
        return result;
    }
    ```     
                                  
    ```java
    // 재귀
    private int factorial(int i){
        if(i < 1){
            return 1;
        }
        return i*factorial(i-1);
    }
    ```
  </details>
### 피보나치
* <details>
    <summary>코드 보기</summary>
  
    ```java
    private int[] pibo(int number){
        int[] result = new int[number+1];
        result[0] = 0;
        result[1] = 1;
        for(int i = 2 ; i <= number ; i++ ){
            result[i] = result[i-1] + result[i-2];
        }

        return result;

    }
    ```
                                  
    ```java
    private int pibo(int n){
        if(n < 3){
            return 1;
        }
        return pibo(n-2) + pibo(n-1);
    }
 
    // with Memoization
    private int pibo(int n, int[] visited){
        if(visited[n] != 0){
            return visited[n];
        }
        if(n < 3){
            return 1;
        }
        visited[n] = pibo(n-2, visited) + pibo(n-1, visited);
        return visited[n];
    }
    ```
 
  </details>
### 진법 변환
<details>
    <summary>펼치기</summary>
  
* <details>
    <summary>변환 사진</summary>
  
    <img width="313" alt="스크린샷 2022-01-09 오후 3 36 11" src="https://user-images.githubusercontent.com/51182964/148671948-4c5eb990-9945-4f2b-a335-b39db080c69c.png">
  </details>
* 10 -> N
  * ```java
    private String converterBin(int number, int bin){
        StringBuilder sb = new StringBuilder();
        while(number > 0){
            sb.append(number % bin);
            number /= bin;
        }
        return sb.reverse().toString();
    }
* N -> 10
  * ```java
    String third = converterBin(45, 3);
    System.out.println(third); // 1200
    System.out.println(Integer.parseInt(third, 3)); // 45
  
  </details>
  
</details
  
  

------------
# ETC
[맨 위로](#index)
<details>
    <summary>기타 (펼치기)</summary>

### 윤년 계산 (+달의 일 계산법)
<details>
    <summary>윤년 계산</summary>
  
* [달의 일수 계산법](https://m.blog.naver.com/deltatech3/220761852623)
* 윤년계산
  ```java
  private boolean isYun(int year){
      if( (year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
          return false;  
      }
      return false;
  }
  
</details>

</details>
  

  
------------
# data structure
[맨 위로](#index)
### 자료구조 틀
* ![자료구조](https://user-images.githubusercontent.com/51182964/148328450-99a90057-b30f-4f39-beef-3612abe95be7.png)
* ![java_collection_3](https://user-images.githubusercontent.com/51182964/148328622-6422f3b6-50b2-4ffe-b1ff-5eb19c806bad.jpg)
