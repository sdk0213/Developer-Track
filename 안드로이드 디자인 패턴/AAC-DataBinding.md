[AAC-DataBinding](https://developer.android.com/topic/libraries/data-binding?hl=ko)
===
* 데이터소스와 UI컴포넌트의 상태르 일치시키는 기술 (wiki document)
* 데이터 바인딩을 사용하는것으 단순히 findbViewVyId를 안쓸려고 대용하기위한 개념이 절대 아니다.
  * **데이터바인딩을 사용할경우 에노테션 프로세서에 의해 xml 파일이 ..Binding.java 클래스를 통해서 view에 접근하고 데이터에 바인딩할수 있는 상태로 만들어준다고 보면된다.**
* View가 ViewModel에 의존적인데 여기서 View는 ViewDataBinding을 상속한 Binding 클래스로 표현이 된다. Binding 클래스가 ViewModel에 의존하게 되는것이다.
View대신에 ViewDataBinding을 사용함으로써 UI 컴포넌트와 View의 결합도가 느슨하게 된다. (디커플링)
* 특징
  * 코드 간단해진다.
  * rebuild 르 해줘야해서 귀찮다.
  * 생각보다 쉽지 않다.
  * 바인딩시 null처리를 알아서 해준다
* implementation X
  * ```gradle
    android {
      ....
      buildFeatures {
          dataBinding true
      }
    }
  * databinding 을 할경우 다음과 같이 xml이 어노테션프로세서에 의해서 class 가 생성이 된다.
  * activity_main.xml -> ActivityMainBinding.java 로 생성이된다. 생성되는 이름은 기존 xml 파일의 이름에 따른다.
    * ![](img/after_databinding_mainclass.png)
* 데이터 바인딩을 할 xml은 layout을 최상위로 같는다.
  * ```xml
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">
        
        <data>
        
        
        <Linenr or Constaraint .... or >
    
    </layout>
  * layout 테그는 하나의 레이아웃만 가질수 있고 두개 이상은 가질수 없으므로 아래의 코드는 에러가 난다.
    ```xml
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">
        
        
        <Linenr or Constaraint .... or >
        <Linenr or Constaraint .... or >
        <Linenr or Constaraint .... or >
    
    </layout>
* [data 태그 사용](https://developer.android.com/topic/libraries/data-binding/expressions?hl=ko#java)
  * ```java
    // data class
    public class User {
       private final String firstName;
       private final String lastName;
       public User(String firstName, String lastName) {
           this.firstName = firstName;
           this.lastName = lastName;
       }
       public String getFirstName() {
           return this.firstName;
       }
       public String getLastName() {
           return this.lastName;
       }
    }

* ```java
  class MainActivity : AppCompatActivity {
      private ActivityMainBinding binding = null;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           User user = new User("sung","daekyoung");
           binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
           binding.setUser(user);
        }
  }

* textview 의 글자를 변경할때 settext함수를 사용해서 변경하는것과 ui 변경 일련의 과정들이 viewDataBinding 으로 들어가져있다. 만약에 내부에 정의되지 않는 view를 조작하는 메서드가 필요하면 binding adapter 메서드를 선언해서 이것을 xml 레이아웃에서 참조시키면 그것이 다 viewDataBinding 코드로 들어가서 사용되는것을 볼수있다.
* activity, fragment에 작성되는 코드량이 줄어든다.

### Fragment DataBinding
* 자바
  ```java
  private FragmentDictBinding binding;
 
  public View onCreateView(@NonNull LayoutInfalter inflater, ViewGroup containter, Bundle savedInstance) {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dict, container, false);
         View root = binding.getRoot();
         ...
         
         return root;
  }
* 코틀린
  ```kotlin
  val binding = FragmentGalleryBinding.inflate(inflater, container, false)
  context ?: return binding.root
##### root 뜻
* 바인딩과 관련된 레이아웃 파일의 가장 바깥 쪽 뷰
* 이 메서드는 레이아웃 파일의 루트 뷰에 관한 직접 참조
* 위 코드에서 return 으로 root을 가져오는데 이를 따라가보면 해당 코드이다.
* https://developer.android.com/topic/libraries/view-binding?hl=ko 에서 getRoot 검색
* ```java
  @NonNull
  @Override
  public View getRoot() {
      return mRoot;
  }
* 그리고 mRoot는 다음과 같이 View이다.
* ```java
  private final View mRoot;
* mRoot가 설정되는곳의 코드를 보면 ViewDataBinding에서 설정되는것을 볼수 있다.
* ```java
  protected ViewDataBinding(DataBindingComponent bindingComponent, View root, int localFieldCount) {
      mBindingComponent = bindingComponent;
      mLocalFieldObservers = new WeakListener[localFieldCount];
      this.mRoot = root;
      ...
      ..
      .
  }
---
### Difference between DataBindingUtil and layoutBinding
##### DataBindingUtil
* If you want dynamic binding class then you can use DataBindingUtil
* layoutId를 미리 알 수없는 경우에만이 버전을 사용
##### layoutBinding
* 나머지 경우에는 무조건 layoutBinding 을 사용하라고 구글에서 권장한다.
---
### binding.lifecycleOwner = this 
* LiveData를 Databinding으로 쓸경우 꼭 작성해야한다. Observable 대신 LiveData를 사용하여 DataBinding 가능하다.
         
