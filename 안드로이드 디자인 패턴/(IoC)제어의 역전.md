# (IOC)Inversion Of Control = 제어의 역전 - [출처는 develogs님의 티스토리입니다.](https://develogs.tistory.com/19)
* Don't call us, we'll call you "연락하지마, 내가 연락할게" 
  * callback이랑 다른점은???
* 라이브러리는 내 코드가 라이브러리를 이용한다. 즉, 제어권이 내 코드에 있다. 반면 프레임 워크는 프레임 워크가 나의 코드를 실행하는 IOC이다.
* 클래스의 생성자를 직접 호출해 인스턴스를 생성하는것을 프레임워크에 맡기는것
* 이해가 잘안가면 출처의 그림 14 참고
* 안드로이드에서 예를들자면 Activity 코드를 작성할 때 생명주기 메소드가 호출되었을 때의 **동작만 정의하고, 언제 생명주기 메소드를 호출 할지는 신경쓰지 않는다.**
  * ```kotlin
    class someActivity : AppCompatActivity() {

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          ...
     }

      override fun onResume() {
          super.onResume()
          ...
      }
    }
* 다른 예로 Computer 클래스안에 CPU 클래스가 필요할때 CPU 클래스를 Computer 클래스 내부가 아닌 외부에서 생성 및 관리를 하도록 위임하는것을 제어의 역전이라고 한다.
  * ```kotlin
    class Computer(private val cpu: CPU){
        fun setCpu(cpu: CPU){
            this.cpu = cpu
        }
    }
    
    ... main(){
       var cpu = I_CPU()
       var computer1 = Computer(cpu)
       var computer2 = Computer()
       computer2.setCpu(cpu)
    }
      
      
---
### 의존성 문제
##### A -> B <- C
* A안에 B를 객체로 가지고 있는 경우 A가 B에 대해 의존을 한다고 한다.
* C도 B에 대해서 의존한다.
##### 발생하는 문제
* B가 바뀌면 A는 B를 의존하므로 다시 바꿔주지 않는이상 사용할수 없다. **즉, B는 코드 하나 변경할려면 여러 경우에 A나 C도 고려해야한다.**
* 만약 A를 다른 포로젝트에서 사용할경우 B와 관련된 부분을 수정해야한다. **즉, 모듈화가 안되어있다.**
---
### [DIP는 이곳을 참고](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20디자인%20패턴/DIP.md)
