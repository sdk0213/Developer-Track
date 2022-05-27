# 동적 계획법 - Dynamic Programing, DP
### 의미와 포인트
* 큰 문제 -> 작은 문제로 축소
* 복잡한 문제 -> 단순한 문제로 축소
* 큰 조각에 대한 답 = 작은 조각의 답 + 작은 조각의 답 + ....
* 재귀
* Memoization
  * 이미 계산된 건 메모리에 저장하여 중복 계산을 줄이는 것
* 보통 누적되어 최대값이 되는 문제는 DP라고 의심해봐야함.
---
### 피보나치
* for 문 (작은 문제부터 차례대로)
  * ```java
    int fibo(int n)
    {
      fibodata[0] = 0;
      fiboData[1] = 1;
      for (int i=2; i<=n; i++)
        fiboData[i] = fiboData[i - 1] + fiboData[i - 2];
      return fiboData[n];
    }
* 재귀(Top-down)
  * ```java
    int fiboData[100] = {0,};

    int fibo(int n)
    {
      if (n<=2) 
        return 1;
      if (fiboData[n]==0) // 중복 계산 방지용으로 0 일경우에만 계산 아니면 그냥 해당 값(이미 계산되어짐)을 반환함
        fiboData[n] = fibo(n-1) + fibo(n-2);
      return fiboData[n];
    }
---
### 2*n 타일링
* d(n) = d(n-1) + d(n-2) + d(n-2)
* ```java
  import java.util.*;
  import java.io.*;

  class Main{

      public static void main(String[] args){
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



          try {

              int input = Integer.parseInt(br.readLine());

              System.out.println(dp(input));

              bw.close();
              br.close();


          } catch(Exception e){

          }


      }

      static int[] d = new int[1001];

      static int dp(int n){
          if(n == 1) return 1;
          if(n == 2) return 3;
          if(d[n] != 0) return d[n];
          return d[n] = (2*dp(n-2) + dp(n-1)) % 10007;
      }

  }
