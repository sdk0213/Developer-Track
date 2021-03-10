# camera2 - [소스코드 - Camera2 Basic Of Google](https://github.com/googlearchive/android-Camera2Basic)

### 용어
* CameraManager
  * 카메라 가져오기
  * 카메라 목록, 특성, 모니터링
* CameraDevice
  * 물리 카메라 표현, 캡쳐 세션, 캡처 리퀘스트 요청
* CameraCharacteristics
  * 카메라를 고르기
  * 카메라 특성 정보
* CameraCaptureSession
  * 카메라로부터 얻은 이미지를 Surface에 요청
* CaptureRequest
  * 카메라로부터 어떻게 이미지를 얻을지 결정
* CaptureResult
  * 카메라로부터 캡쳐한 결과
##### 카메라 등급
* isHardwareLevelSupported
* 카메라의 하드웨어 지원수준 어느정도인지 제공
##### 지원하는 이미지/비디오 사이즈, 포멧 
* StreamConfigurationMap
* chooseOptimalSize
  * 안드로이드 디스플레이에 최적화 계산
  * 굳이 디스플레이 맞지않는것을(또는 과도한 해상도) 출력할필요가 없기에
##### 카메라 오픈
* Camera Id, StateCallback, Handler 필요
##### Surface 얻기
* call getSurfaceTesture() on TextureView
##### ImageReader로부터 Surface 얻기
* ImageReader.newInstance(largest.getWidth(), largest.getHeight(), ImageFormat.JPEG)
##### 카메라 캡쳐 세선 생성
* CameraDevice.createCaptureSession...
* 메모리 많이 차지, 비동기적으로 처리 필요
##### 프리뷰를 위한 캡쳐 리퀘스트
* CaptureRequest.Builder a = CameraDeivce.createCaptureRequest...





### CameraX
* Jetpack 에 있고 Lifeaware의 특징
* 아직 alpha 단계
