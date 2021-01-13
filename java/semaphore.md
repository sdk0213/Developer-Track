semaphore - [출처 - aroundck님의 tistory](https://aroundck.tistory.com/5873)
===
* **필요성**
  * 여러 스레드에서 전역변수를 컨트룰 할경우 값이 동기화되지 않아서 서로 다른 값을 가질수 있기 때문에 이를 방지하고자 나온 개념이다.
* 공유된 자원을 사용하는 방법중 하나
* 특정 자원이나 특정 연산을 동시에 사용하거나 호출할 수 있는 스레드의 수를 제한
* 세마포어의 값이 0 이면 접근 불가, 1이면 하나 접근 가능이며 프로세스가 하나가 세마포어를 흭득하면 세마포어 개수는 1에서 0으로 줄어들며 프로세스가 종료되어서 세마포어를 반납할경우 해당 값을 1을 다시 반환해 1상태로 된다.
* 사용시 주의사항
  * 내부적으로 동기화해주기 때문에 굳이 외부에서 동기화할 필요가없다.
  * permit은 초기값에 고정되어 있지 않고 release() 를 호출해주면 계속 permit 은 증가한다.
  * 동기화가 필요한 코드에 집어넣어주면 된다.
  
> 예제
> >카메라를 열고 닫는 프로세스는 비동기적으로 처리되는 경우가 있어서 이를 컨트룰 가능하게 할때 세마포어를 사용하면 유용하다. 다음 예제는 구글에서 제공하는 Camera2APIBasic 코드이다. 해당 코드에서는 카메라를 열고 닫을때 세마포어를 사용한다.
* ```java
  // 카메라 예제
  private Semaphore mCameraOpenCloseLock = new Semaphore(1); // 하나 생성
* ```java
  try {
    // 2.5 초동안 Semaphore 흭득을 시도하고 실패시 열수없도록 하는 코드
    if (!mCameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
        throw new RuntimeException("Time out waiting to lock camera opening.");
     }
    manager.openCamera(mCameraId, mStateCallback, mBackgroundHandler);
    } catch (CameraAccessException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        throw new RuntimeException("Interrupted while trying to lock camera opening.", e);
  }
* 세마포어를 흭득하면 아래 콜백이 작동된다. 그리고 흭득한 세마포어를 다시 반납한다.
* ```java
     /**
     * {@link CameraDevice.StateCallback} is called when {@link CameraDevice} changes its state.
     * 카메라 변화 상태 콜백해주는 콜백
     */
    private final CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            // This method is called when the camera is opened.  We start camera preview here.
            mCameraOpenCloseLock.release(); // 세마포어 반납
            mCameraDevice = cameraDevice;
            createCameraPreviewSession();
        ...
        ..// Override 생략
        
        }
       
