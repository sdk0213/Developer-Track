# tools attribute - [출처는 찰스님의 안드로이드](https://gun0912.tistory.com/73)
* layout 에 출력이 어떤식으로 되는지 확인하고 싶을때 사용할수 있는 기능이 있다.
---
### 사용
* xmlns:tools="http://schemas.android.com/tools" 를 layout 에 추가한다.(보통은 자동으로 추가되어있다.)
---
### tools:text
* 텍스트를 xml 상에서 표시
* ```xml
  tools:text="TEST"
---
### tools:visibility
* 사라졌을때를 xml 상에서 표시
* ```xml
  tools:visibility="gone"
---
### tools:listitem
* 리스트 출력을 xml 상에서 표시
* ```xml
  tools:listitem="@layout/content_item"
---
### tools:layout 
* 프래그먼트같은 실제 view가 어떻게 표시되는지 확인하고 싶을때 사용 가능
* ```xml
  tools:layout="@layout/feed_fragment"
---
### tools:showIn
* layout의 반대개념으로 나를 쓰는 입장에서 고려되었을때의 view
* ```xml
  tools:showIn="@layout/feed_fragment"
---
### **tools:context**
* 해당 xml이 어떤 액티비티와 연관이 있는지를 알려주는 기능
* onButtonClicked라는 없는 함수이름을 지정해줄경우 이 xml이 연결된 액티비티에 새로 onButtonClicked()라는 함수를 만들건지 도와주는 창을 띄워줄수 있다.
* 뭐랑 연결되었는지 바로 확인가능하다.
* ```xml
  tools:context=".ui.GoToOutActivity">
---
### tools:targetApi
* target api 지정
* ```xml
  tools:targetApi="14"
