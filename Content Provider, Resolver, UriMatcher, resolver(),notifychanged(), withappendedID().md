Content Provider, Resolver, UriMatcher, withAppendedId, notifychanged
===

Content Provdier - [출처](https://blog.naver.com/PostView.nhn?blogId=qbxlvnf11&logNo=221420601185&redirect=Dlog&widgetTypeCall=true&directAccess=false), [출처2](https://galid1.tistory.com/28), [출처](https://gakari.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Content-Provider-%EC%BD%98%ED%85%90%ED%8A%B8-%ED%94%84%EB%A1%9C%EB%B0%94%EC%9D%B4%EB%8D%94)
---
* **생겨난이유** [출처](https://salix97.tistory.com/11)
  * **만약 아무 앱이나 다른 앱의 DB에 접근할 수 있어서, 내 카카오톡 대화 내용을 이상한 앱에서 가져와서 마음대로 쓸수 있다면 보안상의 문제가 생긴다.**
  * 하지만 그렇다고 DB를 막아버리면 instagram에서 기본 갤러리앱의 DB에 접근도 할수없을것이다.
  * 그래서 만들어진것이 바로 **ContentProvider**이다.
* 콘텐트 제공자라는 뜻을 가지고 있다.
* 다른 앱에 있는 데이터베이스에 접근할 수 있도록 도와준다.
* 즉, 이미지/동영상/텍스트 등의 내 앱이 가지고 있는 컨텐츠 정보를 다른 앱에 제공해주는 역할을 한다.
+ Application 간의 데이터 공유를 위해서 사용된다.
  + 다른 어플리케이션이 타앱의 데이터를 사용하고싶다 -> ContentResolver를 이용해서 타앱의 ContentProvider에게 요청 -> ContentProvider가 URI확인후 데이터 전달
* CotentProvider가 Uri를 해석하여 필요한 DB작업을 하게 된다. 기본적으로 CRUD(create, read, update, delete)연산을 모두 처리 한다.
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
      + URI에 해당하는 MIME정보인데 return할때 다음과같이 단수,복수형태로 return한다.
      + 대체적으로 gettype()에서는 단수,복수를 다음과같은 형식으로 구분한다.
        + 단수 : vnd.회사명.cursor.item/타입 
        + 복수 : vnd.회사명.cursor.dir/타입
      + example)
        ```java
        if(Matcher.match(uri) == ALLWORD){

            return "vnd.EnglishWord.andexam.cursor.item/word";

        }

        if(Matcher.match(uri) == ONEWORD){

            return "vnd.EnglishWord.andexam.cursor.dir/word";

        }
        ```        
    
Content Resolver
---
* ContentProvider에게 데이터를 요청하고 수신하는놈이다.
* 내가 파악한 바로는 ContentResolver가 uri로 ContentProvider로 접속하면 ContentProvider의 함수 코드들을 원격으로 실행시키는것같다.
* [그림출처](https://salix97.tistory.com/11)
* ![Alt text](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FWeBOC%2FbtqzYDRcorA%2FaV7EveBYKE2vvvhgNlhWv0%2Fimg.jpg)
* 


UriMatcher
---
+ **요약 : UriMatcher의 addURI를 사용해서 Uri를 만들고 해당 Uri는 db랑 연결된다는의미를 가진다. 그러니까 ContentResolver는 ContentProvider의 UriMatcher의 addURI를 통해 만든 Uri에 접속을 하면 ContentProvider가 필요한 정보를 db에 꺼내다주는것이다. 그러니까 Uri는 그냥 어디어디로 접속하면 우리의 db랑 연결해줄게 라는 약속을 해주는 스키마(?)역할이다.**
+ 앱내에 사용되는 Uri가 content://authority 또는 content://authority/path 두 가지라면 각각의 경우에 다른 처리가 필요하고 이를 if문으로 구분하게 되면 경우의 수가 많아질때 편하게 해주는것
+ 그리고 문자열을 비교해서는 속도가 느리므로 UriMatcher라는 유틸리티 클래스를 이용해서 쉽게 문자열을 비교할수있다.
+ 두개의 Uri값을 비교하여 해당하는 값을 출력해주는 기능
+ addURI()
  + ```java
    matcher.addURI(AUTHORITY, "dictionary", SEARCH_WORDS);
    ```
  + 의미 : AUTHORITY에 dictionary문자열이 온다면 SEARCH_WORDS(상수)값을 return시켜라.
  + 약속된 값을 등록할 때 사용하는것, URI와 URI가 일치할 때 리턴할 코드를 추가한다.
  + addURI메소드로 authority, path의 쌍으로 정수 코드와 대응시켜 맵을 등록한다. path 에서는 임의의 문자열과 대응되며 #은 숫자 하나와 대응된다.
+ match()
  + match 메소드는 uri를 분석하여 등록된 정수 코드를 리턴한다. 만약 uri에 해당하는 코드가 발견되지 않으면 -1을 리턴한다.
  + 비교하여서 정수값을 반환해주는

getContext(),getContentResolver(),notifyChage(insertUri, null)
---
* Provider에서 제공하는 저장소를 나만 쓰는것도 다른 앱에서도 사용할수있는데 그럴경우 변경사항을 알려줘야한다.
* 만약에 내가 사용도중에 값이 변경(삭제,업데이트)되었는데 다른앱은 그 전에 값을 가져올수도 있기 때문이다.
* 그렇기 때문에 위의 코드를 사용해서 변경사항을 알려야한다.

withAppendedId() - [출처](https://posnopi13.tistory.com/19)
---
* content://com.proivder.students/students/4 이런식으로 되어있으면 id 4번을 삭제한다는것이고
* content://con.provider.students/students/ 이런식으로 되어있으면 student의 id전부다 삭제한다는것인데
* 이거를 그러면 매번 할때마다 저런식으로 다 써야될까? 그러니까 앞에 content://com.pro....dents/ 까지 다써야할까?
* 이러한 노가다를 막아주는것이 withAppendedId() 이다. 
* example code)
* ```java
      //행 하나를 삽입한다.

    public Uri insert(Uri uri, ContentValues values) {

        long row = mDB.insert("dic", null, values);

        

        //입력에 성공하면 notifyChange 메소드를 호출함.

        if (row > 0) {

            //아래는 추가된 ID를 보유한 Uri 객체를 리턴하는 메소드

            //관련 링크 : http://posnopi13.tistory.com/19 참고

            Uri notiuri = ContentUris.withAppendedId(CONTENT_URI, row);

            

            //다른 CP들에게 변화를 알림

            getContext().getContentResolver().notifyChange(notiuri, null);

            return notiuri;

        }

        return null;

    }
  ```
흐름
---
+ ![Alt text](https://t1.daumcdn.net/cfile/tistory/013C9E4050EBE1D226)
+ ![Alt text](https://developer.android.com/guide/topics/providers/images/content-provider-tech-stack.png?hl=ko)
+ Context에 있는 ContentResolver 객체를 사용해서 제공자와 통신을 주고받으면 된다.
+ 흐름
  + ![Alt text](https://developer.android.com/guide/topics/providers/images/content-provider-interaction.png?hl=ko)
  
