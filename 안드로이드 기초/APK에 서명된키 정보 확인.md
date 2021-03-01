APK에 서명된 키 정보 [출처](https://qastack.kr/programming/11331469/how-do-i-find-out-which-keystore-was-used-to-sign-an-app)
---
* 복잡한 방법
  * apk 압축풀기 (zip으로 변경해도됨)
  * META-INF - CERT.RSA 
  * 터미널 또는 커멘드 실행후 입력 
    * keytool -printcert -file CERT.RSA
  * 정보확인 및 키확인
* 간단한 방법
  * keytool -printcert -jarfile [ .apk 파일 ]

서명키 정보
---
* keytool -list -v -keystore [ .jks 파일 ]
  * mac에서는 jks 확장자 안붙혀도됨 윈도우는 모름

