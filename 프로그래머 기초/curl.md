# CURL
* meaning
  * **C**ommand **L**ine **T**ool and **L**ibrary

### 명령어
##### GET
* ```curl
  curl http://localhost:8080

##### 요청 Method 설정
* -X, --request
* ```curl
  --request POST "naver.com"
* ```curl
  -X POST "naver.com"

##### Header
* -H —header <header>
* ```curl
  curl http://localhost:8080
  -H 'Accept: application/json'
  
##### 인증서 해지 확인 중지하기
* --ssl-no-revoke 
  * Disable cert revocation checks (Schannel) 

##### Redirect 결과 따라가기
* --location 또는 -L
 
 
#### Multipart Form
* -F 또는 --form 을 사용
* -F 또는 --form 를 사용하면 "Content-Type: multipart/form-data" 를 사용하지 않아도 된다.
 * 추가적으로  boundary 를 설정하지 않아도 된다.
  * -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'
