Getting Stated - [glide document](https://bumptech.github.io/glide/doc/getting-started.html)
===
Basic Usage
---
* Glide와 함께 이미지를 로딩하는 것은 매우 쉽고 많은 경우에는 간단히 한개의 라인만 코딩하면된다.
  * ```java
    Glide.with(fragment)
        .load(myUrl)
        .into(imageView);
* 더 이상 필요하지 않는 로드를 캔슬링하는것도 간단하다
  * ```java
    Glide.with(fragment).clear(imageView);
  * **하지만 필요 하지 않는 load를 clear 하는것은 좋은 연습이지만 그렇게 할필요가없다.**
    * 왜냐하면 Glide는 Activity와 Fragment가 파괴되면 자동적으로 로드된것을 정리하고 로드하는데 사용된 리소스를 재활용하기 때문이다.
    
Customizing requests
---
* Glide 는 다음과 같은 다양한 옵션들을 제공한다. 옵션들은 아래의 코드들 처럼 개별적으로 요청이 가능하다.
  * transformation(변환)
  * transitions(전환)
  * caching options(캐싱 옵션)
* 옵션 기본 사용
  * ```java
    Glide.with(fragment)
      .load(myUrl)
      .placeholder(placeholder)
      .fitCenter()
      .into(imageView);
* RequestOptions 클래스를 사용해서 공유가능한 옵션을 만들수도 있다.
  * ```java
    RequestOptions sharedOptions =
        new RequestOptions()
          .placeholder(placeholder)
          .fitCenter();
    
    Glide.with(fragment)
      .load(myUrl)
      .apply(sharedOptions)
      .into(imageView_1)
      
    Glide.with(fragment)
      .load(myUrl)
      .apply(sharedOptions)
      .into(imageView_2) 
* Glide의 API 는 더 진보된 사용 또는 고급 사용을 위해 (advanced use cases) 경우를 위해 Glide의 generate API를 사용하는 Custom options을 포함하도록 더 확장할수있다.
  * **그러니까 더 많은 옵션 또는 고급옵션이 필요한 사람은 위해서 코드를 작성해서 사용할수있게 Glide에서 커스텀 옵션(custom options)이라는 것을 제공해주니 사용하라는것**

ListView and RecyclerView
---
* ListView와 RecyclerView 에서 이미지를 로딩하는것은 싱글 뷰에서 사용하는것과 똑같고 코드의 변화가 없다.
* Glide는 뷰의 재사용/삭제 요청을 자동적으로 수행한다.
* ```java
  @Override public void onBindViewHolder(ViewHolder holder, int position) {
      String url = urls.get(position);
      Glide.with(fragment)
          .load(url)
          .into(holder.imageView);
  }
* **url의 null 체크를 할필요없다**
  * 만약에 url이 nul이라면 Glide 뷰를 지울것이고 placeholder로 설정하거나 fallback으로 설정한 Drwable(안드로이드 이미지 객체)을 view에다가 붙혀준다.
  
  
 

