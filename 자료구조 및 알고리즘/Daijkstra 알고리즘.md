### 인접행렬
* 비용이 없어서 0 이라고 표시하는것인지 간선이 없어서 0 이라고 표시하는지에 대해 알수없다.

* ```java
  private int INF = Integer.MAX_VALUE; // =infinity distance

  class Node implements Comparable<Node> {
      int index;
      int cost;

      public Node(int index, int cost) {
          this.index = index;
          this.cost = cost;
      }

      @Override
      public int compareTo(Node o) {
          if (this.cost > o.cost) {
              return 1;
          } else if (this.cost < o.cost) {
              return -1;
          } else {
              return 0;
          }
      }
  }

  @Test
  public void daijkstraAlgorithm() {

      // 정점의 개수
      int n = 6;
      // 전체 그래프 (인접행렬 형태)
      int[][] matrix = {
              {0, 2, 5, 1, INF, INF},
              {2, 0, 3, 2, INF, INF},
              {5, 3, 0, 3, 1, 5},
              {1, 2, 3, 0, 1, INF},
              {INF, INF, 1, 1, 0, 2},
              {INF, INF, 5, INF, 2, 0},
      };
      // 방문 하였는지 여부
      boolean[] visited = new boolean[n];
      // 1번에서부터 n 번까지의 최소거리
      int[] minDistance = new int[n];
      Arrays.fill(minDistance, INF);

      // 나 자신은 방문처리 함
      visited[0] = true;
      minDistance[0] = 0;

      // 우선순위 큐에 다가 넣어서 최소비용의 값만 바로바로 꺼낼수 있도록 수정하기
      Queue<Node> distanceQueue = new PriorityQueue<>();
      for (int i = 0; i < matrix[0].length; i++) {
          if (!visited[i] && matrix[0][i] != INF) {
              minDistance[i] = matrix[0][i];
              distanceQueue.offer(new Node(i, matrix[0][i]));
          }
      }

      while (!distanceQueue.isEmpty()) {
          Node node = distanceQueue.poll();
          int minPos = node.index;

          // 방문하였으니까 방문처리
          visited[minPos] = true;

          for (int k = 0; k < n; k++) {
              // 거리가 0 인것은 확인할 필요가없는것은 이미 방문처리하여서 확인하지 않기 때문이다.
              // 내가 갈수있는곳중 == code{matrix[minPos][k] != Integer.MAX_VALUE}
              if (!visited[k] && matrix[minPos][k] != Integer.MAX_VALUE) {
                  // 현재 방문한곳에서 방문하지 않았던 지역을 확인하였는데
                  // 현재 방문한곳까지의 거리와 현재 방문한곳을 거쳐서 이동한 거리가 내가 알고있는 최소거리보다 더 가깝다면 그 값을 갱신한다.
                  if (minDistance[minPos] + matrix[minPos][k] < minDistance[k]) {
                      minDistance[k] = minDistance[minPos] + matrix[minPos][k];
                      distanceQueue.offer(new Node(k, minDistance[k]));
                  }
              }
          }
      }

          System.out.println(Arrays.toString(minDistance)); // [0, 2, 3, 1, 2, 4]


  }
  
* 
