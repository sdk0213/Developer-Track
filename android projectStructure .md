프로젝트파일
===

* manifests = AndroidManifest.xml
* java = JUnit 테스트 코드를 비롯 자바 소스 코드 파일을 포함, 패키지 이름별로 구분
* res = 코드가 아닌 모든 리소스(XML layout, UI 문자열, Bitmap Image 등)

> * **module-name/**
> * **build/** 빌드 출력을 포함합니다.
> * **libs/** 비공개 라이브러리를 포함합니다.
> * **src/** 다음 하위 디렉터리에 모듈에 관한 모든 코드 및 리소스 파일을 포함합니다.
> > * **androidTest/**	Android 기기에서 실행되는 계측 테스트에 관한 코드를 포함합니다.
>	>	* **main/**		'기본' 소스 세트 파일, 즉 모든 빌드 변형이 공유하는 Android 코드 및 리소스(디버그 빌드 유형의 경우 src/debug/와 같이 동일한 수준의 디렉터리에 있는 다른 빌드 변형의 파일)를 포함합니다.
> > > * **AndroidManifest.xml**	애플리케이션의 특성 및 각 구성요소를 설명합니다. 자세한 내용은 AndroidManifest.xml 문서를 참조하세요.
> > > * **java/**	자바 코드 소스를 포함합니다.
> > > * **jni/**	자바 네이티브 인터페이스(JNI)를 사용하는 네이티브 코드를 포함합니다. 자세한 내용은 Android NDK 문서를 참조하세요.
> > > * **gen/**	Android 스튜디오에서 생성하는 자바 파일(예: R.java 파일) 및 AIDL 파일에서 생성되는 인터페이스를 포함합니다.
> > > * **res/**	애플리케이션 리소스(예: 드로어블 파일, 레이아웃 파일 및 UI 문자열)를 포함합니다. 자세한 내용은 애플리케이션 리소스를 참조하세요.
> > > * **assets/**	.apk 파일로 있는 그대로 컴파일해야 하는 파일을 포함합니다. URI를 사용하여 일반적인 파일 시스템과 같은 방식으로 이 디렉터리를 탐색하고 AssetManager 를 사용하여 파일을 바이트 스트림으로 읽을 수 있습니다. 예를 들어, 이는 텍스처 및 게임 데이터에 사용하기 좋은 위치입니다.
>	>	* **test/**	호스트 JVM에서 실행되는 로컬 테스트의 코드를 포함합니다.
>	* **build.gradle(모듈)**	이는 모듈별 빌드 구성을 정의합니다.
> * **build.gradle(프로젝트)** 이는 모든 모듈에 적용되는 빌드 구성을 정의합니다. 이 파일은 프로젝트에 반드시 필요하므로 기타 모든 소스 코드와 함께 버전 관리에서 유지해야 합니다.
기타 빌드 파일에 관해 자세히 알아보려면 빌드 구성을 참조하세요
(이클립스와 다르게 Android Studio와 빌드 시스템이 서로 독립적이라서 빌드배포도구로 Gradle을 둔다.)(Maven이라고 기존에 사용하던것도 있지만 Gradle이 훨씬 뛰어남)


> * **/drawble** 이미지 저장하는곳
> * **/mipmap** launcher 이미지 저장 패키지
> **/values** 
> > * **/colors.xml** 컬러값저장파일 
> > * **/strings.xml** 문자열값 저장 파일
> > * **/styles.xml** 스타일값 저장 파일
---
1) 안드로이드 프로젝트는 app과 Gradle Scripts 로 이루어져 있다.
2) app은 manifests, java, res 패키지로 이루어져 있다.
3) manifests는 프로젝트 설정에 관한 부분, java는 프로그래밍 구현 부분, res는 레이아웃과 이미지, 문자열 리소스등 UI를 관장하는 부분이다.
4) 모든 안드로이드 프로젝트는 MainActivity를 기본적으로 갖고 있어야 하며 manifests/AndroidManifest.xml에서 설정해준다.
5) 화면을 만들때는 Activity파일(xxx,java파일)과 XML파일(xxxx.xml)을 쌍으로 만들어주고 Activity파일에서 XML파일을 연결해준다.
---
* **JNI(Java Native Interface)** : Java와 C/C++ 사이의 연결고리
* **PDK(Platform Development Kit)** : 안드로이드 전체소스를 의미
* **NDK(Native Development Kit)** : PDK와 SDK 중간 위치정도로서 프레임워크와 라이브러리
  * 기존에 만들어져있는 무수히 많은 C기반의 라이브러리 등을 안드로이드에서 사용하기 위해 주로 사용
  * 안드로이드에서 C/C++ 사용하려면 NDK
* **SDK(Software Development Kit)** : 안드로이드에서 제공하는 API를 사용하는 일반적인 앱 개발 방식
* **JDK(Java Development Kit)** : JRE와 개발을 위해 필요한도구들
* **JRE(Java Runtime Environment)** : JVM이 자바프로그램을 동작시킬때 필요한 실행환경을 구성
---
* onCreate() : Activity가 최초 생성할 때 호출
  * Bundle : 엑티비티간에 데이터를 전달하기 위해 사용, 
  * Intent는 전달을 하는 이동수단이고 Bundle은 저장을 하는 저장 공간이다.
  * java는 하나의 프로젝트에서 main메소드를 가진 클래스는 하나만 존재하나 안드로이드는 클래스당 onCreate가 필요에 따라 전부 존재한다.
* onRestart() : Activity가 정지된 후 다시 시작하기 전에 호출
* onStart() : Activity가 사용자에게 보여지기 직전에 호출
* onResume() : Acitivity가 활성화 되었을 때 호출
* onPause() : 다른 Acitivity가 활성화 되었을 때 호출
* onStop() : 다른 Activity가 Activity를 완전히 가려서 더 이상 보이지 않을때 호출
* onDestroy() : Activity가 삭제되기 직전에 호출
---
* Callback : 이벤트가 발생하면 특정 메소드를 호출해 알려준다.(1개)
* Listener : 이벤트가 발생하면 연결된 리스너(핸들러)들에게 이벤트를 전달한다.(n개)
* Observer : 데이터나 속성의 변경을 감지하여 구독자에게 변경사항을 전달.

> 메인 스레드와 서브 스레드간에 UI 충돌을 피하기위해서 안드로이드는 다음과 같이 진행된다.
1. "이러한 동기화 문제를 처리하기 위해 안드로이드에서는 메인 스레드에서만 UI 작업이 가능하도록 제한한겁니다"
2. 서브 스레드 -> Handler -> 메인 스레드 과정을 거쳐서 서브 스레드에서 UI를 변경
3. **Handler**는 Message와 Runnable객체를 처리 합니다.
4. **Looper는 하나의 스레드만을 담당하며 하나의 스레드는 오직 하나의 Looper만 가질수 있다.**
