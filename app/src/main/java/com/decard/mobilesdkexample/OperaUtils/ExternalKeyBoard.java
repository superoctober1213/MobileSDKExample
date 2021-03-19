package com.decard.mobilesdkexample.OperaUtils;

import android.os.SystemClock;
import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ListenerUtils.ExternalScanListener;
import com.decard.mobilesdkexample.ToolUtils.ResultDealUtil;

/**
 * @Author: LD
 * @Description: 外接密码键盘
 * @other:
 * @CreateDate: 2020/9/10
 */
public class ExternalKeyBoard {
    private final static String TAG = "EXTERNAL_KEYBOARD_LOG";
    private static boolean isScan = false;

    /**
     * @param flag    标记。
     *                0x80 - 绿灯亮，液晶显示"欢迎使用"，无语音提示。
     *                0x81 - 绿灯亮，液晶显示"请再输入一次"，有语音提示。
     *                0x82 - 绿灯亮，液晶显示"请输入密码"，有语音提示。
     *                0x83 - 绿灯亮，液晶不操作，无语音提示。
     *                0x84 - 绿灯亮，液晶显示"请输入原密码"，有语音提示。
     *                0x85 - 绿灯亮，液晶显示"请输入新密码"，有语音提示。
     *                0x86 - 绿灯亮，液晶显示"请输入新密码"，无语音提示。
     * @param timeOut 设备超时值，单位为秒。
     * @return 成功："响应数据 ( HEX )"
     * 失败："错误码|错误提示信息"
     * @brief 获取外接密码键盘明文数据。
     */
    public static String getExternalKeyValue(int flag, int timeOut) {
        String ret = BasicOper.SD_IFD_GetPINPro(flag, timeOut);
        return ResultDealUtil.resultInfo(ret);
    }

    /**
     * @brief 外接密码键盘扫码
     */
    public static void externalScan(ExternalScanListener externalScanListener) {
        //开始扫码
        String retStart = BasicOper.SD_IFD_Scan2DBarcodeStart(0);
        Log.d(TAG,"BasicOper.SD_IFD_Scan2DBarcodeStart: "+retStart);
        if (retStart.split("\\|", -1)[0].equals("0000")) {
            isScan = false;
        }
        while (isScan) {
            String retData = BasicOper.SD_IFD_Scan2DBarcodeGetData();   //获取扫码数据
            Log.d(TAG,"BasicOper.SD_IFD_Scan2DBarcodeGetData: "+retData);
            if (ResultDealUtil.isSuccess(retData)) {
                externalScanListener.getExternalScan2D(ResultDealUtil.resultInfo(retData));
                isScan = false;
                BasicOper.SD_IFD_Scan2DBarcodeExit();   //退出扫码
            }
            SystemClock.sleep(200);
        }
    }

    //停止扫码
    public int stopScan() {
        isScan = false;
        String ret = BasicOper.SD_IFD_Scan2DBarcodeExit();
        Log.d(TAG,"BasicOper.SD_IFD_Scan2DBarcodeExit: "+ret);
        return ResultDealUtil.isSuccess(ret) ? 0 : -1;
    }
}