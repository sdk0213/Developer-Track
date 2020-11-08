Android Debugging 최적화
---
> build.gradle
* ```java
  debug{
              minifyEnabled false // 난독화 끄기 (default : false)
  //          proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
              splits.abi.enable = false // 멀티 apk 끄기
              splits.density.enable = false // 멀티 apk 끄기
              ext.alwaysUpdateBuildId = false // Build id를 update해주는 기능 끄기
              ext.enableCrashlytics = false // fabric 끄기 (fabric : 사용자분석이나 오류로그를 분석)
              aaptOptions.cruncherEnabled = false  // png 최적화 끄기
  }


> gradle.properties
* ```java
  org.gradle.jvmargs=-Xmx4096m -XX:MaxPermSize=1024m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8
  org.gradle.daemon=true // Gradle Daemon 사용 (백그라운드 대기상태 유지)
  org.gradle.parallel=true // 서브 모듈을 동시에 빌드
  org.gradle.configureondemand=true // configure on demand
  org.gradle.caching=true // 작업 결과를 캐시
  android.enableBuildScriptClasspathCheck=true
  android.enableBuildCache=true // 빌드캐시활성화 (클린빌드시에는 느려질수있음)
  project.android.dexOptions.preDexLibraries = false // 서브 모듈들을 pre-dexing 하는 작업 끄기 (클린빌드시 속도 향상)
