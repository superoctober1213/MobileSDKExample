package com.decard.mobilesdkexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.decard.NDKMethod.BasicOper;
import com.decard.dc_readsn.DcDeviceUtil;
import com.decard.mobilesdkexample.ToolUtils.Constant;
import com.decard.mobilesdkexample.UI.CardOperaActivity;
import com.decard.mobilesdkexample.OperaUtils.PortUtil;
import com.decard.mobilesdkexample.UI.DevicesOperaActivity;
import com.decard.mobilesdkexample.UI.OpenPortActivity;
import com.decard.mobilesdkexample.UI.T10EM2Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "MAINACIVITY_LOG";
    private int devHandle = -10000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String androidId = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        DcDeviceUtil.setAndroidId(androidId);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                BasicOper.dc_init(getApplicationContext(),"此处填入授权ID");
//            }
//        }).start();

        findViewById(R.id.open_btn).setOnClickListener(this);
        findViewById(R.id.card_opera_btn).setOnClickListener(this);
        findViewById(R.id.device_opera_btn).setOnClickListener(this);
        findViewById(R.id.close_btn).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_btn:
                Intent intent = new Intent(MainActivity.this, OpenPortActivity.class);
                startActivityForResult(intent, Constant.OPEN_PORT_ACTIVITY_RESULT);
                break;
            case R.id.card_opera_btn:
                if (devHandle > 0) {
                    startActivity(new Intent(MainActivity.this, CardOperaActivity.class));
                    Log.d(TAG, "card_opera_btn onClick: ");
                } else Toast.makeText(this, "请先打开端口。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.device_opera_btn:
                startActivity(new Intent(MainActivity.this, DevicesOperaActivity.class));
                break;
            case R.id.close_btn:
                //关闭端口，释放资源
                int ret = PortUtil.closePort();
                if (ret == 0) {
                    Toast.makeText(this, "端口关闭。", Toast.LENGTH_SHORT).show();
                    devHandle = -1;
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.OPEN_PORT_ACTIVITY_RESULT && data != null) {
            devHandle = data.getIntExtra("Handler", -1002);
            Log.d(TAG, "onActivityResult: " + devHandle);
        }
    }
}