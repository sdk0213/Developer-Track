# 모듈(Module) - [출처는 footcode.postype](https://footcode.postype.com/post/3673100)
---
### 라이브러리와의 차이점은?
|명칭|역할|빌드 결과|
|:--:|:--:|:--:|
|Module|전체의 어플리케이션 없이 부분적으로 디버깅 할수 있고 테스트 할 수 있는 작은 부분|.apk|
|Library|어플리케이션의 기능 확장을 위해 이전에 컴파일 된 소스 코드들의 집합|.aar|
|Java Library|순수하게 Java파일로만 이루어진 라이브러리|.jar|
---
### 사용이유
* 코드의 재사용성을 높이기 위해 코드를 모듈화 하여 종속시키는 작업이 필요
* **유지보수**
* 코드 재사용성
* 유닛테스트 용이
* 모듈 단위 코드 수정 용이
* [빌드 속도 감소 --> 모듈화가 라이브러리보다도 속도가 더빠르고 시간도 매우 줄어든다](https://footcode.postype.com/post/3673100)
* 의존성 낮은 코드 작성
---
### 실전
* 같은 폴더에 넣고서 다음과 같이 settings.gradle 설정
* settings.gradle
  * ```gradle
    include(':project_name_A')
    include(':project_name_B')
    
    // 또는
    include ':project_name_A', ':project_name_B'
  
