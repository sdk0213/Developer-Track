Registering Components
---
* 어플리케이션/라이브러리 모두 Glide 기능을 확장시켜줄 컴포넌트를 등록할수있다.
* 확장가능한 컴포넌튿들은 다음과같다.
  * 커스텀 모델을 로드하기 위한 ModelLoader (Urls, uris, 임의의 POJO)
  * 데이터 (InputStreams, FileDescriptors)
  * 새로운 리소스(Drawbles, Bitmaps) 또는 타입(InputStreams, FileDescriptors)을 디코딩 하기위한 **ResourceDecoder**
  * 데이터를 쓰기 위한 **Encoder**
  * 리소스 변환 (BitmapResource -> DrawableResource)
  * Glide 캐시에 쓰기위한 ResourceEncoder
* 등록은 Registry 클래스를 사용하여 등록한다.
  * ```java
    @GlideModule
    public class YoutAppGlideModule extends AppGlideModule { // 또는 extends LibraryGlideModule
      @Override public void registerComponents(Context context, Glide glide, registry registry) {
          registry.append(...);
      }
    }
* 여러개 등록가능하다.
* 특정 유형들은(ModelLoader, ResourceDecoder)은 동일한 유형 인수를 사용해서 여러 구현 가능하다.

load 분석
---
* 기본적으로 구성된 컴포넌트와 모듈로 등록된 컴포넌트들은 로드 경로의 세트를 정의하는 데 사용된다.
* 로드 경로는 load ()에 제공된 모델에서 as ()에 지정된 리소스 유형으로의 단계별 진행으로 다음과 같다.
  * 1.Model -> Data (ModelLoad에 의해 조정)
  * 2.Data -> Resource (ResourceDecoder에 의해 조정)
  * 3.Resource -> Transcoded Resource (선택적, ResourceTranscoder에 의해 조정됨)
  * Model -> Data -> Resource -> Transcoded Resource
* 인코더는 2단계 스탭전에 가능
* 리소스 인코더는 3단계 이전에 가능
* 요청이 시작되면 Glide는 사용 가능한 모든 경로를 사용한다. 모든 경로가 실패하면 그때 실패처리한다.

Odering Components
---
* 다음을 사용해서 Glide가 각 ModelLoader 및 ResourceDecoder를 시도하는 순서를 설정할수있다.
  * prepend() -> 이전에 추가
  * append() -> 추가
  * replace() -> 교체
* 모델또는 데이터의 특정한 .. 예를들면 IE 전용 Uri 타입, 특정 이미지 형식들을 처리하는 등록할수있고 나머지 경우도 그렇다.
* prepend()
  * ModelLoader 또는 ResourceDecoder가 실패 할 경우 Glide의 기본 행동 돌아 가려는 기존 데이터들을 처리할때 사용
  * ModelLoader 또는 ResourceDecoder가 이전에 등록 된 다른 모든 구성 요소보다 먼저 호출되고 먼저 실행될 수 있는지 확인한다.
* append()
  * 새로운 유형의 데이터를 처리하거나 Glide의 기본 비헤이비어에 폴백을 추가할때 사용
  * Glide의 기본값이 시도 된 후에 만 ModelLoader 또는 ResourceDecoder가 호출되도록 한다.
* replace()
  * Glide의 기본 동작을 완전히 대체하고 실행되지 않도록할때 사용
  * replace ()는 특히 OkHttp 또는 Volley와 같은 라이브러리로 Glide의 네트워킹 로직을 교체 할 때 유용하다.
  
Adding a ModelLoader
---
* 예를 들어서, InputStream을 가져오는 CustomModel을 추가할때 다음과 같이 한다.
* ```java
  @GlideModule
  public class YourAppGlideModule extends AppGlideModule {
      @Override public void registerComponents(Context context, Glide glide, Registry registry) {
          registry.append(Photo.class, InputStream.class, new CustomModelLoader.Factory());
      }
  }
* 위 코드는 Photo.class는 특정 어플리케이션에서 만든 사용자전용모델이므로 Glide에는 대체해야하는 기본 동작이 없어서 append 를 했다.
* ```java
  @GlideModule
    public class YourAppGlideModule extends AppGlideModule {
        @Override public void registerComponents(Context context, Glide glide, Registry registry) {
            registry.prepend(String.class, InputStream.class, new CustomUrlModelLoader.Factory());
        }
    }
* 새로운 유형의 문자열 URL에 대한 처리를 추가하려면 prepend ()를 사용해서 Glide의 기본 ModelLoader보다 먼저 실행되도록해야한다.
* ```java
  @GlideModule
  public class YourAppGlideModule extends AppGlideModule {
      @Override public void registerComponents(Context context, Glide glide, Registry registry) {
          registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
      }
  }
* 네트워킹과 같은 특정유형에 대한 Glide의 기본처리르 바꿀려면 replace를 사용한다.
