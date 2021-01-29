glide
===
* [glide library](https://github.com/bumptech/glide)
* Google 에서 추천하는 Android 이미지 로드 라이브러리이다.
* 장점
  * 사용하기가 매우 쉽다.

In RecyclerView
---
* ```java
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
      if (isImagePosition(position)) {
          String url = urls.get(position);
          Glide.with(fragment)
              .load(url)
              .into(holder.imageView);
      } else {
          Glide.with(fragment).clear(holder.imageView);
          holder.imageView.setImageDrawable(specialDrawable);
      }
