# Set
---
### 특징
* 객체를 중복해서 저장 불가능
* 저장순서 보장안함(인덱스로 관리안함)
* 데이터 검색은 iterator() 메서드로 진행
---
### HashSet
* 데이터를 중복 저장할 수 없고 순서를 보장하지 않는다. 
* ```java
  Set<String> set = new HashSet<String>();
		set.add("one"); // 데이터 저장(추가)
		set.add("two");
		set.add("three");
		set.add("one");
		set.add("two");
		set.add("4");
		set.add("5");
		set.add("six");
  ....
  
  // 중복 저장이 안됨
  // 순서 보장안됨
  // ---출력결과---
  // six
  // 4
  // 5
  // one
  // two
  // three
---
### TreeSet
* HashSet + 오름차순 정렬
---
### LinkedHashSet
* HashSet + 입력된 순서대로 정렬
