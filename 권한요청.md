권한요청
===

권한이 요청될때 허용,차단을 눌렀을때의 처리
---
만약에 여러 퍼미션이 요청될경우 그 결과값이 grantResults로 여러값을 받는다. 절대 null값은 반환하지 않는다.

    public abstract void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults)
                
    ----------------------------------------------------------------------------------------
    grantResult은 다음과 같이 두가지를 반환하면 -1과 0으로 구분되고 절대 null 값을 반환하지 않는다.
    PERMISSION_DENIED
    Added in API level 1
    public static final int PERMISSION_DENIED
    Permission check result: this is returned by checkPermission(String, String) if the permission has not been granted to the given package.

    Constant Value: -1 (0xffffffff)

    PERMISSION_GRANTED
    Added in API level 1
    public static final int PERMISSION_GRANTED
    Permission check result: this is returned by checkPermission(String, String) if the permission has been granted to the given package.

    Constant Value: 0 (0x00000000)
    ----------------------------------------------------------------------------------------

권한요청
---
new String[]이기 때문에 여러권한을 요청할수있다. 맨끝에 PERMISSION_REQUEST_CAMERA는 requestcode가 들어가는곳

    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);

그리고 아래 onRequestPermissionsResult에서 허용과 차단에 대해서 설정한다.

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)

그리고 아래와 같은 조건을 확인하고 권한이 승인이 되었는지 안되었는지 처리할수있다.
grantResult.length는 권한을 요청한 수이고 거의 1개이상 요청하니까 0 이상으로 처리하였다.
grantResuls[0][1][2][3][...] 은 권한을 요청한 순서대로 그에 대한 응답이다.

    	if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
					// Permission has been granted. Start camera preview Activity.
					Snackbar.make(mPreview, "카메라, 저장소 권한이 승인 되었습니다", Snackbar.LENGTH_SHORT).show();
                    


* 권한요청을 띄우는 창은 다른스레드에서 작동하는것으로 


