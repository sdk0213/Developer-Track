# WorkManager - [출처는 공식문서](https://developer.android.com/topic/libraries/architecture/workmanager?hl=ko#deferrable)
---
### 무엇을 하는가?
* 지연 가능한 비동기 작업을 쉽게 예약할 수 있는 API 이다.
* 사진과 같이 workmanager 가 sdk 버전에 따라서 알아서 처리해준다.
* ![](https://developer.android.com/images/topic/libraries/architecture/workmanager/overview-criteria.png?hl=ko)
---
### [작업이 실행되는 최적의 조건을 선언적으로 정의](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work?hl=ko#constraints)
* **자세한 설명 및 코드는 위 링크 참고**
* 예를들면 다음과 같은 상황에서 작업이 실행되도록 조건을 정의할수있다.
  * 일회성 및 반복 작업 예약
  * Wi-Fi 또는 충전과 같은 작업 제약조건 설정
  * 작업 실행의 최소 지연 보장
  * 재시도 및 백오프 전략 설정
  * 작업에 입력 데이터 전달
  * 태그를 사용하여 관련 작업 그룹화
* ```kotlin
  val myWorkRequest = ...
  WorkManager.getInstance(myContext).enqueue(myWorkRequest)
##### 일회성 및 반복 작업 예약
* ```kotlin
  val myWorkRequest = OneTimeWorkRequest.from(MyWork::class.java)
  
  // 복잡한 작업은 아래와 같이 builder()를 사용
  
  val uploadWorkRequest: WorkRequest =
   OneTimeWorkRequestBuilder<MyWork>()
       // Additional configuration
       .build()
  
##### 주기적 작업 예약
* 최소 15분
* 상황
  * 데이터를 백업
  * 최신 콘텐츠 다운로드
  * 로그를 서버에 업로드
* val saveRequest =
       PeriodicWorkRequestBuilder<SaveImageToFileWorker>(1, TimeUnit.HOURS)
    // Additional configuration
           .build()

##### 샘플코드 (wifi + device charged)
* ```kotlin
  val constraints = Constraints.Builder()
   .setRequiredNetworkType(NetworkType.UNMETERED)
   .setRequiresCharging(true)
   .build()

  val myWorkRequest: WorkRequest =
     OneTimeWorkRequestBuilder<MyWork>()
         .setConstraints(constraints)
         .build()

##### 지연된 작업
* ```kotlin
  val myWorkRequest = OneTimeWorkRequestBuilder<MyWork>()
    .setInitialDelay(10, TimeUnit.MINUTES)
    .build()

##### 재시도 및 백오프 정책
* ```kotlin
  val myWorkRequest = OneTimeWorkRequestBuilder<MyWork>()
    .setBackoffCriteria(
        BackoffPolicy.LINEAR,
        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
        TimeUnit.MILLISECONDS)
    .build()

##### 태그 작업
* ```kotlin
  val myWorkRequest = OneTimeWorkRequestBuilder<MyWork>()
    .addTag("cleanup")
    .build()

##### 입력 데이터 할당
* ```kotlin
  // Define the Worker requiring input
  class UploadWork(appContext: Context, workerParams: WorkerParameters)
     : Worker(appContext, workerParams) {
  
     override fun doWork(): Result {
         val imageUriInput =
             inputData.getString("IMAGE_URI") ?: return Result.failure()

         uploadFile(imageUriInput)
         return Result.success()
     }
     ...
  }

  // Create a WorkRequest for your Worker and sending it input
  val myUploadWork = OneTimeWorkRequestBuilder<UploadWork>()
     .setInputData(workDataOf(
         "IMAGE_URI" to "http://..."
     ))
     .build()
---
### [작업 관리](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/managing-work?hl=ko)
* 기본
  * ```kotlin
    val myWork: WorkRequest = // ... OneTime or PeriodicWork
    WorkManager.getInstance(requireContext()).enqueue(myWork)
* 고유 작업은 특정 이름의 작업 인스턴스가 한 번에 하나만 있도록 보장하는 강력한 개념
* [충돌 해결 정책](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/managing-work?hl=ko#conflict-resolution)
* [작업 관찰](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/managing-work?hl=ko#observing)
* [실행 중인 작업자 중지](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/managing-work?hl=ko#stop-worker)
---
### 기타 정보는 공식문서 참고
