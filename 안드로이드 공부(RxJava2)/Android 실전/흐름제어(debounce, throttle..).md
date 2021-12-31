### 흐름제어 - [자세한사항은 또는 코드는 여기서 확인](https://beomseok95.tistory.com/66?category=1029204)
* 데이터의 소비속도가 발행속도보다 느릴경우 경우
##### throttleFirst
* 이벤트 -> 1초 동안 검사 -> 가장 첫번째 이벤트 전달
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FHmaft%2FbtrehBATm7D%2Fde0dRrIeCNm4IOrbTIkUkk%2Fimg.png)
##### throttleLast, sample (둘이 동일함)
* 이벤트 -> 1초 동안 검사 -> 가장 마지막 이벤트 전달
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FHmaft%2FbtrehBATm7D%2Fde0dRrIeCNm4IOrbTIkUkk%2Fimg.png)
##### debounce
* 이벤트 -> 1초 동안 검사 -> 1초 내 이벤트 발생 -> 1초 동안 검사 -> 1초 내 이벤트 발생 -> 1초 동안 검사 -> 1초 내 이벤트 X -> 마지막 이벤트 전달
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbJqtJg%2FbtrehlkjgR0%2FjpcJoJir1eB9XSwdWlxBu0%2Fimg.png)
##### buffer
* ```java
  public final Observable<List<T>> buffer(int count)
* 일정 시간 동안 데이터를 모아두었다가 한꺼번에 발행
* 현재 스레드에서 생성
* return List< T >
* ![](https://t1.daumcdn.net/cfile/tistory/99AB43485C2A275432)
##### window
* ```java
  public final Observable<Observable<T> window(long count)
* groupBy -> 병렬적으로 Observable 생성
* window -> 직렬적으로 Obserfable 생성 
* ![](https://t1.daumcdn.net/cfile/tistory/990F694A5C2A33751F)
