# [JSch - Homepage](http://www.jcraft.com/jsch/)
* Secure Shell 를 Java 로 구현한 라이브러리다.
* [BSD 라이선스](http://www.jcraft.com/jsch/LICENSE.txt) 이므로 명시 필요
---
### gradle
* ```gradle
  // SSH
  implementation 'com.jcraft:jsch:0.1.54'
  
---
### 사용
* 아래 코드는 Android 에서 작동시키기 위해서 메인쓰레드가 아닌 일부로 쓰레드를 하나 생성하였으며 샘플코드 이므로 아래처럼 코드 작성 X
* ```java
  
  new Thread(() -> {

      Session session = null;
      String Root = "root";
      Channel channel = null;
      ChannelSftp channelSftp = null;

      JSch jSch = new JSch();
      try {
          session = jSch.getSession(Root, "my.server.kr", myport); // String, String, Int
          session.setPassword("password");
          Properties config = new Properties();
          config.put("StrictHostKeyChecking", "no");
          session.setConfig(config);
          session.connect();


          // SFTP 를 사용하여 파일을 업로드 하는 샘플코드
          channel = session.openChannel("sftp");
          channel.connect();
          channelSftp = (ChannelSftp) channel;
          String folderName = "/dksung";
          String fileName = "/20220523_174040.jpg"; 
          String filePath = getFilesDir() + fileName;
          String remoteFolder = "/home";
          String DestinyPath = remoteFolder + fileName;
          channelSftp.put(filePath, DestinyPath); // String(from) -> String(to)

      } catch (Exception e){
          e.printStackTrace();
      } finally {
          if(channelSftp != null) channelSftp.disconnect();
          if(channel != null) channel.disconnect();
          if(session != null) session.disconnect();
      }

  }).start();
