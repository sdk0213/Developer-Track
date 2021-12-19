# Paging - [출처는 안드로이드 공식 문서](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
---
### Paging 이란?
* 데이터를 가져올 때 한번에 불러오지 않고 나누어서 가져오는 방법
* 페이징은 서버에서 대량의 데이터를 제공하는 보편적인 방법이다.
* [반드시 보기](https://www.youtube.com/watch?v=fnzTtC5vLqE&t=625s)
* [반드시 보기2](https://medium.com/@jungil.han/paging-library-그것이-쓰고싶다-bc2ab4d27b87)
---
### 기능 (v3 기준)
* 중복된 요청 제거
* 오류처리
* 반응형 프로그래밍 호환(flow, livedata, rxjava...)
* 메모리 내 캐싱
* recycler 와 호환
---
### 라이브러리 아키텍처
* ![](https://developer.android.com/topic/libraries/architecture/images/paging3-library-architecture.svg)
* PagingSource (구버전: DataSource)
  * 원래는 [DataSource](https://brunch.co.kr/@oemilk/211) 였고 다음 세 가지 기능중 하나를 선택했으나 Paging3 에서 PagingSource API로 결합
    * PageKeyedDataSource (Deprecated)
    * ItemKeyedDataSource (Deprecated)
    * PositionalDataSource (Deprecated)
  * 로컬 또는 Backend의 데이터를 가져오는 역할이다.
  * getRefreshKey
    * 로드된 페이지 데이터의 중간에서 새로고침을 다시 시작하는 방법을 정의하며 구현해야한다
    * ```kotlin
      override fun getRefreshKey(state: PagingState): Int? {
          return state.anchorPosition
      }
  * LoadParams
    * 실행할 로드 작업에 관한 정보가 포함
    * 로드할 키
    * 로드할 항목 수
    * 로드 작업의 결과
      * 성공
        * LoadResult.Page
      * 실패
        * LoadResult.Error
  * PagingState
    * 지금까지 로드한 페이지, 가장 최근에 액세스한 색인, 페이징 스트림을 초기화하는 데 사용한 PagingConfig 객체에 관한 정보를 포함
  * LoadType
    * 로드의 유형
    * LoadType.APPEND
      * 로드한 데이터의 끝 부분
    * LoadType.PREPEND
      * 시작 부분
    * LoadType.REFRESH
      * 데이터를 처음으로 로드하는지
* PagingData (구버전 : PagedList)
  * PagedList는 DataSource에서 데이터 덩어리(chunck)를 로드하는 Collection
  * 페이지로 나눈 데이터의 스냅샷을 보유하는 컨테이너
* RemoteMediator
  * 페이징 된 데이터들을 네트워크로부터 가져와 로컬 데이터베이스에 저장하는 것을 관리
* Pager
  * 공개 API 제공(반응형 스트림에 노출되는 PagingData 인스턴스를 구성하기 위한)
  * Pager 코드
    ```kotlin
    class Pager<Key : Any, Value : Any>
    @JvmOverloads constructor(
        config: PagingConfig,
        initialKey: Key? = null,
        @OptIn(ExperimentalPagingApi::class)
        remoteMediator: RemoteMediator<Key, Value>? = null,
        pagingSourceFactory: () -> PagingSource<Key, Value>
    ) {
        /**
         * A cold [Flow] of [PagingData], which emits new instances of [PagingData] once they become
         * invalidated by [PagingSource.invalidate] or calls to [AsyncPagingDataDiffer.refresh] or
         * [PagingDataAdapter.refresh].
         */
        val flow: Flow<PagingData<Value>> = PageFetcher(
            pagingSourceFactory,
            initialKey,
            config,
            remoteMediator
        ).flow
    }
  * PagingConfig
    * enablePlaceholders
      * Placeholders 사용여부, 데이터가 없을때 null 로 표시 여부
    * initialLoadSize
      * 초기에 불러들일 사이즈로 일반적으로 loadsize 보다 크게 설정하는것이 좋다고 권장함
      * 예를들어서 loadsize 가 30일때 initialLoadSize 는 60 ~ 90 이상으로 설정
    * jumpThreshold (?)
      * REFRESH를 트리거하여 사용자 위치로 점프하기 전에 로드된 항목의 경계 외부에서 스크롤된 항목 수에 대한 임계값  (?)
      * jumpingSupported 에 대한 이해가 필요
    * **pageSize**
      * (스크롤을 prefetchDistance 까지내렸을때) 다음번에 로드할 데이터 크기
    * prefetchDistance
      * 얼마나 가까워져야 다음것을 로딩할것인지
      * 남아있는 출력가능한 데이터가 얼마나 있으면 ex) 5개 남아있을때 다음것을 로딩
    * maxSize
      * 받은 데이터가 너무 많을경우 최대로 표현할 개수
      * maxSize 가 30 인데 받아온데이터가 60개 일경우 나머지 30개는 사라진다.
      * 최솟값
        * Maximum size must be at least pageSize + 2*prefetchDist
        * 위 사항을 지키지 않을경우 java.lang.IllegalArgumentException 발생한다.
* PagingDataAdapter 
  * 페이지로 나눈 데이터를 처리하고 ui 단에 출력하는 adapter
* AsyncPagingDataDiffer (구버전 : AsyncPagedListDiffer)
  * 고유한 맞춤 어댑터를 빌드
* anchorPosition
  * 가장 최근에 접근한 인덱스
* cachedIn(viewModelScope) - ( cachedIn 은 Flow 타입에서 지원하는 함수이다. )
  * 캐싱지원
  * 액티비티에서 한번 불러오면 프래그먼트들이 바로 아이템을 사용하는 것이 가능하다.
* initialize()
  * 초기화 관리
  * 메서드를 재정의하여 캐시된 데이터가 오래되었는지 점검하고 원격 새로고침을 트리거할지 결정
  * 전에 실행되므로 로컬 또는 원격 로드를 트리거하기 전에 데이터베이스를 조작할
  * ```kotlin
    override suspend fun initialize(): InitializeAction {
      val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
      return if (System.currentTimeMillis() - db.lastUpdated() >= cacheTimeout)
      {
        // Cached data is up-to-date, so there is no need to re-fetch
        // from the network.
        InitializeAction.SKIP_INITIAL_REFRESH
      } else {
        // Need to refresh cached data from network; returning
        // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
        // APPEND and PREPEND from running until REFRESH succeeds.
        InitializeAction.LAUNCH_INITIAL_REFRESH
      }
    }
* Room은 기본적으로 Paging 3도 지원해서 추가 코드 없이 제공된 이전 또는 다음 페이지를 기준으로 데이터를 자동으로 선택한다.
---
### [안드로이드 공식 샘플 Git - 룸 방식](https://github.com/android/architecture-components-samples/tree/main/PagingSample)
### [안드로이드 공식 샘플 Git - 네트워크 방식](https://github.com/android/architecture-components-samples/tree/main/PagingWithNetworkSample)
---
### Paging3 With Rx
* 흐름
  * RxSource -> Repository -> viewModel -> adapter.submitDate
* RxPagingSource
  * 하나의 source data 만을 가지고 있을 때 사용한다.
* RxRemoteMediator
  * 네트워크를 통해 데이터를 로드하고 로컬 스토리지에 저장하고싶을때 사용 (Use this if you want to load data from network and save it to your local storage. RemoteMediator will take care of getting data for you. For example during loading data, it will check the local storage first, if no data found and next page is available, it will get data from network. RemoteMediator is using 1 single source of truth for data source, that is: your local storage.)
* 간단 코드
  * ```kotlin
    class GetMoviesRxRemoteRepositoryImpl(
        private val database: MovieDatabase,
        private val remoteMediator: GetMoviesRxRemoteMediator // GetMoviesRxRemoteMediator : RxRemoteMediator
    ): GetMoviesRxRepository {

        override fun getMovies(): Flowable<PagingData<Movies.Movie>> {
            return Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    maxSize = 30,
                    prefetchDistance = 5,
                    initialLoadSize = 40),
                remoteMediator = remoteMediator,
                pagingSourceFactory = { database.moviesRxDao().selectAll() } // @Query("SELECT * FROM movies ORDER BY id ASC")
                                                                             // fun selectAll(): PagingSource<Int, Movies.Movie>
            ).flowable
        }
    }
   
