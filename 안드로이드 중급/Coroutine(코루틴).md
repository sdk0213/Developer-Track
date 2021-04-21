# Coroutine(코루틴) - [공식문서](https://developer.android.com/kotlin/coroutines?hl=ko), [출처 - wooooooak](https://wooooooak.github.io/kotlin/2019/08/25/코틀린-코루틴-개념-익히기/)
### 코루틴 : 비동기적으로 실행되는 코드를 간소화하기 위해 Android에서 사용할 수 있는 동시 실행 설계 패턴
* Co(협력) + Routine(테스트, 함수)
### 1. 협력형 멀티 태스킹
* 도입조건 - [출처 - limgyumin님의 medium](https://medium.com/@limgyumin/코틀린-코루틴-제어-5132380dad7f)
  * 이미 RxJava를 사용하고 있고 잘 동작 한다면 RxJava 를 사용
  * 아키텍처가 Reactive Stream 을 기반으로 하는 경우 RxJava 를 사용
  * 프로젝트가 Kotlin Native 를 사용한 멀티플랫폼 인 경우 코루틴을 사용
  * 코드 베이스가 Java / Kotlin 인 경우 RxJava를 사용
  * 이외에는, 코루틴 을 사용
* 코루틴
  * [Thread, Async, Rxjava 와 의 차이점](https://thdev.tech/kotlin/2018/10/04/Kotlin-Coroutines/) 를 확인해보면 가독성이 매우 높다는것을 파악가능하다.
  * **return문이나 마지막 닫는 괄호를 만나지 않더라도 언제든지 중간에 나갈 수 있다.**
  * 언제든지 다시 나갔던 그 지점으로 들어올 수 있다
  * 코루틴 내에서는 진입할 수 있는 진입점도 여러개고, 함수를 빠져나갈 수 있는 탈출점도 여러개이다.
  * 하나의 쓰레드에 여러 개의 코루틴이 동시에 실행가능하다.
* 예제
  * ```kotlin
    fun drawPerson(){
     startCoroutine{
       drawHead()
       drawBody()
       drawLegs()
    }
  
    suspend fun drawHead() {
     delay(2000)
     ...
    }
  
    suspend fun drawBody() {
     delay(5000)
     ...
    }
  
    suspend fun drawLegs() {
      delay(3000)
      ...
    }
  * startCoroutine {} : 코루틴 블럭으로 편의상 정해진 이름을 실제 이런 이름을 가지지는 않음
  * suspend 키워드를 가진 함수르 만나게 되면 더 이상 코드를 실행하지 않고 코루틴 블락을 탈출한다.
  * drawHead() 부분에서 더 이상 아래 코드를 실행하지 않고 drawPerson()이라는 코루틴 함수를 (잠시)탈출 힌다.

### 2. 동시성 프로그래밍
* 병렬성 프로그래밍과의 차이점
  * 동시성
    * 두개의 도화지에 그림을 그릴때 팔이 하나 펜이 하나라서 왔다갔다 하면서 작동함
    * 겉으로 보기에는 팔이 두개인것처럼 보임
  * 병렬성
    * 두개의 도화지에 그림을 그릴때 팔이 하나 펜이 두개
    * 실제로 팔이 두개
* 코루틴은 동시성 프로그래밍이다.

---
### 키워드 설명
##### suspend
* 코루틴에서 실행할 수 있는 메소드를 만드려면 함수를 정의할 때 suspend를 줘야한다. 
* suspend 함수는 안에서 다른 코루틴을 실행하는것도 가능
* Suspend 함수는 그 함수가 비동기 환경(Asynchronous)에서 사용될 수 있다는 의미를 내포
* suspend 함수는 다른 suspend 함수, 혹은 코루틴 내에서만 호출할 수 있고, 아닌 곳에서 그냥 호출하려고 하면 warning
##### delay
* 괄호 안의 ms만큼 실행을 멈춘다
* Thread.sleep과의 차이점
  * 해당 쓰레드 안에 있는 코루틴을 다 멈추게 된다. 코루틴 안에서 쓰레드 슬립을 호출하지 않는 편이 좋겠다.
##### withContext
* withContext() 다음 코드를 수행하지 않는다.
  * ```kotlin
    GlobalScope.launch(Dispatchers.IO) {
        Log.d(TAG, "Do Something on IO thread")
        val name = withContext(Dispatchers.Main){
            sleep(2000)
            "My Name is Android"
        }
        
        Log.d(TAG,"Result: $name")
    }
    
    // result:
    // 10-26 22:44:16.488  9723  9752 D MainActivity: Do something on IO thread
    // 10-26 22:44:18.649  9723  9752 D MainActivity: Result : My name is Android

* 해당 코드블럭을 특정 Context에서 실행하고 싶을 때 사용하는 용도 (네트워크 작업을 위한 IO Dispatcher 등)
  * but to temporarily switch the context for the current coroutine
  * 
* ```kotlin
  CoroutineScope(IO).launch {
    val resultStr = getResultFromApi() //resultStr = "ok"

    withContext(Main) {
        textView.text = resultStr
    }
  }
##### CoroutineScope
* coroutineScope는 withContext의 한 유형이다.
* withContext(this.coroutineContext) 와 본질적으로 같은 의미이다.
* 에러처리 등의 목적으로 특정 코드를 하나의 블럭으로 묶고 싶을 때 사용
* ```kotlin
  val scope = CoroutineScope(Dispatchers.Main)
  scope.launch {
      // Foreground
  }
  scope.launch(Dispatchers.Default) {
      // Background
  }
##### GlobalScope
* [GlobalScope 의 사용을 비추천하는 이유](https://thdev.tech/kotlin/2019/04/05/Init-Coroutines/)
* GlobalScope 는 CoroutineScope 의 한 종류입니다. 미리 정의된 방식으로 프로그램 전반에 걸쳐 백그라운드 에서 동작합니다.
* GlobalScope은 싱글톤으로 만들어져있다.
* 앱이 실행될 때부터 앱이 종료될 때까지 Coroutine을 실행시킬 수 있는 Scope이지만 최대한 사용자제
##### withTimeoutOrNull
* 네트워크 타임아웃 처리는 withTimeoutOrNull(timeMillis) 를 이용하면 손쉽게 처리할 수 있다
---
### Activity, ViewModel Scope 만들기
* ```kotlin
  class MainActivity : AppCompatAcitivty(), CoroutineSceop {
      lateinit var job: Job
      
   override val coroutineContext : CoroutineContext
      get() = Dispatchers.Main + Job
   
   
   ... onCreate..{
       job = Job()
   }
   
   ... onDestory..{
       job.cancel()
   }
   
   fun loadDataFromUI() = launch { // <- extension on current activity, launched in the main thread
       val ioData = async(Dispatchers.IO) { // <- extension on launch scope, launched in IO dispatcher
           // blocking I/O operation
       }
       // do something else concurrently with I/O
       val data = ioData.await() // wait for result of I/O
       draw(data) // can draw in the main thread
   }
   

---
### 코드 절약
##### ASYNCTASK
* ```kotlin
  val asyncTask = object : AsyncTask<Unit, Unit, String>() {
     override fun doInBackground(vararg params: Unit?): String {
         return load()
     }

     override fun onPreExecute() {
         super.onPreExecute()
     }

     override fun onPostExecute(result: String?) {
         super.onPostExecute(result)
     }
  }
  asyncTask.execute()
##### RXJAVA2
* ```kotlin
  load()
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
      }
##### COROUTINE
* ```kotlin
  CoroutineScope(Dispatchers.Main).launch {
    var data = ""
    CoroutineScope(Dispatchers.Default).async {
        // background thread
        data = load()
    }.await()
  }
---
### Exception 처리
* ```kotlin
  GlobalScope.launch(Dispatchers.IO + handler) {    // 1
      launch {
          throw Exception()   // 2
      }
  }

  val handler = CoroutineExceptionHandler { coroutineScope, exception ->   // 3
      Log.d(TAG, "$exception handled!")
  }
---
# 사용법 [출처 - limgyumin - meduim](https://medium.com/@limgyumin/코틀린-코루틴의-기초-cac60d4d621b)
* CoroutineScope: 코루틴의 범위, 코루틴 블록을 묶음으로 제어할수 있는 단위
* CoroutineContext: 코루틴을 어떻게 처리 할것인지 에 대한 여러가지 정보의 집합
* Dispatcher: CoroutineContext 의 주요 요소
  * Dispatchers.Default: CPU 사용량이 많은 작업에 사용합니다. 주 스레드에서 작업하기에는 너무 긴 작업 들에게 알맞습니다.
  * Dispatchers.IO: 네트워크, 디스크 사용 할때 사용합니다. 파일 읽고, 쓰고, 소켓을 읽고, 쓰고 작업을 멈추는것에 최적화되어 있습니다.
  * Dispatchers.Main: 안드로이드의 경우 UI 스레드를 사용합니다.
* launch, async: CoroutineScope 의 아래와 같이 반환되는 함수르 사용해서 수행 결과를 받거나, 작업이 끝나기를 대기하거나, 취소하는 등의 제어가 가능
  * launch: Job 객체 반환, 반환값 X
  * async: Deferred 객체 반환,  반환값 X
* 외부 코루틴 블럭안에 내부 코루티 블럭이있을 경우 외부 코루틴 블록이 멈춰도, 내부 코루틴 블록은 끝까지 수행된다.
### 사용순서
1. 사용할 Dispatcher 를 결정
2. Dispatcher 를 이용해서 CoroutineScope 만듦
3. CoroutineScope 의 launch 또는 async 에 수행할 코드 블록을 넘기기

### 코드 [출처 - limgyumin - meduim](https://medium.com/@limgyumin/코틀린-코루틴의-기초-cac60d4d621b)
* launch
  * ```kotlin
    val job1 : Job = launch {
        var i = 0
        while (i < 10) {
            delay(500)
            i++
        }
    }

    val job2 = launch {
        var i = 0
        while (i < 10) {
            delay(1000)
            i++
        }
    }

    job1.join() // join -> 스레드 끝날때까지 다음 코드로 가지않고 대기
    job2.join()
    // 조인을 위 처럼 순서대로 작성할수도 있지만 아래 코드처럼 한번에도 가능하다.
    joinAll(job1, job2
  * 위와 같은 코드르 lauch 를 어디서 할것인지로 정해서 같게 할수도있다. 아래 코드
    * ```kotlin
      val job1 = launch {
          var i = 0
          while (i < 10) {
              delay(500)
              i++
          }
      }

      // 위 블록 과 같은 job1 객체를 사용
      launch(job1) {
          var i = 0
          while (i < 10) {
              delay(1000)
              i++
          }
      }

      // 같은 job 객체를 사용하게 되면
      // joinAll(job1, job2) 와 같다
      job1.join()
* async
  * Deferred 객체를 반환합니다
  * 위의 코드에서 join을 await으로 변경하는것을 제외하고는 전부 똑같다.
  * ```kotlin
    val deferred1 = async {
        var i = 0
        while (i < 10) {
            delay(500)
            i++
        }

        "result1"
    }
    
    val deferred2 = async { // 또는 val deferred2 = async --->  async(deferred1){ 도 가능
        var i = 0           
        while (i < 10) {
            delay(1000)
            i++
        }

        "result2"
    }

    val result1 = deferred1.await()
    val result2 = deferred2.await()
        
    println("$result1 , $result2") // result1 , result 2 출력

### 지연 실행
* ```kotlin
  val job = launch (start = CoroutineStart.LAZY) {
      ...
  }
  // 또는
  val deferred = async (start = CoroutineStart.LAZY) {
      ...  
* ```kotlin
   println("start")

    val deferred = async(start = CoroutineStart.LAZY) {
        var i = 0
        while (i < 5) {
           delay(500)
           println("lazy async $i")
           i++
        }
    }

    deferred.await()
    // deferred.start()
    
    println("end")
    
    // deferred.await() : 출력결과
    // start
    // lazy async 0
    // lazy async 1
    // lazy async 2
    // lazy async 3
    // lazy async 4
    // end
    
    // deferred.start() : 출력결과
    // start
    // end
    // lazy async 0
    // lazy async 1
    // lazy async 2
    // lazy async 3
    // lazy async 4

### runBlocking()
* runBlocking 코루틴 블록이 사용하는 스레드는 현재 runBlocking() 함수가 호출된 스레드가 되버리기 떄문에 메인에 할경우 ANR 가능성
    println("end")리
    println("end")ㄱ
    println("end")
    println("end") 
    println("end")
    
### [작업취소 관련 내용 더보기 링크](https://medium.com/@limgyumin/코틀린-코루틴-제어-5132380dad7f)
