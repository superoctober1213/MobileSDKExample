package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;

/**
 * @Author: LD
 * @Description: 接触式CPU卡接口示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class ContactCpuUtil {
    private final static String TAG = "CONTACT_CPU_LOG";

    public static String read_contact_card_info() {
        String setCpu = BasicOper.dc_setcpu(0);     //设置当前接触式卡座
        Log.d(TAG, "BasicOper.dc_setcpu：" + setCpu);
        if (setCpu.split("\\|")[0].equals("0000")) {
            String result = BasicOper.dc_setcpupara(0, 0x00, 0x5C); //设置cpu卡参数
            String[] resultArr = result.split("\\|", -1);
            if (resultArr[0].equals("0000")) {
                String card_status = BasicOper.dc_card_status();        //检测接触卡是否在位
                Log.d(TAG, "BasicOper.dc_card_status：" + card_status);
                if (card_status.split("\\|")[0].equals("0000")) {
                    String cpureset_hex = BasicOper.dc_cpureset_hex();          //接触式CPU卡上电复位
                    Log.d(TAG, "BasicOper.dc_cpureset_hex：" + cpureset_hex);
                    if (cpureset_hex.split("\\|")[0].equals("0000")) {
                        String cpuapdu_hex = BasicOper.dc_cpuapdu_hex("0084000008");    //指令交互
                        Log.d(TAG, "BasicOper.dc_cpuapdu_hex：" + cpuapdu_hex);
                        if (cpuapdu_hex.split("\\|")[0].equals("0000")) {
                            String cpudown = BasicOper.dc_cpudown();        //接触式CPU卡下电
                            Log.d(TAG, "BasicOper.dc_cpudown：" + cpudown);
                        }
                        return cpuapdu_hex;
                    }
                    return "接触式CPU卡复位失败！";
                }
                return "接触式卡不存在！";
            }
            return "CPU卡参数设置失败！";
        }
        return "接触式卡座设置失败！";
    }
}
