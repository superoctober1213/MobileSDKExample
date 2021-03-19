package com.decard.mobilesdkexample.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.DevicesOperaUtils.SelfServiceDevice;
import com.decard.mobilesdkexample.R;

public class T10EM2Activity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "T10EM2_LOG";
    private EditText t10em2_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t10_e_m2);
        t10em2_tv = findViewById(R.id.T10EM2_tv);

        findViewById(R.id.t10em2_checkCard_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_reset_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_open_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_enter_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_pop_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_move_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_state_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_cardType_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_popMode_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_enterMode_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_enterEndMode_btn).setOnClickListener(this);
        findViewById(R.id.t10em2_stop_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t10em2_open_btn:
                int devHandle = BasicOper.dc_open("COM", null, "/dev/ttyHSL1", 115200);//返回值为设备句柄号。
                Log.d(TAG, " t10em2 open COM: " + devHandle);
                t10em2_tv.append("读卡器端口打开状态：" + devHandle + "\n");
                break;
            case R.id.t10em2_checkCard_btn:     //检测卡位置状态
                String position = SelfServiceDevice.cardPosition();
                Log.d(TAG, "cardPosition: " + position);
                t10em2_tv.append("检测卡位置状态：" + position + "\n");
                break;
            case R.id.t10em2_reset_btn:         //复位设备
                String reset = SelfServiceDevice.resetDevice();
                Log.d(TAG, "resetDevice: " + reset);
                t10em2_tv.append("复位设备：" + reset + "\n");
                break;
            case R.id.t10em2_enter_btn:     //进卡
                String enter = SelfServiceDevice.enterCard(2, 1);
                Log.d(TAG, "进卡: " + enter);
                t10em2_tv.append("进卡：" + enter + "\n");
                break;
            case R.id.t10em2_pop_btn:       //弹卡
                String pop = SelfServiceDevice.CardEject(2, 0);
                Log.d(TAG, "弹卡: " + pop);
                t10em2_tv.append("弹卡：" + pop + "\n");
                break;
            case R.id.t10em2_move_btn:      //移动卡片
                String move = SelfServiceDevice.moveCard(2, 1);
                Log.d(TAG, "移动卡片: " + move);
                t10em2_tv.append("移动卡片：" + move + "\n");
                break;
            case R.id.t10em2_state_btn:     //传感器状态
                String state = SelfServiceDevice.getSensorStatus();
                Log.d(TAG, "传感器状态: " + state);
                t10em2_tv.append("传感器状态：" + state + "\n");
                break;
            case R.id.t10em2_cardType_btn:  //卡类型
                String type = SelfServiceDevice.checkCardType();
                Log.d(TAG, "卡类型: " + type);
                t10em2_tv.append("卡类型：" + type + "\n");
                break;
            case R.id.t10em2_popMode_btn:   //弹卡模式
                String popMode = SelfServiceDevice.ejectCardConfig(2);
                Log.d(TAG, "弹卡模式: " + popMode);
                t10em2_tv.append("弹卡模式：" + popMode + "\n");
                break;
            case R.id.t10em2_enterMode_btn: //前端进卡模式
                String enterMode = SelfServiceDevice.enterFrontConfig(1);
                Log.d(TAG, "前端进卡模式: " + enterMode);
                t10em2_tv.append("前端进卡模式：" + enterMode + "\n");
                break;
            case R.id.t10em2_enterEndMode_btn:  //后端进卡模式
                String enterEndMode = SelfServiceDevice.enterBackConfig(1);
                Log.d(TAG, "卡类型: " + enterEndMode);
                t10em2_tv.append("卡类型：" + enterEndMode + "\n");
                break;
            case R.id.t10em2_stop_btn:          //停卡位置
                String stopPos = SelfServiceDevice.stopCardPosition(4);
                Log.d(TAG, "停卡位置: " + stopPos);
                t10em2_tv.append("停卡位置：" + stopPos + "\n");
                break;
        }
    }
}