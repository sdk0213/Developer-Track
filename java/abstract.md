abstract
===
abstract - [limkydev 블로그](https://limkydev.tistory.com/188)
---
* 돌연변이
  * 인터페이스의 역할 + 구현체도 가짐
* 목적
  * 공통된 필드와 메서드를 통일할 목적
  * 시간절약
  * 규격에 맞는 실체클래스 구현
* ```java
  public abstract class Animal {
    public String kind;
     
    public void breath(){
        System.out.println("숨 쉰다.");
    }
    //추상메서드
    public abstract void sound();//구체적인 구현부는 없음!
  }

  public class Dog extends Animal{
     
    public Dog(){
        this.kind = "포유류";
    }
     
    @Override
    public void sound() {
        // TODO Auto-generated method stub
        System.out.println("왈왈!");
    }
 
  }
