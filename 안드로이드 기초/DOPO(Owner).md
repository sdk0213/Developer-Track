Device Owner(디바이스 소유자)
===
※ Profile은 영어에서 '프로파일'이라고 읽고 우리나라에 정착하면서 '프로필'이라고 읽혔다

+ Device Owner
  + 기기관리자로 실행되는 어플리케이션
  + 이 '기기관리자'로 실행이 되는 어플리케이션은 DevicePolicyManager 클래스를 사용할수있다.
  + DevicePolicyManager 클래스의 클라이언트(?)는 장치관리자로 등록되어야 사용할수있고 장치관리자는 Device Owner를 사용하여야 등록 할 수 있다.
  + Device or Profile Owner로 제한되지 않는 한 모든 메소드를 사용할수 있다.**(무슨말인지 모르겠다.)**
  + 어떤 어플리케이션도 Device Owner가 될수있지만 반드시 Device Owner로 될수있도록 하여야한다.
    + Provisioning 이란 ?
      + 사용자 혹은 비지니스 요구사항에 맞게 할당, 배치, 배포하여 시스템을 사용가능하도록 준비하는 절차
    + Device Provisioning 이란 ?
      + 사용자 또는 장치가 배포 될 환경에 맞게 장치를 구성하거나 준비하는 것
      + 응용 프로그램, 네티워크 설정, 보안 인증서 등
    + Device Owner App 설치하는 세가지 방법
      + 장치를 루팅하고 소유자 정보를 "/data/system/device_owner.xml"파일에 넣기
      + adb를 사용하여 "dpm set-device-owner" command 실행
      + NFC를 사용해서 Non-Provisioning Device 로 보내기
      + Device Owner가 설정되면 공장초기화, 루팅을 제외하고는 제거 불가능
    + 장치 소유자(Device Owner) 와 장치 관리자(Device Administrator)의 차이점
      + **Device Owner Application == Device Administrator, Profile Owner Application == Device Administrator
      + **하지만 Device Owner Application > Profile Owner Application 이다.
      + Device Owner는 특별한 권한이있는 장치 관리자로 생각하면 된다.
      + Device Administrator -> Android 2.2 에서도입되었다.
    + 장치 소유자(Device Owner) 와 프로파일 소유자(Profile Owner) 차이점
      + Profile Owner는 업무용 데이터와 개인용 데이터가 모두있는 장치에서 사용이 된다.
      + 하지만 Device Owner는 Profile Owner보다 다음과 같은 기능들을 더 수행할수 있다.
        + Wi-Fi 및 Bluetooth를 비활성화
        + Device Data 삭제
        + Kiosk(키오스크) 응용 프로그램을 구성 할 수 있다.
      + EMM 에이전트 (DPC) 는 Device,Profile Owner 모두 작동하도록 작성 될 수 있다.      
    + (Device Policy Controller)란?
      + EMM Agent라고도 불리며 장치의 보안 및 사용제한을 적용하는 EMM 공급자의 응용 프로그램이다.
    + Install Device Owner Using NFC(device Provisioning)
      + 0. They call kiosk mode as “screen pinning” or “task locking”(화면 고정, 작업잠금)
      + 1. do a factory reset of android 5.0 device ( it will be in the "unprovisioned" state )
      + 2. do not touch the screen!
      + 3. re-built App"[Set Device Owner](http://sdgsystems.net/flong/SetDeviceOwner.apk)"
        + c.2 Or Compile it from [Source code](http://sdgsystems.net/flong/SetDeviceOwner.zip) on a **different device that Supports NFC**
        + c.3 second device **should be running android 4.1.2 or above**
      + 4. run the Set Device Owner app
      + 5. click that "Compute Checksum" button
      + 6. 그 다음의 과정은 다음 사이트 참고
      + 7. 참고 사이트(3부) : <https://www.sdgsystems.com/post/implementing-kiosk-mode-in-android-part-3-android-lollipop-and-marshmallow>
      + 8. 참고 사이트(4부) : <https://www.sdgsystems.com/post/implementing-kiosk-mode-in-android-part-4-a-better-provisioning-method-for-dpc-device-owner>
      + DevicePolicyManager로 통제할수 있는 영역
  + 다음과 같은 기능을 사용할수 있다.
    + 하드웨어의 기능 비활성화
    + 잠금화면 
    + 알림
    + 위젯
    + 응용프로그램 Hiding
    + 자격증명 비활성화
    + 장치암호화 비활성화
    + 비밀번호 정책 구성
    + 사용자 계정 구성
    + Network 매개변수
    + CA 인증서
    + VPN 정보 구성
    + 외부 저장소의 장치
    + Airplane, Gps, Bluetooth, Roaming
    + 사용자 전환
    + Device Owner 삭제
    
Profile Owner    
===

+ ![Alt text](https://developer.android.com/images/work/dpc/profiles.svg)
+ Work Profile 사용해서 정책을 시행하는것
+ 개인/직장 영역이 분리된다.


Device Administrator(디바이스 관리자)
===

+ Device Administrator는 Device Owner와 Profile Owner로 불리기도 한다.
+ Device Administrator가 된다면 Device Administration API 를 사용할수 있다.
  + Device administration API를 사용하면 각종 정책(비밀번호 설정, 카메라, 기기 잠금 등)을 시행할수있다.

