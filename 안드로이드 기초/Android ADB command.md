Android adb command - [출처 - superfelix님의 tistory](https://superfelix.tistory.com/89)
===
### 연결된 디바이스 전부 명령
* 추가하기
* zshrc
   ```zshrc
   function adball()
    {
        adb devices | egrep '\t(device|emulator)' | cut -f 1 | xargs -t -J% -n1 -P5 \
              adb -s % "$@"
    }
* 명령은 기존과 똑같이 진행 + function 으로 조합해서 한번에 실행하기
    
---
> adb 도움말
* adb -h
패키지관련
---
> 패키지 강제 종료
* adb shell am force-stop [packagename]
> App data 삭제
* adb shell pm clear 패키지명
> APK 설치
* adb install -r APK_FILE
> APK 제거
* adb unintall 패키지명
> 모든 패키지명 가져오기
* adb shell pm list packages -f

소프트웨어 정보
---
> Android 버전 확인
* adb shell getprop ro.build.version.release
> SDK 버전 확인
* adb shell getprop ro.build.version.sdk
> Android Setting 열기
* adb shell am start -n com.android.settings/com.android.settings.Settings

하드웨어
---
> 장치 모델명 확인
* adb shell getprop ro.product.model
> 화면 해상도 확인
* adb shell dupsys window | grep DisplayWidth
> Screenshot 저장
* adb shell /system/bin/screencap -p 장치내경로
> 장치 검색
* adb devices
> 장치 재부팅
* adb reboot 
> 장치 작동 시간 확인
* adb uptime

ADB
---
> adb 종료
* adb kill-sever
> adb 실행
* adb start-server
> adb shell 실행
* adb shell

리눅스 명령은 adb shell 이후에 처리하면됨 예를들어 다음과같이 리눅스 명령어를 활용해서 특정 파일을 삭제할수있음
---
파일 삭제
---
> 파일 삭제
* adb shell find PATH sdcard/Download/ -exec rm {} \;
> 폴더 삭제
* adb shell find PATH sdcard/Download/ -exec rm -r {} \;
