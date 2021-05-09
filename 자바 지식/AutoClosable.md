# AutoClosable
### AutoClosable 란?
##### AutoClosable 사용안함
* 자원을 사용하고 해제할때 명시적으로 작성해야하는 close를 대시 처리해준다.
* ```java
  try {
    is = new FileInputStream("/Users/limjun-young/test.txt");
    bis = new BufferedInputStream(is);

    int n = -1;
    while ((n = bis.read()) != -1) {
        System.out.println((char) n);
    }
  } finally {
     if (is != null) {
         is.close();
      }
           
     if (is != null) {
         bis.close();
      }
  }
##### AutoClosable 사용
* ```java
  try (
        FileInputStream is = new FileInputStream("/Users/limjun-young/test.txt");
        BufferedInputStream bis = new BufferedInputStream(is);
  ) {
      int n = -1;
      while ((n = bis.read()) != -1) {
          System.out.println((char) n);
      }
  } catch (IOException e) {
      e.printStackTrace();
  }
