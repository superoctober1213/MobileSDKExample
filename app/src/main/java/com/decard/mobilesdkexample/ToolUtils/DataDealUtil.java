package com.decard.mobilesdkexample.ToolUtils;

/**
 * @Author: LD
 * @Description: 数据转换处理类
 * @other:
 * @CreateDate: 2020/9/7
 */
public class DataDealUtil {
    private final static String TAG = "DATA_DEAL_LOG";

    public static int byteArrayToInt(byte[] var0) {
        return var0[0] & 255 | (var0[1] & 255) << 8 | (var0[2] & 255) << 16 | (var0[3] & 255) << 24;
    }

    public static byte[] intToByteArray(int var0) {
        return new byte[]{(byte) (var0 & 255), (byte) (var0 >> 8 & 255), (byte) (var0 >> 16 & 255), (byte) (var0 >> 24 & 255)};
    }
}
