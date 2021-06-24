
# svn -> git 이력 이관하기 - [출처는 https://pjs21s.github.io/svn-to-git/](https://pjs21s.github.io/svn-to-git/)
### 환경 : 윈도우 10 (맥에서는 여러번 해봤는데 진행이 안됩니다.)
* 1. 준비물
  * git
  * svn
  * git bash
* 2. 위 블로그의 순서대로 작업을 진행합니다.
  * 2-1. 블로그 내용과 다르게 git clone 부분은 생성한 폴더에서 svn 폴더를 입력하여 진행하면 됩니다.
  * 2-2. svn 이력에 참여한 아이디는 전부 users.txt 파일에 입력해야합니다.
  * 2-3. 파일입력시에는 gitLab 계정과 이메일을 동일하게 해야합니다.)
* 3. 이관 완료후 git log 로 이력 확인
* 4. git remote 명령어를 사용하여 clone 받은 svn 폴더를 바라보는것을 끊습니다.
* 5. git remote 로 gitLab 에 https 를 연결합니다.
* 6. 연결 과정중 생기는 Access Denied 또는 fetch 에러는 다음 블로그를 참고해주세요
  * https://wrjeoung.tistory.com/43
  * https://donggu1105.tistory.com/104

