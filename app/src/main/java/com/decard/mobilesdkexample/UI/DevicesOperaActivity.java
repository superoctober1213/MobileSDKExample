package com.decard.mobilesdkexample.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.R;

public class DevicesOperaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText beepTimeET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_opera);

        beepTimeET = findViewById(R.id.beepTime_et);
        findViewById(R.id.T10EM_btn).setOnClickListener(this);
        findViewById(R.id.beep_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.T10EM_btn:
                startActivity(new Intent(DevicesOperaActivity.this, T10EM2Activity.class));
                break;
            case R.id.beep_btn:
                String beepStr = beepTimeET.getText().toString();
                if (beepStr.equals("") || beepStr == null) {
                    Toast.makeText(this, "请输入蜂鸣时间！", Toast.LENGTH_SHORT).show();
                } else {
                    int beepTime = Integer.parseInt(beepStr);
                    String beep = BasicOper.dc_beep(beepTime);
                    if (beep.split("\\|", -1)[0].equals("0000")) {
                        Toast.makeText(this, "蜂鸣成功！", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(this, "蜂鸣失败！-> "+beep, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}