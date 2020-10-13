exam_3_6
===
> 함수를 합성하되 적용 순서가 반대인 higherAndThen 함수를 정의하라.
반대라는 말은 앞선 예제문제의 higherCompose(f, g) 와 higherAndThen(g, f)가 같다는 말이다.
* fun <T, U, V> higherAndThen: ((T) -> U) -> 
