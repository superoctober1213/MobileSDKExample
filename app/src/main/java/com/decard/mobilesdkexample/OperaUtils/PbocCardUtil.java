package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@Author: LD
 *@Description: 获取ARQC数据接口示例
 *@other:
 *@CreateDate: 2020/9/8
 */
public class PbocCardUtil {
    private final static String TAG = "PBOC_INFO_LOG";

    /**
     * @brief 获取ARQC数据
     * @param type 接触类型   1:接触式  2：非接
     * */
    public static String getArqcData(int type) {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd HHmmss");
        String time = sdformat.format(new Date());
        String[] times = time.split(" ");
        String TxData = "P012" + "1" + "Q012000000000000R003156S008" + times[0]
                + "T00201U006" + times[1] + "V0250000000000000000000000000";
        String info = BasicOper.dc_GetArqc(type, TxData, "");
        Log.d(TAG, "BasicOper.dc_GetArqc"+info);
        return info;
    }
}
