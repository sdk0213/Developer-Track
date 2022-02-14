### 위상정렬
* DAG(Directed Acyclic Graph)(= 싸이클이없는 방향그래프) 에서 사용가능하다.
* 순서가 있는 일을 차례대로 수행하는 알고리즘이다. 쉽게 말해 캐릭터의 스킬을 A 부터 Z 까지 찍을때 선행스킬같은것이 존재하는데 이때 순서에 맞게 차례대로 수행하는 알고리즘이라고 할수있다.
---
### 코드 - [출처 - bcp0109님(뱀귤브로그)의 위상정렬](https://bcp0109.tistory.com/21)
* 간선의 개수가 0 인것만을 bfs 로 queue 에 넣으며 탐색하며 간선의 개수가 0 이 아닌것을 탐색했다면 간선을 하나씩 줄임
* 시작점은 무조건 정해야되며 (시작점이 정해져있는 그래프에서 시작하기 때문) 여기서는 1로 봄
* ![](https://github.com/ParkJiwoon/Algorithm/blob/master/Algorithm/image/topological_srot_1.png?raw=true)
* ```java
  @Test
  public void topologicalSort() {
      List<int[]> graph = new ArrayList<>();

      graph.add(new int[]{0}); // 시작을 1로 보기위한 의미없는 값
      graph.add(new int[]{2, 3}); // 1 -> 2, 1 -> 3
      graph.add(new int[]{4, 5}); // 2 -> 5, 2 -> 5
      graph.add(new int[]{4, 7}); // 3 -> 4, 3 -> 5
      graph.add(new int[]{6}); // 4 -> 6
      graph.add(new int[]{4, 6}); // 5 -> 4, 5 -> 6
      graph.add(new int[]{});
      graph.add(new int[]{});

      // 들어오는 간선
      int[] inDegree = new int[graph.size()];

      for (int i = 1; i < graph.size(); i++) {
          for(int j : graph.get(i)){
              inDegree[j]++;
          }
      }

      //  inDegree 출력
      //  System.out.println(Arrays.toString(inDegree));

      topologicalSort(inDegree, graph);

  }

  public void topologicalSort(int[] inDegree, List<int[]> graph) {
      Queue<Integer> q = new LinkedList<>();
      Queue<Integer> result = new LinkedList<>();


      // 0 인노드 q 에다가 넣기
      for (int i = 1; i < graph.size(); i++) {
          if (inDegree[i] == 0) {
              q.offer(i);
          }
      }

      while (!q.isEmpty()) {
          int node = q.poll(); // q 에서 꺼내서
          result.offer(node); // 결과에 넣고

          for (int i : graph.get(node)) { // 해당 q 에서 연결된 정점의 간선 개수를 하나 제거
              inDegree[i]--;

              if (inDegree[i] == 0) { // 제거하는 와중에 0인 간선은 다시 큐에 넣기..
                  q.offer(i);
              }
          }
      }

      System.out.println(result.toString());
      
      // 1 -> 2 -> 3 -> 5 -> 7 -> 4 -> 6

  }
