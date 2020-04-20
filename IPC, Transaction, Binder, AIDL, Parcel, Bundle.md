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
* **바인더란 각각 독립된 프로세서들을 연결 해주는 역할을 한다.** - [Binder의 더 자세한 사항](https://www.oss.kr/info_techtip/show/32d5f561-b998-496c-a328-a58a5555e2c6)
  * binding - [출처](http://tcpschool.com/cpp/cpp_polymorphism_virtual)
    * 함수를 호출하는 코드에서 어느 블록에 있는 함수를 실행하라는 의미로 해석하는 것을 바인딩(binding)이라고 합니다.
    * 정적 바인딩 (static binding, early binding)
      * 컴파일 타임에 고정된 메모리 주소로 변환
    * 동적 바인딩 (dynamic binding, late binding)
      * virtural(가상)함수를 사용하면 런 타임에에 실행되는 동적바인딩으로 된다.
    * 그리고 이러한 virtual의 주소들을 시스템이 알수있게 virtual function table(vtbl)이 만들어지며 이는 복잡해지고 속도에서 느려질수있다.
이것을 동적 바인딩(dynamic binding) 또는 지연 바인딩(late binding)이라
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
  
Proxy,stub - [출처](https://powere.tistory.com/79)
---
* Client <-> Proxy <-> Stub <-> COM
* COM
  * Componet Object Model이라고 c++에서 쓰이는 개념으로 알고있다.
  * 그리고 여기서 가상함수 table pointer를 Interface라고 부른다.
  * 그렇다면 사실 Client Application은 COM을 통해서 table pointer를 얻고 거기서 함수를 사용한다.
  * **그런데 COM은 InProcess, OutProcess으로 나뉘는데**
    * InProcess
      * COM이 Application 메모리영역으로 올라감
      * 그러므로 COM에서 알려준데로 table pointer를 바로사용가능
    * OutProcess
      * COM이 Applciation 밖에 있는경우 table pointer를 이해 불가능하다.
      * **그래서 메모리 영역에 있다고 생각하고 작업할수 있는 방법이 필요함**
      * **그것이 바로 Proxy(Application영역), Stub(COM영역)이 필요하다**
      * Proxy
        * 멀리있는 COM인척 해주는것
      * Stub
        * Proxy요청을 받아서 COM이랑 통신담당자
      * Applciation이 Proxy에 있는 Interface를 이용해 Methode Call하면 Proxy는 Stub과 IPC를 통해 전달하고 COM에게 요청해서 다시 반대로 보낸다.
      * 이러한 IPC통신중에 규약이 필요하고 통신 규약에 맞춰서 변경하는것을 Marshalling이라고 부른다. 반대의 경우 unMarshalling 이다.
* 가상함수 table pointer를 Component(Component Object Model)에서 Interface라고 부른
* COM object
* 인터페이스란?
  * Component가 자신이 제공하는 서비스를 외부에 노출시키는 방법
* 프로젝트를 빌드하면 .aidl이 ServerStub과 client Proxy를 자동으로 생성한다.
* 그렇게 해서 전체적인 그림은 다음과 같다.
* ![Alt text](https://t1.daumcdn.net/cfile/tistory/260AE550579959E02B)


RPC - [출처](https://androidyongyong.tistory.com/8)
---
* .aidl를 구현해야한다.
* 동기식 RPC
  * 이해하고 쉽고 구현하기 쉽지만 스레드를 차단할수 있는 위험이 생기고 서버 스레드가 차단될 경우 클라이언트 스레드는 모두 차단된다. 그렇게되면 메모리 누수의 위험성을 가진다.
* 비동기식 RPC
  * 위의 문제를 해결
  * 클라이언트 스레드는 비동기식 RPC로 실행하고 즉시 반환
  * oneway 키워드를 붙여 AIDL안에 정의한다.
  * 반드시 void를 통해 반환해야한다
  * 결과값을 얻기위한 callback을 구현해야 한다.
* 성능을 높이고자 한다면 RPC
* 구현하기 쉬운것은 Messanger

프로세스 바인더 통신 - [출처](https://androidyongyong.tistory.com/8)
---
* 프로세스가 통신에는 공유메모리가 없다.
* 그렇기 때문에 Binder Driver를 통해 커널메모리 영역을 통해 프로세스 경계를 넘어서야 한다.
* 이럴때는 Binder FrameWork를 사용한다.
* ![Alt text](https://t1.daumcdn.net/cfile/tistory/234B3D4E5799694A1E)

