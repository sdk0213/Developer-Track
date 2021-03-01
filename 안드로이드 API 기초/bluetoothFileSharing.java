import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BluetoothActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "BLUETOOTHFILESENDER " + BluetoothActivity.class.getSimpleName();

    private EditText mEtData;
    private Button mBtnBluetoothSend;

    private static final int REQUEST_BLUETOOTH_CODE = 1;
    private BluetoothAdapter mBluetoothAdapter; // 블루투스 어댑터

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_test_bluetoothview);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN}, 0);
        } else{
            initView();
            bluetoothActivation();
        }
    }

    private void initView() {
        mEtData = findViewById(R.id.et_data);
        mBtnBluetoothSend = findViewById(R.id.btn_bluetooth_send);
        mBtnBluetoothSend.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == mBtnBluetoothSend.getId()){
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.setPackage("com.android.bluetooth");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, mEtData.getText().toString());
            startActivity(Intent.createChooser(sharingIntent, "File Send"));
        }
    }

    private void bluetoothActivation() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            if (!mBluetoothAdapter.isEnabled()) {
                Toast.makeText(this, "Connect To Bluetooth", Toast.LENGTH_LONG).show();
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_BLUETOOTH_CODE);
            }
        } else {
            Toast.makeText(this, "This Device isn't support Bluetooth ", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initView();
                bluetoothActivation();
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_BLUETOOTH_CODE:
                if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "if you do not activate bluetooth, this function is not available", Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
