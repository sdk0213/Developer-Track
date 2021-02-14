retrofit
===
* OkHttp와 retrofit의 중요성
  * 그냥 HttpUrlConnection 으로 연결하고 받고 파싱하면 되지 왜 굳이 라이브러리를 사용하는거야???
  * 고려해야할 요소가 너무도 많기 때문이다. 예를들어서 내가 데이터르 요청을 했는데 이게 요청이 잘 됬는지 잘 안됬다면 다시 요청해야되는데 이때는 또 쓰레드를 어떻게 또 만들어야 되는것인지 루프는 어떻게 해야되는것인지 받은 데이터를 파싱하는것은 또 어떻게 어디 쓰레드에서 해야되는지 값을 확인했는데 이 오류코드가 또 뭔지 그리고 또 효율적으로 사용할려면 캐싱을 어떤식으로 할것인지.... 고려해야할 요소가 한두개다.
  * 위의 모든것들을 전부 고려해서 개발한다면 아무 문제없지만 대게 쓰레드로부터 안전하게 개발하거나 효율적이 알고리즘으로 구성되 통신을 하는것은 쉽지 않다. 그리고 통신 한번한다고 코드의 길이가 엉청나게 길어진다.
  * **이를 방지하기위해 뛰어난 개발자들이 만들어준 라이브러리를 사용하면 아주 편하고 속도도 좋고 무엇보다도 코드도 간단해지고 통신에 고려해야할 요소가 적어지니 어플리케이션에 핵심개발에 더 시간을 투자할수있다.**
* [Retrofit 다운로드 및 문서](https://square.github.io/retrofit/)
* Thread가 중요한 네트워크 통신에서 Thread Safe 한 Http 클라이언트이다(A type-safe HTTP client for Android and Java)
* 서버 연동과 응답 전체를 관리하는 라이브러리
  * retofit = OkHttp + 응답관리 라고 생각하면된다.
  * OkHttp에 의존적이다.
* 장점
  * 빠른 성능 - Asynctask 를 사용하지 않고 자체적인 비동기 실행 및 스레드 관리를 통해 3~10배정도 빠르다고함
  * 간단한 구현 - 반복된 작업은 라이브러리에 처리
  * 가독성 - annotation 사용
  * 동기/비동기 구현
* 구성요소
  * DTO
    * 모델/JSON 타입변화에 사용
  * Interface
    * 사용하 HTTP CRUD등을 정의해놓은 인터페이스
  * Retrofit.Builder
    * interface를 사용할 인스턴스, baseUrl, Converter 설정
* 정의
 * ```java
   public interface GitHubService {
       @GET("users/{user}/repos")
       Call<List<Repo>> listRepos(@Path("user") String user);
   }
 
 
   ...
   ..
   
   Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build();

   GitHubService service = retrofit.create(GitHubService.class);
   Call<JsonArray> request = service.listRepos("sudeky");
   request.enqueue(new Callback<JsonArray>() {
       @Override
       public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
           // 성공시...
       }
       @Override
       public void onFailure(Call<JsonArray> call, Throwable t) {
           // 실패시... 
       }
   });
 
   // 정의된 service 부터 retrofit을 사용함
   
