# UseCase
---
### 개념
* UseCase는 하나의 유저 액션에 따른 하나의 로식만 수행하며 Domain Layer에 속한다.
* Repository가 여러 ViewModel에서 쓰이고 있는데 그 Repository가 바뀐다면 해당 Repository를 사용하는 
모든 ViewModel을 수정하는 경우가 생긴다
* application에서 network 통신 처리, database CRUD 처리와 같은 각각의 flow를 캡슐화하여 세부구현을 Controller에서 분리할 수 있다.
* 
