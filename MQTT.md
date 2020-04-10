MQTT
===

출처 : <https://jgtonys.github.io/iot/2018/07/13/mqtt-test/>

출처 : <https://twinw.tistory.com/158>

+ MQTT(Message Queuing Telemerty Transport)
  + IOT 기기들에 최적화될만큼 아주 가벼운 메시징 프로토콜이다.
  + 
  + 기존에 웹에서 통신하던 HTTP등의 프로토콜보다 제한적이고 특수한 상황에서 사용할 수 있는 모바일 특화 프로토콜
  + **Broker / Publisher / Subscriber**                                               
  + 용어 설명
    + topic = 클라이언트들이 topic을 타겟으로 삼아 메시지를 보낸다.
    + Publisher = 센서 장치(Client)
      + 메시지 송신하는 클라이언트
    + Broker = MQTT(Server)
      + Client간 연결시켜주는 Cloud Service
    + Subscriber = 출력 장치 또는 처리 장치
      + 메시지 수신하는 클라이언트
 
  
