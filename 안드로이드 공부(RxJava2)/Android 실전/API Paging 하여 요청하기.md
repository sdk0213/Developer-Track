##### API 를 요청시 페이징하여 순서대로 요청하고 이를 받아오는 두 가지 방법이다.
---
### with recursive function
* reference: [https://stackoverflow.com/questions/28047272/handle-paging-with-rxjava](https://stackoverflow.com/questions/28047272/handle-paging-with-rxjava)
* 코드 설명
  * concatMap 으로 순서를 보장한다.
  * 이와 동시에 concatWith 로 기존것에 다음 페이지에 대한 데이터흐름을 concatenation 한다.
* ```kotlin
  fun pagingConcat(page: Int): Flowable<YososuAndLoadPage> {
      val perPage = 400
      return apiService.getDataAfterCallApi(page, perPage)
          .toFlowable()
          .concatMap { response ->
              if (response.isSuccessful) {
                  val totalPage = (response.body()!!.totalCount / perPage) + 1 // 요청하는 페이지에서 주거나 추측하거나 다른방법
                  val nowPage = response.body()!!.page // 요청하는 페이지에 맞추어서 변형
                  // 마지막 페이지
                  if (totalPage == nowPage) {
                      Flowable.just( 데이터생성 )
                  }
                  // 다음 페이지
                  else {
                      Flowable.just( 데이터생성 )
                        .concatWith(getGasStationListHasYososuByPagingConcat(page = nowPage + 1))
                  }
              } else {
                  // 에러 처리
                  Flowable.create({ emitter ->
                      emitter.onError(Exception(response.errorBody().toString()))
                  }, BackpressureStrategy.MISSING)
              }
          }
  }
---
### without recursive function
* reference: [https://stackoverflow.com/questions/37326380/paginate-observable-results-without-recursion-rxjava](https://stackoverflow.com/questions/37326380/paginate-observable-results-without-recursion-rxjava)
* 코드 설명
  * 이해를 위해 람다 사용을 일부로 제거하였음
  * BehaviorProcessor 는 배압이 존재하는 Subject 이다.
    * BehaviorSubject 로 진행할시 Flowable 이 아닌 Observable 로 진행된다
  * concatMap 은 순서를 보장해준다.
  * doOnNext 에서 side-effect 를 사용가능하므로 값이 들어왔을때 다음 page 를 요청한다.
  * concatMapIterable 로 리스트를 순서대로 추출하여 Flowable<T> 를 최종적으로 구독자에게 전달한다.
* 아래코드를 API 요청하는 코드로 변형하여 사용
* ```kotlin
  class Pagination {
  
      fun test() {
          val result = fetchItems(object : Function<Int, Single<Page<String>>> {
              override fun apply(int: Int): Single<Page<String>> {
                  return examplePageFetcher(int)
              }
          })
              .subscribe(
                  { data ->
                      println(data)
                  },
                  {
  
                  }
              )
      }
  
      // Fetch all pages and return the items contained in those pages, using the provided page fetcher function
      private fun <T> fetchItems(fetchPage: Function<Int, Single<Page<T>>>): Flowable<T> {
          // Processor issues page indices
          val processor: BehaviorProcessor<Int> = BehaviorProcessor.createDefault(0)
          // When an index number is issued, fetch the corresponding page
  
          return processor
              .concatMap(object : Function<Int, Flowable<Page<T>>> {
                  override fun apply(index: Int): Flowable<Page<T>> {
                      return fetchPage.apply(index).toFlowable()
                  }
              })
              .doOnNext(object : Consumer<Page<T>> {
                  override fun accept(page: Page<T>) {
                      page.nextPageIndex?.let {
                          processor.onNext(page.nextPageIndex)
                      } ?: run {
                          processor.onComplete()
                      }
                  }
              })
              .concatMapIterable(object : Function<Page<T>, Iterable<T>> {
                  override fun apply(page: Page<T>): Iterable<T> {
                      return page.elements
                  }
              })
  
      }
  
      // A function to fetch a page of our paged data
      private fun examplePageFetcher(index: Int): Single<Page<String>> {
          return Single.just(pages[index])
      }
  
      // Create some paged data
      private val pages = arrayListOf(
          Page(arrayListOf("1", "2"), 1),
          Page(arrayListOf("3", "4", "4"), 2),
          Page(arrayListOf("5", "6", "7", "8"), 3),
          Page(arrayListOf("9"), 4),
          Page(arrayListOf("10"), 5),
          Page(arrayListOf("11"), 6),
          Page(arrayListOf("12"), 7),
          Page(arrayListOf("13"), 8),
          Page(arrayListOf("14"), 9),
          Page(arrayListOf("15"), null)
      )
  
      data class Page<T>(
          val elements: List<T>,
          val nextPageIndex: Int?
      )
  }
