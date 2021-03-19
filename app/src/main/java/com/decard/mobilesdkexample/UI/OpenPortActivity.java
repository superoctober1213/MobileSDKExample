package com.decard.mobilesdkexample.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.R;
import com.decard.mobilesdkexample.ToolUtils.Constant;

public class OpenPortActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "OPEN_PORT_ACTIVITY_LOG";
    private Intent intent = new Intent();
    private int usbChoice = -1;
    private boolean isSupportCom = false;
    private Spinner comPathSpinner;
    private Spinner comRateSpinner;
    private String[] comRateList = new String[]{"9600", "19200", "38400", "57600", "115200"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //申请USB权限
        BasicOper.dc_AUSB_ReqPermission(this);
        setContentView(R.layout.activity_open_port);
        findViewById(R.id.open_usb_btn).setOnClickListener(this);
        findViewById(R.id.open_com_btn).setOnClickListener(this);
        findViewById(R.id.open_bluetooth_btn).setOnClickListener(this);
        comPathSpinner = findViewById(R.id.sp_com_path);
        comRateSpinner = findViewById(R.id.sp_com_baudrate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_usb_btn:
                int result = BasicOper.dc_open("AUSB", OpenPortActivity.this, "", 0);
                Log.d(TAG, "dc_open USB: " + result);
                setActivityResultData(result);
                break;
            case R.id.open_com_btn:
                String[] portPathExist = BasicOper.dc_getSerialPortPaths();
                if (portPathExist.length < 1) {
                    Toast.makeText(this, "设备不支持串口通讯！", Toast.LENGTH_SHORT).show();
                } else {
                    int devHandle = BasicOper.dc_open("COM", OpenPortActivity.this, "/dev/ttyUSB0", 115200);//返回值为设备句柄号。
                    Log.d(TAG, "dc_open COM: " + devHandle);
                    setActivityResultData(devHandle);
                }
                break;
            case R.id.open_bluetooth_btn:
                int openResult = BasicOper.dc_open("BT", this, "DC:0D:30:B9:7D:F7", 0);//返回值为设备句柄号。88:1B:99:07:67:44
                Log.d(TAG, "dc_open BT: " + openResult);
                if (openResult < 0) {
                    Toast.makeText(this, "请在源码中修改蓝牙MAC地址。。。", Toast.LENGTH_SHORT).show();
                }
                setActivityResultData(openResult);
                break;
        }
    }

    public void setActivityResultData(int devHandler) {
        intent.putExtra("Handler", devHandler);
        setResult(Constant.OPEN_PORT_ACTIVITY_RESULT, intent);
        if (devHandler > 0)
            Toast.makeText(OpenPortActivity.this, "端口打开成功：" + devHandler, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(OpenPortActivity.this, "端口打开失败：" + devHandler, Toast.LENGTH_SHORT).show();
    }
}