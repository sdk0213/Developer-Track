[View Model(뷰 모델)](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko#java)
===
* mvvm(Model — View — ViewModel)의 view model에서 파생되었음
* Android Architecture ViewModel의 약자인 AAC ViewModel이라고 부르기도 한다.
* Activity 와 Fragment 의 목적
  * 
* ```java 
  public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
  }
