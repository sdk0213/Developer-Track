# multi part - [출처](https://privatedevelopnote.tistory.com/18)
---
### 정의
* **form data가 여러 부분으로 나뉘어 서버로 전송**되는 것을 의미합니다.
* 웹 클라이언트가 요청을 보낼 때, http 프로토콜의 바디 부분에 데이터를 여러 부분으로 나눠서 보내는 것이다.
---
### file upload form
* 파일 업로드 시에는 form 태그의 enctype이 "multipart/form-data"로 설정
* method는 "POST"이어야 한다.
* input 태그의 type 속성 값을 "file"로 지정해야한다.
* type이 "file"인 input 태그가 여러개 있고, name 속성의 값이 같다면 파일이 배열 형태로 Controller에게 전달된다.
---
### android
*  브라우저처럼 Multipart/form-data형식에 대한 Request Body를 자동으로 만들어 주는 것이 아니기 때문에 개발자가 직접 그 패턴을 숙지하고 해당 데이터에 대한 Request Body를 만들어 주어야만 한다.
##### boundary
* 각 파라미터를 감싸는 경계
* w3c 규격
  * boundary에 하이픈을 두개 넣어야만 한다. 그리고 마지막 파라미터 블럭에는 boundary 양 옆으로 각각 두개의 하이픈을 넣어주어야만 한다
##### 파일 전송시
* filename
  * 파일을 전송할 때 반드시 필요한 값이다.
* Content-type 아래에 byte화 된 파일의 정보가 들어간다
