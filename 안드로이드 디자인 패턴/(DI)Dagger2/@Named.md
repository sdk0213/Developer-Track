# @Named - [출처 - 투덜이님의 리얼 블로그](https://tourspace.tistory.com/327?category=797357)
* ```java
  @Module class HeroModule { 
      @Provides fun provideIronMan(): Person = IronMan() 
      @Provides fun provideSuit(): Weapon = Suit() 
      @Provides fun provideCaptinAmerica(): Person = CaptainAmerica() 
      @Provides fun provideShield(): Weapon = Shield() 
      @Provides fun provideHeroIronMan(person: Person, weapon: Weapon) = Hero(person, weapon) 
      
  }
* 위 코드를 보면 Weapon과 Person의 반환값을 가지는 함수가 두개가 있다. 근데 Dagger2 모듈의 반환값을 체크하고 이를 연결해주는데 똑같은 반환값을 가진 함수가 있다면 Dagger는 이를 어떻게 처리 해야하는지를 모른다.
* 이에 대한 해답으로 있는것이 @Named 이다.
* 정확히 말하자면 **자료형(Type)만으로는 의존성을 식별하기에 어려운 경우에 @Named annotation을 사용해 의존성을 주입하여 식별가능하게 만들어준다.**
### @Named 추가
##### Module
* ```kotlin
  @Module class HeroModule { 
  
      @Provides 
      @Named("ironMan") 
      fun provideIronMan(): Person = IronMan() 
      
      @Provides 
      @Named("suit") 
      fun provideSuit(): Weapon = Suit() 
      
      @Provides 
      @Named("captainAmerica") 
      fun provideCaptainAmerica(): Person = CaptainAmerica() 
      
      @Provides 
      @Named("shield") 
      fun provideShield(): Weapon = Shield() 
      
      @Provides 
      @Named("heroIronMan") 
      fun provideHeroIronMan(
          @Named("ironMan") person: Person, 
          @Named("suit") weapon: Weapon
      ) = Hero(person, weapon) 
      
      @Provides 
      @Named("heroCaptainAmerica") 
      fun provideHeroCaptainAmerica(
          @Named("captainAmerica") person: Person, 
          @Named("shield") weapon: Weapon
      ) = Hero(person, weapon) 
   }
##### Component
* ```kotlin
  @Component(modules = [HeroModule::class]) 
  interface HeroComponent { 
      @Named("heroIronMan") 
      fun callIronMan(): Hero 
      
      @Named("heroCaptainAmerica") 
      fun callCaptainAmerica(): Hero 
      
  }
##### filed inject 사용한다면
* ```kotlin
  class Hero { 
      @Inject @Named("ironMan") lateinit var person: Person 
      @Inject @Named("suit") lateinit var weapon: Weapon 
      fun info() { 
          Log.d("doo", "name: ${person.name()} skill: ${person.skill()} | weapon:${weapon.type()}") 
      } 
  }
