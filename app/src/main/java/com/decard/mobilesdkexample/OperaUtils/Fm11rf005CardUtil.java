package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

/**
 * @Author: LD
 * @Description: fm11rf005卡接口示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class Fm11rf005CardUtil {
    private final static String TAG = "FM11RF005_CARD_LOG";

    /**
     * @param mode 模式，0x00表示对空闲卡进行操作，0x01表示对所有卡操作。
     * @param adr  块地址（0~15）。
     * @return 成功："0000|固定四字节数据"  失败："错误码|错误提示信息"
     * @brief fm11rf005卡信息读取
     */
    public static String getFm11rf005Info(int mode, int adr) {
        //fm11rf0005寻卡操作  寻卡成功返回卡片序列号
        String ret = BasicOper.dc_card_fm11rf005(mode);
        Log.d(TAG, "BasicOper.dc_card_fm11rf005: " + ret);
        if (ret.split("\\|", -1)[0].equals("0000")) {
            ret = BasicOper.dc_read_fm11rf005(adr);       //读取卡信息  成功返回4字节数据
            Log.d(TAG, "BasicOper.dc_read_fm11rf005: " + ret);
            return ret;
        }
        return "fm11rf005寻卡失败！";
    }
}
