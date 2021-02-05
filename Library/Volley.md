Volley
===
* [Goolge Volley Document](https://ju-hy.tistory.com/66)
* [Google Volley Github](https://github.com/google/volley)
* Android 앱의 네트워킹을 더 쉽고, 무엇보다도 더 빠르게 하는 HTTP 라이브러리
* 기존 HttpURLConnection 작성할때의 단점
  * 직접 쓰레드를 구현해야함
  * 코드가 많아짐
  * 여간 귀찮은 작업이 아닐수 없다.
* 장점
  1. 네트워크 요청의 자동 예약.
  2. 여러 개의 동시 네트워크 연결
  3. 표준 HTTP 캐시 일관성을 갖춘 투명한 디스크 및 메모리 응답 캐싱
  4. 요청 우선순위 지정 지원
  5. 취소 요청 API. 단일 요청을 취소하거나 취소할 요청의 블록 또는 범위를 설정할 수 있습니다.
  6. 용이한 맞춤설정(예: 재시도, 백오프)
  7. 강력한 정렬 기능을 이용하여 네트워크에서 비동기식으로 가져온 데이터로 UI를 올바로 채우는 작업을 쉽게 실행할 수 있음.
  8. 디버깅 및 추적 도구.
* 단점
  * 모든 응답을 메모리에 유지하므로 대규모 다운로드 또는 스트리밍 작업에는 적합하지 않다.
    * [DownloadManager](https://developer.android.com/reference/android/app/DownloadManager?hl=ko)를 사용해야한다.
* 적용
  * ```gradle
    dependencies {
        ...
        implementation 'com.android.volley:volley:1.1.1'
    }
  * 당연하겠지만 인터넷 작업이니 android.permission.INTERNET 있어야된다.
* 원리
  * Request객체를 RequestQueue(요청큐)에 담으면 알아서 쓰레드로 서버랑 통신하고 그 결과를 반환해준다.
  * 결과값 반환은 Request에 등록된 Listener로 온다.
  * ![](img/volley_process.png)
* 요청
  ```java
  // https://developer.android.com/training/volley/simple?hl=ko#java 문서상에 있는 코드입니다.
  final TextView textView = (TextView) findViewById(R.id.text);
  // ...

  // Instantiate the RequestQueue.
  RequestQueue queue = Volley.newRequestQueue(this);
  String url ="http://www.google.com";

  // Request a string response from the provided URL.
  StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
              new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
          // Display the first 500 characters of the response string.
          textView.setText("Response is: "+ response.substring(0,500));
      }
  }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
          textView.setText("That didn't work!");
      }
  });

  // Add the request to the RequestQueue.
  queue.add(stringRequest);
* 취소
  ```java
  // https://developer.android.com/training/volley/simple?hl=ko#java 문서상에 있는 코드입니다.
  
  // 태그 설정
  public static final String TAG = "MyTag";
  StringRequest stringRequest; // Assume this exists.
  RequestQueue requestQueue;  // Assume this exists.

  // Set the tag on the request.
  stringRequest.setTag(TAG);

  // Add the request to the RequestQueue.
  requestQueue.add(stringRequest);
    
  // 해당 태그는 취소
  // 여기서 cancelall 로 call 하면 Volley에서 요청 핸들러가 호출되지 않도록 보장한다.
  // 이미 진행되고 있는건에서는 취소 불가능한지 확인 필요(아마도 불가능할듯으로 싶음)
  @Override
  protected void onStop () {
      super.onStop();
      if (requestQueue != null) {
          requestQueue.cancelAll(TAG);
      }
  }
    
    
  }, new Response.ErrorListener() {ㅣ
  }, new Response.ErrorListener() {ㅅㅗ
  }, new Response.ErrorListener() {
