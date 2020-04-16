XmlPullParser
===

Xml - [출처](https://namu.wiki/w/XML)
--- 
  + eXtensible Markup Language
  + MarkUp Language란 무엇일까?
    + MarkUp Language는 태그 등을 이용하여 데이터의 구조를 기술하는 언어의 한 종류이다.
       + Html
       + Markdown
          + git에서 사용하는 지금 읽고있는 이 문서도 Markdown으로 작성되었다.
  + 다음과 같은 기능을 가지고 있다.
    + 데이터 이름의 범위
    + 실제 데이터의 범위
    + 데이터 단위의 범위
  + 매우 간단한 형태로 되어있기 때문에 구조화된 데이터를 읽고 쓰는데 매우 편리하다.
    + 지금의 대세는 Json이다.
  + 단순함(Simplicity), 범용성(Generality), 사용성(Usability)을 제공한다.
  + 본래 웹에서 사용할 목적으로 만들었으나 지금은 웹환경을 벗어난곳에서도 많이 사용된다.
  + 하지만 어찌되었든 기본적으로 표기할때 정의하는부분에서 데이터가 늘어나기 때문에 메모리복원(Deserialization, UnMarshalling) 비용이 증가한다.
  
Xml Parser - [출처](https://recipes4dev.tistory.com/134)
---
  + 종류
      + DOM
        + Document Object Model
        + w3c 공식 표준
          + w3C(World Wide Web Consortium)는 www을 위한 표준을 개발하고 장려하는 조직이다.
        + **문서 전체를 파싱한 결과를 메모리에 로드하여 문서의 내용과 1:1 Mapping을 이루는 Object(객체)로 생성/유지/탐색하는 방식**
      + SAX
        + Simple API for XML
        + 메모리를 많이 차지하지 않지만 데이터 내용을 조직하는 기능은 DOM보다 떨어짐
        + **문서를 하나의 스트림으로 취급하여 문서의 처음부터 끝까지 단 한번의 탐색 과정만 거치면서, 유효한 요소가 식별될 때마다 Parser의 외부에 이벤트로 전달하여 식별된 요소에 대한 처리를 Parser의 외부에 일임하는 방식**
  + xml파서는 숙련된 소프트웨어 엔지니어가 아니라면 쉽지 않은 작업이다.
    + 하지만, 대부분 플랫폼에서 API형태로 제공된다.
  + 
  |항목|DOM(Documnet Object Model)|SAM(Simple API for XMl)|
  |:------:|:-----------------------------------------:|:------------------------------------:|
  |파서의 동작|파싱 시작후 완료될때가지 대기후 Object return|파싱중 유효한 요소가 식별되면 event로 전달|
  |장점|탐색,추가,수정,삭제 용이|속도 빠름, 메모리 적음|
  |단점|메모리사용량많음,속도 느림|탐색,추가,수정,삭제 복잡, xml생성 개발자가 해야함|
  |적합성|탐색이 빈번한곳, 구조가 자주 변경되는곳|구조가 간단하나 동일한 요소가 반복되거나 요소의 값이 중요한경우|
  + 
        
XmlPullParser
---
  + http://www.xmlpull.org/ 에서 제공하는 구문 분석 기능을 정의하는 Interface이다.
  + Android에서 지원하는 Xml 파서로서 **SAX(Simple API for XML)** 방식으로 동작
  + **그리고 이를 사용할수있게 Android에서는 다음과 같은 두가지 함수를 제공하는데 둘중 하나만 사용하면된다.**
    + KXmlParser -> XmlPullParserFactory.newPullParser() 로 생성
    + KXmlParser 사용하는 코드
    ```Java
      try {
         XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
         XmlPullParser parser = parserFactory.newPullParser() ;
      } catch () {
         ...
      }
    ```
    + ExpatPullParser 사용하는 코드
    ```Java
      try {
          XmlPullParser parser = Xml.newPullParser() ;
      } catch () {
          ...
      }
     ```
    + 위의 초기화과정은 다르지만 결과적으로 얻는 Instance는 XmlPullParser 타입으로 참조가 가능하다. 그러므로 차이가 없다.
    + xml 파일열기 - 직접적으로 xml을 여는 방법은 존재하지 않고 스트림으로 처리하도록 되어있음
      ```java
      FileInputStream fis = new FileInputStream("file.xml");
      parser.setInput(fis, null);
      ```
    + next()함수는 Xml요소를 식별할때까지 지속
 
 
XmlPullParser 사용법 - [출처](https://recipes4dev.tistory.com/134)
---
  
+ ![Alt text](https://t1.daumcdn.net/cfile/tistory/2723E03B594A18831A)
+ src/main/assets/contact.xml 에 다음과 같이 정의하였을때
```xml
<?xml version="1.0" encoding="utf-8"?>
<CONTACT>
    <NO>1</NO>
    <NAME>ppotta</NAME>
    <PHONE>010-8888-9999</PHONE>
    <OVER20>true</OVER20>
</CONTACT>
```
+ 코드 요약 - [예제풀코드](https://recipes4dev.tistory.com/137?category=697793)
```java
...
parserFactorynewPullParser();
..
parser.setInput(is, "UTF-8");
while(eventType != XmlPullParser.End_DOCUMNET)
  if(..START_DOCUMNET) ...
  else if(XmlPullParser.START_TAG) ...
  else if(XmlPullParser.ENT_TEAG) ...
  else if(XmlPullParser.TEXT) ...
  중간중간 에러처리 ...
...
받아온값 반영
```
  
