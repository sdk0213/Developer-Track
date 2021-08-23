# 대표 메서드
---
> HEAD
* 메세지 헤더(문서 정보) 취득
> GET **(매우 중요)**
* 서버로부터 정보를 조회하기 위해 설계된 메소드
* 필요한 데이터를 Body에 담지 않고, 쿼리스트링을 통해 전송
* example
  * www.example-url.com/resources?name1=value1&name2=value2
* 보를 보여줘도 상관없는 기능을 수행할때 get 메소드를 수행한다.
* **주로 조회를 할 때에 사용한다**
> POST **(매우 중요)**
* 내용 전송 (파일 전송 가능)
* 리소스를 생성/변경하기 위해 설계되었기 때문에 GET과 달리 전송해야될 데이터를 HTTP 메세지의 Body에 담아서 전송
* 반드시 암호화해 전송
* 요청 헤더의 Content-Type에 요청 데이터의 타입을 표시해야 한다.
* **서버에게 동일한 요청을 여러 번 전송해도 응답은 항상 다를 수 있습니다. 이에 따라 POST는 서버의 상태나 데이터를 변경시킬 때 사용된다.**
* PUT이나 PATCH, DELETE 보다 더 보편적으 POST로 생성, 수정, 삭제를 진행한다.
* 요청시 데이터양의 제한이 없습니다.
* url상에 나타나지 않아 보안상 좋습니다

기타
---
> PUT
* 내용 갱신 위주 (파일 전송 가능)
> DELETE
* 파일 삭제
> OPTIONS
* 웹서버측 제공 메소드에 대한 질의
> TRACE (거의 사용 안함)
* 요청 리소스가 수신되는 경로를 보여줌
> CONNECT (거의 사용 안함)
* 프락시 서버와 같은 중간 서버 경유

---
### 형식 - [출처는 teddybearjung 님의 velog](https://velog.io/@teddybearjung/HTTP-구조-및-핵심-요소)
* REQUEST
  ```html
  --------------------------------- STATUS LINE ----------------- 
  POST /payment-sync HTTP/1.1        <---------- GET, POST, PUT, DELETE, OPTIONS / request가 전송되는 목표 uri / HTTP 버젼. 버젼에는 1.0, 1.1, 2.0

  --------------------------------- HADER --------------------- 
  Accept: application/json           <---------- 해당 요청이 받을 수 있는 응답(response) 타입
  Accept-Encoding: gzip, deflate
  Connection: keep-alive             <---------- 해당 요청이 끝난후에 클라이언트와 서버가 계속해서 네트워크 컨넥션을 유지 할것인지 아니면 끊을것인지에 대해 지시하는 부분.
  Content-Length: 83                 <---------- 메세지 body의 길이.
  Content-Type: application/json     <---------- 해당 요청이 보내는 메세지 body의 타입. 예를 들어, JSON을 보내면 application/json.
  Host: intropython.com              <---------- 요청이 전송되는 target의 host url: 예를 들어, google.com
  User-Agent: HTTPie/0.9.3           <---------- 요청을 보내는 클라이언트의 대한 정보: 예를 들어, 웹브라우저에 대한 정보.

  --------------------------------- BODY --------------------- 
  {
      "imp_uid": "imp_1234567890",        <---------- BODY 가 없는 경우도 있다.
      "merchant_uid": "order_id_8237352",
      "status": "paid"
  }
* RESPONSE
  ```html
  --------------------------------- STATUS LINE -----------------
  HTTP/1.1 404 Not Found             <---------- HTTP 버젼 / 응답 상태를 나타내는 코드 / 응답 상태를 간략하게 설명해주는 부분

  --------------------------------- HEADER ----------------------
  Connection: close                         <---------- 해당 요청이 끝난후에 클라이언트와 서버가 계속해서 네트워크 컨넥션을 유지 할것인지 아니면 끊을것인지에 대해 지시하는 부분.
  Content-Length: 1573                      <---------- 메세지 body의 길이.
  Content-Type: text/html; charset=UTF-8    <---------- 해당 요청이 보내는 메세지 body의 타입. 예를 들어, JSON을 보내면 application/json.
  Date: Mon, 20 Aug 2018 07:59:05 GMT       <---------- 날짜

  --------------------------------- BODY ------------------------                                                        
  <!DOCTYPE html>
  <html lang=en>
    <meta charset=utf-8>
    <meta name=viewport content="initial-scale=1, minimum-scale=1, width=device-width">
    <title>Error 404 (Not Found)!!1</title>
    <style>
      *{margin:0;padding:0}html,code{font:15px/22px arial,sans-serif}html{background:#fff;color:#222;padding:15px}body{margin:7% auto 0;max-width:390px;min-height:180px;padding:30px 0 15px}* > body{background:url(//www.google.com/images/errors/robot.png) 100% 5px no-repeat;padding-right:205px}p{margin:11px 0 22px;overflow:hidden}ins{color:#777;text-decoration:none}a img{border:0}@media screen and (max-width:772px){body{background:none;margin-top:0;max-width:none;padding-right:0}}#logo{background:url(//www.google.com/images/branding/googlelogo/1x/googlelogo_color_150x54dp.png) no-repeat;margin-left:-5px}@media only screen and (min-resolution:192dpi){#logo{background:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) no-repeat 0% 0%/100% 100%;-moz-border-image:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) 0}}@media only screen and (-webkit-min-device-pixel-ratio:2){#logo{background:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) no-repeat;-webkit-background-size:100% 100%}}#logo{display:inline-block;height:54px;width:150px}
    </style>
    <a href=//www.google.com/><span id=logo aria-label=Google></span></a>
    <p><b>404.</b> <ins>That’s an error.</ins>
    <p>The requested URL <code>/payment-sync</code> was not found on this server.  <ins>That’s all we know.</ins>
  
---     
### 응답 코드 (상식)
* 200
  * 문제없이 다 잘 실행 되었을때 보내는 코드.     
* 301 
  * 해당 URI가 다른 주소로 바뀌었을때 보내는 코드.
* 400
  * 해당 요청이 잘못된 요청일때 보내는 코드
  * 주로 요청에 포함된 input 값들이 잘못된 값들이 보내졌을때 사용되는 코드.
* 401
  * 유저가 해당 요청을 진행 할려면 먼저 로그인을 하거나 회원 가입을 하거나 등등이 필요
* 403
  * 유저가 해당 요청에 대한 권한이 없다는 뜻
* 404
  * uri가 존재 하지 않는다는 뜻
* 500
  * 서버에서 에러가 났을때 사용되는 코드.
  * API 개발자들이 싫어한다고 함 왜냐하며 자기 잘못이니까???
      
