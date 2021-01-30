Generated API
===
* Glide v4 에서는 어플리케이션이 Glide's API를 확장하고 통합 라이브러리에서 제공되어지는 구성요소(components)를 포함하는것을 허락(사용)하는 API를 만드는 어노테션 프로세서(annotation processor)를 사용한다.
* Generate API는 두가지 원칙이 잇다.
  * 커스텀 옵션으로 통합 라이브러리를 Glide's API로 확장가능하다.
  * 어플리케이션은 공통으로 사용되는 옵션을 번들로 제공되는 메서드를 추가함으로써 Glide API를 확장 가능하다.
* 비록 이런 과정은 귀찮게 RequestOptions의 서브클래스들을 커스텀해서 만들수는 있지만 어렵고 형편없을수 있다. 한 마디로 어렵지만 잘 만들기로 어렵다.

Getting Stated
---
* Generated API 는 해당하는(지금의) 어플리케이션에서만 만들수 있고 다른 어플리케이션에서는 사용할수없다.
  * 이렇게 어플리케이션으로 제한함으로써 API의 단일구현이 가능해지고 이로써 모든것들을 체크하는 과정이 줄어들고 간단해진다.
  * 하지만 이러한 제한은 향후 삭제될수도 있다.
* **현재 API는 반드시 AppGlideModule을 어노테션했을경우에만 발생한다.**
* 어플리케이션당 하나의 어노테션만 가능하다.
* 무조건 하나의 AppGlideModule 만 있을수 있기 떄문에 라이브러리와 어플리케이션 두 쪽에 다 존재할수는없다. (둘 중 하나만 API가 사용가능하다는 뜻이 아님, 다만 extends를 한곳에서만 할수 있다는뜻)
* JAVA
  ```gradle
  repositories {
    mavenCentral()
  }

  dependencies {
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
  }
* AppGlideModule 를 어플리케이션에 다음과 같이 넣어야 한다.
  ```java
  package com.example.myapp;

  import com.bumptech.glide.annotation.GlideModule;
  import com.bumptech.glide.module.AppGlideModule;

  @GlideModule
  public final class MyAppGlideModule extends AppGlideModule {}
* 어노테션만 달아주고 AppGlideModule을 extends 할경우 어디에서나 API를 생성가능하다.
  * **어노테션을 안할경우 모듈을 찾을수 없다는 에러가 발생한다.**
* 참고 : 라이브러리에서는 AppGlideModule을 포함하지 않아야 되고 이와 관련되내용은 [여기참고](https://bumptech.github.io/glide/doc/configuration.html#avoid-appglidemodule-in-libraries)
* [Kotlin 관련 설정은 여기 참고](https://bumptech.github.io/glide/doc/generatedapi.html)

안드로이드 스튜디오 설정
---
* AppGlideModule을 추가하거나 몇몇 타입의 변화가 있을경우는 **Rebuild를 해야만 제대로 동작**한다.

Generated API 사용
---
* AppGlideModule를 구현한 패키지이름으로 API가 GlideApp(기본값) 이라는 이름으로 생섣된다.
* Glide.with 말고 GlideApp.with로 시작해야만 API를 사용가능하다.
* ```java
  GlideApp.with(fragment)
   .load(myUrl)
   .placeholder(R.drawable.placeholder)
   .fitCenter()
   .into(imageView);
* **Glide.with 를 사용할때와는 다르게 fitCenter()나 placeholder() 을 RequestOptions 로 분리된 오브젝트에서 넘겨주지 않아도 직접적으로 빌드가 가능하다.**

GlideExtension
---
* Glide에서 생성 된 API는 애플리케이션과 라이브러리 모두에서 확장이 가능하다.
* GlideExtension 어노텐션 추가하면 된다.(@GlideExtension)
* **주석이 달린 정적 메서드를 사용하여 새 옵션을 추가하거나 기존 옵션을 수정하거나 추가 유형을 추가하는 방식이다.**
* ```java
  @GlideExtension
  public class MyAppExtension {
    // Size of mini thumb in pixels.
    private static final int MINI_THUMB_SIZE = 100;

    private MyAppExtension() { } // utility class

    @NonNull
    @GlideOption
    public static BaseRequestOptions<?> miniThumb(BaseRequestOptions<?> options) {
      return options
        .fitCenter()
        .override(MINI_THUMB_SIZE);
   }
* 위와 같은 코드로 miniTumb라는 함수를 추가하면(추가하고 안드로이드 스튜디오 rebuild 필요)
* RequestOtions의 서브클래스로 GlideOptions 에 아래 코드가 추가된다.(직접 확인해보십쇼 신기함)
  * ```java
    public class GlideOptions extends RequestOptions {
  
    public GlideOptions miniThumb() {
      return (GlideOptions) MyAppExtension.miniThumb(this);
    }

    ...
    }
* 첫번 째 파라미터가 RequestOptions 라면 다른 파라미터도 계속 추가할수 있다.
  * ```java
    @GlideOption
    public static BaseRequestOptions<?> miniThumb(BaseRequestOptions<?> options, int size) {
      return options
        .fitCenter()
        .override(size);
   }
  * 만약 위와 같이 size 인수를 추가하고 rebuild 할경우 다음과같이 miniThumb에 파라미터도 추가된다.
    * ```java
      public GlideOptions miniThumb(int size) {
         return (GlideOptions) MyAppExtension.miniThumb(this);
      }
* **custom 메서드 호출하기**
  * ```java
    GlideApp.with(fragment)
        .load(url)
        .miniThumb(thumbnailSize)
        .into(imageView);      
* **GlideOption 어노테션이 있는 메서드는 static 이여야 하며 BaseRequestoptions<?> 를 반환해야한다.**
  * **주의점 : generated method는 standard Glide(그냥 Glide) 와 RequestOptions 클래스에서 사용이 불가능하다.**

GlideType
---
* RequestManager를 확장한다.
* 새로운 type에 대한 지원을 추가 할 수 있다.
* 예를들어서 GIF 를 지원하기위해서는 GlideType 메서드를 사용해야한다.
* ```java
  @GlideExtension
  public class MyAppExtension {
    private static final RequestOptions DECODE_TYPE_GIF = decodeTypeOf(GifDrawble.class).lock();
    
    @NonNull
    @GlideType(GifDrwable.class)
    public static RequestBuilder<GifDrwable> asGif(RequestBuilder<GifDrwable> requestBuilder) {
      return requestBuilder
        .transition(new DrawableTransitionOptions())
        .apply(DECODE_TYPE_GIF);
    }
  }
* 위의 코드 작성하면 requestMenanger 에서 asGif() 메서드가 만들어진다.
  * ```java
    public class GlideRequests extends RequesetManager {

    public GlideRequest<GifDrawable> asGif() {
      return (GlideRequest<GifDrawable> MyAppExtension.asGif(this.as(GifDrawable.class));
    }
  
    ...
    }
* 실질적인 사용은 다음과 같다.
  * ```java
    GlideApp.with(fragment)
      .asGif()
      .load(url)
      .into(imageView);
* GlideType에 달린 메서드는 **반드시 첫번째 인수로 RequestBuilder<T> 를 가져야하며 <T> 유형은 어노테션 GlideType에 들어가는(ex) GifDrwable.class) 타입과 동일해야 한다.
  * 이해 안가면 위의 코드 참고
* 그리고 static이여야 하고 RequestBuilder<T> 를 반환해야한다.
* 메서드는 **GlideExtension으로 주석이 달린 클래스 내에서 정의**되어야합니다.
    
