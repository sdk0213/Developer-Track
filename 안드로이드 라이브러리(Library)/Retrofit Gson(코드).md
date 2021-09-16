### buildGradle
* ```gradle
  implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
---  
### DI
* 변환팩토리에 GsonConverterFactory 추가
  * .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
* ```kotlin
  @Singleton
  @Provides
  @Named("WeatherRetrofit")
  fun provideWeatherRetrofit(okHttpClient: OkHttpClient): Retrofit {
      return Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
          .baseUrl(ApiClient.WEATHER_BASE_URL)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()
          .client(okHttpClient)
          .build()
  }
  
  // okhttp 인스턴스 생성
  @Singleton
  @Provides
  fun provideOkHttpClient(htpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
      return OkHttpClient.Builder()
          .addInterceptor(htpLoggingInterceptor)
          .connectTimeout(20, TimeUnit.SECONDS)  // 커넥션 타임아웃
          .readTimeout(20, TimeUnit.SECONDS)
          .writeTimeout(20, TimeUnit.SECONDS)
          .retryOnConnectionFailure(true)
          .build()
  }

  // 로그를 찍기 위한 로깅 인터셉터 설정
  @Provides
  fun provideHttpLogginInterceptor(): HttpLoggingInterceptor {
      val loggingInterceptor = HttpLoggingInterceptor { message ->
          Log.d("Amatda NetModule", "request/received data to/from Server : ${message}")
      }
      loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      return loggingInterceptor
  }
---
### API
* RxJava 는 기호에 따라서
* WeatherJson 가 POJO
* ```kotlin
  // 날씨 검색 API
  @GET("getVilageFcst?")
  fun getWeather(
      @Query("serviceKey") serviceKey: String = URLDecoder.decode(ApiClient.WEATHER_API_KEY, "UTF-8"),
      @Query("numOfRows") numOfRows: String,
      @Query("pageNo") pageNo: String,
      @Query("dataType") dataType: String,
      @Query("base_date") base_date: String, // 호출하는 시각의 날짜 즉, (반드시 지금 기준)
      @Query("base_time") base_time: String, // 호출하는 시각의 시간 지금 기준으로 전 것으로 최신화
      @Query("nx") nx: String,
      @Query("ny") ny: String
  ) : Single<Response<WeatherJson>>
---
### POJO
* ```kotlin
  
  data class WeatherJson(
      val response: WeatherJsonResponse
  )

  data class WeatherJsonResponse(
      val header: WeatherJsonHeader,
      val body: WeatherJsonBody
  )

  data class WeatherJsonHeader(
      val resultCode: Int,
      val resultMsg: String
  )

  data class WeatherJsonBody(
      val dataType: String,
      val numOfRows: String,
      val pageNo: String,
      val totalCount: String,
      val items: WeatherJsonItems
  )

  data class WeatherJsonItems(
      val item: List<WeatherEntity>
  )
  
