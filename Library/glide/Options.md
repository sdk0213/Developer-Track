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
