# Decorator Pattern
### 사용 이유
* 기존에 생성된 객체에 새로운 기능을 추가하고 싶을 상속을 사용하지 않고 추가하는 패턴
* 상속을 사용하지 않기 때문에 구현이 간단해진다는 장점을 가지고 있다.
### 설명 코드
* ```java
  // 음료
  abstract class Beverage {
      String description = "제목 없음";

      public String getDescription() { return description; }
      public abstract double cost();

      @Override
      public String toString() {
          return getDescription() + ": $" + cost();
      }
  }

  // 첨가물
  abstract class CondimentDecorator extends Beverage {
     public abstract String getDescription();
  }
* 베이스가 되는 음료
  ```java
  class Espresso extends Beverage {
      public Espresso() { description = "에스프레소"; }

      @Override
      public double cost() { return 1.99; }
  }

  class HouseBlend extends Beverage {
      public HouseBlend() { description = "하우스 블렌드 커피"; }

      @Override
      public double cost() { return 0.89; }
  }
* 모카첨가물
  class Mocha extends CondimentDecorator {
      Beverage beverage;

      public Mocha(Beverage beverage) {
          description = "모카";
          this.beverage = beverage;
      }

      @Override
      public double cost() {
          // 중요한 부분
          return 0.20 + beverage.cost();
      }

      @Override
      public String getDescription() {
          // 중요한 부분
          return beverage.getDescription() + ", " + description;
      }
  }
* ```java
  Beverage beverage = new Espresso();
  System.out.println(beverage);

  beverage = new Mocha(beverage);
  System.out.println(beverage);

  Beverage beverage2 = new HouseBlend();
  System.out.println(beverage2);

  beverage2 = new Mocha(beverage2);
  System.out.println(beverage2);

  // result:
  // 에스프레소: $1.99
  // 에스프레소, 모카: $2.19
  // 하우스 블렌드 커피: $0.89
  // 하우스 블렌드 커피, 모카: $1.09
##### 해석
* 모카가 첨가된 에스프레소나 모카가 첨가된 하우스 블랜드 커피를 베이스 음료수(에스프레소, 하우스 블랜드)에서 상속받아서 모카를 첨가한 클래스를 따로 만들지 않으면서 생성하였다.
* 만약에 상속관계로 처리되었다면 총 두개의 클래스를 만들었어야 하며 만약에 이것에 첨가물이 추가되거나 베이스음료수가 추가될경우 그 경우의수만큼 처리해줘야하는데 데코레이터 패션은 이 단점을 극복하였다.
* 상속관계를 막아놓은 코틀린 언어에서 용이한 패턴이다.
* 자바의 I/O 클래스에서 이 패턴으로 설계되었다고 한다.~~~
