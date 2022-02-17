# Branch
---
### upstream, downstream
* 브랜치의 상대적인 개념으로 쓰이는 용어
* clone 한 경우, origin 브랜치가 upstream 브랜치
* 내가 다른 사람의 레포를 포크해왔을 때에 upstream은 일반적으로 오리지널 레포(다른 사람의 레포)
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
