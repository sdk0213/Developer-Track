# Floyd Warshall
* ||Dijkstra|Floyd Warshall|
  |:--:|:--:|:--:|
  |용도|한 정점 → 다른 모든 정점 최단 거리|모든 정점 → 모든 정점 최단 거리|
  |공간복잡도|인접행렬 O(V^2), 인접리스트 O(V+E)|O(V^2)|
  |시간복잡도|인접행렬 O(V^2), 인접리스트 + 우선순위 큐 O((V+E)logV)|O(V^3)|
* Floyd Warshall 알고리즘
  * 모든 정점 -> 최단거리를 구하는 알고리즘
* V의 크기가 크다(500 이상)면 가급적 피하는 것이 좋고 목적에 맞게 변형하거나 목적에 맞게 다익스트라를 사용하여서 값을 구하여야한다.
* ```java
  private int INF = Integer.MAX_VALUE / 2; // =infinity distance

  @Test
  public void daijkstraAlgorithm() {

      // 인접 행렬
      int[][] adjMatrix = {
              {0, 2, 5, 1, INF, INF},
              {2, 0, 3, 2, INF, INF},
              {5, 3, 0, 3, 1, 5},
              {1, 2, 3, 0, 1, INF},
              {INF, INF, 1, 1, 0, 2},
              {INF, INF, 5, INF, 2, 0},
      };

      int n = 6;

      floyd(n, adjMatrix);


  }

  public void floyd(int n, int[][] floyd){
      // k : 거쳐가는 노드 (기준)
      for(int k=0; k<n; k++) {
          // i : 출발 노드
          for(int i=0; i<n; i++) {
              // j : 도착 노드
              for(int j=0; j<n; j++) {
                  // i에서 j로 가는 최소 비용 VS
                  //         i에서 노드 k로 가는 비용 + 노드 k에서 jY로 가는 비용
                  if(floyd[i][k] + floyd[k][j] < floyd[i][j]) {
                      floyd[i][j] = floyd[i][k] + floyd[k][j];
                  }
              }
          }
      }

      for(int i=0; i<n; i++) {
          for(int j=0; j<n; j++) {
              System.out.print(floyd[i][j]+ " ");
          }
          System.out.println();
      }
  }
  
  // result:
  // 0 2 3 1 2 4 
  // 2 0 3 2 3 5 
  // 3 3 0 2 1 3 
  // 1 2 2 0 1 3 
  // 2 3 1 1 0 2 
  // 4 5 3 3 2 0 
    
    
