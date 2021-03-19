package com.decard.mobilesdkexample.OperaUtils;

import android.os.SystemClock;
import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ListenerUtils.MsgInfoListener;

/**
 * @Author: LD
 * @Description: 磁条卡接口调用示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class MagCardUtil {
    private static final String TAG = "MAG_CARD_LOG";

    /**
     * @param type 0：同步  1：异步
     */
    public static int read_mag_card(int type, MsgInfoListener msgInfoListener) {
        String readmagcard = null;
        switch (type) {
            case 0:
                readmagcard = BasicOper.dc_readmagcardall(10);       //读取磁条卡数据
                Log.d(TAG, "BasicOper.dc_readmagcardall: " + readmagcard);
                msgInfoListener.getMsgInfo(readmagcard);
                if(readmagcard.split("\\|",-1)[0].equals("0000")){
                    return 0;
                }
                break;
            case 1:
                //进入读磁条状态
                String startMag = BasicOper.dc_startreadmag();
                while (startMag.split("\\|",-1)[0].equals("0000")) {
                    //读取磁条卡信息
                    String readMag = BasicOper.dc_readmag();
                    Log.d(TAG, "BasicOper.dc_readmag: " + readMag);
                    String readArr[] = readMag.split("\\|", -1);
                    if (readArr[0].equals("0001") || readArr[0].equals("0000")) {
                        if (readArr[0].equals("0000")) {
                            Log.d(TAG, "BasicOper.dc_readmag  result: "+readMag);
                            msgInfoListener.getMsgInfo(readMag);
                            return 0;
                        }
                        //退出读取磁条卡状态
                        readMag = BasicOper.dc_stopreadmag();
                        Log.d(TAG, "BasicOper.dc_stopreadmag  result: $ret");
                        readMag = BasicOper.dc_startreadmag();
                        Log.d(TAG, "BasicOper.dc_startreadmag  result: $ret");
                    }
                    SystemClock.sleep(200);
                }
        }
        return -1;
    }
}
