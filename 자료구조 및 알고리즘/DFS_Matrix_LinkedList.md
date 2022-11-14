* DFS
  * 깊이 우선 탐색
* ![dfs-vs-bfs](https://user-images.githubusercontent.com/51182964/149276674-29eac22f-974e-4c59-94e2-fd7559e20319.gif)
* <img width="771" alt="image" src="https://user-images.githubusercontent.com/51182964/201645956-9aa65d19-e34c-4cc1-ada3-395ac84ff4d7.png">


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
                //              ▼ (실제 그래프 시작지점)
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
/*(그래프 시작지점) ▶ */    {0, 0, 1, 1, 0, 0, 0, 0, 1},
                            {0, 1, 0, 0, 0, 0, 1, 0, 1},
                            {0, 1, 0, 0, 0, 1, 0, 0, 0},
                            {0, 0, 0, 0, 0, 1, 0, 1, 0},
                            {0, 0, 0, 1, 1, 0, 0, 1, 0},
                            {0, 0, 1, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 1, 1, 0, 0, 0},
                            {0, 1, 1, 0, 0, 0, 0, 0, 0}
        };

        System.out.println("dfs_linkedGraph: ");
        System.out.println(dfs_linkedGraph(1, linkedGraph));
        System.out.println();
        System.out.println("dfs_matrixGraph: ");
        System.out.println(dfs_matrixGraph(1, matrixGraph));
    }

    public String dfs_linkedGraph(int start, int[][] graph){

        boolean[] visited = new boolean[graph.length];

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        visited[start] = true;

        StringBuilder sb = new StringBuilder(start + " -> ");

        boolean flag;
        while(!stack.isEmpty()){
            int myPos = stack.peek();
            flag = false;

            for(int i = 1 ; i < graph[myPos].length ; i++){
                int temp = graph[myPos][i];
                if(!visited[temp]){
                    visited[temp] = true;
                    flag = true;
                    sb.append(temp).append(" -> ");
                    stack.push(temp);
                    break;
                }
            }

            if(!flag){
                stack.pop();
            }

        }

        return sb.toString();
    }

    public String dfs_matrixGraph(int start, int[][] graph) {

        boolean[] visited = new boolean[graph.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        StringBuilder sb = new StringBuilder(start + " -> ");

        boolean flag;
        while(!stack.isEmpty()) {
            int myPos = stack.peek();
            flag = false;
            for(int i = 1; i < graph.length; i++) {
                if(graph[myPos][i] == 1 && !visited[i]) {
                    stack.push(i);
                    sb.append(i).append(" -> ");
                    visited[i] = true;
                    flag = true; // 어느곳도 방문하지 못했다면 나의 위치를 한단계 back
                    break;
                }
            }

            if(!flag) {
                stack.pop();
            }
        }

        return sb.toString();
    }
}
