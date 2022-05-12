# @Annotation
* ![Page1](https://user-images.githubusercontent.com/51182964/168073582-360e1b37-3944-4888-80ad-9a786f65f915.jpg)
* ![image](https://user-images.githubusercontent.com/51182964/168073680-fbc8a745-5730-4e08-b917-f4f1ccd3bf0f.png)
---
### @RETENTION
* ```java
  public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     */
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     */
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     *
     * @see java.lang.reflect.AnnotatedElement
     */
    RUNTIME
  }
* ![image](https://user-images.githubusercontent.com/51182964/167870313-baf181ae-d57e-4690-94ef-ec78454914ee.png)
