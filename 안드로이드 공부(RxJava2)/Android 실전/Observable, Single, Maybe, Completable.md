### Observable
* 세 가지 상태를 구독자에게 전달
  * onNext
  * onComplete
  * onError
* Complete 또는 Error 후에는 자동으로 dispose 된다. 
---
### Single
* 1개 데이터
* 두 가지 상태를 구독자에게 전달
  * onSuccess
  * onError
---
### Maybe
* 0 또는 1개 데이터
* 데이터 개수빼고 나머지는 Observable과 동일
---
### Completable
* 그냥 백그라운드 사용이 필요할때(ex) room) 에서 사용
* 리턴값이 없음
* 두 가지 상태를 구독자에게 전달
  * onComplete
  * onError
