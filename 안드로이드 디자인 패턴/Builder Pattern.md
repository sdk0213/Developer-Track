# Builder Pattern
* ```java
  Member customer = Member.build()
      .name("성대경")
      .age(29)
      .build();
---
### Builder Pattern(빌더 패턴) 이란?
* 객체의 생성과 조립방법을 분리한다.
##### 기존 패턴 1(점층적 생성자 패턴)
* 생성자를 통해 전달한다.
* 코드 가독성 하락
* ```java
  // 호출 코드만으로는 각 인자의 의미를 알기 어렵다.
  NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 3, 35, 27);
  NutritionFacts pepsiCola = new NutritionFacts(220, 10, 110, 4, 30);
  NutritionFacts mountainDew = new NutritionFacts(230, 10);
##### 기존 패턴 2(JavaBeans pattern(자바빈 패턴))
* ```java
  NutritionFacts cocaCola = new NutritionFacts();
  cocaCola.setServingSize(240);
  cocaCola.setServings(8);
  cocaCola.setCalories(100);
  cocaCola.setSodium(35);
  cocaCola.setCarbohdydrate(27);
* 객체 일광성이 깨지고 이미 생성된 객체에 계속 호출하며 변경을 위해서는 스레드로부터 안전성을 보장하기 위해 더 많은 관리가 필요하다.
##### 빌더 패턴(Builder Pattern)
* 코드
  * return this
    * 체이닝을 이어가기 위한 코드
  * build()
    * 실질적 객체 생성
* 객체 일관성이 유지되며 각 의미를 알기 쉽다.
* ```java
  // Effective Java의 Builder Pattern
  public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters(필수 인자)
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values(선택적 인자는 기본값으로 초기화)
        private int calories      = 0;
        private int fat           = 0;
        private int carbohydrate  = 0;
        private int sodium        = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings    = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;    // 이렇게 하면 . 으로 체인을 이어갈 수 있다.
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize  = builder.servingSize;
        servings     = builder.servings;
        calories     = builder.calories;
        fat          = builder.fat;
        sodium       = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
  }
* ```java
  NutritionFacts.Builder builder = new NutritionFacts.Builder(240, 8);
  builder.calories(100); // 체이닝으로 연결
  builder.sodium(35);
  builder.carbohydrate(27); // 여기까지는 NutritionFacts.builder 객체를 생성한것이고
  NutritionFacts cocaCola = builder.build(); // 실질적 객체(NutritionFacts) 생성은 여기서 진행된다.
  // 즉 객체를 조립하는 객체(NutritionFacts.builder)와 생성하는 객체(NutritionFacts)를 분리하였다.
  // 아래와 같이 더 명확한 코드로도 작성이 가능하다.
  // 위와 동일하다.
  NutritionFacts cocaCola = new NutritionFacts
    .Builder(240, 8)    // 필수값 입력
    .calories(100)
    .sodium(35)
    .carbohydrate(27)
    .build();           // build() 가 객체를 생성해 돌려준다.

