APK 서명키 RSA 확인 [출처](https://qastack.kr/programming/11331469/how-do-i-find-out-which-keystore-was-used-to-sign-an-app)
===
* apk 압축풀기 (zip으로 변경해도됨)
* META-INF - CERT.RSA 
* 터미널 또는 커멘드 실행후 입력 
  * keytool -printcert -file CERT.RSA
* 정보확인 및 키확인
