Options
===
RequestBuilder options
---
* 대부분의 옵션들은 RequestBuilder 객체에 직접 적용 할 수 있다.
* 사용가능한 옵션들은 다음과 같다.
  * Placeholders (플레이스홀더)
  * Transformations (변환)
  * Caching Strategies (캐싱전략)
  * Component specific options, like encode quality, or decode Bitmap configurations. (특정한 옵션 구성, 인코딩 퀄리티 또는 비트맵 디코딩 설정 같은것)
* 예를들어서 CenterCrop 변환을 적용할 경우 다음과같다.
  * ```java
    Glide.with(fragment)
      .load(url)
      .centerCrop()
      .into(imageView);

TransitionOptions
---
* 로드가 끝났을때 어떻게 할건지를 정하는것
* 로드가 끝나서 이제 이미지를 바꿔야 되는데 갑자기 사라지는것이 싫거나(View fade in) , 플레이스오더를 사용할때 크로스로 사라지고 싶게 할때, 또는 전환자체가 없는경우에 사용할수있다.
* 예를들어서 다음과 같이 사용할수있다.
* ```java
  import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

  Glide.with(fragment)
      .load(url)
      .transition(withCrossFade())
      .into(view);
 * RequestOptions 과 다르게 transitionOptions는 로드하는 리소스의 유형(type)에 맞춰진다.
   * 그렇기 때문에 만약 Bitmap 을 요청할경우에는 DrawableTransitionOptions 대신 BitmapTransitionOptions를 사용해야 한다.
   * 그리고 Bitmap 을 사용할 경우에는 Cross fade in 말고 간단한 Fade in 을 사용해야 한다.

RequestBuilder
---
* 편하게 Glide.with().load() 또는 glide.with().asDrawable() 코드라고 생각하면됨
* RequestBuilder는 Glide에서 요청하는것의 뼈대를 맡는다.
* **요청한 url,모델과 함께 옵션을 가져오는 역할을 함**
* 다음과 같은것을 지정할수있다.
  * The type of resource you want to load (Bitmap, Drawable etc) (내가 로드하고싶은 리소소의 타입)
  * The url/model you want to load the resource from (url 또는 모델)
  * The view you want to load the resource into (View)
  * Any RequestOption object(s) you want to apply (적용시킬 RequestOption)
  * Any TransitionOption object(s) you want to apply (적용시킬 TransitionOption)
  * Any thumbnail() you want to load. (썸네일 로드)
* **Glide.with() 를 호출하고 as 매서드중 하나를 선택해서 RequestBuilder를 얻을수있다.**
  ```java
  RequestBuilder<Drawable> requestBuilder = Gilde.with(fragment).asDrawable();
  // 또는 load 사용
  RequestBuilder<Drawable> requestBuilder = Glide.with(fragment).load(url);
* RequestBuilders는로드 할 리소스 유형에 따라 다르다.
* 기본적으로 Drawable RequestBuilder를 얻는다.
* as ... 메소드를 사용하면 요청 된 유형을 변경할 수 있다.
  * 예를 들어 asBitmap ()을 호출하면 대신 Bitmap RequestBuilder를 얻게됩니다.

Applying RequestOptions
---
* apply () 메서드를 사용후 RequestOptions를 적용하고 transition () 메서드를 사용하여 TransitionOptions를 적용을 하는데 다음과 같다.
  ```java
  RequestBuilder<Drawable> requestBuilder = Glide.with(fragment).asDrawable();
  requestBuilder.apply(requestOptions);
  requestBuilder.transition(transitionOptions);
* 재사용가능하다.
  ```java
  RequestBuilder<Drawable> requestBuilder =
    Glide.with(fragment)
      .asDrawable()
      .apply(requestOptions);

  for (int i = 0; i < numViews; i++) {
     ImageView view = viewGroup.getChildAt(i);
     String url = urls.get(i);
     requestBuilder.load(url).into(view);
  }
  
Thumbnail reqeust
---
* 로드되는 동안 보여줄 이미지
* [자세한 내용은 공식 문서 참고](https://bumptech.github.io/glide/doc/options.html)

Starting a new request on failure
---
* 4.3.0 버전부터는 로드에 실패할경우 새로드를 시작하는데 이를 지정할수있다. 다음과 같다.
* ```java
  Glide.with(fragment)
    .load(primaryUrl)
    .error(
        Glide.with(fragment)
          .load(fallbackUrl))
    .into(imageView);
    requestBuilder.transition(transitionOptions)
* 기본 요청이 성공하면 RequestBuilder는 실행안됨
  * 기본적으로 썸네일도 요청에 성공해도 기본 요청이 실패하면 에러 RequestBuilder가 실행이됨

Component Options
---
* Glide의 구성요소들은 다음과 같이 있다.
  * ModelLoaders
  * ResourceDecoders
  * ResourceEncoders
  * Encoders
  * etc..
* Options 클래스는 Glide의 구성 요소에 매개변수를 추가하는 방법이다.
* 기본 제공 요소에는 Options 가 포함되어있고 사용자의 구성 요소에 옵션을 추가 가능하다.
* ```java
  Glide.with(context)
    .load(url)
    .option(MyCustomModelLoader.TIMEOUT_MS, 1000L)
    .into(imageView);
* 또한 ReqeustOptions 오브젝트를 새롭게 만들수 있다.
  ```java
  RequestOptions options = 
    new RequestOptions()
      .set(MyCustomModelLoader.TIMEOUT_MS, 1000L);
  
  Glide.with(context)
    .load(url)
    .apply(options)
    .into(imageView);

