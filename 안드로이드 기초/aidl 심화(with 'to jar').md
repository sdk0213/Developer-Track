# aidl 심화
### aidl 구현 그대로 전달
* client
  * aidl 파일 생성 ( 클라이언트 <-> 서버 둘다 같은 파일임 )
  * 생성후 리빌드시 aidl 인터페이스 구현체를 컴파일러에서 생성해줌
* server
  * aidl 파일 생성 ( 클라이언트 <-> 서버 둘다 같은 파일임 )
  * 생성후 리빌드시 aidl 인터페이스 구현체를 컴파일러에서 생성해줌
  * Service를 구현하고 onBind()를 재정의하고 aidl 인터페이스를 실제 구현
  * ```java
    interface aidlName {

        boolean myFunction();
    }

  * ```java
    public class aidlservice extends Service {
    
    
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return binder;
        }

        private aidlName.Stub binder = new aidlName.Stub() {
          // myFunction 기능들 구현
        }
    
    }
---
### aidl to jar 라이브러리 전달
* 타앱이 aidl 을 구현하는것이 매번 그럴수없을떄
* task 로 생성하여서 jar 로 파일로 변환하기
* baseName
  * baseName gradle 명
* extension
  * 확장자
* destinationDir File
  * 생성될 파일 목적지
* $buildDir
  * build(주활색 파일명) 의 경로
* ```gradle
  task aidlToJar(type: Jar, description: 'create a jar archive of the AIDL interface') {
      destinationDir file("$buildDir/export")
      baseName 'filename'
      extension 'jar' 
      from fileTree('build/intermediates/javac/....classes/').include('com/sudeky/....[aidl 파일 클래스 경로 및 파일명]*.class')
  }
