package com.decard.mobilesdkexample.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.decard.NDKMethod.BasicOper;
import com.decard.NDKMethod.SSCardDriver;
import com.decard.entitys.IDCard;
import com.decard.mobilesdkexample.ListenerUtils.MsgInfoListener;
import com.decard.mobilesdkexample.ListenerUtils.Scan2DListener;
import com.decard.mobilesdkexample.OperaUtils.Card102Util;
import com.decard.mobilesdkexample.OperaUtils.Card4428Util;
import com.decard.mobilesdkexample.OperaUtils.Card4442Util;
import com.decard.mobilesdkexample.OperaUtils.ContactCpuUtil;
import com.decard.mobilesdkexample.OperaUtils.ContactlessCpuUtil;
import com.decard.mobilesdkexample.OperaUtils.Fm11rf005CardUtil;
import com.decard.mobilesdkexample.OperaUtils.IDCardUtil;
import com.decard.mobilesdkexample.OperaUtils.M1CardUtil;
import com.decard.mobilesdkexample.OperaUtils.MagCardUtil;
import com.decard.mobilesdkexample.OperaUtils.PrintUtil;
import com.decard.mobilesdkexample.OperaUtils.SSCardUtil;
import com.decard.mobilesdkexample.OperaUtils.Scan2DUtil;
import com.decard.mobilesdkexample.OperaUtils.UltralightUtil;
import com.decard.mobilesdkexample.R;

import java.util.IdentityHashMap;

public class CardOperaActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "CARD_OPERA_ACTIVITY_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_opera);

        findViewById(R.id.contact_cpu_btn).setOnClickListener(this);
        findViewById(R.id.contactless_cpu_btn).setOnClickListener(this);
        findViewById(R.id.m1_card_btn).setOnClickListener(this);
        findViewById(R.id.ultralight_card_btn).setOnClickListener(this);
        findViewById(R.id.card_4428_btn).setOnClickListener(this);
        findViewById(R.id.card_4442_btn).setOnClickListener(this);
        findViewById(R.id.card_102_btn).setOnClickListener(this);
        findViewById(R.id.mag_card_btn).setOnClickListener(this);
        findViewById(R.id.ID_card_btn).setOnClickListener(this);
        findViewById(R.id.SS_card_btn).setOnClickListener(this);
        findViewById(R.id.Scan_2D_btn).setOnClickListener(this);
        findViewById(R.id.Print_picture_btn).setOnClickListener(this);
        findViewById(R.id.Fm11rf005_card_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_cpu_btn:  //接触式CPU卡
                String cpuInfo = ContactCpuUtil.read_contact_card_info();
                Log.e(TAG, "R.id.contact_cpu_btn: " + cpuInfo);
                Toast.makeText(this, "接触式CPU卡读取结果： " + cpuInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.contactless_cpu_btn: //非接CPU卡
                String RfInfo = ContactlessCpuUtil.read_discontacy_card_info();
                Log.e(TAG, "R.id.contactless_cpu_btn: " + RfInfo);
                Toast.makeText(this, "非接CPU卡读取结果： " + RfInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.m1_card_btn:      //  M1卡
                String M1Info = M1CardUtil.load_M1_key(1, 0, 6, "FFFFFFFFFFFF", 27);
                Toast.makeText(this, "M1卡读取结果： " + M1Info, Toast.LENGTH_SHORT).show();
//                M1CardUtil.write_M1_card(1, 0, 15, 61);
                break;
            case R.id.ultralight_card_btn:      //ultralight卡
                String ultralightInfo = UltralightUtil.operaUltralight(0, "passward", 7);
                Toast.makeText(this, "ultralight卡读取结果： " + ultralightInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_4428_btn:     //4428卡
                String info4428 = Card4428Util.card4428();
                Toast.makeText(this, "4428卡读取结果： " + info4428, Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_4442_btn:     //4442卡
                String info4442 = Card4442Util.card4442();
                Toast.makeText(this, "4428卡读取结果： " + info4442, Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_102_btn:     //102卡
                String card102 = Card102Util.card102((short) 0,"FF",5,10);
                Toast.makeText(this, "102卡读取结果： "+card102, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mag_card_btn:     //磁条卡
                final String MsgInfo = "";
                int ret = MagCardUtil.read_mag_card(0, new MsgInfoListener() {
                    @Override
                    public void getMsgInfo(String info) {
                        Log.e(TAG, "磁条卡结果: " + info);
                    }
                });
                if (ret == 0) {
                    Toast.makeText(this, "磁条卡读取成功。。 ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "磁条卡读取失败:。。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ID_card_btn:

//                short retRaw = IDCardUtil.getSamAReadCardInfoRaw(1);
//                Log.d(TAG, "raw: "+retRaw);
//                String retInfo = IDCardUtil.dc_IdCardReadCardInfoString();
//                Log.d(TAG, "info: "+retInfo);
//                Toast.makeText(this, "身份证文字信息。。 "+retInfo, Toast.LENGTH_SHORT).show();
//                IDCardUtil.getIdInfo();
                IDCard idCard = IDCardUtil.getIdCard(1);
                if (idCard!= null) {
                    Toast.makeText(this, "身份证读取成功。。 ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "身份证读取失败。。 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SS_card_btn:      //社保卡
                String ssCardInfo = SSCardUtil.getSSCardInfo();
                Toast.makeText(this, "社保卡读结果： " + ssCardInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.Scan_2D_btn:      //二维码扫描
                int scanResult = Scan2DUtil.scan_2D(new Scan2DListener() {
                    @Override
                    public void getScan2DInfo(String info) {
                        Log.e(TAG, "二维码扫描结果: " + info);
                    }
                });
                if (scanResult == 0) {
                    Toast.makeText(this, "二维码扫描成功。。 ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "二维码扫描失败。。 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Print_picture_btn:    //图片打印
                int printResult = PrintUtil.printFactor(2, getResources());
                if (printResult == 0) {
                    Toast.makeText(this, "打印成功。。 ", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "打印失败。。 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Fm11rf005_card_btn:
                String fm11 = Fm11rf005CardUtil.getFm11rf005Info(0, 10);
                Log.e(TAG, "R.id.Fm11rf005_card_btn: " + fm11);
                Toast.makeText(this, "Fm11rf005读取结果: " + fm11, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}