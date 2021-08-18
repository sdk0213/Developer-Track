### Completable
* 성공 또는 실패만 결과값으로 알려주는 스트림 이다.
### 만약 여러 개의 스트림을 Completable 로 알고 싶을때는?
* 즉, 5개의 파일을 전송하는데 이 중에 하나라도 실패할경우 Completable 을 실패로 처리하고 싶다면?
---
### andThen
* 연속으로 처리하되 하나라도 실패할경우 실패처리해준다.
* <img width="1014" alt="스크린샷 2021-08-18 오후 9 36 39" src="https://user-images.githubusercontent.com/51182964/129899123-0f2f4053-1d7b-4335-a2f8-0f23400bf745.png">
* ```java

  ...viewModel....
  ..
  ...
  
  fun uploadFiles(files: List<File>) {
    var fileUploadCompletable = Completable.fromSingle(Single.just(1))
    
    files.forEach { file ->
      fileUploadCompletable = fileUploadCompltable.andThen(
        fileService.upload(file.getBytes())
      )
    }
    
    fileUploadCompletable
        .subscribeOn..
        .observeOn...
        .subscribe(
          { _isUploadSuccess.value = Unit },
          { exp -> Log.e(...exp.message) }
        )
        .addToDisposable
  }
  
  ...
  ..
  .
      
