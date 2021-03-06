# Delegation Pattern - [출처](https://codechacha.com/ko/kotlin-deligation-using-by/)
* 객체 지향 프로그램에서 한 객체가 모든 일을 수행하는 것이 아니라 어떤 일의 일부를 다른 객체(helper object)에게 위임하는 패턴이다.
### 예
* ```java
  class RealPrinter {
      void Print() {
        System.out.println("something");
      }
  }
  
  class Printer{
      RealPrinter p = new Printer();
      void print(){
        p.print();
      }
  }
  
  public class Main {
      
      public static void main(String args[]){
          Printer printer = new Printer();
          printer.print();
      }
  }
### 설명
* Printer에서 RealPrinter의 print를 가져다쓰니 사실상 Printer의 print를 호출하면 RealPrinter의 print를 쓰는것과 동일한 셈이다.
---
### 다른예 - Android
##### clickListener interface
* ```java
  public interface OnClickListener {
      /**
       * Called when a view has been clicked.
       *
       * @param v The view that was clicked.
       */
      void onClick(View v);
  }
##### Listener 등록
* ```java
  mButtonLogin.setOnClickListener(this);
##### OnClickListner 를 전달
* ```java
  public void setOnClickListener(@Nullable OnClickListener l) {
        if (!isClickable()) {
            setClickable(true);
        }
        getListenerInfo().mOnClickListener = l;
  }
### 설명
* OnClickListener 인터페이스를 view 클래스안에 setOnClickListener를 통해 전달하고 앞으로 클릭이 일어날때의 호출을 view클래스에다가 넘긴다. 이것처럼 하나의 객체가 가지고 있는 권한(예를들면 클릭)을 다른 객체에 넘기는 디자인 패턴을 권한 위임 패턴이라고 할수있다.
  
