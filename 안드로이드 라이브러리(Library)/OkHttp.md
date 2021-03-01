OkHttp
===
* [OkHttp 라이브러리 github 다운로드 및 문서](https://github.com/square/okhttp)
* HTTP 및 HTTP/2 통신을 보다 쉽게 할 수 있도록 다양한 기능을 제공해주는 Android 및 Java 용 라이브러리
* 최소 사양
  * Android 5.0+ (API level 21+)
  * Java 8+
* 객체 생성
  ```java
  OkHttpClient client = new OkHttpClient();
  
Get
---
* ```java
  OkHttpClient client = new OkHttpClient();

  String run(String url) throws IOException {
    Request request = new Request.Builder()
       .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
      return response.body().string(); // 결과값 얻고 파싱하기
    }
  }
  
POST
---
* ```java
  OkHttpClient client = new OkHttpClient();

  String url = SERVER_CONFIGURATION.ADDRESS + ":" +
      SERVER_CONFIGURATION.PORT + "/v1/updateMetaInfo";

  Gson gson = new Gson();
  String json = gson.toJson(metaInfo);

  Request request = new Request.Builder()
      .url(url)
      .post(RequestBody.create(MediaType.parse("application/json"), json))
      .build();

  Response response = client.newCall(request).execute();
 
[PUT 예제](https://snowdeer.github.io/android/2017/03/03/get-and-post-and-put-using-okhttp/)
---
