# 투명도
* ex)
  ```xml
  <color name="tranparent_material_50">#D9EDE7F6</color>
* |투명도|값|
  |:--:|:--:|
  |100%|FF|
  |95%|F2|
  |90%|E6|
  |85%|D9|
  |80%|CC|
  |75%|BF|
  |70%|B3|
  |65%|A6|
  |60%|99|
  |55%|8C|
  |50%|80|
  |45%|73|
  |40%|66|
  |35%|59|
  |30%|4D|
  |25%|40|
  |20%|33|
  |15%|26|
  |10%|1A|
  |5%|0D|
  |0%|00|

* [코드로 투명도 조절](https://stackoverflow.com/questions/1492554/set-transparent-background-of-an-imageview-on-android)
  ```java
  yourView.getBackground().setAlpha(127); // 여기서 127은 50%가 아니다. 위의 출처 참고
  
