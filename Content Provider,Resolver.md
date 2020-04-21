Content Provider/Resolver
===

Content Provdier - [출처](https://blog.naver.com/PostView.nhn?blogId=qbxlvnf11&logNo=221420601185&redirect=Dlog&widgetTypeCall=true&directAccess=false), [출처2](https://galid1.tistory.com/28)
---
* 콘텐트 제공자
* 다른 앱에 있는 데이터베이스에 접근할 수 있도록 도와준다.
+ Application 간의 데이터 공유를 위해서 사용된다.
  + 다른 어플리케이션이 타앱의 데이터를 사용하고싶다 -> ContentResolver를 이용해서 타앱의 ContentProvider에게 요청 -> ContentProvider가 URI확인후 데이터 전달
  + If you don't need to share data amongst multiple applications you can use a database directly via
  + extends ContentProvider를 사용
    + onCreate()
      + ```java
        protected void onCreate(Bundle savedInstanceState)
        ```
    + query()
      + ```java
        public Cursor query(Uri uri, String[] projection,
            String selection, String[] selectionArgs, String sortOrder)
        ```
    + insert()
      + ```java
        public Uri insert(Uri uri, ContentValues values)
        ```
    + delete()
      + ```java
        public int delete(Uri uri, String selection, String[] selectionArgs)
        ```
    + update()
      + ```java
        public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs)
        ```
    + gettype()
      + ```java
        public String getType(Uri uri)
        ```
    
Content Resolver
---
+ 쉽게 생각해서 ContentProvider = 데이터 제공자, ContentResolver = 데이터 수신자 라고 생각하면 된다.

UriMatcher
---
+ 앱내에 사용되는 Uri가 content://authority 또는 content://authority/path 두 가지라면 각각의 경우에 다른 처리가 필요하고 이를 if문으로 구분하게 되면 경우의 수가 많아질때 편하게 해주는것
+ 두개의 Uri값을 비교하여 해당하는 값을 출력해주는 기능
+ addURI()
  + 약속된 값을 등록할 때 사용하는것, URI와 URI가 일치할 때 리턴할 코드를 추가한다.
+ match()
  + 비교하여서 정수값을 반환해주는것
+ **Uri를 사용하는 이유**
  + ContentResolver앱에서는 ContentProvider 내부의 상세한 DB구조를 알 수 없다.
  + 그래서 ContentProvider가 제공한 Uri를 가지고 DB를 간단하게 접근가능하도록 기능을 제공
  + [출처](https://choidev-1.tistory.com/58?category=811200) - ![Alt text](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile26.uf.tistory.com%2Fimage%2F994692425C4FAFA40C7202)
    + [출처](https://posnopi13.tistory.com/18)
    + content:/ 
      + ContentProvider에 의해 제공되는 데이터, 이부분은 변하지 않는다.
    + /com.a.b.c
      + ContentProvider의 authority부분이며 ContentProvider의 고유이름
    + /name/john
      + ContentProvider의 Path부분이며, 어떤 데이터를 반환할지를 이 부분을 통해 지정
    + /3
      + 해당 데이터의 ID를 나타낸다.
  + 학생 테이블에서 3학년 중 이름이 홍길동인 학생 Uri : 학생/학년/3/이름/홍길동
  + 규칙은 어려운것이 아니다.
  * ex) content://학생관리프로바이더 주소/학생/성적/학번/#
    * #은 정수(상수 와일드카드라고도 불림), *은 문자열로 자유롭게 변경할 수 있는 약속된 기호
    
withAppendedId()
---
* 새로운 행이 db에 추가가 되었고 나머지 ContentProvider들이 알아야 할때 쓰는것으로 코드는 다음과같다.
  * getContext(),getContentResolver(),notifyChage(insertUri, null)


흐름
---
+ ![Alt text](https://t1.daumcdn.net/cfile/tistory/013C9E4050EBE1D226)
+ ![Alt text](https://developer.android.com/guide/topics/providers/images/content-provider-tech-stack.png?hl=ko)
+ Context에 있는 ContentResolver 객체를 사용해서 제공자와 통신을 주고받으면 된다.
+ 흐름
  + ![Alt text](https://developer.android.com/guide/topics/providers/images/content-provider-interaction.png?hl=ko)
  
