Uri
===

Url
---
* **Uniform Resource Locator**
* 리소스 위치
* Url말고 Uri를 쓰는것이 대세이다.

Uri - [출처](https://blog.lael.be/post/61)
---
* **Uniform Resource Identifier**
* 요청하는 주소가 파일이라기 보다는 구분자로 보는것이다. 예를들어서 http://test.com/company/location 이라고 했을때 실제로 company/location 파일이 없는것처럼말이다.
* URI(동물), URL(다람쥐,강아지)
+ **Uri를 사용하는 이유**
  + ContentResolver앱에서는 ContentProvider 내부의 상세한 DB구조를 알 수 없다.
  + 그래서 ContentProvider가 제공한 Uri를 가지고 DB를 간단하게 접근가능하도록 기능을 제공
+ [그림출처](https://choidev-1.tistory.com/58?category=811200) - ![Alt text](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile26.uf.tistory.com%2Fimage%2F994692425C4FAFA40C7202)
+ 뜻하는 의미 - [출처](https://posnopi13.tistory.com/18)   
+ [그림출처](https://www.androidpub.com/49537) - ![Alt text](http://developer.android.com/images/content_uri.png)
  + content:/ 
    + ContentProvider에 의해 제공되는 데이터, 이부 변하지 않는다.
  + /com.a.b.c
    + ContentProvider의 authority부분이며 ContentProvider의 고유이름이고, 중복되면 안되므로 패키지명을 사용할것은 권장
  + /name/john
    + ContentProvider의 Path부분이며, 어떤 데이터를 반환할지를 이 부분을 통해 지정
  + /3
      + 해당 데이터의 ID를 나타낸다.
  + 단수
    + id까지 있을경우
  + 복수
    + path까지만 있을경우
    
+ 학생 테이블에서 3학년 중 이름이 홍길동인 학생 Uri : 학생/학년/3/이름/홍길동
+ 규칙은 어려운것이 아니다.
* ex) content://학생관리프로바이더 주소/학생/성적/학번/#
  * #은 정수(상수 와일드카드라고도 불림), *은 문자열로 자유롭게 변경할 수 있는 약속된 기호
* **URI 는 절대 테이블명이 아니다. 내부에서 테이블이 뭐 그런것들으 찾기 위한  키워드 쯤으로 생각하면 된다.**
  
