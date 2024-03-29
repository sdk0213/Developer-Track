# 빠른 찾기
### str.upperCase() -> 대문자, str.lowercase() -> 소문자
# 아스키
* 전환
  * ```kotlin
    println(65.toChar()); // A
    println('A'.toInt()); // 65 
    // 또는 
    println('A'.code); // 65
* 0	<-> 48
* 9	<-> 57
* A	<-> 65
* Z	<-> 90
* a	<-> 97
* z	<-> 122
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkDTO6%2Fbtq1ca8DHu6%2FbRY6sqRipuwtTRsEokfw1K%2Fimg.png)

---
# 코틀린
##### 비교
  * ==  (동등성 (identity)) 비교
    * 값을 비교
  * === (동일성 (equality)) 비교
    * 주소르 비교 (즉,객체를 비교)  
##### for
  * step(양수만가능)
  * until -> 해당 숫자 제외하고 ( <, > 와 같은 뜻 )
  * ```kotlin
    val len: Int = 5
    for(i in 1..len)
        print("$i ")    //output : 1, 2, 3, 4, 5

    for(i in 1 until len)
        print("$i ")    //output : 1, 2, 3, 4
    
    for(i in 5 downTo 1)
        print("$i ")    //output : 5, 4, 3, 2, 1
    
    for(i in 5 downTo 1 step(3))
        print("$i ")    //output : 5, 2
        
    // downTo 는 until 이 없는것으로 추정
        
    val list = listOf<String>("korea", "salmon", "T_T")
    for(country in list)
        print("$country ")    //output : korea, salmon, T_T
##### while
  * == java
##### 배열 (array) = java's array[][][]
  * ```kotlin
    val arr: IntArray = arrayOf(10, 20, 30, 40, 50)

    for(i in arr)
        print("$i ")    //output : 10, 20, 30, 40, 50

    for(i in arr.reversed())
        print("$i ")    //output : 50, 40, 30, 20, 10
           
    // 5개만 우선 선언
    val array = Array<Int>(5){0}
    
    val arr = IntArray(4) { it }
    // 내용물을 전체 확인하는 코드
    print(arr.contentToString()) // 결과 [0, 1, 2, 3]
    
    val numbers = IntArray(63){ int -> int*2 }
    (numbers.indices).forEach { // 순서대로 출력한다. 1,2,3,4,5,6,7, index 순으로 증가 함
        print("${numbers[it]} ")
    }
    
  * n 차원 배열
    ```kotlin
    // int 2차원 (후 선언)
    val twoDimensionArray = Array<IntArray>(2) { IntArray(3) }
    
    // int 3차원 (후 선언)
    val twoDimensionArray = Array(2) { Array(3){ IntArray(3) } }
    
    // String 3차원 (후 선언)
    val twoDimensionArray = Array(2) { Array(3){ Array(3){""} } }
   
    // 숫자 삽입 (선 선언)
    val xy = arrayOf(arrayOf(1,2,3), arrayOf(4,5,6), arrayOf(7,8,9), arrayOf(10,11,12))
    // 문자 삽입 (선 선언)
    val xy = arrayOf(arrayOf("1","2","3"), arrayOf("4","5","6"), arrayOf("7","8","9"), arrayOf("*","0","#"))
    
    // 출력 : 방법1
    for(i in 0..3) {
        for(j in 0..2) {
            print(xy[i][j])
        }
        println()
    }
    
    // 출력 : 방법2 == 위와 동일함 
    (1..3).forEach { i ->
         (1..7).forEach { j ->
             println(xy[i][j])
         }
         println()
    }
  * 배열사이즈 구하기
    ```kotlin
    val array = arrayOf(arrayOf(0,0,0,0), arrayOf(2,3,4,2))

    println("array: ${array.size}")
    println("array: ${array[0].size}")
    
    array: 2
    array: 4
    
    
    
    
##### 스택
* ```kotlin
  val numbers = mutableListOf(1, 2, 3, 4)
  val stack = MutableList<Int>(4) { it }

  // stack.push(5)
  numbers.add(5)

  // stack.pop()
  numbers.removeLast()

  // stack.peek()
  numbers.last()

  // 비어있는지 확인
  numbers.isEmpty()

  // 내용물이 있는지 확인
  numbers.isNotEmpty()

  //크기
  numbers.size
##### 큐
* ```kotlin
  val que = LinkedList<Int>()
  que.offer(3)
  que.poll()
  que.peek()
##### Comparator
  * ```kotlin
     var list = listOf("나", "다", "가", "라")

     list = list.sortedWith(Comparator<String>{ a, b ->
         when {
             a > b -> 1 // a 가 앞에 
             a < b -> -1 //  a 가 뒤에
             else -> 0 // 변화없음
         }
     })
##### a.compareTo(b)
  * ```kotlin
    // 숫자
    val x = 3; 
    val y = 4; 
    val z = 1.0; 
    println( x.compareTo(y) ); // -1
    println( x.compareTo(3) ); // 0 
    println( x.compareTo(2) ); // 1 
    println( z.compareTo(2.7) ); // -1
    
    // 문자 -> 사전순 정렬
    val list = listOf("나", "다", "가", "라")

    list = list.sortedWith(Comparator<String>{ a, b ->
        when {
            a > b -> 1
            a < b -> -1
            else -> 0
        }
    }) 
    
    // result : [가, 나, 다, 라]
    
  * 문자열 비교 디테일
    * ```kotlin
      // 비교대상 문자열의 각 문자들을 첫번째자리 부터 하나씩 비교하다가 가장 먼저 만나는 상이한 문자들의 ASCII코드 값 차이를 반환하고 끝낸다
      val numStr1 = "756";
      val numStr2 = "719";
      val rtn = numStr1.compareTo(numStr2); // '7'같으니까 비교안함 -> '5' '1' 서로 다르니까 비교 -> 서로의 아스키값 차이는 4임 -> 4 반환 -> 더이상 진행하지않음
      println(rtn)
      
      // 4
      
      val numStr1 = "abcd";
      val numStr2 = "cd";
      val rtn = numStr1.compareTo(numStr2);
      println(rtn)
      // -7
      
      val numStr1 = "Zbfk";
      val numStr2 = "bbmz";
      val rtn = numStr1.compareTo(numStr2);
      println(rtn)
      // -8


      val numStr1 = "Zbfk";
      val numStr2 = "bbmz";
      val rtn = numStr1.compareToIgnoreCase(numStr2); // 대소문자 무시
      println(rtn)
      // 24
      
##### sort - [출처 notepad96님의 티스토리](https://notepad96.tistory.com/entry/Kotlin-8)
  * ```kotlin
    println("==================1========================")
    val ml = mutableListOf(5, 4, 1, 2, 3, 6)
    println(ml)
    ml.sort()   // 오름차순 정렬
    println(ml)



    println("===================2=======================")
    val ml2 = ml.sorted().toMutableList()   // 오름차순으로 정렬된 List 반환
    println(ml2)
    ml2.sortDescending()    // 내림차순으로 정렬
    println(ml2)

    println("====================3======================")
    val ml3 = mutableListOf(2 to "abc", 4 to "abc", 3 to "qwe", 1 to "zwr")
    println(ml3)
    ml3.sortByDescending { it.second }  // second를 기준으로 내림차순
    println(ml3)
    
    println("====================4======================")
    val ml4 = ml3.sortedWith( compareBy( {it.second}, {-it.first}) )    // 우선 second 기준으로 오름차순, 만약 second가 동일하다면 first로 내림차순
    println(ml4)


    println("====================5======================")
    val ml5 = mutableListOf("1", "32", "23", "4", "5")
    val ml6 = ml5.sortedWith( Comparator { a, b ->  (b+a).compareTo(a+b) })
    println(ml5)
    println(ml6)

    val ml7 = mutableListOf(2, 5, 3, 1, 4)
    val ml8 = ml7.sortedWith( Comparator { a, b -> b - a })
    println(ml7)
    println(ml8)
 * 출력
   * ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fc0oweh%2FbtqO0TFs1ts%2FZHShFqsMaDmxQ8UKnIdsLK%2Fimg.png)
### Collection
* |Collection|Immutable|Mutable|
  |:--:|:--:|:--:|
  |List|listOf|mutableListOf, arrayListOf|
  |Set|setOf|mutableSetOf, hashSetOf, linkedSetOf, sortedSetOf|
  |Map|mapOf|mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf|
---
### 문자열 (자바랑 큰차이없음)
* string
  * ```kotlin
    var x: String = "Kotlin"
    println(x.get(0))
    println(x.length)
    
##### 문자열 그대로 삽입(특수문자포함)
  * ```kotlin
    val heroes = """ #@#^#@^32490-0fdkgporekjpot34o6k346#$% """
    
    // 함수 같은 곳에서 사용하면 들여쓰기로 보기 좋게하기 가능 (| <--- 는 개행 시작점 알려주는 특수문자)
    var str2 = """
        |hi
        |my
        |name
        |is
        |sudeky
    """.trimMargin()
##### 자르기
  * ```kotlin
    var s = "hello, my friend. hello kotlin. hello world."
    var new_s = s.split(' ')
##### 정수 짜르기(스트링 변환없이)
  * ```kotlin
    var remain = 0
    var value = 84716
    while(value !=0 ){
        println(value % 10)
        value /= 10
    }
    // 6 1 7 4 8
##### 대체
  * ```kotlin
    var s = "hello, my friend. hello kotlin. hello world."
    var new_s = s.replace(' ', '+')
##### 확인
  * ```kotlin
    var s = "hello, my friend. hello kotlin. hello world."
    val start = s.startsWith("hello") // true
    val end =  s.endsWith("world2") // false
    val start = s.contains("kotlin")) // ture
