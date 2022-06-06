factoryPattern - [출처](https://readystory.tistory.com/117)
===
* 원칙
  * 바뀔 수 있는 부분을 찾아내서 바뀌지 않는 부분하고 분리
  * 객체지향 디자인 원칙 : 변경이 일어나는 부분을 캡슐화
* 사용 이유
  * 복잡한 Object 들의 생성과정들을 클라이언트(개발자?)가 직접 다룰 필요가 없다.
  * 코드 수정을 줄여준다.
    * 만약에 객체 생성을 모두 java 에서 클라이언트(개발자)가 new 로 직접 생성할경우 나중에 생성자나 또는 다른이유로 객체 생성시 필요한 파라미터들의 추가들로 코드의 수정이 일어났을때 만약에 팩토리 패턴으로 작성된코드였을경우 팩토리만 수정하면되지만 그렇지 않는 경우 new 로 새로운 객체를 생성하는 모든 부분을 수정해야하는 비용이 생긴다.
  * new 를 사용하는 것은 구상클래스의 인스턴스를 만드는것인데 이는 나중에 코드를 수정해야할 가능성이 높아지고, 유연성이 떨어지게 된다.
    * Interface에 맞춰서 코딩을 하면 시스템에서 일어날 수 있는 여러가지 변화에 유연성있는 대처가 가능
  * 객체 생성 하는 코드를 분리하여 클라이언트 코드와 결합도(의존성)를 낮추어 코드를 건드리는 횟수를 최소화 하기 위한 패턴이다.
    * 객체를 생성하는 코드가 여러 클래스내 에서도 사용 되면 예를 들어 10개의 클래스 내에서 객체를 생성하는 if ~ else if 구문이 있다고 했을때 객체 추가/수정이 발생하면 10개의 클래스에 코드를 변경해 줘야 하는 일이 발생
    * 객체를 생성하는 코드를 추상화하여 코드를 한곳에서 관리하지 않으면, 변화(생성,수정,삭제)가 발생 했을 때 해당 클라이언트 코드를 전부 수정해줘야 한다.
  * 코드 중복을 줄일수 있고 인터페이스(추상클래스, 인터페이스 등)를 구현하는 방식을 사용해 다형성에 이점을 가져 갈 수 있어 코드 수정없이 유연한 코드를 작성
* 객체를 생성하는 인터페이스는 미리 정의하되, 인스턴스를 만들 클래스의 결정은 서브 클래스 쪽에서 내리는 패턴
  * **여러 개의 서브 클래스를 가진 슈퍼 클래스**가 있을 때 인풋에 따라 하나의 자식 클래스의 인스턴스를 리턴해주는 방식
* 장점
  * 서로 간의 종속성을 낮추고, 결합도를 느슨하게 하며(Loosely Coupled), 확장을 쉽게
  * 메인 프로그램에서는 어떤 객체가 생성되었는지 신경 쓸 필요 없이 반환된 객체만 사용하면 됨
  
팩토리 메서드 패턴
---
* 쉽게 생각하면 공장에서 생산하듯 빠르게 객체들을 편하게 생성가능하게 해주는 패턴이라고 생각하면됨
* 예를들어서 컴퓨터라는 객체를 만들때 이를 OS에 따라서 Mac, Window, Linux 와 같이 만든다고 할때 이를 코드로 작성한다면 다음과 같이 작성해야한다.
  * ```java
    public abstract class Computer{
        public abstract String getName();
    }
    // 당연히 MacComputer class, WindowComputer class, LinuxComputer class 는 extends Computer 이다.
    Computer computer = null;
                      
    if(type="MAC"){
       computer = new MacComputer();
    }
    else if(type="WINDOW"){
       computer = new WindowComputer();
    }
    else if(type="LINUX"){
       computer = new LiunxComputer();
    }
                    
* 이를 팩토리 메서드 패턴으로 변경한다면 다음과 같이 변경된다.
  * ```java
    public abstract class ComputerFactory{
        public abstract Computer createComputer(String type);
    }
    
    public class TypeComputerFactory extends ComputerFactory{

    @Override
    public createComputer(String type){
        Computer computer = null;

        switch(type){
            case("MAC"):
                computer = new MacComputer();
                break;
            case("WINDOW"):
                computer = new WindowComputer();
                break;
            case("LINUX"):
                computer = new LiunxComputer();
                break;
            default:
                computer = new JustComputer();
        }
        return computer;
    }
}
                      
추상 팩토리 메서드 패턴
---
* 관련된 객체들을 한꺼번에 캡슐화 하여 팩토리로 만들어서 일관되게 객체를 생성
* [자세히](https://biggwang.github.io/2019/06/28/Design%20Patterns/[Design%20Patterns]%20팩토리%20패턴,%20도대체%20왜%20쓰는거야-기본%20이론편/)
                      
  
                      
