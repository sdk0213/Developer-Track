# ViewModel과의 상호운용
### ViewModel 제약사항
* Context 참고 절대로 해서는 안된다. 필요하다면 AndroidViewModel 을 사용한다.
* 프레임워크 코드 최소 사용. 단순히 데이터만을 갖는게 좋다.
* Dagger2의 Scope와 ViewModel의 Scope차이로 인하여 엑티비티 재생성시 객체의 동일성이 깨질수있다.
  * Dagger2와 ViewModel의 생명주기가 다르다.(ViewModel이 더길다.) -> 객체간의 동일성을 보장하지 못할수있다.
* ViewModel은 시스템 언어 변경시 이전 문자열이 그대로 남기 때문에 반드시 리소스아이디를 참조(당연한것이지만)
---
### Dagger와의 상호운용시 설계방법
* **ViewModel의 생성 시** 필요한 생성자 매개 변수를 오브젝트 그래프의 **@Singleton 범위 내에서만 제공**받는다.
* ViewModel 생성자 매개 변수를 **필드에 할당 시 final로 선언하여 외부로부터 변경하지 못하도록 한다.**
* 단일의 **ViewModelProvider.Factory 인스턴스를 오브젝트 그래프에서 관리**하고 Activity또는 Fragment에 제공하여 ViewModelProvider로부터 ViewModel인스턴스를 요청한다.
  * Custom으로 관리하라는 뜻
* 단일의 ViewModelProvider.Factory로 모든 ViewModel 서브 클래스를 생성해야 하므로, **리플렉션과 멀티 바인딩으로 모든 ViewModel 타입을 생성하고 관리해야 한다.**

---
### [의존성이 있는 ViewModel을 만들기위해서는 어떻게 해야되는가? - 출처](https://velog.io/@ptm0304/ViewModel에-의존성-주입하기-Dagger-2-Java)
* ViewModel의 생성자를 주입할려면 반드시!! ViewModelProvider를 상속받아서 Custom 클래스를 구현해야 한다.
  * [왜 반드시 ViewModelProvider Factory를 통해 구현해야 하는가에 대한 링크 - 한로니님의 블로그](https://medium.com/@jungil.han/아키텍처-컴포넌트-viewmodel-이해하기-2e4d136d28d2)
  * [ViewModel을 생성하는 6가지 방법에 링크](https://readystory.tistory.com/176)
##### 첫번쨰 방법
* ViewModel이 5개 또는 그 이상 필요할때
* ```java
  public class ViewModelFactory implements ViewModelProvider.Factory {
    private final MyViewModel mMyViewModel;
    private final MyViewModel mMyViewModel2;
    private final MyViewModel mMyViewModel3;
    private final MyViewModel mMyViewModel4;
    private final MyViewModel mMyViewModel5;
    ...
    ..
    @Inject
    public ViewModelFactory(
            MyViewModel myViewModel, 
            MyViewModel2 myViewModel2, 
            MyViewModel3 myViewModel3, 
            MyViewModel4 myViewModel4, 
            MyViewModel5 myViewModel5, 
    ) {
        mMyViewModel = myViewModel;
    }
    
    ...
   
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    ViewModel viewModel;
    if (modelClass == MyViewModel.class) {
        viewModel = mMyViewModel;
    } else if( modelClass == MyViewModel2.class) {
        viewModel = mMyViewModel2;
    } ...
 
    ...
        return (T) viewModel;
  }
* 5개 정도까지는 이해해준다고 치자. 하지만 어플리케이션이 커지고 ViewModel이 10개.. 아니 20개가 넘어간다면? 이는 엉청난 보일러플레이트 코드를 생산한다.
* 그리고 무엇보다 SOLID 의 원칙중 LSP 원칙(상속관계를 똑바로 처리하지 못한것)을 꺤다.
  * 왜냐하면 Factory의 create는 본래 무조건 새로운 객체를 생성해야 하지만 위처럼 create를 처리할경우는 이미 생성된 객체가 있을경우 해당 객체를 반환해주기 떄문이다.
  * 이 말은 본래 create가 무조건 새로운 객체를 생성해야 하는 상위클래스에서 상속받아서(여기서는 implement(구현)) 처리할때는 항상 새로운 객체를 생성하지 않기 떄문에 이는 부모와 자식관계가 정확히 기능되지 못해서 LSP 원칙을 위반한다고 볼수있다.
* 그렇기 때문에 다음과 같이 새로운 뷰모델을 생성할때 같은 ViewModel로 반환될수있다.
  * ```java
    ...
    mMyViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MyViewModel.class);
    mMyViewModelActivity = ViewModelProviders.of(requireActivity(), mViewModelFactory).get(MyViewModel.class);
    ...
    
    // mMyViewModel과 mMyViewModelActivity 는 같아져 버린다.
##### 조금 더 나은 방법
* 
### Factory 구현
* ```java
  public class AppViewModelFactory implements ViewModelProvider.Factory {
  
      //ViewModel 클래스를 키로 갖는 멀티바인딩 된 Map
      private Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;
  
      @Inject
      public AppViewModelFactory(@NonNull Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
          this.creators = creators;
      }
  
      @SuppressWarnings("unchecked")
      @NonNull
      @Override
      public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
          //ViewModel 클래스를 키로 하여, ViewModel 객체를 생성하는 Provider를 가져온다.
          Provider<? extends ViewModel> creator = creators.get(modelClass);
          // 만약에 modelclass키가 없다면 modelclass를 상속하거나 구현한것이 있으면 해당 클래스로 찾아보기
          if (creator == null) { 
              //클래스 키로 못찾았다면 적당한 Provider가 있는지, 다시 Map에서 찾는다.
              for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                  if (modelClass.isAssignableFrom(entry.getKey())) {
                      creator = entry.getValue();
                  }
              }
          }
  
          if (creator == null) {
              throw new IllegalArgumentException("Unknown model class " + modelClass);
          }
  
          try {
              //Dagger의 Provider로 부터 ViewModel 객체 생성 및 반환
              return (T) creator.get();
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }
  }
* ```java
  public class PostFragment extends DaggerFragment {
      
      ..
      ...
      
      @Inject
      AppViewModelFactory viewModelFactory;
      
      ...
  }
