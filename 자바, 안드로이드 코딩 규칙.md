참고1 : http://developer.gaeasoft.co.kr/development-guide/java-guide/java-coding-style-guide/

참고2 : https://myeonguni.tistory.com/1596

참고 3 : http://developer.gaeasoft.co.kr/development-guide/android-guide/android-coding-style-guide/

참고 4 : https://soojin.ro/blog/naming-boolean-variables


공통규칙
===
+ is
  + is + 명사 : "무엇인가?"
  + is + ing : "~하는중인가?"
  + is + 형용사 : 과거분사, 형용사 잘 나누어서 적용
  + ~~is + 동사원형 : 절대쓰면안된다.~~
+ has
  + has + 명사 : "~를 가지고 있는가?"
  + has + 과거분사 : is랑 비슷한형태로 그냥 입맛대로
+ 동사원형
  + 3인칭 단수 형태로
 


Java 명명규칙
===
+ 공통
  + **예약어 포함 절대금지**
  + 특수문자는 '_' , '$' 를 사용한다.
  + 파스칼 표기법 (PascalCase)과 카멜 표기법(camelCase)를 사용한다.
    + PascalCase : 모든 단어에서 첫 번째 문자는 대문자이며 나머지는 소문자이다.
    + camelCase : 최초에 사용된 단어를 제외한 첫 번째 문자가 대문자이며 나머지는 소문자이다.
+ class, interface
  + 대문자로 시작
+ method
  + 소문자로 시작
  + 여러단어 조합시 다음 단어 첫글자 대문자
  + 기능 <-> 동사
  + 기능에 따른 명명법
    + 속성에 접근 : get, set을 사용
    + 데이터 조회 : find
    + 데이터 입력 : input
    + 데이터 변경 : modify
    + 데이터 삭제 : delete
    + 데이터 초기화 : init
    + 반환값 boolean : is
    + 데이터 불러오기 : load
    + 데이터 유무확인 : has
    + 지능적인 set : register
    + 객체 생성 및 반환 : create
    + 형태 변환 : to
    + 객체의 복수,단수 : ~s
    + B를 기준으로 A를 하겠다 : by ... Ex) public void getUserByName(String name){}
    
+ 속성
  + method랑 동일 
+ 상수
  + 대문자
  + 여러단어일시 '_' 로 구분자 사용
  + 헝가리안 표기법 및 범위 식별자는 사용하지 않음 ex) mname( m = member ), sname( s = string )
+ Package
  + 한단어 사용
  + 소문자만 사용
+ 기타
  + a = b + c;  << 띄어쓰기
  + for (int i = 0; i < 10; i++) ; 쉼표와 세미콜론 사이 space
  + String[] args(O) , String args[](X)
  + do~ while문 사용 지양
  + 메소드 중간에 return 사용 금지
  + continue 사용금지
  + d = (a = b + c) + r;  -----> a = b + c; , d = a + r; 두개로 나누어서 사용 (할당문은 성능을 저하시킨다)
  
Android 명명 규칙
===
+ 예외를 절대로 무시하지않기
+ 메소드는 40줄 넘어가지 않게 작성
  + 40줄이 넘어간다면 문제가 있을 확률이 크다
+ Non-public, non-static field 는 m으로 시작
  + Static field : s~
  + 나머지는 소문자로 시작.
  + Public static final fields (상수) 는 모두 대문자로 ALL_CAPS_WITH_UNDERSCORES.
+ 축약어 사용
  + XmlHttpRequest ~~XMLHTTPRequest~~
  + getCustomerId	~~getCustomerID~~
  + class Html ~~class HTML~~
  + String url ~~String URL~~
  + long id	~~long ID~~
+ 일관성을 준수
  + **기존 코드나 레거시 규칙이 있고 거기에 추가로 개발을 하게 된다면 기존 룰을 준수하여 일관성**
+ ~~private Context mContext = null;~~
  + context를 별도 멤버로 선언하고 관리하지 않도록 한다.

변수명 지어주는 사이트 : https://www.curioustore.com/#!/
