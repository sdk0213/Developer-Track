OS 6.0
---
* Doze모드
  * 6.0
    * 화면 꺼지고,  배터리 전원 공급이 없는 상태로  가만히  기기가  놓였을경우
  * 7.0
    * 디바이스가  움직여도  진행 (휴대폰에 사용하지 않고 들고다니는 조건에 따른 추가)
  *  [FCM이 높은 우선순위 메시지를 즉시 전송하려고 시도하며 필요한 경우 FCM 서비스에서 기기의 절전 모드를 해제하고 매우 제한된 네트워크 액세스를 포함하여 제한된 일부 처리 작업을 실행](https://firebase.google.com/docs/cloud-messaging/concept-options?hl=ko#setting-the-priority-of-a-message)
* 권한흭득 방식 변경
  * 권한을 **Manifest에 명시할뿐만 아니라 권한을 사용자에게 직접 요청하여서 허용을 선택할경우 해당권한을 흭득**할수있다.
  * 권한을 거부하거나 허용할경우에 대한 코드처리를 진행하여야 한다.
  * [코드 대응법](https://github.com/sdk0213/Android_Develop_Info_Record/blob/master/Android%20permission%20Request.md)
