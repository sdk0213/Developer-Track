# Repository pattern
### Google 아키텍쳐 가이드
* ViewModel 밑에 layer를 하나 더 둔다.
* ViewModel이 Model과 소통할때 Repository 를 거쳐서 소통하는것을 볼수있다.
* ![](img/google_android_architecture.png)
### 역할
* ViewModel이 요청하는 데이터를 Room이나 Retrofit으로부터 가져와 전달해준다.
  * --> ViewModel이 어디서 데이터르 가져왔는지 몰라도 된다.
    * --> ViewModel 자신의 비즈니스 로직에만 집중하면 된다. 
### 특징
* 코드량이 많이지지만 모듈의 교체와 유지 보수가 편해진다.
* 
##### 기존
* 비즈니스 로직에 집중해야할 ViewModel이 db인 Realm으 초기화 코드까지 관여하는 상태
* ```kotlin
  open class MainViewModels : ViewModel() {
      mRealm: Realm by lazy {
          Realm.getDefaultInstance()
      }
      
      fun getAllStudentData() : LiveData<RealmResults<Students>> {
          return mRealm.studentDao().getAllStudents()
      }
      
      override fun onCleared() {
          mRealm.close()
          super.onCleared()
      }
  }
##### 분리
* Repository
  ```kotlin
  class TestRepository {
      
      private val mRealm: Realm by lazy {
          Realm.getDefaultInstance()
      }
      
      fun getAllStudentData(): LiveData<RealmResults<Students>> {
          return mRealm.studentDao().getAllStudents()
      }
      
      fun insert(student: Student)
          return mRealm.studentDao().addStudent(student)
      }
  }
* ViewModel
  ```kotlin
  open class MainViewModels: ViewModel() {
      
      private val mRepository: TestRepository by lazy {
          TestRepository()
      }
      
      fun getAllStudents(): LiveData<RealmResults<Student>> {
          return mRepository.getallStudentData()
      }
      
      fun insertStudent(student: Student) {
          return mRepository.insert(student)
      }
  }
