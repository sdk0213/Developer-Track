### Retrofit HTTP 전체 응답 확인하기 - [출처는 th-biglight 님의 tistory](https://th-biglight.tistory.com/11)
* Retrofit 은 응답값이 무엇인지 미리 알아야 사용가능한데 서버 개발자랑 소통이 힘든상황이라면 전체로그를 찍어서 확인해야함
* 그럴때는 Callback 제네릭 타입으로 Object 나 Any 를 사용하면 전체가 호출이 된다.
* ```java
  // API 인터페이스 부분
  @GET("v1/user")
  Call<Object> getUserData();
  
  // API 호출 부분
  Call call = userAPIInterface.getUserData();
  call.enqueue(new Callback() {
      @Override
      public void onResponse(Call call, Response response) {
        // 아래는 Body 부분으 Json 으로 변경한것
        // 또는 response 를 통채로 String 으로 변환시 HTTP 전체 응답이 가능하다.
      	Log.e(TAG, "body: " + new Gson().toJson(response.body()));
      }

      @Override
      public void onFailure(Call call, Throwable t) {}
  });
