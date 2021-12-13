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
* PagingSource
  * 로컬 또는 Backend의 데이터를 가져오는 역할
* RemoteMediator
  * 페이징 처리
* Pager
  * 공개 API 제공(반응형 스트림에 노출되는 PagingData 인스턴스를 구성하기 위한)
* PagingData
  * 페이지로 나눈 데이터의 스냅샷을 보유하는 컨테이너
* PagingDataAdapter입니다.
  * 페이지로 나눈 데이터를 처리
* AsyncPagingDataDiffer 
  * 고유한 맞춤 어댑터를 빌드
* anchorPosition
  * 가장 최근에 접근한 인덱스
---
### [샘플 Git](https://github.com/android/architecture-components-samples/tree/main/PagingSample)
* room 사용
### [샘플 Git](https://github.com/android/architecture-components-samples/tree/main/PagingWithNetworkSample)
* 네트워크 사용
