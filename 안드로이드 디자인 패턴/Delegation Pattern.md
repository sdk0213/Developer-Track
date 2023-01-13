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
      private RealPrinter rp = null;
      public Printer(RealPrinter rp){
          this.rp = rp;
      }
      
      void print(){
        rp.print()
      }
  }
  
  public class Main {
      
      public static void main(String args[]){
          Printer printer = new Printer(new RealPrinter());
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
  
  
---  
### kotlin 에서의 위임 패턴
* 위임 패턴은 만약 위임한 기능이 변경이 될경우 이러한 변화로 인해서 위임을 해준 코드에도 변화를 주기에 결국 A 와 B 둘다 수정해줘야하는 개방폐쇄원칙(OCP) 에 어긋난다.
* 하지만 위의 단점을 극복가능하도록 코틀린에서는 by 라는 키워드를 통해 코드차원에서 지원하기 때문에 OCP 에 어긋나지 않으면서 코드를 작성할수있게 해준다. 이로써 위임패턴이 가지는 장점을 가져가되 단점은 없앨수있다.
* by 키워드가 없다면
  * ```kotlin
    interface Sample {
        fun print()
    }

    class A(private val sample: Sample) : Sample {
        override fun print() {
            sample.print()
        }
    }

    class B : Sample {
        override fun print() {
            ...
        }
    }

    val b = B()
    val a = A(b).print()
    
    // B 에 기능추가될경우 A 클래스도 override 해주거나 변경해야함
* by 키워드가 지원됨으로써
  * ```kotlin
    interface Sample {
        fun print()
    }

    class A(sample: Sample) : Sample by sample

    class B : Sample {
        override fun print() {
            ...
        }
    }

    val b = B()
    val a = A(b).print()

    // B 에 기능추가되어도 A 클래스에서 B 관련 코드는 변경할필요없다. by 키워드를 통해 위임패턴을 kotlin 차원에서 지원하기 떄문..
