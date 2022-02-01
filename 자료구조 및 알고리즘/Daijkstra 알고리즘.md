### 인접행렬 + 인접리스트 형태
* ||Dijkstra|Floyd Warshall|
  |:--:|:--:|:--:|
  |용도|한 정점 → 다른 모든 정점 최단 거리|모든 정점 → 모든 정점 최단 거리|
  |공간복잡도|인접행렬 O(V^2), 인접리스트 O(V+E)|O(V^2)|
  |시간복잡도|인접행렬 O(V^2), 인접리스트 + 우선순위 큐 O((V+E)logV)|O(V^3)|
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


      // 인접 리스트
      List<ArrayList<Node>> adjList = new ArrayList<>();
      int n = 6;
      for (int i = 0; i < n; i++) {
          adjList.add(new ArrayList<>());
      }

      adjList.get(0).add(new Node(1, 2));
      adjList.get(0).add(new Node(2, 5));
      adjList.get(0).add(new Node(3, 1));

      adjList.get(1).add(new Node(0, 2));
      adjList.get(1).add(new Node(2, 3));
      adjList.get(1).add(new Node(3, 2));

      adjList.get(2).add(new Node(0, 5));
      adjList.get(2).add(new Node(1, 3));
      adjList.get(2).add(new Node(3, 3));
      adjList.get(2).add(new Node(4, 1));
      adjList.get(2).add(new Node(5, 5));

      adjList.get(3).add(new Node(0, 1));
      adjList.get(3).add(new Node(1, 2));
      adjList.get(3).add(new Node(2, 3));
      adjList.get(3).add(new Node(4, 1));

      adjList.get(4).add(new Node(2, 1));
      adjList.get(4).add(new Node(3, 1));
      adjList.get(4).add(new Node(5, 2));

      adjList.get(5).add(new Node(2, 5));
      adjList.get(5).add(new Node(4, 2));

      dijkstra_adjMatrix(0, adjMatrix);
      dijkstra_adjList(0, adjList);

  }

  class Node {
      int idx;
      int cost;

      public Node(int idx, int cost) {
          this.idx = idx;
          this.cost = cost;
      }
  }


  public void dijkstra_adjMatrix(int start, int[][] matrix){

      int n = matrix.length;

      int[] dist = new int[n];
      for (int i = 0; i < matrix[0].length; i++) {
          dist[i] = matrix[0][i];
      }

      boolean[] visited = new boolean[n];
      visited[0] = true;

      for(int i = 0 ; i < n-1 ; i++){
          int min = INF;
          int cur_idx = 0;
          for(int j = 0; j < n; j++) {
              if(dist[j] < min && !visited[j]) {
                  min = dist[j];
                  cur_idx = j;
              }
          }
          // 방문하였으니까 방문처리
          visited[cur_idx] = true;

          for(int j = 0 ; j < n ; j++){
              if(!visited[j]){
                  if(dist[cur_idx] + matrix[cur_idx][j] < dist[j]) {
                      dist[j] = dist[cur_idx] + matrix[cur_idx][j];
                  }
              }
          }
      }

      System.out.println(Arrays.toString(dist)); // [0, 2, 3, 1, 2, 4]

  }


  // https://sskl660.tistory.com/59
  public void dijkstra_adjList(int start, List<ArrayList<Node>> adjList){
      int n = adjList.size();
      int[] dist = new int[n];
      Arrays.fill(dist, INF);
      dist[start] = 0;

      Queue<Node> queue = new PriorityQueue<>((a, b) -> {
          if(a.cost > b.cost) return 1;
          else if(a.cost < b.cost) return -1;
          else return 0;
      });
      queue.offer(new Node(start, 0));

      while (!queue.isEmpty()) {
          Node cur_node = queue.poll();

          // 아래 주석된 코드는 목표 정점이 구해졌다면 빠르게 탈출할 수 있는 조건이다.
          //			if (curNode.idx == end) {
          //				System.out.println(dist[curNode.idx]);
          //				return;
          //			}

          if(dist[cur_node.idx] < cur_node.cost) continue;

          for (int i = 0; i < adjList.get(cur_node.idx).size(); i++) {

              Node nxt_node = adjList.get(cur_node.idx).get(i);

              if (cur_node.cost + nxt_node.cost < dist[nxt_node.idx]) {
                  dist[nxt_node.idx] = cur_node.cost + nxt_node.cost;
                  queue.offer(new Node(nxt_node.idx, dist[nxt_node.idx]));
              }
          }
      }

      System.out.println(Arrays.toString(dist));
  }
