DataBinding(데이터바인딩)
===
* 데이터소스와 UI컴포넌트의 상태르 일치시키느 기술 (wiki document)
* 데이터 바인딩을 사용하는것으 단순히 findbViewVyId를 안쓸려고 대용하기위한 개념이 절대 아니다.
  * 에노테션 프로세서에 의해 xml 파일이 ..Binding.java 클래스를 통해서 view에 접근하고 데이터에 바인딩할수 있다.
* View가 ViewModel에 의존적인데 여기서 View는 ViewDataBinding을 상속한 Binding 클래스로 표현이 된다. Binding 클래스가 ViewModel에 의존하게 되는것이다.
View대신에 ViewDataBinding을 사용함으로써 UI 컴포넌트와 View의 결합도가 느슨하게 된다. (디커플링)
* ```java
  class MainActivity : AppCompatActivity {
    val viewModel : ViewModel
    val binding : ActivityMainBinding
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            ...
            binding.viewModel = viewModel // view랑 viewmodel 바인딩 해줌
        }
  }
* textview 의 글자를 변경할때 settext함수를 사용해서 변경하는것과 ui 변경 일련의 과정들이 viewDataBinding 으로 들어가져있다. 만약에 내부에 정의되지 않는 view를 조작하는 메서드가 필요하면 binding adapter 메서드를 선언해서 이것을 xml 레이아웃에서 참조시키면 그것이 다 viewDataBinding 코드로 들어가서 사용되는것을 볼수있다.
* activity, fragment에 작성되는 코드량이 줄어든다.
* 기본적인 자바로 코딩할때
  ```java
  TextView textView = findViewById(R.id.sample_text);
  textView.setText(viewModel.getUserName());
* 데이터 바인딩 - Databinding 
  ```java
  <TextView 
      android:text="@{viewmodel.userName}" />
