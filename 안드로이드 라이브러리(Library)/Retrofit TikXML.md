### <strike>com.squareup.retrofit2:converter-simplexml
* Deprecated 되어서 사용하지 않는다.</strike>
---
### TikXML - [출처는 블랙빈님의 티스토리](https://bb-library.tistory.com/177)
* **현재 알수없는 이유로 0.8.15를 사용하면 resolve 에러가 발생한다.** 
* [Tickaroo의 tikxml 라이브러리](https://github.com/Tickaroo/tikxml)
* Retrofit 사용시 XML 타입을 파싱하고 싶을때 사용하는 라이브러리
* ```kotlin
  Retrofit.Builder()
      .이것저것 설정하기
      .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build())
      .build()
* POJO 에서 굳이 읽고 싶지 않는 데이턱 있을경우 추가
  ```kotlin 
  TikXml.Builder().exceptionOnUnreadXml(false).build()
  
##### data class 형식 - [코드 출처는 블랜빈님 블로그 코드](https://bb-library.tistory.com/177)
* ```kotlin
  package com.turtle.amatda.data.model

  import com.tickaroo.tikxml.annotation.Element
  import com.tickaroo.tikxml.annotation.PropertyElement
  import com.tickaroo.tikxml.annotation.Xml

  @Xml(name="")
  data class AreaXml(
      @Element
      val response: AreaXmlResponse
  )

  @Xml(name="response")
  data class AreaXmlResponse(
      @Element
      val header: AreaXmlHeader,
      @Element
      val body: AreaXmlBody
  )

  @Xml(name="header")
  data class AreaXmlHeader(
      @PropertyElement
      val resultCode: Int,
      @PropertyElement
      val resultMsg: String
  )

  @Xml(name="body")
  data class AreaXmlBody(
      @Element
      val items: AreaXmlItems,
      @PropertyElement
      val numOfRows: String,
      @PropertyElement
      val pageNo: String,
      @PropertyElement
      val totalCount: String
  )

  @Xml(name="header")
  data class AreaXmlItems(
      @Element(name = "item")
      val item: List<AreaEntity>
  )
  
  @Xml
  data class AreaEntity (
      @PropertyElement(name="code")
      val code: String?,
      @PropertyElement(name="name")
      val name: String?,
      @PropertyElement(name="rnum")
      val rnum : String?
  ){
      constructor() : this(null,null,null)
  }

##### @Xml
* 하위 Element 들의 상위 개념
##### @Element
* 하위 ProportyElement 들의 상위 개념
##### @PropertyElement
* 데이터 그 자체
##### nullable 로 해주기
* POJO 에서 받은 데이터가 없을수도 있기 때문이다.
  * 만약 XML에서 받으려고 했던 일부 프로퍼티가 존재하지 않을 경우 null로 받을 수 있도록 처리

      
   
