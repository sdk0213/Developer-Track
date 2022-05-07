### fragment 생성
* container 는 contraintlayout 이나 linearlayout 이나 아무거나 상관없으나 대체적으로 FrameLayout 을 사용하는듯 보임
* null 발생하니 getActivity() 말고 requireActivity() 사용
* 프래그먼트는 new 로 새로 생성하지 않을경우 popBackStack() 후 다시 fragment 를 commit 시 view 데이터가 삭제되는 현상이 있어서 아마도 savedinstance 에서 관리해줘야하는 부분이 있는것같기 때문에 큰 문제가 있지 않는 이상 매번 새로 생성해서 진행
* ```java
  FragmentManager fm = requireActivity().getSupportFragmentManager();
  FragmentTransaction ft = fm.beginTransaction();
  ft.add(mFragmentContainer.getId(), new ViewMoreFragment());
  ft.addToBackStack(null);
  ft.commit();
---
### backpress 설정
* activity 에서 onbackPressed() 호출 안할경우 해당 이벤트 동작안하니 주의
* ```java
  @Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		onBackPressedCallback = new OnBackPressedCallback(true){
			@Override
			public void handleOnBackPressed() {
				requireActivity().getSupportFragmentManager().popBackStack();
			}
		};
		requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		onBackPressedCallback.remove();
	}
---
# 문제
### 프래그먼트 repalce, add 후에도 button 이나 특정 view 가 남아있는 현상
* 해당 Button 과 같은 view 를 container 레이아웃 상단에 두고 contsraint나 linear와 같은 상위 레이아웃으로 한번더 묶어서 진행
* ```xml
  <Const...(상위 레이아웃으로 한번더 묶기
    <Button (문제가 있는 View)
    
  />
  
  <Const.. (Container)
  
  
### 프래그먼트 터치 겹침 문제 해결
* 이전 프로그래먼트와 터치가 겹칠경우 아래와같이 전체 레이아웃을 터치가능하도록 수정
* ```xml
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      android:background="@color/white"
      android:orientation="vertical"
      android:clickable="true"
      android:focusable="true">
      
  ...
  ..
  
  </androidx.constraintlayout.widget.ConstraintLayout>
