### 재귀를 사용한 DFS 탐색
* DFS 같은경우는 재귀와 매우 비슷하고 직관적으로 이해가 쉬우며 더불어 코드 또한 짧아서 이를 사용하는것이 더 유리
* <img width="771" alt="image" src="https://user-images.githubusercontent.com/51182964/201645867-17dfc778-bc9f-4b25-9b0f-f95eb7b90cca.png">

---
```java
// 0 번 트랙은 실제 트랙에서 1번으로 취급하면 된다.

public class javaUnitTest {

    int[][] linkedGraph = {
            {1,2,7},
            {0,5,7},
            {0,4},
            {4,6},
            {2,3,6},
            {1},
            {3,4},
            {0,1}
    };

    int[][] matrixGraph = {
            {0, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0}
    };

    @Test
    public void solution() {

        System.out.println("dfs_linkedGraph: ");
        dfs_graph(0);
        System.out.println();
        System.out.println("dfs_matrixGraph: ");
        dfs_matrixGraph(0);
    }

    boolean[] visited = new boolean[linkedGraph.length];
    boolean[] visited_matrix = new boolean[matrixGraph.length];

    public void dfs_graph(int myPos){
        // 현재 노드를 방문 처리
        visited[myPos] = true;
        System.out.print(myPos + " -> ");
        // 현재 노드와 연결된 다른 노드를 재귀적으로 방문
        for (int i = 0; i < linkedGraph[myPos].length; i++) {
            int temp = linkedGraph[myPos][i];
            if(!visited[temp]){
                dfs_graph(temp);
            }
        }
    }

    public void dfs_matrixGraph(int myPos){
        // 현재 노드를 방문 처리
        visited_matrix[myPos] = true;
        System.out.print(myPos + " -> ");
        // 현재 노드와 연결된 다른 노드를 재귀적으로 방문
        for (int i = 0; i < matrixGraph.length; i++) {
            int temp = matrixGraph[myPos][i];
            if(temp == 1 && !visited_matrix[i]){
                dfs_matrixGraph(i);
            }
        }
    }
}

// 출력결과
// dfs_linkedGraph: 
// 0 -> 1 -> 5 -> 7 -> 2 -> 4 -> 3 -> 6 -> 
// dfs_matrixGraph: 
// 0 -> 1 -> 5 -> 7 -> 2 -> 4 -> 3 -> 6 ->
