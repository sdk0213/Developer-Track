# MST(Minimum Cost Spanning Tree)
---
##### 사이클
* 정점의 위치 이상으로 탐색하는것, 쉽게말해서 A -> B -> C -> A -> B 처럼 무한적으로 재귀하며 탐색하는것
* 최소 신장 트리는 이 사이클을 넘지 않는것이 중요하기 때문에 내가 탐색한 정점 이상으로 탐색하지 않는다.

##### 경로는 여러가지가 될수있다.
* 탐색결과로 A -> B .... Y -> Z 로 가는경우와 A -> Z 로 직행하는 경우 전부다 탐색되기 떄문에 여러개가 존재할수있다.
---
### Kruskal(크루스칼) 알고리즘
* 사이클이 형성되지 않는 상태로만 정점들을 간선으로 연결한다.
* 사이클이 형성되어있는지 여부는 합집합 알고리즘을 사용한다.
* 간선의 비용들을 오름차순으로 정렬하며 낮은 비용의 간선부터 서로 정점을 연결하며 그래프를 생성하며 이때 간선끼리는 사이클이 형성되지 않게 만든다. 사이클이 형성되어있는지 여부는 합집합 알고리즘을 따른다. 
<details>
  <summary>Kruskal(크루스칼) 코드</summary>
  
```java
@Test
public void algorithmTest() {
    List<Edge> graph = new ArrayList<>();
    // 정점의 개수 : 7개
    int n = 7;
    // 간선의 개수 : 11개
    int m = 11;
    // 1 과 연결된곳
    graph.add(new Edge(new int[]{1,7}, 12));
    graph.add(new Edge(new int[]{1,4}, 28));
    graph.add(new Edge(new int[]{1,2}, 67));
    graph.add(new Edge(new int[]{1,5}, 17));
    // 2 과 연결된곳
    graph.add(new Edge(new int[]{2,4}, 24));
    graph.add(new Edge(new int[]{2,5}, 62));
    // 3 과 연결된곳
    graph.add(new Edge(new int[]{3,5}, 20));
    graph.add(new Edge(new int[]{3,6}, 37));
    // 4 과 연결된곳
    graph.add(new Edge(new int[]{4,7}, 13));
    // 5 과 연결된곳
    graph.add(new Edge(new int[]{5,6}, 45));
    graph.add(new Edge(new int[]{5,7}, 73));

    // 오름차순으로 정렬
    graph.sort((a, b) -> {
       if(a.cost > b.cost) return 1;
       else if(a.cost < b.cost) return -1;
       else return 0;
    });

    // 자기 자신을 부모노드로 만듬
    int[] parent = new int[n+1];
    for(int i = 1 ; i < parent.length ; i++){
        parent[i] = i;
    }

    // 전체 비용 초기화
    int totalCost = 0;
    for(int i = 0 ; i < graph.size() ; i++){
        // 사이클이 돌지 않는다면 (= 같은 부모노드를 가지지 않았다면)
        if(!isSameParent(parent, graph.get(i).getNode()[0], graph.get(i).getNode()[1])){
            totalCost += graph.get(i).getCost();
            // 두개의 정점을 간선으로 연결
            unionParent(parent, graph.get(i).getNode()[0], graph.get(i).getNode()[1]);
        }
    }

    System.out.println("totalCost: " + totalCost); // 123

}

// 간선 클래스
class Edge{
    int[] node;
    int cost;
    public Edge(int[] node, int cost){
        this.node = node;
        this.cost = cost;
    }

    int[] getNode(){
        return node;
    }

    int getCost(){
        return cost;
    }
}

public int getParent(int[] parent, int x){
    if(parent[x] == x) {
        return x;
    }
    else return getParent(parent, parent[x]);
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
```
  
</details>  
  
### Prim(프림) 알고리즘
*
