# collection - [출처](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=yuyyulee&logNo=221239956765)
## Mutable vs Immutable
* 코틀린은 변경가능한 컬렉션과 불가능한 컬렉션이 구분된다.
## Collection 사전 지식
* |종류|순처적|키 중복|값 중복|
  |:--:|:--:|:--:|:--:|
  |List|O|O|O|
  |Set|X|X|X|
  |Map|X|X|O|
##### Set 의 대표적인 세가지
* |종류|데이터 관리|
  |:--:|:--:|
  |HashSet|순서 보장안됨|
  |TreeSet|오름차순으로 보장|
  |LinkedHashSet|입력한 순서대로 보장|
---
### Type
* |Collection|Immutable|Mutable|
  |:--:|:--:|:--:|
  |List|listOf|mutableListOf, arrayListOf|
  |Set|setOf|mutableSetOf, hashSetOf, linkedSetOf, sortedSetOf|
  |Map|mapOf|mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf|
---
### Function
* 기본
  |Function|return|ex|
  |:--:|:--:|:--:|
  |size()|크기||list.size|
  |contains()|단어 포함여부|list.contains("text")|
  |get()|index 반환|list.get(2) //"text"|
  |isEmpty()|비어있는지 체크||
  |subList()|범위 반환|list.subList(1,3)|
* 확장
  |Extention Function|return|기능|ex|
  |:--:|:--:|:--|:--:|
  |all|Boolean|모든 항목이 지정되 조건에 만족|list.all { it.startsWith("T") }|
  |any|Boolean|하나이상의 항목이 지정된 조건에 만족|list.any { it.startsWith("T") }|
  |asReversed|List|리스트의 역순 리스트 반환|list.asReversed()|
  |containsAll|Boolean|지정된 컬렉션의 모든 요속 리스트 내에 있는지 반환|list.containsAll(listOf("One","Two")) // true|
  |count|Int|조건과 일치하는 항목의 개수|list.count { it.startsWith("F") }|
  |drop|List|처음 n개의 항목을 제외한 리스트를 반환|list.drop(2)  // [Three, Four, Five]|
  |dropLast|List|마지막 n개의 항목을 제외한 리스트를 반환|list.dropLast(2)  // [One, Two, Three]|
  |filter|List|지정된 조건과 일치하는 항목만 포함하는 리스트를 반환|list.filter { it.startsWith("F") }  // [Four, Five]|
  |filterNot|List|지정된 조건과 일치하지 않는 항목만 포함하는 리스트를 반환|list.filterNot { it.startsWith("T") }  // [One, Four, Five]|
  |find|T?|지정된 조건과 일치하는 첫 번째 항목을 반환. 일치하는 항목이 없을 경우 null을 반환|list.find { it.startsWith("T") }  // Two|
  |findLast|T?|지정된 조건과 일치하는 마지막 항목을 반환. 일치하는 항목이 없을 경우 null을 반환|list.findLast { it.startsWith("T") }  // Three|
  |first|T|첫 번째 항목을 반환|list.first()  // One|
  |forEach|Unit|각 항목에 대해 지정된 작업을 수행|list.forEach { print("$it ") }  // One Two Three Four Five|
  |forEachIndexed|Unit|각 항목에 순차적으로 인덱스를 제공하며, 지정된 작업을 수행|list.forEachIndexed { i, e -> print("$e(${i+1}) ") }  // One(1) Two(2) Three(3) Four(4) Five(5)|
  |Last|T|마지막 항목을 반환|list.last()  // Five|
  |map|List<R>|모든 항목에 지정된 변환 함수가 적용된 결과 리스트를 반환|list.map { it + "!" }  // [One!, Two!, Three!, Four!, Five!]|
  |mapIndexed|List<R>|인덱스와 함께 지정된 변환 함수가 적용된 결과 리스트를 반환|list.mapIndexed { i, e -> e + "(${i+1})" }  // [One(1), Two(2), Three(3), Four(4), Five(5)]|
  |max|T?|값이 가장 큰 항목을 반환. 항목이 없을 경우 null을 반환|list.max()  // Two|
  |min|T?|값이 가장 작은 항목을 반환. 항목이 없을 경우 null 반환|list.min()  // Five|
  |minus|List<T>|지정된 항목이 제외된 리스트를 반환. remove()  메서드와 달리 새로운 리스트를 만들어 반환한다는 것에 유의. 항목 대신 배열, 컬렉션, 범위 등이 들어갈 수 있음|list.minus("Five")  // [One, Two, Three, Four]|
  |plus|List<T>|지정된 항목이 추가된 리스트를 반환. add() 메서드와 달리 새로운 리스트를 만들어 반환한다는 것에 유의. 항목 대신 배열, 컬렉션, 범위 등이 들어갈 수 있음|list.plus(listOf("Six", "Seven"))  // [One, Two, Three, Four, Five, Six, Seven]|
  |shuffled|List<T>|순서가 랜덤하게 섞인 새로운 리스트를 반환|list.shuffled()  // [Two, Four, Five, One, Three]|
  |sorted|List<T>|Comparable 인터페이스에 구현된 순서대로 정렬된 리스트를 반환. sort() 메서드와 달리 새로운 리스트를 반환한다는 것에 유의|list.sorted()  // [Five, Four, One, Three, Two]|
  |sortedBy|List<T>|지정한 람다 식에 의한 순서대로 정렬된 리스트를 반환. sortBy() 메서드와 달리 새로운 리스트를 반환한다는 것에 유의|list.sortedBy { it }  // [Five, Four, One, Three, Two]|
  |take|List<T>|첫 항목부터 n개의 항목을 반환|list.take(2)  // [One, Two]|
* \+ Mutable
  |Mutable Function|return|기능|ex|
  |:--:|:--:|:--|:--:|
  |add|Boolean|리스트에 항목을 추가한다|list.add("Six")|
  |clear()||모든 항목 제거|list.clear()|
  |remove|Boolean|하나의 항목을 제거한다|list.remove("Four")|
  |removeAll|Boolean|지정된 컬렉션에 포함된 항목을 모두 제거한다|list.removeAll( listOf("Two", "Four"))|
  |retainAll|Boolean|지정된 컬렉션에 포함된 항목만 남겨두고 모두 제거한다|list.retainAll( listOf("Two", "Four", "Six"))|
   
  
