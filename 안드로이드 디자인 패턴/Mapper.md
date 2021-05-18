# Mapper
### 클리 아키텍쳐와 Mapper
* 데이터베이스가 가지고 있는 모델과 비즈니스 규칙의 모델을 중간에서 변환시켜주는 역할을 한다.
* DB <-> Mapper <-> model
* <img width="191" alt="스크린샷 2021-05-02 오전 9 28 23" src="https://user-images.githubusercontent.com/51182964/116798307-294f3e00-ab29-11eb-8805-dec97ee7f737.png">
* 장점
  * data model과 결합도가 낮아지기 때문에 변경에 대해서 유연해진다.
##### Mapper Interface
* ```kotlin
  interface Mapper<E, D> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E

  }
##### Mapper
* ```kotlin
  open class TodoMapper @Inject constructor(): Mapper<TodoEntity, Todo> {

    override fun mapFromEntity(type: TodoEntity): Todo {
        return Todo(type.Id, type.title, type.subTitle)
    }

    override fun mapToEntity(type: Todo): TodoEntity {
        return TodoEntity(type.Id, type.title, type.subTitle)
    }

  }
##### data model
* ```kotlin
  @Entity
  data class TodoEntity(
      @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")  val Id: Long,

      @ColumnInfo(name = "title") val title: String,

      @ColumnInfo(name = "subtitle") val subTitle: String,
  )
##### domain model
* ```kotlin
  data class Todo (val Id: Long, val title: String, val subTitle: String)
