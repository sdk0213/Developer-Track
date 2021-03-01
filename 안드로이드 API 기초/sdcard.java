import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SdcardActivity extends AppCompatActivity {

    private final static String TAG = "SDCARD " + SdcardActivity.class.getSimpleName();

    private TextView mTvSdcard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_test_sdcardview);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        } else {
            initView();
            checkSdcard();
        }
    }

    private void initView(){
        mTvSdcard = findViewById(R.id.tv_sdcard);
    }

    private void checkSdcard(){
        String[] sdcardList = getExternalStorageDirectories();
        File sdFile;
        File[] FilesInSdCard;
        if(sdcardList != null){
            if(sdcardList.length > 0){
                for(int i = 0 ; i < sdcardList.length ; i++){
                    mTvSdcard.append(sdcardList[i] + "\n");
                    sdFile = new File(sdcardList[i]);
                    FilesInSdCard = sdFile.listFiles();
                    mTvSdcard.append("'s inner FileList :\n");
                    if(FilesInSdCard != null) {
                        for (int j = 0; j < FilesInSdCard.length; j++) {
                            mTvSdcard.append((j+1) + ". " + FilesInSdCard[j].getName() + "\n");
                        }
                        mTvSdcard.append("\n");
                    } else{
                        mTvSdcard.append("No File Or Can't Access");
                    }
                    mTvSdcard.append("\n");
                }
            } else {
                mTvSdcard.append("sdcard is not exist");
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 0){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initView();
                checkSdcard();
            } else {
                finish();
            }
        }
    }

    private String[] getExternalStorageDirectories() {
        List<String> results = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File[] externalDirs = getExternalFilesDirs(null);
            for (File file : externalDirs) {
                String path = file.getPath().split("/Android")[0];
                boolean addPath = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addPath = Environment.isExternalStorageRemovable(file);
                } else {
                    addPath = Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(file));
                }
                if (addPath) {
                    results.add(path);
                }
            }
        }

        if (results.isEmpty()) {
            String output = "";
            try {
                final Process process = new ProcessBuilder().command("mount | grep /dev/block/vold").redirectErrorStream(true).start();
                process.waitFor();
                final InputStream is = process.getInputStream();
                final byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    output = output + new String(buffer);
                }
                is.close();
            } catch (final Exception e) {
                e.printStackTrace();
            }
            if (!output.trim().isEmpty()) {
                String devicePoints[] = output.split("\n");
                for (String voldPoint : devicePoints) {
                    results.add(voldPoint.split(" ")[2]);
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().matches(".*[0-9a-f]{4}[-][0-9a-f]{4}")) {
                    Log.d(TAG, results.get(i) + " might not be ext SDcard");
                    results.remove(i--);
                }
            }
        } else {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().contains("ext") && !results.get(i).toLowerCase().contains("sdcard")) {
                    Log.d(TAG, results.get(i) + " might not be ext SDcard");
                    results.remove(i--);
                }
            }
        }
        String[] storageDirectories = new String[results.size()];
        for (int i = 0; i < results.size(); ++i) storageDirectories[i] = results.get(i);
        return storageDirectories;
    }
    
    @Deprecated // 파일탐색기를 여는 ACTION
    public void performFileSearch() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // in my case, showing only text-files is enough, therefore I guess the type is as follows:
        intent.setType("text/plain");

        startActivityForResult(intent, 0); // requestCode ==> READ_REQUEST_CODE
    }

}
