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
