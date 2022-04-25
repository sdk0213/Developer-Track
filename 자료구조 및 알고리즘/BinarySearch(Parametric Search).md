### 이진탐색
* 정렬된 알고리즘에서 사용되며 선형 탐색에 비해 속도가 빠르다. 하지만 정렬이 되어있어야한다는 전제조건이 붙는다.
* 시간복잡도: O(logN)

* ```java

  @Test
  ... Test() {
  
      int[] arr = {1, 3, 5, 7, 8, 10, 20, 35, 99, 100};
      System.out.println(binSearch(7, 0, arr.length - 1, arr)); // 7 검색됨
      System.out.println(binSearch(100, 0, arr.length - 1, arr)); // 100 검색됨
      System.out.println(binSearch(30, 0, arr.length - 1, arr)); // -1.. 검색안됨

  }

  public int binSearch(int[] arr, int key) {
      // 맨처음 low = 0, high는 배열의 끝이다.
      int low = 0;
      int high = arr.length - 1;

      while (low <= high) {
          int mid = (low + high)/2; // mid 값을 계산.

          if (key > arr[mid]) // 키값이 더 크면 왼쪽을 버린다.
              low = mid + 1;
          else if (key < arr[mid]) // 키값이 더 작으면 오른쪽을 버린다.
              high = mid - 1;
          else
              return arr[mid]; // key found
      }
      return - (low + 1);  // key not found
  }

### Upper,Lower Bound binary Search
* upper 와 lower 의 필요성
  * {2,2,3,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,7,8,8,9} 과같은 배열에서 만약에 '5'의 인덱스를 검색할경우
    * 단순 이진탐색 -> 배열의 상태에 따라 다른 인덱스를 내놓을수 있다.
    * lowerBound -> 5가 시작되는 첫항을 검색
    * upperBound 로 4를 검색하며 -> 5가 시작되는 첫항을 검색
* upper -> 무조건 큰 수의 index
* lower -> 같거나 큰 수의 index
* ```java
  @Test
  public void Test(){
      int[] arr = {1, 3, 5, 7, 8, 10, 20, 35, 99, 100};
      System.out.println(binSearchBound(arr, 20, true)); // 35 upper
      System.out.println(binSearchBound(arr, 20, false)); // 20 lower

  }


  public int binSearchBound(int[] arr, int key, boolean upper) {
      int low = 0;
      int high = arr.length;

      while(low < high){
          int mid = (low + high)/2;

          if(key > arr[mid]){
              low = mid + 1;
          } else if(key < arr[mid]){
              high = mid;
          } else{
              if(upper){
                  low = mid + 1;
              } else {
                  high = mid;
              }
          }

      }
      return arr[low]; // 만약에 key가 배열 모든 값보다 초과할경우 arr.length 만큼 나오기 때문에 indexExcpetion 발생할수있음
      // 개수를 구할경우: low - arr.length
  }
  
---
### Parametric Search
* 최적화 문제를 결정 문제로 바꾸어 푸는 것
* 선택할수 있는 모든 경우의 수가 있을텐데 모든 경우의 수를 오름차순으로 나열하고 이분탐색으로 반으로 쪼개서 해당 경우의 수가 가능한지 불가능한지 여부를 따지는것이다.
  * 1, 2, 3 은 조건 만족 4, 5는 조건 불만족의 형태로 나타낼수있는 문제의 경우는 Parametric Search 로 해결가능하다.
* 복잡도는 이분탐색이기 때문에 O(logN)이다.
* 적용되는 문제
  * 최적화된 값을 요구
  * 구하고자 하는 값의 범위가 상당히 큼
  * 최댓값 혹은 최솟값 을 구하세요.
  * ~ 최대 거리를 출력하세요.
  * ~ 최솟값을 출력하세요.
* 문제
  * 떡볶이 떡 만들기 - 나동빈의 이것이 코딩 테스트다.
  * [입국심사 - 프로그래머스](https://github.com/sdk0213/Developer-Track/blob/master/자료구조%20및%20알고리즘/프로그래머스/3.%20입국심사.md)
  * [금과 은 운반하기 - 프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/86053)

