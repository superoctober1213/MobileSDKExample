package com.decard.mobilesdkexample.OperaUtils;

import android.location.LocationManager;
import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

/**
 *@Author: LD
 *@Description: 非接触式CPU卡接口示例
 *@other:
 *@CreateDate: 2020/9/7
 */
public class ContactlessCpuUtil {
    private final static String TAG = "CONTACTLESS_CPU_LOG";
    public static String read_discontacy_card_info() {
        String reset = BasicOper.dc_reset();        //射频复位
        Log.d(TAG, "BasicOper.dc_reset：" + reset);
        if (reset.split("\\|")[0].equals("0000")) {
            //获取感应区中存在的非接触卡类型
            String cardType = BasicOper.dc_RfGetCardType();
            Log.d(TAG, "BasicOper.dc_RfGetCardType: "+cardType);
//            String[] resultArr = cardType.split("\\|");
//            if (resultArr[0].equals("0000")) {
//                Log.d("dc_RfGetCardType", "success card type = " + resultArr[1]);
//                if (resultArr[1].equals("00")) {
//                    Log.d("dc_RfGetCardType", "无卡");
//                } else if (resultArr[1].equals("11")) {
//                    Log.d("dc_RfGetCardType", "Type A CPU Card");
//                } else if (resultArr[1].equals("13")) {
//                    Log.d("dc_RfGetCardType", "Type A Mifare S50");
//                } else if (resultArr[1].equals("14")) {
//                    Log.d("dc_RfGetCardType", "Type A Mifare S70");
//                } else if (resultArr[1].equals("15")) {
//                    Log.d("dc_RfGetCardType", "Type A Mifare Ultralight");
//                } else if (resultArr[1].equals("21")) {
//                    Log.d("dc_RfGetCardType", "Type B CPU Card");
//                } else if (resultArr[1].equals("22")) {
//                    Log.d("dc_RfGetCardType", "Type B 存储卡");
//                }
//            } else {
//                Log.d("dc_RfGetCardType", "error code = " + resultArr[0] + " error msg = " + resultArr[1]);
//                return "获取非接触卡类型错误！";
//            }
            String config_card = BasicOper.dc_config_card(0);           //配置接触卡的类型   param: 0：typeA 1:typeB
            Log.d(TAG, "BasicOper.dc_config_card: " + config_card);
            String dc_card_n_hex = BasicOper.dc_card_n_hex(1);              //寻卡 0x00表示对空闲卡进行操作，0x01表示对所有卡操作。
            Log.d(TAG, "BasicOper.dc_card_n_hex: " + dc_card_n_hex);
            if (dc_card_n_hex.split("\\|")[0].equals("0000")) {
                String pro_resethex = BasicOper.dc_pro_resethex();          //非接触式CPU卡复位
                Log.d(TAG, "BasicOper.dc_pro_resethex：" + pro_resethex);
                if (pro_resethex.split("\\|")[0].equals("0000")) {
                    String pro_commandhex = BasicOper.dc_pro_commandhex("0084000008",7);    //指令交互
                    Log.d(TAG,"BasicOper.dc_pro_commandhex："+pro_commandhex);
                    String pro_commandhex_arr[] = pro_commandhex.split("\\|",-1);
                    if(pro_commandhex_arr[0].equals("0000")){
                        //终止非接触式CPU卡操作
                        String result = BasicOper.dc_pro_halt();
                        Log.d(TAG, "BasicOper.dc_pro_halt: "+result);
                        return pro_commandhex_arr[1];
                    }
                    return "指令交互失败！";
                }
                return "卡复位失败！";
            }
            return "寻卡失败！";
        }
        return "射频复位失败！";
    }
}
