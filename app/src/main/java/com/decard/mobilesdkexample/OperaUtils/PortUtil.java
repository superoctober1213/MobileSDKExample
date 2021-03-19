package com.decard.mobilesdkexample.OperaUtils;

import android.content.Context;
import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

/**
 * @Author: LD
 * @Description: 端口打开示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class PortUtil {
    public static final String TAG = "PORT_OPERA_UTIL";

    //打开USB
    public static int openUsb(Context context) {
        int devHandle = BasicOper.dc_open("AUSB", context, "", 0);
        if (devHandle > 0) {
            Log.d(TAG, "dc_open success devHandle = " + devHandle);
        }
        return devHandle;
    }

    //打开libUsb
    public static int openLibUsb(Context context) {
        int devHandle = BasicOper.dc_open("LUSB", null, "", 0);
        if (devHandle > 0) {
            Log.d(TAG, "dc_open success devHandle = " + devHandle);
        }
        return devHandle;
    }

    //串口
    public static int openCom(String comPath, int baudRate) {
        int devHandle = BasicOper.dc_open("COM", null, comPath, baudRate);
        if (devHandle > 0) {
            Log.d(TAG, "dc_open success devHandle = " + devHandle);
        }
        return devHandle;
    }

    //经典蓝牙
    public static int openBluetooth(Context context, String mac) {
        int devHandle = BasicOper.dc_open("BT", context, mac, 0);
        if (devHandle > 0) {
            Log.d(TAG, "dc_open success devHandle = " + devHandle);
        }
        return devHandle;
    }

    //低功耗蓝牙
    public static int openBLEBluetooth(Context context, String mac) {
        int devHandle = BasicOper.dc_open("BLE", context, mac, 0);
        if (devHandle > 0) {
            Log.d(TAG, "dc_open success devHandle = " + devHandle);
        }
        return devHandle;
    }

    //关闭端口
    public static int closePort() {
        return BasicOper.dc_exit();
    }

}
