##### operators/transforms 를 지원하기 때문에 비슷해보이지만 다르다.
### stream
* synchronous sequences of values
* multicore architectures
* pull based
* Stream can only be used once
* Streams offer restricted set of operations in comparison with RxJava. E.g. Streams are lacking cut-off operations (takeWhile(), takeUntil()); 
### rxjava
* asynchronous sequences of values
* single-threaded
* push-based
* notified when an element is available
* Observable can be subscribed to many times.
* RxJava is focussed on concurrent task
* the same task using RxJava may be slower than with Java 8 stream.
