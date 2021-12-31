### 흐름제어 - [자세한사항은 또는 코드는 여기서 확인](https://beomseok95.tistory.com/66?category=1029204), [자바독 - 검색](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html#throttleLast-long-java.util.concurrent.TimeUnit-)
* 데이터의 소비속도가 발행속도보다 느릴경우 경우
---
##### throttleFirst
* 이벤트 -> 1초 동안 검사 -> 가장 첫번째 이벤트 전달
* ![](https://t1.daumcdn.net/cfile/tistory/9951C24E5C2A2E4B39)
---
##### throttleLast, sample (둘이 동일함)
* 이벤트 -> 1초 동안 검사 -> 가장 마지막 이벤트 전달
* <img width="766" alt="스크린샷 2021-12-31 오전 11 47 36" src="https://user-images.githubusercontent.com/51182964/147800136-c3224e81-69a9-4714-a0f1-f19c69a01790.png">
---
##### debounce
* 이벤트 -> 1초 동안 검사 -> 1초 내 이벤트 발생 -> 1초 동안 검사 -> 1초 내 이벤트 발생 -> 1초 동안 검사 -> 1초 내 이벤트 X -> 마지막 이벤트 전달
* <img width="769" alt="스크린샷 2021-12-31 오전 11 46 39" src="https://user-images.githubusercontent.com/51182964/147800102-3cf9ed5e-b6e2-444b-b2b8-f738f8e5a509.png">

---
##### buffer
* ```java
  public final Observable<List<T>> buffer(int count)
* 일정 시간 동안 데이터를 모아두었다가 한꺼번에 발행
* 현재 스레드에서 생성
* return List< T >
* ![](https://t1.daumcdn.net/cfile/tistory/99AB43485C2A275432)
---
##### window
* ```java
  public final Observable<Observable<T> window(long count)
* groupBy -> 병렬적으로 Observable 생성
* window -> 직렬적으로 Obserfable 생성 
* ![](https://t1.daumcdn.net/cfile/tistory/990F694A5C2A33751F)
