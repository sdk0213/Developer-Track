retrofit
===
* [Retrofit을 사용하기전까지의 역사를 코드로 보았을때](https://pluu.github.io/blog/android/2016/12/25/android-network)
* OkHttp와 retrofit의 중요성
  * 그냥 HttpUrlConnection 으로 연결하고 받고 파싱하면 되지 왜 굳이 라이브러리를 사용하는거야???
  * 고려해야할 요소가 너무도 많기 때문이다. 예를들어서 내가 데이터르 요청을 했는데 이게 요청이 잘 됬는지 잘 안됬다면 다시 요청해야되는데 이때는 또 쓰레드를 어떻게 또 만들어야 되는것인지 루프는 어떻게 해야되는것인지 받은 데이터를 파싱하는것은 또 어떻게 어디 쓰레드에서 해야되는지 값을 확인했는데 이 오류코드가 또 뭔지 그리고 또 효율적으로 사용할려면 캐싱을 어떤식으로 할것인지.... 고려해야할 요소가 한두개가 아니다.
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
* 정의 - [문서 참고](http://devflow.github.io/retrofit-kr/)
 * ```java
   public interface GitHubService {
       @GET("users/{user}/repos")
       Call<List<Repo>> listRepos(@Path("user") String user);
       // getSomething() {
       //     @Header("X-Naver-Client-id") String clientId;
       //     @Header("X-Naver-Client-secret") String clientPw;
       //     @Path("type") String type;  // Path는 일부경로를 필요에 따라 동적바인딩이 필요할때
       //     @Query("query") String query;
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
   
   
retrofit(Rxjava 프로그래밍)
---
* retrofit의 장점은 애너테이션을 지원하는것이라 스프링처럼 애너테이션으로 API를 설계핤 있다.
* ```java
  public interface GithubServiceApi {
      @GET("repos/{owner}/{repo}/contributors")
      Call<List<Contributor>> getCallContributors(
          @Path("owner") String owner, @Path("repo") String repo);
      @GET("repos/{owner}/{repo}/contributors")
      Observable<List<Contributor>> getObContributors(
          @Path("owner") String owner, @Path("repo") String repo);
      @Headers({"Accept: application/vnd.gihub.v3.full+json"})
      @GET("repos/{owner}/{repo}/contributors")
      Call<List<Contributors>> getCallContributorsWithHeader(
          @Path("owner") String owner, @Path("repo") String repo);
  }
* getCallContributors() 메서드를 호출할면 기본 URI와 결합하여 URL 을 생성한다.
  * 각각 owner/repo -> android/Rxjava 을 전달할경우 https://api.github.com/repos/android/RxJava/contributors 로 해석
 
* ```java 
  public class RestfulAdapter {
      private static final String BASE_URI = "https://api.github.com/";
      
      public GithubServiceApi getSimpleApi() {
          Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
          return retrofit.create(GithubServiceApi.class);
      }
      
      public GithubServiceApi getServiceApi() {
          HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
          logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
          
          OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build();
          
          Retrofit retrofit = new Retofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URI)
            .build();
            
          return retrofit.create(GithubServiceApi.class);
          
      }
      
      private RestfulAdpater() { }
      
      private static class Singleton {
          private static final RestfulAdapter instance = new RestfulAdapter();
      }
      
      public static RestfulAdapter getInstance() {
          return Singleton.instance;
      }
   }

* getSimpleApi() 와 getServiceApi() 는 REST API 스택의 디버깅 가능여부에 따른 차이이다.
  * getSimpleApi()
     * Retrofit에 포함된 OkHttpClient 클래스를 사용
  * getServiceApi()
     * OkHttpClient.Builder() 객체를 생성하여 로그를 위한 인터셉터를 설정해줘야함
     * **하지만 인터셉터르 설정해줄경우 네트워크를 통해 이동하는 데이터나 에러 메시지를 실시간으로 확인할 수 있다.**
     * 더 쉽게 말해서 로그 확인해보력고 OkHttp 객체르 따로 만든것

* JSON 응답에서 필요한 데이터 추출
  * ```java
    public class Contributor {
        String login;
        String url;
        int id;
      
        @Override public class toString() {
            return "login : " + login + "id : " + id + "url : " + url;
        }
    }

* GSON 에서 디코딩하고 원하는값(login, url, ir) 만 추출
