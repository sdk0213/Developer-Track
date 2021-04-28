# Room With Rx.md - [출처 - androiddevelopers - medium](https://medium.com/androiddevelopers/room-rxjava-acb0cd4f3757)
---
#### 사전지식
  * Single : 1개 데이터 발행후 완료 / Maybe : 0 or 1 개 데이터 발행후 완료 = Maybe Single 의 의미
  * Completable : 값이 없는 Maybe, 에러 발생할경우만 리턴
---
### Dao
##### Insert, Update, Delete
* ```kotlin
  @Insert
  insert(User user): Completable // 
  // or
  @Insert
  insert(User user): Maybe<Long> 
  // or
  @Insert
  insert(User[] user): Single<Long> 
  // or
  @Insert
  insert(User[] user): Maybe<List<Long>> 
  // or
  @Insert
  insert(User[] user): Single<List<Long>>

  @Update
  Completable update(User user);
  // or
  @Update
  Single<Integer> update(User user);
  // or
  @Update
  Single<Integer> updateAll(User[] user);
  // or
  @Delete
  Single<Integer> deleteAll(User[] user);
  // or
  @Delete
  Single<Integer> deleteAll(User[] user);
---
### Query의 문제점
* ```kotlin
  @Query(“SELECT * FROM Users WHERE id = :userId”)
  User getUserById(String userId);
* 차단 동기 호출  
* 매번 호출해줘야함 -> 귀찮음
##### 해결책으로 Flowable/Observable 사용
* ```kotlin
  @Query(“SELECT * FROM Users WHERE id = :userId”)
  fun getUserById(String userId): Flowable<User> 
* db에 유저가 없을때 아무 행동을 하지 않는다.
* db에 유저가 있다면 onNext를 트리거한다.
* db에 유저가 업데이트 된다면 Flowable는 자동적으로 방출한다.  


  
  
  
  
  
