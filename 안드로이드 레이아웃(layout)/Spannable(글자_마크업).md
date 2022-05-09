# [Spannable](https://developer.android.com/guide/topics/text/spans?hl=ko)
* 마크업 객체
* 아래와 같이 마크업객체만 사용하면 글자를 자유자재로 변형가능
* ![](https://developer.android.com/guide/topics/text/images/spans-fg-color.png?hl=ko)
---
### 생성
* <img width="319" alt="스크린샷 2022-05-09 오후 9 42 01" src="https://user-images.githubusercontent.com/51182964/167412045-2bd1aff3-c649-43b4-816e-244c045cb344.png">
* ```java
  mTvView = (TextView) findByView....
  Spannable span = (Spannable) mTvView.getText();
---
### 글자색
* <img width="158" alt="스크린샷 2022-05-09 오후 9 44 38" src="https://user-images.githubusercontent.com/51182964/167412430-bb06f63d-f68a-4d49-b642-8d64a5368dcc.png">
* ```xml
  span.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
---
### 글자 배경색
* <img width="209" alt="스크린샷 2022-05-09 오후 9 45 13" src="https://user-images.githubusercontent.com/51182964/167412537-ea0bf2aa-4968-4596-b95c-555a77f7c03d.png">
* ```java
  span.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
---
### 글자 크기
* ![](https://developer.android.com/reference/android/images/text/style/absolutesizespan.png)
* 절대 크기 설정하기
  ```java
  span.setSpan(new AbsoluteSizeSpan(100), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
* 상대 크기 설정하기
  ```java
  span.setSpan(new RelativeSizeSpan(1.5f), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
---
### 밑줄
* <img width="246" alt="스크린샷 2022-05-09 오후 9 50 14" src="https://user-images.githubusercontent.com/51182964/167413364-67440bc7-3ab9-4bec-9cf0-ef67e8feea05.png">
* ```java
  span.setSpan(new UnderlineSpan(), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
---
### 글자 스타일
* Typeface.java
  * <img width="461" alt="스크린샷 2022-05-09 오후 9 51 40" src="https://user-images.githubusercontent.com/51182964/167413655-1c100c23-0466-428c-9958-f9ef271cef1a.png">
* ```java
  span.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
---
### [이미지 스팬](https://developer.android.com/reference/android/text/style/ImageSpan)
* ![](https://developer.android.com/reference/android/images/text/style/imagespan.png)
* ```java
   SpannableString string = new SpannableString("Bottom: span.\nBaseline: span.");
   // using the default alignment: ALIGN_BOTTOM
   string.setSpan(new ImageSpan(this, R.mipmap.ic_launcher), 7, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
   string.setSpan(new ImageSpan(this, R.mipmap.ic_launcher, DynamicDrawableSpan.ALIGN_BASELINE),
   22, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
 
---
### 링크
* <img width="181" alt="스크린샷 2022-05-09 오후 9 56 42" src="https://user-images.githubusercontent.com/51182964/167414577-dddd3599-b2ae-46b9-8ca6-f1619dbb9161.png">
* ```java
  String url = "https://github.com/sdk0213";
  span.setSpan(new URLSpan(url), 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  mTvView.setMovementMethod(LinkMovementMethod.getInstance());
---
### 클릭 리스너
* <img width="287" alt="스크린샷 2022-05-09 오후 9 57 58" src="https://user-images.githubusercontent.com/51182964/167414787-4dd41f40-d820-4ce0-bca4-775776761f82.png">
* ```java
  span.setSpan(new ClickableSpan() {
      @Override
      public void onClick(View widget) {
          Toast.makeText(mContext, "라크라꾸~~", Toast.LENGTH_SHORT).show();
      }
  }, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  mTvView.setMovementMethod(LinkMovementMethod.getInstance());
