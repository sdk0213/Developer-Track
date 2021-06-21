# map과 flatmap 차이
---
### map
---
* 하나의 item에 대해 그저 그 아이템에 대한 것만 변경할수있다
* String의 item에 대하여 .map을 사용할 경우 같은 String으로 반환할뿐이다.
  * 그렇기 때문에 변형해봤자 String이다
---
### flatmap
* 하나의 item에 대해 새로운 스트림을 만들어서 여러개의 변형된 item을 발행할 수 있게 된다.
* String item에 대하여 .flatmap을 사용할경우
