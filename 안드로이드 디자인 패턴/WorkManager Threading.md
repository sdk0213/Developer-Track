# WorkManager Threading
---
### 기본 작업 유형
##### Worker
* 가장 간단한 구현
* WorkManager는 백그라운드 스레드(재정의할 수 있음)에서 이를 자동으로 실행합
* ```kotlin
  class UploadWorker(appContext: Context, workerParams: WorkerParameters):
         Worker(appContext, workerParams) {
     override fun doWork(): Result {

         // Do the work here--in this case, upload the images.
         uploadImages()
    
         // Indicate whether the work finished successfully with the Result
         return Result.success()
     }
  }
##### CoroutineWorker - [출처](https://medium.com/androiddevelopers/workmanager-meets-kotlin-b9ad02f7405e)
* Kotlin 사용자에게 권장되는 구현입니다. 
* CoroutineWorker 인스턴스는 **백그라운드 작업을 위한 정지 함수를 제공**합니다. 
* 비동기로 진행됨
* 진행중....
