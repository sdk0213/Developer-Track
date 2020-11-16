Libary 종류 - [출처 - simsi6님으 tistory](https://simsi6.tistory.com/10)
===
* JAR (Java Archive)
  * JAR는 해당 플랫폼에서 JAVA 응용 프로그램을 배포하기 위해 고안된 패키지 파일 형식
  * 컴파일 된 Java 클래스 파일과 MANIFEST와 같은 파일들이 포함된다.
  * 기본적으로 ZIP 아카이브 형태 입니다.
* APK (Android Application Package)
  * APK는 Android 플랫폼에 배포할 수 있도록 설계된 파일 형식입니다. 
  * 컴파일 된 클래스를 Dex파일형태로 포함시키고 AndroidManifest.xml 등 리소스 파일들도 포함한다.
* AAR (Android Archive)
  * **Android 라이브러리 프로젝트의 바이너리 배포판**
  * 주로 Java클래스 파일들만 포함하는 Jar와 달리 리소스 파일들도 포함한다.
* DEX (Dalvik Excutable)
  * DVM(Dalvik Virtual Machine)을 위한 실행 파일
  * JVM을 위한 .class 파일들과 같은 역할
  * Android SDK의 Dex 컴파일러에 의해 JVM 바이트코드 ---(변환)--> DVM 바이트코드, 모든 클래스파일들을 Dex파일에 넣습니다.
  * DEX는 바이너리 파일 형식으로 컴파일 됩니다.

