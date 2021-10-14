# List&#60;A&#62; 에서 List&#60;B&#62; 로 변경하기 - [출처](https://stackoverflow.com/questions/35734060/rxjava-how-to-convert-list-of-objects-to-list-of-another-objects)
* Observable&#60;List&#60;T&#62;&#62;에서 Observable&#60;List&#60;R&#62;&#62; 로 안에 Object 를 변경하여 흐름을 연결하고싶을때
* ```kotlin
  Observable<List<SourceObject>> source = ...
  source.flatMap(list ->
        Observable.fromIterable(list)
            .map(item -> new ResultsObject().convertFromSource(item))
            .toList()
            .toObservable() // Required for RxJava 2.x
    )
  .subscribe(resultsList -> ...);
