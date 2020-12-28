butterKnife - [박상권의 삽질블로그](https://gun0912.tistory.com/2)
===
* [ButterKnife github](http://jakewharton.github.io/butterknife/)
* 안드로이드에서 작업을 할때 가장 귀찮은 반복작업중 하나가 view 를 선언하고 할당하는 작업들이다.
* 이를 간단하게 표현할수 있느 라이브러리가 바로 ButterKnife 이다.
* **노가다 작업을 줄여주는 라이브러리다.**
* 차이점
  * 기본 코드
    ```java
    View view_root;
  
    ...
    view_root = findViewById(R.id.....);
  
  * ButterKnife 코드
    ```java
    @BindView(R.id......)
    View view_root;
* Bind
  * ```java
    super.setContentView(layoutResID);
    ButterKnife.bind(this)
    
    View view = Layoutinflater.from(getActivity()).inflate(resId, null);
    ButterKnife.bind(this, view);

  * onDestory 부분에서 unbind 필요
* View
  * ```java
    @BindView(R.id.et_comment)
    CustomEditText et_comment;
* Listener
  * ```java
    @OnClick(R.id.view_action_call)
    void onCallClick() {
        .... 
    }
    
    @OnClick({R.id.pager, R.id.indicator, R.id.cell_picture, R.id.cell_product, R.id.cell_price, R.id.cell_description,R.id.cell_location})
    public void onSomeThingClick(View view) {

    }
  * view 같은 경우는 선언을 하지 않아도 됨
* Resource Bind
  * ```java
    @BindString(R.string.title) String title;
* Holder
  * ```java
    public static class ViewHolder extends RecyclerView.ViewHolder {
     
      @Bind(R.id.iv_image)
      ImageView iv_image;
    
      int position = -1;
    
      public ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
      }
    }
    
