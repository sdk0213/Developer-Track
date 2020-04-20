IPC, Parcel, Bundle...
===

IPC통신을 통해 AIDL을 하는 이유 - [출처](https://oysu.tistory.com/17)
===
* App <-> App 통신은 BroadcastReceiver와 Messenger로도 충분하다고 생각하지만 다음과같은 상황에서는 AIDL이 필요하다.
  * 멀티스레드에서 동작
  * 데이터 요청이 자주 있을때
  * 내가 원하는 앱 이외는 데이터를 공개하고 싶지 않을때(보안성)


IPC - [출처](https://androidyongyong.tistory.com/8)
---
* Inter-Process Communication
* 안드로이드에서는 binder Framework를 통해 IPC를 지원한다.
  * binder Framework는 data transection을 관리한다.
* IPC는 다음과 같은 예들이 있다.
  * Intent
  * System service
  * Content Provider
* 이 모든것들은 Linux OS에 의해 관리된다.
* **Binder FrameWork**
  * RPC Mechanism을 수행하고 RPC Mechanism은 다음과 같이 구성된다.
    * Marshalling - 데이터 분해
    * 원격 프로세스로 Marshalling 정보를 전송
    * unMarshalling
    * 원래 프로세스로 반환값을 전송
* **※ 안드로이드는 **IPC**(Inter-Process Communication)와 **AIDL**(Android Interface Definition Language)를 통해 Process 통신을 한다.**

Transaction - [출처](https://finerss.tistory.com/entry/%ED%8A%B8%EB%9E%9C%EC%A0%9D%EC%85%98%EC%9D%B4%EB%9E%80)
---
* 영어에서의 의미 "거래"
* "원자"
  * 절대로 쪼갤수없는 단위
* "원자" + "거래"
  * 쪼갤 수 없는 하나의 처리 행위를 원자적 행위라고 하며 시스템에서는 쪼갤경우 심각한 오류가 초래하는 개념으로 쓰인다.
* 정확한 정의는 다음과 같다.
  * **Transaction은 응용 프로그램에서 모두 성공적으로 완료하지 않으면 각 작업의 변경 사항이 철회되는 일련의 작업이다.
* 쉽게말해서 쪼갤 수 없는 업무처리 단위이다.


Binder - [출처](https://androidyongyong.tistory.com/8)
---
* Transaction
  * 함수와 데이터 전송하는 원격 프로시저 호출
* onTransact
  * 클라이언트가 Transcation을 받는 메서드
* Transaction 전송은 Parcel 객체로 구성된다.
* IPC는 양방향 동작이 가능하다.
  * IBinder.FLAG_ONEWAY를 설정함으로써 비동기 Transaction을 지원하며 설정시에는 transact를 호출후 onTransact를 기다리지 않고, 즉시 반환된다.

AIDL - [출처](https://androidyongyong.tistory.com/8)
---
* Android Interface Definition Language(안드로이드 인터페이스 정의 언어)
* 하나의 프로세스가 다른 프로세스에서 접근하도록 허용하고 싶은 기능이 있을때는 어떻게 해야할까?
  * 이럴때는 통신 계약을 정의해야한다.
* 안드로이드에서 이러한 계약을 하고싶을때는 .aidl파일에 정의해야한다.
* .aidl -> compiling -> .java 파일이 생성되며 **Client/Server 모두에 포함** 된다.
* Proxy, Stub의 역할
  * Server에서 실행되더라도 Client가 지역적으로 메서드를 호출할수 있도록 허용하는 두 프로그램대신해서 RPC를 관리
  *
  
Proxy,stub
---
* 인터페이스란?
  * Component가 자신이 제공하는 서비스를 외부에 노출시키는 방법
* 프로젝트를 빌드하면 .aidl이 ServerStub과 client Proxy를 자동으로 생성한다.
