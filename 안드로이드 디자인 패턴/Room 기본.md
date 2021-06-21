# Room 코드
---
### Entity - [출처는 jaejong](https://jaejong.tistory.com/119)
* [더자세한 내용은 jaejong님의 블로그 참고](https://jaejong.tistory.com/119)
* ```kotlin
  @Entity(tableName = "user", inheritSuperIndices = true)
  data class UserEntity(
      @PrimaryKey 
      var uid: Long,
      
      @ForeignKey( // 외래키 설정
          entity = BookEntity::class,		// 외래키 연결대상 Entity 클래스
          parentColmns = ["userId"],		// 외래키 연결대상 Entity 필드명
          childColumns = ["userForeignKey"],
          onDelete = ForeignKey.CASCADE	// 삭제될 경우 같이 삭제 설정
      ) 
      var userForeignKey: Int = 0, 		// 외래키 설정 Field (childColumn)

      @ColumnInfo(name = "user_name", index = true) 
      var name: String?,
      
      @ColumnInfo(name = "user_age", index = true) 
      var age: Int?,
      
      @Ignore 
      var image: Bitmap? = null		// IgnoredColumns 설정 (DB에 필드 생성X)
) 

### @Embedded 개별 열을 쿼리하듯 삽입된 필드를 쿼리
* 손 쉽게 다른 Entity 객체를 열로 저장
* ```kotlin
   data class Address(
       val street: String?,
       val state: String?,
       val city: String?,
       @ColumnInfo(name = "post_code") val postCode: Int
       @ColumnInfo(name = "post_id") val postId: Int
   )

   @Entity
   data class User(
       @PrimaryKey val id: Int,
       val firstName: String?,
       @Embedded val address: Address?
   )
* UserTable
  |id|post_code|post_id|
  |:--:|:--:|:--:|
  |1|123|12|
  |2|456|32|
  |3|678|45|
  |.|..|...|
* 테이블이 합쳐지는 과정에서 이름이 겹칠수도 있기에 다음과 같이 prefix 구문 사용
  ```kotlin
  @Entity(tableName = "userReview")
  data class UserReview(
      @PrimaryKey var id: String = "",
      @Embedded(prefix = "user_") var user: User,
      @Embedded(prefix = "review_") var review: Review,
      @Embedded(prefix = "product_") var product: Product
  )
  
### 관계 정의
* 클라이언트 단에서는 지연로딩의 문제로 성능 저하로 이어질수있기때문에 Room에서는 객체 모델로 관겨를 매핑하는것을 금지한다.
* ForeignKey CASECADE 조건으로 캐리어가 지워진다면 아이템 전부 삭제됨
* CarrierAndItems 데이터 클래스를 새롭게 정의함으로써
* 하나의 Carrier + List<Item> 형태로 여러가지 Carrier 를 가져온다.
* ```kotlin
  @Entity(tableName = "Carrier")
  data class CarrierEntity(
      @PrimaryKey
      val id: Long,
      val name: String,
      val date: Date,
      val type: String,
  )

  @Entity(tableName = "Item", foreignKeys = [
      ForeignKey(
          entity = CarrierEntity::class,
          parentColumns = ["id"],
          childColumns = ["carrier_id"],
          onDelete = ForeignKey.CASCADE
      )])
  data class ItemEntity(
      @PrimaryKey(autoGenerate = true)
      val id: Long,
      val name: String,
      val position_x: Int = 0,
      val position_y: Int = 0,
      val priority: Long = 0,
      val checked : Boolean = false,
      val carrier_id: Long
  )

  data class CarrierAndItems(
      @Embedded(prefix = "carrier_") val carrier: CarrierEntity,

      @Relation(
          parentColumn = "id",
          entityColumn = "carrier_id"
      )
      val items: List<ItemEntity>
  )
