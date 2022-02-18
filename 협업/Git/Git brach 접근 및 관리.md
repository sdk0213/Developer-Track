# Branch
---
### upstream, downstream
* 브랜치의 상대적인 개념으로 쓰이는 용어
* clone 한 경우, origin 브랜치가 upstream 브랜치
* 내가 다른 사람의 레포를 포크해왔을 때에 upstream은 일반적으로 오리지널 레포(다른 사람의 레포)
---
### Fork
* clone 과의 차이
  * ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcNIKgW%2FbtqzVC5QIms%2F7NgNXHZvZoWbaP3Wy6QWd1%2Fimg.png)
* fork한 저장소는 원본(원래 Repository 주인)과 연결되어 원래 레포지토리에 어떤 변화가 생기면(새로운 commit, push) 이는 그대로 fetch, pull의 과정을 통해 fork된 repository로 반영할 수 있다.
* Repository에 권한이 없는 사용자가 저장소를 fork하고 fork한 자신의 저장소에 변경 사항을 적용한 후 Push한다. 이 후 내 저장소에 있는 브랜치를 원래 저장소(original repository)에 Pull Request 요청을 보낸다.(pull request가 original repository의 관리자로 부터 승인 되었으면) 내가 만든 코드가 승인되면 해당 저장소에 Merge 된다.

---
![](https://media.vlpt.us/images/kgh239/post/b889ec37-1ce2-4065-94a5-6aa9591f07a2/image.png)
### Merge
* 병합을 하면 합쳐진 브랜치의 커밋 메시지가 중복으로 쌓인다.
### Rebase
* A와 B 브랜치중 기준을 정해서 하나의 브랜치를 베이스로 잡고 일렬로 정렬한다.

---
### Fetch
* fetch 는 이력만 가져오는것을 뜻한다.
* 누군가 파일을 변경했는데 pull 로 땡겨오면 전부다 맞춰야되니까 싫고 그저 어떤게 변경되었는지 이력만 하고싶을때 사용하면됨
* pull 은 본래 fetch + merge 이다.
