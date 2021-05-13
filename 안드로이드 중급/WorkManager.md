# WorkManager - [출처는 공식문서](https://developer.android.com/topic/libraries/architecture/workmanager?hl=ko#deferrable)
---
### 무엇을 하는가?
* 지연 가능한 비동기 작업을 쉽게 예약할 수 있는 API 이다.
* 사진과 같이 workmanager 가 sdk 버전에 따라서 알아서 처리해준다.
* ![](https://developer.android.com/images/topic/libraries/architecture/workmanager/overview-criteria.png?hl=ko)
---
### 안드로이드에서 백그라운드 제약사항
* 안드로이드에서 백그라운드 작업시에는 다음과 같은 제약사항이 존재한다.
  * 1. Android 6.0 DozeMode, 앱 대기
  * 2. Android 7.0 암시적 브로드 캐스트 제한, 이동 중 잠자기 모드도입
  * 3. Android 8.0 위치, wakelock을 해제
  * 4. Android 9.0 시스템이 앱의 리소스 요청 우선순위에 따라서 지정하도록 변경
  * 5. Android 10.0 앱이 백그라운드 실행될때 Activity 시작 시점 제한
---
### Workmanager를 사용할수 없는 경우
* 물건 구입 과정에서의 결제 진행 등 즉시 처리해야 하는 작업
* UI를 빠르게 변경해야 하는 작업
* 긴 다운로드 작업
* 즉시 실행되어야 하는경우
* 알람 앱처럼 정확한 시간에 작업 수행이 필요 -> 이 경우는 AlarmManager 사용
* 알고리즘
  * Loop-running HTTP Downloads -> DownloadManager
  * non - Deferrablework -> Foreground service
  * Triggered by system condition -> workmanager
  * run at precise time -> alarmmanager
  * etc... -> workmanager
##### 이럴때 사용
* 앱이 종료되거나 기기가 재시작되어도 실행되시에 비동기 작업으로 구현하는 것들
* 이미지를 서버에 업로드
* 데이터를 분석하고 이를 데이터베이스에 저장
* Executing long running background tasks like uploading media
* Parsing and storing data in database.
* Critical Tasks which needs to survive process deaths

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
##### 시스템에 작업 전달하기
* ```kotlin
  val myWorkRequest = ...
  WorkManager.getInstance(myContext).enqueue(myWorkRequest)
* 이때 작업자가 실행될 정확한 시간은 WorkRequest에 사용되는 제약 조건과 시스템 최적화에 따라 달라진다.
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
### 작업 상태
##### BLOCKED
* 실행될수 있는 전제조건 아직 미달성되서 차단된 상태
##### CANCELLED
* WorkRequest가 취소됨 -> 종속작업 전부 CANCELLED
##### ENQUEUED
* Constraints 충족 및 대기 큐에 들어감 -> 실행가능상태
##### FAILED
* WorkRequest가 실패된 상태로 완료 -> 종속작업 전부 FAILED
##### RUNNING
* 말그대로 현재 작업 진행중
##### SUCCEEDED
* WorkRequest 성공적으로 완료
* PeriodicWorkRequests는 이 상태 없음
##### 작업상태를 관찰하는 법
* ID, 태크, 고유 이름으로 찾는다.
---
### 작업 체이닝 - [코드 출처](https://jeongupark-study-house.tistory.com/36)
* 작업을 연속적으로 진행할수 있게끔 지원
##### beginWith
* 진행할 목록
##### then
* 진행이 끝나고 진행할 다음것
##### enqueue
* 큐에 넣기
##### 예제코드
* ```kotlin
  class MyWorker_A_1 (appContext : Context, workerParams: WorkerParameters) : 
     Worker(appContext,workerParams){ 
         override fun doWork(): Result {
         // 대충 카운트를 출력 하는코드
     }
  }
   
  ... 
  
  class MyWorker_A_2 : Worker... // 이하생략
  ...
  class MyWorker_A_3 : Worker... // 이하생략
  ...
  class MyWorker_A_4 : Worker... // 이하생략
  ...
  class MyWorker_A_5 : Worker... // 이하생략
  
  // dowork
  val A_1 = OneTimeWorkRequestBuilder<MyWorker_A_1>().build() 
  val A_2 = OneTimeWorkRequestBuilder<MyWorker_A_2>().build()
  val A_3 = ...
  val B = ...
  val C = ...
  
  WorkManager.getInstance(context!!).beginWith(listOf(A_1,A_2,A_3)).then(B).then(C).enqueue()
##### Input Mergers
* 상위 OneTimeWorkRequests에서 값을 하위 항목에 전달
* workmanager에서 제공해주는 두가지 유형의 InputMergers
  * OverwritingInputMerger
    * 전부 다 가져옴
  * ArrayCreatingInputMerger
    * 병합해서 가져옴
  * [자세한 차이점은 여기 블로그 참고](https://medium.com/@limgyumin/workmanager-잘-써보기-1643a999776b)
* ```kotlin
  val A_4 = OneTimeWorkRequestBuilder<MyWorker_A_4>().setInputMerger(ArrayCreatingInputMerger::class).build()

  MyWorker_A_1 : Worker...{
      val outputData = workDataOf(Pair("outputData", "outputData from doWork"))
  }
  MyWorker_A_2 : Worker... // 코드 동일
  MyWorker_A_3 : Worker... // 코드 동일

  MyWorker_A_4 : Worker(...
  
    val list = inputData.getStringArray("outputData") list?.let { Log.d("TEST","MyWorker_B outputData list : ${list[0]}, ${list[1]}, ${list[2]}") }

  }
 
---
### [기타 정보는 공식문서 참고](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/managing-work?hl=ko)
### 충돌 해결 정책
* https://developer.android.com/topic/libraries/architecture/workmanager/how-to/managing-work?hl=ko#conflict-resolution
### 작업 취소
* https://developer.android.com/topic/libraries/architecture/workmanager/how-to/managing-work?hl=ko#stop-worker

### 외부에서 데이터 넣어주기
* Pass params as follow
* ```java
  Data.Builder data = new Data.Builder();
  data.putString("SyncMaster", syncModuleName);

  OneTimeWorkRequest syncWorkRequest =
          new OneTimeWorkRequest.Builder(SyncWorker.class)
                  .addTag("Sync")
                  .setInputData(data.build())   // <-------- INPUT TO WORKER
                  .setConstraints(builder.build()) 
                  .build();
                  
                  
  // WorkManager
  
  public class SyncWorker extends Worker {

    private static final String TAG = "MyWorker";

    public SyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork for Sync");
        String syncTable = getInputData().getString("SyncMaster");  // <-------- GET FROM OUT
        return Result.success();
    }
  }
