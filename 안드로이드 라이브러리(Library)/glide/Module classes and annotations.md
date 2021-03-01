Module classes and annotations
---
* Glide v4는 AppGlideModule 및 LibraryGlideModule의 두 클래스를 사용하여 Glide 싱글 톤을 구성한다.
* 두 클래스 모두 ModelLoaders, ResourceDecoder 등과 같은 추가 구성 요소를 등록 할 수 있다.
  * 캐시 구현 및 크기와 같은 애플리케이션 특정 설정 구성은 AppGlideModules 만 가능하다.

AppGlideModule
---
* 어플리케이션이 AppGlideModule에서 메서드를 구현하거나 통합 라이브러리를 사용하려는 경우 AppGlideModule 구현을 추가 할 수 있다.
* AppGlideModule 구현은 Glide의 어노테션 주석이 발견 된 모든 LibraryGlideModules와 결합 된 단일 클래스를 생성 할 수 있도록하는 신호 역할을 한다.
* 어플리케이셔 당 하나의 AppGlideModule 구현 만있을 수 있다. (컴파일시 둘 이상의 오류가 발생 함). 그렇기 때문에 라이브러리는 AppGlideModule 구현을 제공해서는 안된다.

@GlideModule
---
* Glide가 AppGlideModule 및 LibraryGlideModule 구현을 올바르게 검색하려면 두 클래스의 모든 구현에 @GlideModule 주석을 추가해야한다.
* 주석을 처리해야만 Glide의 주석 프로세서는 컴파일 시간에 모든 구현을 검색 할 수 있다.

Annotation Processor
---
* AppGlideModule 및 LibraryGlideModules를 검색하려면 모든 라이브러리와 응용 프로그램이 Glide의 주석 프로세서에 대한 종속성을 포함시켜야한다.

Conflicts
---
* LibraryGlideModules는 충돌하는 옵션을 정의하거나 애플리케이션이 피하려는 동작을 포함 할 수 있습니다. 
* 응용 프로그램은 AppGlideModule에 @Excludes 주석을 추가하여 이러한 충돌을 해결하거나 원치 않는 종속성을 피할 수 있습니다.
* ```java
  @Excludes({com.example.unwanted.GlideModule.class, com.example.conflicing.GlideModule.class})
  @GlideModule
  public final class MyAppGlideModule extends AppGlideModule { }
* @Excludes는 아직 Glide v3에서 마이그레이션하는 과정에있는 경우 LibraryGlideModules 및 기존의 지원 중단 된 GlideModule 구현을 제외하는 데 사용할 수 있습니다.

Manifest Parsing
---
* **Glide v3**의 GlideModules와의 하위 호환성을 유지하기 위해 Glide는 애플리케이션과 포함 된 라이브러리 모두에서 AndroidManifest.xml 파일을 계속 파싱하며 매니페스트에 나열된 기존 GlideModules를 포함합니다. 
  * **이 기능은 향후 버전에서 제거 될 예정이지만 전환을 쉽게하기 위해 지금은 동작을 유지했습니다.**
* Glide의 초기 시작 시간을 개선하고 메타 데이터 구문 분석을 시도 할 때 발생할 수있는 몇 가지 잠재적 문제를 방지 할 수 있습니다. 
  * 메니페스트 파싱을 비활성화하려면 AppGlideModule 구현에서 isManifestParsingEnabled () 메서드를 재정의합니다
  * ```java
    @GlideModule
    public final class MyAppGlideModule extends AppGlideModule {
      @Override
      public boolean isManifestParsingEnabled() {
        return false;
      }
    }
