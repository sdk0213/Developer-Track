### 이진탐색 - 아래 UpperBound 코드만 활용해도 문제풀이가능함
* 정렬된 알고리즘에서 사용되며 선형 탐색에 비해 속도가 빠르다. 하지만 정렬이 되어있어야한다는 전제조건이 붙는다.
* 시간복잡도: O(logN)
* ![image](https://user-images.githubusercontent.com/51182964/169071765-3da3e423-9494-462b-8f32-81ba789f03c9.png)
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
      int left = 0;
      int right = arr.length - 1;

      while (left <= right) {
          int mid = (left + right)/2; // mid 값을 계산.

          if (key > arr[mid]) // 키값이 더 크면 왼쪽을 버린다.
              left = mid + 1;
          else if (key < arr[mid]) // 키값이 더 작으면 오른쪽을 버린다.
              right = mid - 1;
          else
              return arr[mid]; // key found
      }
      return - (left + 1);  // key not found
  }

### Upper,Lower Bound binary Search
* upper 와 lower 의 필요성
  * {2,2,3,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,7,8,8,9} 과같은 배열에서 만약에 '5'의 인덱스를 검색할경우
  * 단순 이진탐색일경우에는 배열의 상태에 따라 다른 인덱스를 내놓을수 있는데 upper와 lower의 개념을 도입함으로써 더 중요한 정보인 처음또는 그 다음항에 대한 인덱스를 살펴볼수있다.
    * lowerBound -> 5가 시작되는 첫항을 검색
    * upperBound 로 4를 검색하며 -> 5가 시작되는 첫항을 검색
* upper -> 무조건 큰 수의 index
* lower -> 같거나 큰 수의 index
* loewr 일 경우에 [1,2,3,3,4] 에서 3을 탐색한 경우 첫번째로 mid 가 바로 3을 찾았는데 이때 만약에 right = mid - 1 을 하는 경우에는 이미 찾은 값을 건너뛰어버리는 경우가 발생하기 때문에 high = mid 로 진행한다.
* upper 일 경우에는 [1,2,3,3,3,3,4,5] 에서 3이 첫번째로 탐색되었을 때 3이라는값보다 큰 값을 찾아야 하기 때문에 3을 left = mid + 1 로 둔다. +1 을 하는 이유는 어짜피 3보다는 큰값이기 때문에 +1항을 탐색해도되기 때문이다.
  * upper 일 경우에도 left 와 마찬가지로 mid 가 큰 값이라면 right = mid - 1 을 할 경우 해당 값이 정답일수도 있는 경우도 있기 때문에 right = mid 로 정해놓는다.
  * 즉, ...3,3,4,4... 배열에서 mid 가 첫번째 4를 찾았는데 right 를 mid - 1 을 해버릴경우 정답인 '4' 는 건너뛰어 버리고 마지막 3을 마지막 범위로 두어서 오답이 나타날수있다.
* ```java
  @Test
  public void Test(){
      int[] arr = {1, 3, 5, 7, 8, 10, 20, 35, 99, 100};
      System.out.println(binSearchBound(arr, 20, true)); // 35 upper
      System.out.println(binSearchBound(arr, 20, false)); // 20 lower

  }


  public int binSearchBound(int[] arr, int key, boolean upper) {
      int left = 0;
      int right = arr.length; // 찾는 값이 배열의 있는 모든 값보다 큰 경우가 존재하기때문에

      while(left < right){
          int mid = (left + right)/2;

          if(key > arr[mid]){
              left = mid + 1;

          } else if(key < arr[mid]){
              right = mid;
          } else{
              if(upper){
                  left = mid + 1; 
              } else {
                  right = mid;
              }
          }

      }

      if(left == arr.length){
          return -1;
      } else {
          return left;
          // lowerBound: 해당 값을 포함하여 큰 값의 개수를 구할경우: low - arr.length;
          // upperBound: 해당 값보다 큰 값의 개수를 구할경우: low - arr.length;
          // 해당 값을 구할경우 : arr[low];
      }
  }
  
---
### Parametric Search (매개변수탐색)
* <img width="907" alt="스크린샷 2022-05-06 오후 7 30 59" src="https://user-images.githubusercontent.com/51182964/167115437-9cb87ea6-bc63-45c8-8a46-c8c9f35dbc62.png">
* <img width="921" alt="스크린샷 2022-05-06 오후 7 31 49" src="https://user-images.githubusercontent.com/51182964/167115483-ce811182-3f89-4c80-8de8-9c96c8376a96.png">
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

