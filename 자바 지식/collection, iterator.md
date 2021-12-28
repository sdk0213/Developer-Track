### collection
* ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmjVFA%2FbtqZBcPCt5e%2FiwtcUaOcIBEQiCRXIvqEjK%2Fimg.jpg)

#### Iterator
* 최상단 인터페이스로 순서대로 값을 가져오는 즉, hasNext(), next(), remove() 등의 메소드를 이용할 수 있다. 용도는 컬렉션클래스의 데이터를 하나씩 읽어올 때 사용한다
* 코틀린
  ```kotlin
  public interface Iterator<out T> {
    /**
     * Returns the next element in the iteration.
     */
    public operator fun next(): T

    /**
     * Returns `true` if the iteration has more elements.
     */
    public operator fun hasNext(): Boolean
  }

  /**
   * An iterator over a mutable collection. Provides the ability to remove elements while iterating.
   * @see MutableCollection.iterator
   */
  public interface MutableIterator<out T> : Iterator<T> {
      /**
       * Removes from the underlying collection the last element returned by this iterator.
       */
      public fun remove(): Unit
  }
#### Collection
* ```java
  public interface Collection<out E> : Iterable<E> { ...
##### List
* ```java
  public interface List<out E> : Collection<E> { ...
##### Queue
* ```java
  public interface Queue<E> extends Collection<E> { ...
##### Set
* ```java
  public interface Set<out E> : Collection<E> { ...

  
