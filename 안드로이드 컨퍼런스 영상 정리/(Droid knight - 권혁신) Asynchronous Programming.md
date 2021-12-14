# Asynchronous Programming
* 안드로이드는 최소 60Hz 지원
* MainThread의 책임
  * Parse xml code
  * inflate xml
  * Draw the view
  * 상호작용
* 위의 문제로 멀티 스레딩이 필요 그리고 대안으로 RxJava 와 Coroutine 이 있다.
--- 
### RxJava 
* 러닝커브가 높음
* 다양한 기능을 지원함
### Coroutine
* 러닝커브가 높음
* 에러 핸들링이 try ~ catch 로 진행됨
* 시퀀셜(Sequential Code Stucture)
### Coroutine + Flow 
* RxJava 와 비슷
* RxJava 보다 부족한 기능
* 아직 Experimental
---
* 비교
* <img width="1079" alt="스크린샷 2021-12-14 오후 8 07 16" src="https://user-images.githubusercontent.com/51182964/145987019-0f37db66-6309-4d6b-af97-77ec89dfe707.png">
* <img width="1083" alt="스크린샷 2021-12-14 오후 8 07 39" src="https://user-images.githubusercontent.com/51182964/145987075-0b793357-7b11-45f2-925d-d41548cf00e3.png">


---
### 결론
* 간단한 작업 -> Coroutine Suspend Function 을 사용하며 외에는 RxJava 또는 Coroutine Flow 사용
* Coroutine 을 구글이 밀고 있지만 아직까지는 RxJava 가 가치가 충분하고 RxJava 잘 다룬다면 Coroutine 으로 이전할 필요는 없다.
