SharedPreferences
===
SharedPreferences
---
* 디바이스의 내부에 xml 파일 형태로 key, value의 쌍의 값들을 저장해 놓는 방식
* ```java
  private static String DateKey = null;
* ```java
  public static boolean setDate(String date) {
    SharedPreferences.Editor editor = sPreference.edit();
    editor.putString(DateKey, date);
    return editor.commit();
  }
* ```java
  public static String getDate() {
    return sPreference.getString(DateKey, null);
  }
