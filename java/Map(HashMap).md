맵(Map)
===
* 자료형으로 Associative array, Hash라고도 불린다.
* 맵은 **사전과 비슷**하다.
* |key|value|
  |:---:|:---:|
  |people|사람|
  |baseball|야구|
> HashMap
* ```java
  HashMap<String, String> map = new HashMap<String, String>();
  map.put("people","사람");
  map.put("baseball","야구");
  
  System.out.println(map.get("people"));
