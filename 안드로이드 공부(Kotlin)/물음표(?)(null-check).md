?
---
* nullable

?.
---
* Safe Call
* ex)
  ```kotlin
  s?.toUpperCase()
* if s is null, return null, and non-null otherwise return toUpperCase()
* use the safe call operator together with let:

?:
---
* ex)
  ```kotlin
  fun getName(str: String?){
    val name = str ?: "Unknown"
  }
* If the expression to the left of ?: is not null, the elvis operator returns it, otherwise it returns the expression to the right

!!
---
* operator (!!) converts any value to a non-null type and throws an exception if the value is null

