# 맵(Map) - [출처 wikidocs](https://wikidocs.net/208)
* 자료형으로 Associative array(Map, Dictionary, Symbol Table), Hash라고도 불린다.
### 특징
* 키는 중복불가능, 값은 중복가능
* 순차를 보장하지 않음
* 효율적이 검새 지원
* 맵은 **사전과 비슷**하다.
* |key|value|
  |:---:|:---:|
  |people|사람|
  |baseball|야구|
---  
### HashMap
* ![](https://d2.naver.com/content/images/2015/06/helloworld-831311-1.png)
* hashtable과 hashmap은 성능과 기능에서 hashtable이 하위호환성을 제공해준다는것 빼고는 없다.
* ```java
  HashMap<String, String> map = new HashMap<String, String>();
  map.put("people","사람"); // put
  map.put("baseball","야구");
  
  System.out.println(map.get("people")); // get
  
  System.out.println(map.containsKey("people"));  // contains keys - check value
  
  System.out.println(map.remove("people")); // remove key
  
  System.out.println(map.size()); // return map size
##### 코틀린
* Immutable(default)
  ```kotlin
  val map1: Map<String, Int> = mapOf("One" to 1, "Two" to 2, "Three" to 3)
  val map2: Map<String, Int> = mapOf(Pair("Four", 4), Pair("Five", 5))
  
  println(map1)
  println(map2)
  println(map1.get("One"))
  println(map1.get("Five"))
  
  // {One=1, Two=2, Three=3}
  // {Four=4, Five=5}
  // 1
  // 5
* Mutable(mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf..)
  ```kotlin
  val map1: MutableMap<String, Int> = mutableMapOf("One" to 1)
  val map2: HashMap<String, Int> = hashMapOf("One" to 1, "Two" to 2, "Three" to 3)

  println(map1)
  println(map2)
  
  map1.put("Ten", 10)
  map2.set("Two", 20)
  map2.remove("Three")
  
  println(map1)
  println(map2)
  
  // {One=1}
  // {Two=2, Three=3, One=1}
  // {One=1, Ten=10}
  // {Two=20, One=1}
