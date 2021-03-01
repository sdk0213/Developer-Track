화면회전감지
===
선언
---
    private DisplayManager mdisplayManager;
    private Display display;
    
등록
---
    mdisplayManager = (DisplayManager) this.getSystemService(Context.DISPLAY_SERVICE);
    mdisplayManager.registerDisplayListener(mDisplayListener, new Handler()); 
    
감지
---
    private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() {
		@Override
		public void onDisplayAdded(int displayId) {

		}

		@Override
		public void onDisplayRemoved(int displayId) {

		}

		@Override
		public void onDisplayChanged(int displayId) {

			display = SecureCameraMain.this.getWindowManager().getDefaultDisplay();
			// 방향에 따라 처리
			switch( display.getRotation() )
			{
				case Surface.ROTATION_0:
					Log.d(TAG,"0도");
					break;
				case Surface.ROTATION_90:
					Log.d(TAG,"90도");
					break;
				case Surface.ROTATION_180:
					Log.d(TAG,"180도");
					break;
				case Surface.ROTATION_270:
					Log.d(TAG,"270도");
					break;
			}

		}
	};
  
해제
---
    mdisplayManager.unregisterDisplayListener(mDisplayListener, new Handler()); 
    
    
    
orientationEventListener
---
    public class RotationListener {
    private int lastRotation;

    private WindowManager windowManager;
    private OrientationEventListener orientationEventListener;

    public RotationListener() {
    }

    public void listen(Context context) {
        // Stop to make sure we're not registering the listening twice.
        stop();

        // Only use the ApplicationContext. In case of a memory leak (e.g. from a framework bug),
        // this will result in less being leaked.
        context = context.getApplicationContext();

        this.windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        this.orientationEventListener = new OrientationEventListener(context, SensorManager.SENSOR_DELAY_NORMAL) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onOrientationChanged(int orientation) {
                if(orientation >= 315 || orientation < 45)  // 90˚

                    SecureCameraMain.ImgRotate(SecureCameraMain.mImgCameraChange,0f,500);
                else if(orientation >= 45 && orientation < 135) // 180˚
                    SecureCameraMain.ImgRotate(SecureCameraMain.mImgCameraChange,270f,500);
                else if(orientation >= 135 && orientation < 225) // 270˚
                    SecureCameraMain.ImgRotate(SecureCameraMain.mImgCameraChange,180f,500);
                else if(orientation >= 225 && orientation < 315) // 0˚
                    SecureCameraMain.ImgRotate(SecureCameraMain.mImgCameraChange,90f,500);

            }
        };

        this.orientationEventListener.enable();

        lastRotation = windowManager.getDefaultDisplay().getRotation();
    }

    public void stop() {
        // To reduce the effect of possible leaks, we clear any references we have to external
        // objects.
        if (this.orientationEventListener != null) {
            this.orientationEventListener.disable();
        }
        this.orientationEventListener = null;
        this.windowManager = null;
    }
 }
 
* stop()은 더블체크 방지
* orientation 방향

