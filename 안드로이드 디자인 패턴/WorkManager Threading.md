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
##### CoroutineWorker
* Kotlin 사용자에게 권장되는 구현입니다. 
* CoroutineWorker 인스턴스는 **백그라운드 작업을 위한 정지 함수를 제공**합니다. 기본적으로 이 유형은 맞춤설정할 수 있는 기본 Dispatcher를 실행합니다. CoroutineWorker 인스턴스의 스레딩에 관한 자세한 내용은 CoroutineWorker의 스레딩을 참고하세요.
RxWorker는 RxJava 사용자에게 권장되는 구현입니다. 기존 비동기 코드의 많은 부분을 RxJava에서 모델링하는 경우 RxWorker를 사용해야 합니다. 모든 RxJava 개념과 같이 원하는 스레딩 전략을 자유롭게 선택할 수 있습니다. RxWorker의 스레딩에서 RxWorker 인스턴스의 스레딩에 관해 자세히 알아보세요.
ListenableWorker는 Worker, CoroutineWorker, RxWorker의 기본 클래스입니다. 이 유형은 FusedLocationProviderClient 같은 콜백 기반의 비동기 API와 상호작용해야 하고 RxJava를 사용하지 않는 자바 개발자를 대상으로 합니다. ListenableWorker의 스레딩에서 ListenableWorker 인스턴스의 스레딩에 관해 자세히 알아보세요.
