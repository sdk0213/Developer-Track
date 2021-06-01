# UseCase
---
### 개념
* UseCase는 하나의 유저 액션에 따른 하나의 로식만 수행하며 Domain Layer에 속한다.
* Repository가 여러 ViewModel에서 쓰이고 있는데 그 Repository가 바뀐다면 해당 Repository를 사용하는 
모든 ViewModel을 수정하는 경우가 생긴다
* application에서 network 통신 처리, database CRUD 처리와 같은 각각의 flow를 캡슐화하여 세부구현을 Controller에서 분리할 수 있다.
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
