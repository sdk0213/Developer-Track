# [lazy vs lateinit](https://medium.com/@waqarul/kotlin-lateinit-vs-lazy-properties-bc8ce42ea0a0)
### lazy
* lateinit can only be used **with var** (mutable)properties
* While using **Singleton Pattern** (Object Declaration in Kotlin) we should use lazy, as it will be initialized upon first use.
* Initialization by lazy { … } is **thread-safe** by default and make sure initialization is done at most once.
---
### lateinit
* lazy can only be used for **val (immutable)properties**
* lateinit **don’t work with primitive type.**
* In lateinit the type of the property **must be non-null.**
* lateinit can only be used when the property **does not have a custom getter or setter.**
* In lateinit var, **it depends on the user** to initialize the property **properly** in multi-threaded environments. 
