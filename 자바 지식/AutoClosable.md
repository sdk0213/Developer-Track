# AutoClosable - [출처 - @sa1341님의 velog](https://velog.io/@sa1341/AutoCloseable-클래스)
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
##### AutoClosable 을 지원하는 커스텀 클래스 만들기
* ```java
  public class AutoCloseableExample {
    public static void main(String[] args) {
        try (CustomResource customResource = new CustomResource()){
            customResource.doSomething();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
  }

  public class CustomResource implements AutoCloseable {

      public void doSomething(){
          System.out.println("Do something ...");
      }

      @Override
      public void close() throws Exception {
          System.out.println("CustomResource Closed!");
      }
  }
