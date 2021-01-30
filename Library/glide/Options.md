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

Picking a resource type
---
* 

