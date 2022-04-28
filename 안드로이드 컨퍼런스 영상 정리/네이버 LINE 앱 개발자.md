### 기억나는것만 적음
* 구글의 동향을 살피며 알파/베타 버젼을 적극적으로 도입하진 않으나 이것을 구글에서 미는이유에 대한 논의를 지속적으로 한다고함
* [LINE 개발문화](https://www.youtube.com/watch?v=SoRwMQXdPEc&t=609s)
  * 실수는 누구나 하고 다시 일어나지 않도록 처리
  * 코드리뷰는 누구나 가능
* [LINE 앱 개발](https://www.youtube.com/watch?v=kXUSTvz1ryA)
  * 비동기처리에 대한 라이브러리가 계속 바뀌어서 고민중이며 이를 어떻게 분리할수있을까에 대한 고민중
  * 앱 용량이 계속 커져서 앱 번들에 대한 이야기
* [LINE 앱 빌드시간 최적화](https://www.youtube.com/watch?v=nu_-_D9I5ek)
  * 모듈화 중요하다고 생각함
  * 빌드 시간보다 안정성이 중요
* [LINE 앱 개발자의 코틀린에 대한 생각](https://www.youtube.com/watch?v=qFitd3Ukgcc)
  * 빌드시간은 느려지지만 리더블리티가 중요해서 좋다고 생각
  * 테스트시에 불편한점이 있음 (Companion Object, final 처리로 인한 mockito 처리시간 증가)
* [LINE 앱의 테스트](https://www.youtube.com/watch?v=gHC4NHN-ZdA)
  * 유닛 테스트 위주
  * QA 팀에서 UI 나 파편화 테스트
  * 정적분석
  * Lint
  * CI
  * 기타 툴 적극적으로 빠짐없이 사용하는듯 보임
