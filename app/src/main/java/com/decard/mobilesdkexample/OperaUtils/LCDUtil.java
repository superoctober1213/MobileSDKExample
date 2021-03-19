package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;
import com.decard.mobilesdkexample.ToolUtils.ResultDealUtil;

/**
 * @Author: LD
 * @Description: 液晶显示接口示例
 * @other:
 * @CreateDate: 2020/9/10
 */
public class LCDUtil {
    private final static String TAG = "LCD_LOG";

    /**
     * @param line 需要清除的行号，编号从1开始。
     * @return 0：成功     -1：失败
     * @brief 清除LCD屏幕的显示内容。
     */
    public static int clearScreen(int line) {
        String ret = BasicOper.dc_lcdclrscrn(line);
        Log.d(TAG,"BasicOper.dc_lcdclrscrn: "+ret);
        return ResultDealUtil.isSuccess(ret) ? 0 : -1;
    }

    /**
     * @param line   行号。
     * @param offset 偏移。
     * @param data   要显示的字符串(中文请传入GBK编码，如：byte[] data = "汉字".getBates("GBK"))。
     * @brief 液晶显示
     */
    public static int displayScreen(int line, int offset, byte[] data) {
        String ret = BasicOper.dc_dispinfoSrc(line, offset, data);
        Log.d(TAG,"BasicOper.dc_dispinfoSrc: "+ret);
        return ResultDealUtil.isSuccess(ret) ? 0 : -1;
    }

    /**
     * @param openFlag true-开背光，false-关背光。
     * @brief LCD背光控制
     */
    public static int lcdBackControl(boolean openFlag) {
        String ret = BasicOper.dc_ctlbacklight(openFlag);
        Log.d(TAG,"BasicOper.dc_ctlbacklight: "+ret);
        return ResultDealUtil.isSuccess(ret) ? 0 : -1;
    }
}
