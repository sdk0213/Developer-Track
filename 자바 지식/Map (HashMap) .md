맵(Map) - [출처 wikidocs](https://wikidocs.net/208)
===
* 자료형으로 Associative array(Map, Dictionary, Symbol Table), Hash라고도 불린다.
* 맵은 **사전과 비슷**하다.
* |key|value|
  |:---:|:---:|
  |people|사람|
  |baseball|야구|
  
> HashMap
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
* 
