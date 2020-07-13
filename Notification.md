Notification
===

Notification
---
* 알림
  * 안드로이드 OS 8.0을 기준으로 알림 유형이 추가되어서 8.0을 기준으로 코드가 다르다.
    * OS 8.0 이전
      * ```java
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Notification Title");
        builder.setContentText("Notification Text");
        builder.setColor(Color.RED);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notift(channelID,builder.build);
    * OS 8.0 이후
      * 아래 사진과 같이 알림 유형이 새로 생겼다. 그러므로 이 알림유형을 따로 따로 설정해줘야한다.
      * ![kakaotalk](/img/Notification_Channel.png)
      * 채널 유형 만들기
        * ```java
          NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
          NotificationChannel channelMessage = new NotificationChannel(channelID, "default", android.app.NotificationManager.IMPORTANCE_DEFAULT); // 채널 아이디, 채널 이름, 채널 중요도 설정
          channelMessage.setDescription(channelDescription);
          channelMessage.enableLights(enableLight);
          channelMessage.setLightColor(lightColor.toArgb());
          channelMessage.setSound(null, null);
          channelMessage.enableVibration(enableVibration);
          channelMessage.setVibrationPattern(new long[]{100, 200, 100, 200});
          channelMessage.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
          notificationManager.createNotificationChannel(channelMessage);
      * 위에서 만든 채널 유형("default")으로 알림 추가하기
      * ```java
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default); // + 채널유형 (채널유형은 위에처럼 관리하여서 내가 보내고싶은 채널유형으로 보내기)
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Notification Title");
        builder.setContentText("Notification Text");
        builder.setColor(Color.RED);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notift(channelID,builder.build);
