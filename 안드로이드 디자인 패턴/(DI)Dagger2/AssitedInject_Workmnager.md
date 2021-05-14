# AssitedInject With Workmnager
### WorkManager
* WorkManager 를 Dagger2를 사용하였을때 파라미터를 추가하기에는 까다로운 면이 있다고 한다.
* 왜냐하면 WorkManger의 생성은 다음과 같이 이루어지기 때문이다.
  * ```kotlin
    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
  
    // 또는 주기적 작업 예약
    val request = PeriodicWorkRequestBuilder<SeedDatabaseWorker>().build()
  * RequestBuilder에 의해 생성되기 때문이다.
### WorkManager
   
