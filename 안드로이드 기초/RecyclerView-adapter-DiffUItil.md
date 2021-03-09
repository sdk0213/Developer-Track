# DiffUItil - [출처 - thdev tech](https://thdev.tech/kotlin/2020/09/22/kotlin_effective_03/) [출처 - s2choco](https://s2choco.tistory.com/33)
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
##### 설명
* shuffle()에서 기존것을 복사해 섞음 -> setNewItems에서 기존것 없애고 새롭게 삽입 -> calDiff에 넣어서 기존것과 바뀐것의 차이거 무엇무엇인지 파악후 재정렬
* calDiff() 에서 주목할점
  * ItemDiffUtilCallback 생성후 DiffUtil.calculateDiff 로 던져줘 diffResult로 받고 dispatchUpdatesTo()에서 지정된 Adapter로 업데이트 이벤트를 전달한다.
  * dispatchUpdatesTo()로 RecyclerView에 변경 사항을 적용하기 전에 원본 리스트를 새로운 리스트로 교체하는 작업을 해 줘야 한다.
    * dispatchUpdatesTo()에서는 데이터를 변경할 때 notify- 메서드로 즉시 어댑터로 업데이트 이벤트를 전달하기 때문에 그 전에 새로운 리스트로 교체를 해야 RecyclerView에 제대로 적용이 된다
  * dispatchUpdatesTo()에서 변경사항을 리스트에 적용할 때 추가 및 삭제 애니메이션을 제공
  * **DiffUtil은 아이템 개수가 많을 경우 calculateDiff()의 diff 계산 시간이 길어질 수 있기 때문에 백그라운드 스레드에서 처리 해야함**
---
# AsyncListDiffer
* 직접 백그라운드 스레드에서 비교 처리를 수행하고 결과를 메인 스레드에서 처리하는 코드를 작성해야하는 번거로움을 없애준다
  * **즉, 내부적으로 diff 계산을 백그라운드 스레드로 처리한 뒤 리스트 업데이트까지 해 준다.**
### 사용
##### DiffUtil.ItemCallback
* ```kotlin
  class PersonDiffItemCallback : DiffUtil.ItemCallback<Person>() {
      override fun areItemsTheSame(oldItem: Person, newItem: Person) = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Person, newItem: Person) = oldItem == newItem
  }
##### RecyclerView
* AsyncListDiffer를 생성해 사용
* ```kotlin
  class PersonAsyncDifferAdapter : RecyclerView.Adapter<PersonViewHolder>() {
      private val asyncDiffer = AsyncListDiffer(this, PersonDiffItemCallback())
  
      override fun onCreateViewHoldet(parent: ViewGroup, viewType: Itn) = 
          PersonViewHolder(ItemPersonBinding.inflate(LayoutInfalter.from(parent.context), parent, false)
      
      override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =
          holder.bind(asyncDiffer.currentList[position])
          
      override fun getItemCount() = asyncDiffer.currentList.size
    
      fun replaceItems(newPeople: List<Person>) {
          asyncDiffer.submitList(newPeople)
      }
  }
* submitList()
  * diffing 작업
  * 리스트 변경
* currentList
  * 현재 리스트의 아이템들을 확인
  * **READ ONLY LIST** 주의
    * **변경은 반드시 submitList() 에서 진행해야한다** 
