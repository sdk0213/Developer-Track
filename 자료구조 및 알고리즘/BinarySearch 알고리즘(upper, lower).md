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
          int mid = low + (high - low)/2; // mid 값을 계산.

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
* upper -> 무조건 큰 수의 index
* lower -> 같거나 큰 수의 index
* ```java
  @Test
  public void Test(){
      int[] arr = {1, 3, 5, 7, 8, 10, 20, 35, 99, 100};
      System.out.println(binSearchBound(arr, 20, true)); // 35 upper
      System.out.println(binSearchBound(arr, 20, false)); // 20 lower

  }


  public int binSearchBound(int[] arr, int value, boolean upper) {
      int low = 0;
      int high = arr.length;
      while (low < high) {
          int mid = low + (high - low)/2;
          if(value == arr[mid]){
              if (upper) {
                  low = mid + 1;
              } else {
                  high = mid;
              }
          } else if (value < arr[mid]) {
              high = mid;
          } else {
              low = mid + 1;
          }
      }
      return arr[low];
  }
