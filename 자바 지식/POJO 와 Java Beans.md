#### 모든 JavaBeans는 POJO 이지만 POJO 는 JavaBeans 가 아닐수 있다
---
### POJO
* = Plain Old Java Object 
* getter / setter 를 가진 단순한 자바 오브젝트
  * 의존성 없는 객체
  * 테스트도 용이
  * 추후 수정이 편리(소프트웨어적)
* POJO 는 **가독성**과 **재사용성** 이 중요함
* 아래 Beans 특징과 반대의 성격을 띄는것은 아니나 굳이 Beans 특징을 지키지 않는다면 POJO 라고 볼수있다
---
### Java Beans
* POJO 보다 엄격한 규칙이 있는것
* 특징
  * Fields에 대한 통제
  * 반드시 Serializable 에 대한 interface 를 구현해야함
  * Fields 는 getter 와 setter 로만 접근
  * Fields 는 접근제어자를 private만 가능
  * 생성자 argument 불가능
  * member와 restriction 한 접근 규칙을 정하고자 할 때 사용
