RecyclerView.Aadapter
===

출처 : <https://ppizil.tistory.com/25>

Viewholder
---
+ Listview에서 findviewById는 호출 비용이 부담스러워서 사용되는 패턴이다.
  + ListView의 Performance를 높인다.
+ xml view와 Data를 담아줄수 있는것

ListView와 RecyclerView
---
+ 공통점
  + 화면에 보이게 되는 영역의 아이템만을 인플레이트를 시도한다.
  + 나머지는 후에 화면 화면에 보일때 로드한다.
+ 차이점
  + 이미 만들어진 뷰를 "재사용(Recycle)"을 하냐의 차이이다.
+ **그래서**
  + 이 개념(Viewholder)을 강제할것이냐 아니냐의 차이가 바로 Listview와 RecylcerView의 차이이다.
  + ListView + Flexibility + Performance <=> RecyclerBiew
  + RecyclerView는 구글이 뷰홀더 패턴을 항상 코드로 강제하도록 라이브러리로 만든것이다.
