# DiffUItil - [출처 - thdev tech](https://thdev.tech/kotlin/2020/09/22/kotlin_effective_03/)
* 서로 다른 아이템인지를 체크하여 **달라진 아이템만 갱신**을 도와주는 Util
### override
##### getOldListSize
* 현재 리스트에 올라와있는 List 크기
* 바뀌 기 전 리스트의 크기 리턴
##### getNMewListSize
* 추가 또는 갱신할 List 크기
* 바뀐 후 리스트의 크기 리턴
##### areItemsTheSame
* 서로 같은 아이템인지 고유한 ID 비교
##### areContentsTheSame
* 서로 같은 아이템인지 equal 비교
---
### 사용
##### ItemDiffUtilCallback
* ```kotlin
  private class Diff(
      private val oldItems: List<Any>,
      private val newItems: List<Any>
  ) : DiffUtil.Callback() {

      override fun getOldListSize(): Int = OldItem.size
      override fun getNewListSize(): Int = newItems.size
      override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =   
          return oldItems[oldItemPosition].id == newItems[newItemPosition].id
      override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
          return oldItems[oldItemPosition] == newItems[newItemPosition]  
  }
##### Adapter
* ```kotlin
  class DiffUtilAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
      
       private val dateSet = mutableListOf<Item>()
       
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder (
          return TileViewHolder(parent)
       }
       
       override fun getItemCount(): Int = dataSet.size
       
       override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
          (holder as TileViewHolder).bind(dataSet[position])
       }
       
       preivate fun setNewItems(newItems: MutableList<Item>) {
          dataSet.run {
              claer()
              addAll(newItems)
          }
       }
       
       private fun calDiff(newItems: MutableList<Item>) {
          val itemDiffUtilCallback = ItemDiffUtilCallback(dateSet, newItems)
          val diffResult: DiffUtil.DiffReuslt = DiffUtil.calculateDiff(itemDiffUtilCallback)
          diffResult.dispatchUpdatesTo(this)
       }
       
       fun setItems(num: Int) {
          dataSet.clear()
          (1..num).forEach {
              dataSet.add(Item(it))
          }
       }
       
       fun shuffle() {
          val newItems = mutableListOf<Item>().apply {
              addAll(dataSet)
              shuffle()
          }
          calDiff(newItems)
          set?NewItems(newItems)
       }
       
       fun addOneItem() {
           val newItems = mutableListOf<Item>().apply {
              addAll(dataSet)
           }
           val insertRandomIdx = (Random.nextDouble() * newItems.size).toInt()
           newItems.add(insertRandomIdx, Item(dataSet.size + 1))
           calDiff(newItems)
           setNewItems(newItems)
       }
       
       fun eraseOneTile() {
           val newItems = mutableListOf<Item>()
           dataSet.isNotEmpty().let {
               val erasedRandomIdx = (Random.nextDouble() * newItems.size).toInt()//0
               dataSet.forEachIndexed { index, tile ->
                   if (index != erasedRandomIdx) newItems.add(item)
               }
           }
           calDiff(newItems)
           setNewItems(newItems)
       }


       fun eraseThreeTile() {
           val newItems = mutableListOf<Tile>().apply {
               addAll(dataSet)
           }
           repeat(3) {
               val erasedRandomIdx = (Random.nextDouble() * newTiles.size).toInt()
               newItems.removeAt(erasedRandomIdx)
           }
           calDiff(newTiles)
           setNewItems(newItems)
       }

       fun addThreeTile() {
           val newItems = mutableListOf<Item>()
           newItems.addAll(dataSet)
           repeat(3) {
               val insertRandomIdx = (Random.nextDouble() * newTiles.size).toInt()
               newItems.add(insertRandomIdx, Item(newItems.size + 1))
           }
           calDiff(newItems)
           setNewItems(newItems)
       }
   }
* shuffle()에서 기존것을 복사해 섞음 -> setNewItems에서 기존것 없애고 새롭게 삽입 -> calDiff에 넣어서 기존것과 바뀐것의 차이거 무엇무엇인지 파악후 재정렬
* calDiff() 에서 주목할점
  * ItemDiffUtilCallback 생성후 DiffUtil.calculateDiff 로 던져줘 diffResult로 받고 dispatchUpdatesTo()에서 지정된 Adapter로 업데이트 이벤트를 전달한다.
