* BFS
  * 넓이 우선 탐색

* 전체 노드 개수: V 간선의 개수: E
* 기본적으로 이미 방문한 정점을 다시 방문하지 않아야 함
* 시간복잡도
  * 인접 리스트로 표현된 그래프: O(V+E)
    * 적은 숫자의 간선에서 사용하기
  * 인접 행렬로 표현된 그래프: O(V^2)
    * 많은 숫자의 간선에서 사용하기
* 서로 간선이 존재하는지 확인하고 싶을때는?
  * 인접행렬 -> [i][j] 로 바로 파악가능 -> O(1)
  * 링크드리스트 -> [i] 방문후 [j] 검색하여야함 -> O(V의 E 개수) == O(i의 j개수)
* 정점들의 연결정보를 확인하고 싶을때는?
  * 인접행렬 -> 전부다 출력하여야함
  * 링크드리스트 -> [i] 번 출력하면된다. -> O(E)


```java
public class javaUnitTest {

    @Test
    public void solution() {

        int[][] linkedGraph = {
                {}, // 편의상 1부터 판단하기위해 0이라는 의미없는 정점을 추가
                {0,2,3,8}, // 그렇기때문에 정점은 1번부터 시작
                {0,1,6,8}, // 편의상 1부터 계산하기위해 배열 앞에 0을 넣어버림
                {0,1,5},
                {0,5,7},
                {0,3,4,7},
                {0,2},
                {0,4,5},
                {0,1,2}
        };

        int[][] matrixGraph = { // 편의상 1부터 판단하기위해 0이라는 의미없는 정점을 추가
                //        ▼ (실제 그래프 시작지점)
                      {0, 0, 0, 0, 0, 0, 0, 0, 0},
/*(그래프 시작지점) ▶ */  {0, 0, 1, 1, 0, 0, 0, 0, 1},
                      {0, 1, 0, 0, 0, 0, 1, 0, 1},
                      {0, 1, 0, 0, 0, 1, 0, 0, 0},
                      {0, 0, 0, 0, 0, 1, 0, 1, 0},
                      {0, 0, 0, 1, 1, 0, 0, 1, 0},
                      {0, 0, 1, 0, 0, 0, 0, 0, 0},
                      {0, 0, 0, 0, 1, 1, 0, 0, 0},
                      {0, 1, 1, 0, 0, 0, 0, 0, 0}
        };
        // 방문처리를 위한 boolean배열 선언
        boolean[] visited = new boolean[9];

        System.out.println("bfs_linkedGraph: ");
        System.out.println(bfs_linkedGraph(1, linkedGraph));
        System.out.println();
        System.out.println("bfs_matrixGraph: ");
        System.out.println(bfs_matrixGraph(1, matrixGraph));
    }

    public String bfs_linkedGraph(int start, int[][] graph){

        // 방문했는지 표시하기위함
        StringBuilder sb = new StringBuilder();

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);

        boolean[] visited = new boolean[graph.length + 1];
        visited[start] = true;

        while(!queue.isEmpty()){
            int myPos = queue.poll();

            sb.append(myPos).append(" -> ");

            for(int i = 1 ; i < graph[myPos].length ; i++){
                int temp = graph[myPos][i];

                // 방문하지 않았다면
                if(!visited[temp]){
                    queue.offer(graph[myPos][i]);
                    visited[temp] = true;
                }
            }
        }

        return sb.toString();

    }

    public String bfs_matrixGraph(int start, int[][] graph){
        StringBuilder sb = new StringBuilder();

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);

        boolean[] visited = new boolean[graph.length + 1];
        visited[start] = true;

        while(!queue.isEmpty()){
            int myPos = queue.poll();
            sb.append(myPos + " -> ");

            for(int i = 1 ; i < graph.length ; i++){
                int temp = graph[myPos][i];
                if(temp == 1 && !visited[i]){
                    queue.offer(i);
                    visited[i] = true;
                }
            }

        }

        return sb.toString();

    }
}
