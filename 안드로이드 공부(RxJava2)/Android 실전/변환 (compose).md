# 코드 재사용
---
### [출처는 투덜이의 리얼 블로그](https://tourspace.tistory.com/294) [출처는 투덜이의 리얼 블로그](https://tourspace.tistory.com/294)
### compose && ObservableTransformer<Upstream, Downstream>
* 전달받은 observable 을 새로운 observable 로 변환하는 역할
* ```java
  public interface ObservableTransformer<Upstream, Downstream> {
      /**
       * Applies a function to the upstream Observable and returns an ObservableSource with
       * optionally different element type.
       * @param upstream the upstream Observable instance
       * @return the transformed ObservableSource instance
       */
      @NonNull
      ObservableSource<Downstream> apply(@NonNull Observable<Upstream> upstream);
  }
##### subscribeOn(IO).observeOn(Main) 과 같은 로직을 Observable 에 대한 kotlin extension 으로 정의해서 쓸수있다.
* ```kotlin
  fun main(args: Array<String>) { 
      Observable.range(1, 10) 
          .compose(defaultObservable())
          .map { "number: $it" } 
          .blockingSubscribe { println(it) } 
  } 
  
  class defaultObservable : ObservableTransformer<Int, Int> {
      override fun apply(upstream: Observable<Int>): ObservableSource<Int> {
          return upstream
              .subscribeOn(Schedulers.computation()) 
              .observeOn(Schedulers.io()) 
      }
  }
* 위를 Extension 으로 사용하면 매번 subscribeOn, observeOn 을 매번 명시해줄필요없이 쉽게 사용가능하다.
##### flatMap vs Compose
* flatMap
  * 각각의 아이템마다 Observable 을 생성시킬때 그 때 적용가능
* compose
  * 아이템을 받아오는 스트림 전부에 전체 적용
