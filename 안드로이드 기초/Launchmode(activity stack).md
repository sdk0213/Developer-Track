LaunchMode
===
+ Activity Stack 을 쌓는 방식에 대해서 설정하는 방식에 대해서 설정하는것
  + 다음과 같은 4가지로 분류된다.
    + standard
      + 일반적인 스택모드
    + singletop
      + 최상위 스텍에 있는 엑티비티를 또 다시 호출할때 재활용
      + 해당 엑티비티가 존재하는거
    + singleTask
      + [참고](https://m.blog.naver.com/PostView.nhn?blogId=manhdh&logNo=120162763951&proxyReferer=https:%2F%2Fwww.google.com%2F)
    + singleInstance
      + 하나의 테스크에 하나의 엑티비티만 존재
+ 백스택 전부 삭제
  + ```java
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    // or
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

[출처1](https://m.blog.naver.com/PostView.nhn?blogId=manhdh&logNo=120162763951&proxyReferer=https%3A%2F%2Fwww.google.com%2F)
[출처2](https://medium.com/@logishudson0218/intent-flag%EC%97%90-%EB%8C%80%ED%95%9C-%EC%9D%B4%ED%95%B4-d8c91ddd3bfc)
[출처3](https://arabiannight.tistory.com/entry/286)
[출처4](https://dev.re.kr/22)
