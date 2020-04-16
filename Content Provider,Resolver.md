Content Provider/Resolver
===

Content Provdier/Resolver - [출처](https://blog.naver.com/PostView.nhn?blogId=qbxlvnf11&logNo=221420601185&redirect=Dlog&widgetTypeCall=true&directAccess=false), [출처2](https://galid1.tistory.com/28)
---
+ Application 간의 데이터 공유를 위해서 사용된다.
  + 다른 어플리케이션이 타앱의 데이터를 사용하고싶다 -> ContentResolver를 이용해서 타앱의 ContentProvider에게 요청 -> ContentProvider가 URI확인후 데이터 전달
  + extends ContentProvider를 사용
    + onCreate()
    + query()
    + insert()
    + delete()
    + update()
  + 쉽게 생각해서 ContentProvider = 데이터 제공자, ContentResolver = 데이터 수신자 라고 생각하면 된다.
+ ![Alt text](https://developer.android.com/guide/topics/providers/images/content-provider-tech-stack.png?hl=ko)
+ Context에 있는 ContentResolver 객체를 사용해서 제공자와 통신을 주고받으면 된다.
+ 흐름
  + ![Alt text](https://developer.android.com/guide/topics/providers/images/content-provider-interaction.png?hl=ko)
