# UseCase
---
### 개념
* UseCase는 하나의 유저 액션에 따른 하나의 로식만 수행하며 Domain Layer에 속한다.
* Repository가 여러 ViewModel에서 쓰이고 있는데 그 Repository가 바뀐다면 해당 Repository를 사용하는 
모든 ViewModel을 수정하는 경우가 생긴다
* application에서 network 통신 처리, database CRUD 처리와 같은 각각의 flow를 캡슐화하여 세부구현을 Controller에서 분리할 수 있다.
---
### 전체적인 흐름
* coroutine 이나 Rxjava에 따라 달리지겠지만 UseCase마다 데이터를 처리하는 추상 클래스를 두고 해당 클래스를 상속받는 형태로 작성한다.
* 추상 클래스에는 데이터의 상태를 처리하는 클래스인 sealed class Result<T> 클래스를 만든다. 그리고 해당 클래스는 MutableLiveData<Result<R>> 로 만들고 변경을 콜백받도록 한다.  
* 흐름
  * UseCase사용하는곳에서 ReservationActionUseCase 사용후 반환되는 값으로 Result 에 대한 처리를 해준다.  
  * ReservationActionUseCase : UseCase
    * UseCase
      * invoke (Rx Or Coroutine)
        * execute 시도 -> 성공
          * Result.Success(it) 반환 (여기서 it은 execute가 성공하였을때의 return 값으로 성공한값을 그대로 Success<out T>(val data: T) 안 data에 넣어서 반환해준다.)
        * execute 시도 -> 실패
          * Result.Error(e) 반환 (여기서 e은 execute가 실패하였을때의 해당 exception을 그대로 Error(val exception: Exception) 안 exception에 넣어서 반환해준다.)
      * execute
        * Rx Or Coroutine 등.. 하려던것 실행
* ```kotlin
  ..
  ...
  ....
  val result = reservationActionUseCase(
      ReservationRequestParameters(
          userId,
          sessionSnapshot.id,
          RequestAction(),
          userSession
      )
  )
  when (result) {
     is Success -> reservationActionResult.value = result.data
     is Error -> {
         _snackBarMessage.value =
             Event(
                 SnackbarMessage(
                     messageId = R.string.reservation_error,
                     longDuration = true
                 )
             )
     }  
  }
  ...
  ..
  .
  
  
---
### with RxJava
##### abstract FlowableUseCase
* ```kotlin
  abstract class FlowableUseCase<T, R>() {
      private val result = MutableLiveData<Result<R>>()
      fun observe() = result

      operator fun invoke(parameter: T): Disposable {
          return execute(parameter)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe (
                  {
                      // onNext
                      result.value = it
                  },
                  {
                      // onError
                  }
              )
      }
      abstract fun execute(parameter: T): Flowable<Result<R>>
  }
##### Result Class
* [seald class 개념 참고](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20공부(Kotlin)/sealed%20class(Result%20class).md)
* ```kotlin
  sealed class Result<out R> {

      data class Success<out T>(val data: T) : Result<T>()
      data class Error(val exception: Exception) : Result<Nothing>()
      object Loading : Result<Nothing>()

      override fun toString(): String {
          return when (this) {
              is Success<*> -> "Success[data=$data]"
              is Error -> "Error[exception=$exception]"
              Loading -> "Loading"
          }
      }
  }

  /**
   * `true` if [Result] is of type [Success] & holds non-null [Success.data].
   */
  val Result<*>.succeeded
      get() = this is Success && data != null

  fun <T> Result<T>.successOr(fallback: T): T {
      return (this as? Success<T>)?.data ?: fallback
  }

  val <T> Result<T>.data: T?
      get() = (this as? Success)?.data

  /**
   * Updates value of [liveData] if [Result] is of type [Success]
   */
  inline fun <reified T> Result<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
      if (this is Success) {
          liveData.value = data
      }
  }
##### UseCase
* ```kotlin
  class GetTodoUseCase @Inject constructor(private val repository: TodoRepository) :
      FlowableUseCase<Int, Todo>() {

      fun getTodoAll() : Flowable<List<Todo>> {
          return repository.getUserTodoAll()
      }

      override fun execute(parameter: Int): Flowable<Result<Todo>> {
          TODO("Not yet implemented")
      }

  }
