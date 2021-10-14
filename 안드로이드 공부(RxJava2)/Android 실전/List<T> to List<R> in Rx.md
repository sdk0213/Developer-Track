# List&#60;A&#62; 에서 List&#60;B&#62; 로 변경하기 - [출처](https://stackoverflow.com/questions/35734060/rxjava-how-to-convert-list-of-objects-to-list-of-another-objects)
* Observable&#60;List&#60;T&#62;&#62;에서 Observable&#60;List&#60;R&#62;&#62; 로 안에 Object 를 변경하여 흐름을 연결하고싶을때
* 사전지식
  * flatMapIterable 은 사실 아래 코드와 동작이 같다
    * ```kotlin
      .flatMap(numberList -> 
        Observable.fromIterable(numberList)
       )
      // 위 코드와 동일한 동작
      .flatMapIterable(numberList -> numberList)
* 방법1
  ```kotlin
  // 예제 코드 1
  Observable<List<SourceObject>> source = ...
  source.flatMap(list ->
        Observable.fromIterable(list)
            .map(item -> new ResultsObject().convertFromSource(item))
            .toList()
            .toObservable() // Required for RxJava 2.x
    )
  .subscribe(resultsList -> ...);
  
  // 예제 코드 2
  return factory.getTripAll().flatMapIterable { it }.map { tripMapper.entityToMap(it) }
            .toList()
            .toFlowable()
 
  // 위 코드와 아래코드는 동작상 같다.
  return factory.getTripAll().flatMap { list ->
      Flowable.fromIterable(list)
              .map { tripMapper.entityToMap(it) }
              .toList()
              .toFlowable()
  }
        
  
* 방법2
  ```kotlin
  Observable.from(Arrays.asList(new String[] {"1", "2", "3", }))
    .map(s -> Integer.valueOf(s))
    .reduce(new ArrayList<Integer>, (list, s) -> {
        list.add(s);
        return list;
    }) // 또는 toList() 사용
    .subscribe(i -> {
        // Do some thing with 'i', it's a list of Integer.
    });
