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
