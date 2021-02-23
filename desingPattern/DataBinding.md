DataBinding(데이터바인딩)
===
* 기본적인 자바로 코딩할때
  ```java
  TextView textView = findViewById(R.id.sample_text);
  textView.setText(viewModel.getUserName());
* 데이터 바인딩 - Databinding 
  ```java
  <TextView 
      android:text="@{viewmodel.userName}" />
