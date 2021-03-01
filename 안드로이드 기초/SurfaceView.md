SurfaceView
===

* Camera Preview나, 동영상 재생 같은 처리들은 1초에 몇 십 프레임을 출력해야하는 제법 무거운 작업들이고 
Android UI 혼자 감당하기에는 자원이 부족, 이는 곧 치명적인 ANR이 발생하는 원인
* SurfaceView는 UI 쓰레드와는 별도로 백그라운드 쓰레드에서 동작
* openGL을 사용하여 가속도 지원되기 때문에 3D 출력도 가능

* SurfaceHolder와 surface가 계속 통신하면서 SurfaceHolder가 Surface에 접근하여 화면을 처리해주는 구조이다. 

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile10.uf.tistory.com%2Fimage%2F99B89B435C063B9A29C281" width="40%" height="30%" title="Surface View" alt="Surface View"></img>


    public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder mHolder;

    public MySurfaceView(Context context) {
        super(context);
        mHolder = getHolder();
        mHolder.addCallback(this);
        
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    }
 
 
* SurfaceCreated
  * Surface에 렌더링 하려는 코드를 여기에서 시작해야 한다. 
  단 하나의 쓰레드 만이 Surface를 그릴 수 있으므로 다른 쓰레드에 접근하여 그리는 시도를 해서는 안된다. 
* SurfaceDestroyed
  * surfaceDestroyed가 호출된 이후에는 더 이상 surface에 접근해서는 안된다. 
  만약 surface에 바로 접근하여 그리는 렌더링 쓰레드가 있다면, 이 함수의 리턴을 받기 전에는 더 이상 surface를 건드릴 수 없다. 
* surfaceChanged
  * surfaceChanged는 surface의 구조적인 변화(포맷이나 사이즈)가 생기면 호출된다.
  이 시점에서 surface 안의 image를 변경할 수 있다. surfaceCreated가 호출된 이후로 최소 한 번은 불린다.
  
  

