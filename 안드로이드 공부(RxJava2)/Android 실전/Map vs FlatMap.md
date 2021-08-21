# map과 flatmap 차이
---
### map --> 단순히 데이터 가공
---
* 하나의 item에 대해 그저 그 아이템에 대한 것만 변경할수있다
* 하나의 value에 대해서 그저 하나의 value 를 리턴한다
---
### flatmap --> 받은 데이터를 통해 새로운 흐름 만듬
* 하나의 item에 대해 새로운 스트림을 만들어서 여러개의 변형된 item을 발행할 수 있게 된다.
* 하나의 value를 받아서 observable을 생성하고 모든 value에 대해서 observable이 생성되면 이를 하나로 묶어 하나의 Observable로 (흐름(평평하게)) 으로 만든다
