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

  public int binSearch(int key, int low, int high, int[] arr) {
      int mid;

      while(low <= high) {
          mid = (low + high) / 2;

          if(key == arr[mid]) {
              return mid;
          } else if(key < arr[mid]) {
              high = mid - 1;
          } else {
              low = mid + 1;
          }
      }

      return -1; // 탐색 실패
  }
