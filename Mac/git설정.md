git설정 - [출처 - ZeddiOS님의 티스토리](https://zeddios.tistory.com/4)
---
* terminal.app -> git version 없으면 [git download - 링크](https://git-scm.com/download/mac)
  * 아마 mac은 기본적으로 설치가 되어있을거임
* git 설정 순서
  * 명령어
    ```git
    // 초기한번 설정해야함
    git config --global user.name "Your Name Here"
    git confgit config --global user.email "your_email@youremail.com"
  * 터미널에서 git을 올릴 프로젝트나 폴더로 이동 (cd 사용)
  * 전체 파일올리기
    ```git
    git add .
    // 특정파일올리기
    git add test1.java
  * 커밋하기
    ```git
    git commit -m “commit 내용”
  * git 리모트 설정 - git 저장소에 있음
    git remote add origin [https://github.com/[계정]/[저장소].git]
  * push 하기
    ```git
    git push -u origin [브랜치]
    // 브랜치 기본값은 보통 master
  
  
