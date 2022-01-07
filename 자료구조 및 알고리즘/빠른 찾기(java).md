# 빠른 찾기
* ```java
  // 필수적으로 하기
  import java.util.* 
### 색인
* [아스키](#ascii)
* [비교](#comparison)
* [for](#for)
* [List -> String](#list-to-string)
* [배열](#array)
* [stack](#stack)
* [queue](#queue)
* [comparator](#comparator)
* [compareTo](#compareto)
* [collection - stream](#collection-stream)
* [string](#string)
* [수학](#math)
* [자료구조 전체사진, 컬렉션포함](#data-structure)

# ASCII
### 아스키
* char <-> ascii 전환
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
<summary>아스키 코드 전체 (펼치기/접기)</summary>
<div markdown="1">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkDTO6%2Fbtq1ca8DHu6%2FbRY6sqRipuwtTRsEokfw1K%2Fimg.png">
</div>
</details>
  

---
# comparison
##### 비교
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
# for
##### for
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
# list to String
##### 리스트를 스트링(String) 으로 출력
* ```java  
    List<Integer> linkedList = new LinkedList<>();
    linkedList.add(3);
    linkedList.add(1);
    linkedList.add(5);
    linkedList.add(37);

    System.out.println(Arrays.toString(linkedList.toArray()));
    // [3, 1, 5, 37]
# array
##### 배열
* [][]
  * 고정
* ArrayList
  * 가변
    * 기존 + 기존/2 만큼 추가적으로 가변됨
* 배열 거꾸로 (String)
  ```java
  // 가장 쉽게
  String[] array = {"5","2","89","423","111"};
  Arrays.sort(array, (a, b) -> -1);

  System.out.println(Arrays.toString(array));
  
  String[] array = {"5","2","89","423","111"};
  List<String> list = Arrays.asList(array); // convert to ArrayList
  Collections.reverse(list);
  System.out.println(list);
  // [111, 423, 89, 2, 5]
* 배열 거꾸로 (int)
  ```java
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
* list -> int[]
  ```java
  // list -> Integer[] -> int[]
  int[] reverseArray = Arrays.stream(list.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
  for(int i: reverseArray){
      System.out.print(i + " ");
  }
* 배열사이즈 구하기
  ```java
  int[][][] array = new int[3][4][5];

  int x = array.length;
  int y = array[0].length;
  int z = array[0][0].length;

  System.out.println("x: " + x + " / y:" + y + " / z :" + z);
  // x: 3 / y:4 / z :5
    
# stack
##### 스택
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
  
# queue
##### 큐
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

# comparator
##### Comparator
  * ```java
    Comparator<Integer> comp = (a, b) ->
                {
                    // 오름차순
                    if(a > b) return 1; // a 가 앞에
                    else if(a < b) return -1; // a 가 뒤에 
                    else return 0; // 변화 없음
                    // 내림차순
                    // if(a > b) return -1; // a 가 앞에
                    // else if(a < b) return 1; // a 가 뒤에 
                    // else return 0; // 변화 없음
                };
* 숫자 정렬
  ```java
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
* 문자 정렬
  ```java
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
      if(a.compareTo(b) > 0) return 1;
      else if(a.compareTo(b) < 0) return -1;
      else return 0;
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
  
# compareTo
##### a.compareTo(b)
* 문자열 비교시에는 아스키코드 값 차이만큼 반환함
  * ```java
    String numStr1 = "756";
    String numStr2 = "719";
    int rtn = numStr1.compareTo(numStr2); // '7'같으니까 비교안함 -> '5' '1' 서로 다르니까 비교 -> 서로의 아스키값 차이는 4임 -> 4 반환 -> 더이상 진행하지않음
    // compareToIgnoreCase(numStr2) -> 대소문자를 무시하고 비교함
    System.out.println(rtn);
      
    // 4
      
##### sort - [출처 notepad96님의 티스토리](https://notepad96.tistory.com/entry/Kotlin-8)
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
    
# collection-stream
##### forEach
* Collecitons - return void
  ```java
  list.forEach(a -> {
            System.out.println(a);
        }); 
  // 156 32 20
##### findFirst() - Optional<T>
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

##### filter - return Stream<T>
* Stream
  ```java
  // 마찬가지로 stream 
  int[][] array = { {5,2}, {1,2}, {7,7}, {8, 9}, {1, 9} };

  Arrays.stream(array).filter( a -> a[1] == 2).forEach( a-> {
     System.out.println(Arrays.toString(a));
  });
  
##### removeIf
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
---
# string
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
    
##### 자르기
  * ```java
    String s = "hello, my friend. hello kotlin. hello world.";
    String[] splitedString = s.split(", ");
##### 정수 짜르기(스트링 변환없이)
  * ```java
    int remain = 0;
    int value = 84716;
    while(value != 0){
        System.out.print(value % 10 + " ");
        value /= 10;
    }
    // 6 1 7 4 8
##### 대체
  * ```java
    String s = "hello, my friend. hello kotlin. hello world.";
    String new_s = s.replace(' ', '+');
##### 확인
  * ```java
    String s = "hello, my friend. hello kotlin. hello world.";
    boolean start = s.startsWith("hello"); // true
    boolean end = s.endsWith("world2"); // false
    boolean contains = s.contains("kotlin"); // ture
    System.out.println(start + " / " + end + " / " + contains);
    // true / false / true
    
---
# Math
### 수학 
* 최댓값
  * ```java
    Math.max(a,b)
* 최솟값
  * ```java
    Math.min(a,b)
* 제곱
  * ```java
    Math.pow(a,b)
* 절대값
  * ```java
    Math.abs(a)
* 일반적으로 경우의수
  * 순열 >= 조합
* 조합
  * 3개 중에 2 개를 선택 근데 이미 선택한건 **못 선택함**
    ```java
    1 2
    1 3
    2 3
  * <details>
      <summary>코드 보기</summary>
  
      ```java
      /**
        * r : 뽑고자 하는 개수
        * temp : r개를 뽑는 결과값을 저장해놓는 배열
        * current : 현재 개수를 저장해 놓는 값
        * start : 그다음 반복문을 시작하는 값
       **/
      static int[] arr = { 1, 2, 3, 4, 5 };
      private void Comb(int r, int[] temp, int current, int start) {
    		  if (r == current) {
    		  	  System.out.println(Arrays.toString(temp));
    		  } else {
    		  	  for (int i = start; i < arr.length; i++) {
    		  		    temp[current] = arr[i];
    		  		    Comb(r, temp, current + 1, i + 1);
    		  	  }
    		  }
    	}
      ```
    </details>
  
* 중복조합
  * 3개 중에 2 개를 선택 근데 이미 선택한거 **또 선택됨**
    ```java
    1 1 
    1 2 
    1 3 
    2 2 
    2 3 
    3 3
  * <details>
      <summary>코드 보기</summary>

      ```java
      /**
        * r : 뽑고자 하는 개수
        * temp : r개를 뽑는 결과값을 저장해놓는 배열
        * current : 현재 개수를 저장해 놓는 값
        * start : 그다음 반복문을 시작하는 값
       **/
      static int[] arr = { 1, 2, 3, 4, 5 };
      private void CombDup(int r, int[] temp, int current, int start) {
      		if (r == current) {
      			System.out.println(Arrays.toString(temp));
      		} else {
      			for (int i = start; i < arr.length; i++) {
      				temp[current] = arr[i];
      				CombDup(r, temp, current + 1, i);
      			}
      		}
      }
      ```
    </details>
* 순열
  * 3개 중에 2 개를 선택해서 나열함 근데 이미 선택한건 **못 선택함**, 쉽게말해서 뽑는 순서까지 포함해서 내가 1 이라는 값을 첫번째에 뽑고 2 라는값을 두번째에 뽑은거랑 2라는 값을 첫번째에 뽑고 1이라는 값을 두번째에 뽑은것은 다른 경우의수로 취급함
    ```java
    1 2 
    1 3 
    2 1 
    2 3 
    3 1 
    3 2
  * <details>
      <summary>코드 보기</summary>

      ```java
      /**
        * r : 뽑고자 하는 개수
        * temp : r개를 뽑는 결과값을 저장해놓는 배열
        * current : 현재 개수를 저장해 놓는 값
        * visited : 방문 여부를 확인하는 배열
       **/
      static int[] arr = { 1, 2, 3, 4, 5 };
      private static void Permut(int r, int[] temp, int current, boolean[] visited) {
      		if (r == current) {
      			System.out.println(Arrays.toString(temp));
      		} else {
      			for (int i = 0; i < arr.length; i++) {
      				if (!visited[i]) {
      					visited[i] = true;
      					temp[current] = arr[i];
      					Permut(r, temp, current +1, visited);
      					visited[i] = false;
      				}
      			}
      		}
      }
      ```
    </details>
* 중복순열
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
  * <details>
      <summary>코드 보기</summary>

      ```java
      /**
        * r : 뽑고자 하는 개수
        * temp : r개를 뽑는 결과값을 저장해놓는 배열
        * current : 현재 개수를 저장해 놓는 값
       **/
      static int[] arr = { 1, 2, 3, 4, 5 };
      private void PermutDup(int r, int[] temp, int current) {
      		if (r == current) {
      			System.out.println(Arrays.toString(temp));
      		} else {
      			for (int i = 0; i < arr.length; i++) {
      				temp[current] = arr[i];
      				PermutDup(r, temp, current + 1);
      			}
      		}
      }
      ```
    </details>
---  
### 기타
* Array -> List 변환
  ```java
  Arrays.asList() // return List<T>
  
  
---
# data structure
### 자료구조 틀
* ![자료구조](https://user-images.githubusercontent.com/51182964/148328450-99a90057-b30f-4f39-beef-3612abe95be7.png)
* ![java_collection_3](https://user-images.githubusercontent.com/51182964/148328622-6422f3b6-50b2-4ffe-b1ff-5eb19c806bad.jpg)
