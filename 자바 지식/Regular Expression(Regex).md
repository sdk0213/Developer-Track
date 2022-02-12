# Regular Expression(Regex) - 정규 표현 - [출처는 gocoding님의 티스토리](https://gocoding.tistory.com/93)
* 특정한 **규칙을 가진 문자열의 집합을 표현**하는 데 사용하는 형식 언어, 아이디에 들어갈 형식, 비밀번호에 들어갈 형식
* 형식이 매우 많기 때문에 필요한것만 습득
* 주요 클래스
  ```java
  // Pattern.java
  import java.util.regex
  
  public final class Pattern
    implements java.io.Serializable {
      ...
  
  }
  
  // Matcher.java
  import java.util.regex
  
  public final class Matcher implements MatchResult {
      ...
  }
  
  // String 도 matches 를 가지고 있다.
  // String.java 
  public boolean matches(String regex) {
      return Pattern.matches(regex, this);
  }
* 이 외
  * Matcher.find() - 문자열에서 패턴과 일치하는 부분이 있는지 찾는다.
  * Matcher.lookingAt() - 문자열이 주어진 패턴으로 시작하는지 일치시켜 본다.
  * Matcher.replaceAll() - 일치하는 부분을 모두 치환한다.
  * Matcher.replaceFirst() - 처음 일치하는 부분만 치환한다.

##### 정규식 작성
  * 패턴 작성 
    * ```java    
      String str = "[a-t]rum[pt]"
  * 패턴 컴파일
    * ```java    
      public static Pattern compile(String regex) {
          return new Pattern(regex, 0);
      } 
      
* 표현식
  |식|내용|
  |:--:|:--:|
  |[ ]|하나의 문자|
  |[abc]|abc 만 됩니다.|
  |^|제외 or ~로 시작하는 문자열|
  |$|~로 종료되는 문자열|
  |( )|문자열 묶음, group 을 만들때 사용|
  |[^abc]|abc 가 아니여야 됩니다.|
  |[a-z&&[^bc]]|b와 c를 제외한 a 부터 z까지 중의 하나와 일치합니다|
  |[a-z&&[^m-p]]|m부터 p 까지를 제외한, a 부터 z까지 중의 하나와 일치합니다|
  |\\\\ | \\\\ 뒤에 특수문자 취급|
  |.|모든 문자를 확인|
  |\\d|[0-9] 와 동일함|
  |\\D|[^0-9] 와 동일함|
  |\\w|[A-Za-z_0-9] 와 동일함 = char형 문자]
  |\\W|[^\\w]
  |\\s|공백포함 허용|
  |\\S|공백포함 허용 안됨|
  |\n|줄넘기 문자도 검색가능|
  |?|앞 문자가 하나 이상이어야 함|
  |+|한개 이상 있어야됨|
  |+.| . 이 하나 이상 와야함 다른 문자 가능. {1,} 와 동일|
  |*|없거나 무엇이라도 있어야 되나요? {0,} 와 동일|
  |{n}|n 개 있어야함|
  |{n,}|최소한 n개가 있어야 함|
  |{n, m}|최소 n개, 최대 m 개 있어야 함|
  |(?i)|대소문자 구별 안함|
* [ ] 안에서는 특수 문자가 모두 효력을 잃게
##### 간단예제
* |식|내용|
  |:--:|:--:|
  |^The|The로 시작하는 문자열|
  |of despair$|of despair로 끝나는 문자열|
  |^abc$|abc로 시작하고 abc로 끝나는 문자열 (abc 라는 문자열도 해당됨)|
  |notice|notice가 들어 있는 문자열|
  |ab*|a 다음에 b가 0개 이상 (a, ab, abbb 등등)|
  |ab+|a 다음에 b가 1개 이상 (ab, abbb 등등)|
  |ab?|a 다음에 b가 있거나 없거나 (ab 또는 a)|
  |ab{2}|a 다음에 b가 2개 있는 문자열 (abb)|
  |ab{2,}|a 다음에 b가 2개 이상 (abb, abbbb 등등)|
  |ab{3,5}|a 다음에 b가 3개에서 5개 사이 (abbb, abbbb, 또는 abbbbb)|
  |a(bc)*|a 다음에 bc가 0개 이상 (묶음 처리)|
  |a(bc){1,5}|a 다음에 bc가 1개에서 5개 사이|
  |^.{3}$|3문자로만 되어 있는 문자열|
  |[ ]|괄호 안에 있는 내용 중 임의의 한 문자|
  |[ab]|a 또는 b (a|b 와 동일한 표현)|
  |[a-d]|소문자 a에서 d까지 (a|b|c|d 또는 [abcd] 와 동일)|
  |^[a-zA-Z]|영문자로 시작하는 문자열|
  |[0-9]%|% 문자 앞에 하나의 숫자가 붙어 있는 패턴|
  |%[^a-zA-Z]%|두 % 문자 사이에 영문자가 없는 패턴|

##### 예제
* ```kotlin
  val s = "41오9135,97  나4539,11허32".split(",").toTypedArray()
  for (i in s.indices) {
      println(s[i] + " matches " + s[i].matches(Regex("\\d{2}\\s?[가-힇]\\d{3,}"))) 
      // 숫자 2개 + 공백 확인 + 문자 확인 + 숫자 최소 3개 이상
      // 97  나4539
      // 공백은 2개이기 때문에 ?로는 false, *로하면 공백이 여러개여도 상관없음
  }
  
##### 그룹화
* <img width="500" alt="스크린샷 2022-01-04 오전 11 03 51" src="https://user-images.githubusercontent.com/51182964/147999988-a3224124-f03e-4b23-8444-9e873a6be419.png">|

##### 자주 쓰이는 정규식
* ' \ \ ' 가 ' \ ' 로 표현될수 있음, 시용시에는 ' \ \ ' 로 사용하여야함 (java, kotlin 기준)
* 표현식
  |의미|식|
  |:--:|:--:|
  |뒷쪽의 4자리를 제외하고 * 으로 변경|s.replaceAll(".(?=.{4})", "*");|
  |공백제거|" a  3people  unFollowed   me  ".replaceAll("\\s{1,}","");|
  |공백제거해서 첫항이 "" 이 안나오게 분리|Arrays.stream("  a  3people   unFollowed    me   ".split("\\s{1,}")).filter( a -> !a.equals("")).toArray(String[]::new);|
  |숫자는 모두 삭제하여 분리하기|String[] operator = exp.replaceAll("[\\d]","").split("");|
  |숫자|^[0-9]*$|
  |영문자|^[a-zA-Z]*$|
  |한글|^[가-힣]*$|
  |영어&숫자|^[a-zA-Z0-9]*$|
  |비밀번호 (숫자, 문자 포함의 6~12자리 이내)|^[A-Za-z0-9]{6,12}$|
  |비밀번호 (숫자, 문자, 특수문자 포함 8~15자리 이내)|^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$|
  |이메일|^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$|
  |휴대전화|^\\d{3}-\\d{3,4}-\\d{4}$|
  |일반전화|^\\d{2,3}-\\d{3,4}-\\d{4}$|
  |주민등록번호|\d{6} \- [1-4]\d{6}|
  |파일확장자|^\\S+.(?i)(txt|pdf|hwp|xls)$|
  |이중 파일확장자|(.+?)((\\.tar)?\\.gz)$|
  |http:// https:// 포함해도 OR 안해도|/^(((http(s?))\:\/\/)?)([0-9a-zA-Z\-]+\.)+[a-zA-Z]{2,6}(\:[0-9]+)?(\/\S*)?$/|
  |비밀번호 (숫자, 문자 포함의 6~12자리 이내)|^[A-Za-z0-9]{6,12}$|
