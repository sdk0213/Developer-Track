E2E
===

E2E
---
* End-to-End
* 끝과 끝의 통신을 의미하며 단말장치간의 통신을 의미한다.

E2E 암호화 - [출처](http://guide.jinbo.net/digital-security/communication-security/e2e-encryption)
---
* 개념
  * 서버에 암호키를 저장하지 않고 개인 단말기에만 저장
    * 쉽게말해서 중간에 무엇이 중계되던 **중계하는쪽은 암호와 관련된것을 전혀 건드리지 않고 정말로 중계만 하는역할**을 수행
    * **암호화, 복호화는 순전히 End와 End에서만 가능**하며 여기서는 단말기에서만 수행가능하다
  * [그림출처](http://guide.jinbo.net/digital-security/communication-security/e2e-encryption)
  * ![Alt text](http://guide.jinbo.net/digital-security/wp-content/uploads/sites/3/2015/07/3.1_01.png)
* 문제점
  * * 이론적으로는 통신하는 쌍방이 플랫폼을 신뢰하지 않더라도 통신 보안을 보장할수있다.
    * **하지만** 자신들이 사용하는 암호키가 자신이 통신하고자 하는 상대방의 것임을 입증할 수 있어야 한다.
    * 또한, 암호화의 강도가 얼마정도인지 믿을수 있는지는 순전히 사용자 판단의 몫이다.
* 해결책
  * OTR(Off-the-Record) 
    * 실시간 문자 대화를 위한 종단간 암호화 프로토콜
  * PGP(Pretty Good Privacy)
    * 이메일 종단간 암호화의 표준
  * https
* 한계점
  * 통신을 했다는 사실 그 자체를 보호할 수 없다.
  * 통신 내용을 확보하지는 못하지만 누군가와 통했다는 사실과 위치기록이 통신에 기록된다.
