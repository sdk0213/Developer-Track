# module 을 정할때 abstract class 와 interface 의 차이는..?
* [참고 링크](https://stackoverflow.com/questions/66067710/dagger-hilt-is-there-a-difference-in-modules-if-they-are-abstract-classes-or-int)
  * There is no difference between using interface or abstract class when @Binds is used. These @Module classes' @Binds methods are used by Dagger only to understand which implementation class  
* 차이는 없다. 단순히 dagger 가 코드르 이해하기 위한 방식의 차이일뿐 어떻게 표현하느냐에 따라 기능이 달라지지 않는다.
