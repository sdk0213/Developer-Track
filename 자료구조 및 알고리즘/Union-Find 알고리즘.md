# Union-Find
* Union-Find는 대표적인 그래프 알고리즘이며 서로소 집합(Disjoint-Set) 알고리즘이라고도 부른다.(서로소 집합과 동일하다)
* 부모노드 상태
  * 초기 상태
    |노드|1|2|3|4|5|6|7|8|9|10|
    |:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
    |부모노드|1|2|3|4|5|6|7|8|9|10|
  * 아래 코드처럼 연결된 후
    |노드|1|2|3|4|5|6|7|8|9|10|
    |:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
    |부모노드|1|1|3|3|1|6|7|8|9|10|

* ```java
  @Test
  public void alogorithmTest() {
      int[] parent = new int[11];
      for (int i = 1; i < 11; i++) {
          parent[i] = i;
          // 자기 자신을 부모노드로 같은 노드를 생성 -> 아무 간선도 없는 정점을 10개 생성한다고 보면된다.
      }

      unionParent(parent, 1, 2); // 1번과 2번 간선을 연결하며 부모노드를 둘 중 가장 낮은 값으로 변경
      unionParent(parent, 2, 5); // 2번과 5번 간선을 연결하며 부모노드를 둘 중 가장 낮은 값으로 변경
      unionParent(parent, 3, 4); // 3번과 4번 간선을 연결하며 부모노드를 둘 중 가장 낮은 값으로 변경


      // (1) -- (2) -- (5)  <------ 1번, 2번, 5번 정점이 간선으로 연결된상태
      // (3) -- (4)         <------ 3번, 4번 정점이 간선으로 연결된상태
      // (6) (7) (8) (9) (10) <---- 어느 정점도 서로 연결되어있지 않는 상태

      System.out.println("5의 부모노드는: " + getParent(parent,5)); // 1
      System.out.println("4의 부모노드는: " + getParent(parent,4)); // 3

      System.out.println("2와 5는 같은 부모를 가졌나요?(서로 연결이 되어있나요?): " + isSameParent(parent, 2, 5)); // true
      System.out.println("5와 4는 같은 부모를 가졌나요?(서로 연결이 되어있나요?): " + isSameParent(parent, 5, 4)); // false

  }

  public int getParent(int[] parent, int x){
      if(parent[x] == x) {
          return x;
      }
      else return parent[x] = getParent(parent, parent[x]);
      // 부모 노드의 값과 자신이 다르다면 재귀 호출
  }

  // 두 부모 노드를 합치는 함수
  public void unionParent(int[] parent, int a, int b)
  {
      a = getParent(parent, a); // a의 부모 노드 확인
      b = getParent(parent, b); // b의 부모 노드 확인
      if (a < b) parent[b] = a; // 더 작은 값을 부모 노드로 지정
      else parent[a] = b;
  }

  // 같은 부모를 가지는지 확인
  public boolean isSameParent(int[] parent, int a, int b)
  {
      a = getParent(parent, a); // a의 부모 노드 확인
      b = getParent(parent, b); // b의 부모 노드 확인
      if (a == b) return true; // 같은 부모를 가진다면 1을 리턴
      else return false; // 다르다면 0리턴
  }
