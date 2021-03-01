LiveData
---
* Data의 변경을 관찰 할 수 있는 Data Holder 클래스로 컴포넌트의 LifeCycle을 인식하여 상태에따라(STARTED, RESUMED) 데이터를 업데이트 하는 DataType이다.
* Observer 객체에 변화를 알려주고, Observer의 onChanged() 메소드가 실행
* MVVM 관점에서 보면 view의 역할에 충실할수있게 해주는 라이브러리라고 볼수있다.
* 장점
  * Observer 패턴을 따르기떄문에 Data와 UI간 동기화
    * 데이터의 변경이 일어났을때 콜백으로 받아 처리  
  * 생명주기 객체와 결합되어서 메모리 누수 없음(onDestory되면 삭제)
  * 자원(Resource)를 공유할 수 있다.
* 주의
  * LiveData에 Observer를 결합하는 코드는 onCreate() 메소드 내에 위치하는 것이 바람직
* LiveData()
  * 변경할 수 없고 오로지 데이터의 변경값만을 소비
* MutableLiveDta()
  * 변경할 수 있는 LiveData 형으로 데이터를 UI Thread와 Background Thread에서 선택적으로 변경
  * setValue()
    * background Thread
  * postValue()
    * ui Thread 에서 처리
