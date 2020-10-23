Android Debugging 속도올리기
---
> build.gradle
* ```java
  debug{
  //          minifyEnabled false
  //          proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
              splits.abi.enable = false
              splits.density.enable = false
              ext.alwaysUpdateBuildId = false
              aaptOptions.cruncherEnabled = false  // png 최적화 끄끼
  }


> build.properties
* ```java
  org.gradle.jvmargs=-Xmx4096m -XX:MaxPermSize=1024m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8
  org.gradle.daemon=true
  org.gradle.parallel=true
  org.gradle.configureondemand=true
  org.gradle.caching=true
  android.enableBuildScriptClasspathCheck=true
  android.enableBuildCache=true
  project.android.dexOptions.preDexLibraries = false
