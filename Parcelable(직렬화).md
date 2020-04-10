직렬화를 하는이유
===

출처 : <https://medium.com/@limgyumin/parcelable-vs-serializable-%EC%A0%95%EB%A7%90-serializable%EC%9D%80-%EB%8A%90%EB%A6%B4%EA%B9%8C-bc2b9a7ba810>

Android 에서 직렬화
---
  + Android 에서 Intent를 통해 값을 넘길때 일반적인 String, boolean 말고 클래스나 객체를 넘겨줘야 하는 상황이 있다.
  + 이럴때 필요한것이 직렬화이다. 직렬화를 통해 하나의 액티비티에서 다른 액티비티로 복잡한 클래스나 객체를 이동할수 있다.
  + 직렬화의 정의는 다음과 같다.
    + 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할수 있도록 Byte 형태로 데이터 변환(직렬화), 재변환(역직렬화)하는 과정
  
Serializable
---
+ 표준 Java의 Interface
+ Reflection을 사용하여 직렬화를 처리
  + 하지만 Reflection은 프로세스 동작 중에 사용되며 처리 과정중에 많은 추가 객체를 생성하며 이로인해 GC의 과도한 동작이 일어남(쓰레기들을 처리하기위해서)

Parcelable
---
+ 표준 Androd SDK의 Interface
+ Reflection이 필요 없다.
+ BoilerPlate가 발생한다 - [출처](https://aroundck.tistory.com/2082]
  + BoilerPlater는 코딩에서 꼭 필요한 간단한 기능인데, 반복적인 코드를 필요로 하며 이것이 중복되어 많은 양의 코드를 양산하는것을 의미
  + 예를들면 get/set func 로서 꼭필요하지만 반복적이고 그래서 계속 양산되는 코드와 같은 것이다.
  + kotlin 언어를 통해 많이 사라짐
  + BoilerPlate는 클래스를 이해하기 어렵고, 새로운 기능을 추가하기 어렵게 만들고 유지보수측면에서 어렵게 만든다.

결론
---
1. 속도측면에서는 서로 차이가 나는것 같지는 않으니 어떤것을 사용했을때 앱이 사용자가 원하는대로 잘 작동하는지에 따라 선호하면 될듯함
