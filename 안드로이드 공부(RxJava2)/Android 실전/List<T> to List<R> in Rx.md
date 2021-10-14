# List&#60;A&#62; 에서 List&#60;B&#62; 로 변경하기 - [출처 - https://stackoverflow.com/questions/35734060/rxjava-how-to-convert-list-of-objects-to-list-of-another-objects]
* ```kotlin
  Observable<List<SourceObject>> source = ...
  source.flatMap(list ->
        Observable.fromIterable(list)
            .map(item -> new ResultsObject().convertFromSource(item))
            .toList()
            .toObservable() // Required for RxJava 2.x
    )
  .subscribe(resultsList -> ...);
