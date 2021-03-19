package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;

import java.util.Random;

/**
 * @Author: LD
 * @Description: 102卡接口示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class Card102Util {
    private final static String TAG = "CARD_102_LOG";

    /**
     * @return 成功："0000|" 表示存在102卡。 失败："0001|" 表示失败或不存在。
     * @brief 检测是否存在102卡
     */
    public static String check102Card() {
        String ret = BasicOper.dc_check_102();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "check_102: success: " + retArr[1]);
        else
            Log.d(TAG, "check_102: error code: " + ret);
        return ret;
    }

    /**
     * @return @return 成功："0000|"         失败："错误码|错误提示信息"
     * @brief 102卡下电
     */
    public static String down102Card() {
        String ret = BasicOper.dc_down_102();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "down_102: success: " + retArr[1]);
        else
            Log.d(TAG, "down_102: error code: " + ret);
        return ret;
    }

    /**
     * @param offset 偏移量
     * @param length 写入数据的长度
     * @param info   传入的数据
     * @return 成功："0000|"         失败："错误码|错误提示信息"
     * @brief 写102卡
     */
    public static String write102Card(int offset, int length, String info) {
        String ret = BasicOper.dc_write_102(offset, length, info);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "write_102: success: " + retArr[1]);
        else
            Log.d(TAG, "write_102: error code: " + ret);
        return ret;
    }

    /**
     * @param offset 偏移量
     * @param length 读取数据的长度
     * @return 成功：0000|返回的数据(HEX)"     失败："错误码|错误提示信息"
     * @brief 读102卡
     */
    public static String read102Card(int offset, int length) {
        String ret = BasicOper.dc_read_102(offset, length);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "read_102: success: " + retArr[1]);
        else
            Log.d(TAG, "read_102: error code: " + ret);
        return ret;
    }

    /**
     * @param zone     密码区
     *                 0 - 表示总密码，密码长度为2个字节。
     *                 11 - 表示一区擦除密码，密码长度为6个字节。
     *                 12 - 表示二区擦除密码，密码长度为4个字节。
     * @param passWord 密码
     * @return 成功："0000|"     失败："错误码|错误提示信息"
     * @brief 校验102卡密码
     */
    public static String checkPin102Card(short zone, String passWord) {
        String ret = BasicOper.dc_checkpass_102(zone, passWord);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "checkpass_102: success: " + retArr[1]);
        else
            Log.d(TAG, "checkpass_102: error code: " + ret);
        return ret;
    }

    /**
     * @param zone     密码区
     *                 0 - 表示总密码，密码长度为2个字节。
     *                 11 - 表示一区擦除密码，密码长度为6个字节。
     *                 12 - 表示二区擦除密码，密码长度为4个字节。
     * @param passWord 密码
     * @return 成功："0000|"     失败："错误码|错误提示信息"
     * @brief 修改102卡密码
     */
    public static String changePin102Card(short zone, String passWord) {
        String ret = BasicOper.dc_changepass_102(zone, passWord);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "changepass_102: success: " + retArr[1]);
        else
            Log.d(TAG, "changepass_102: error code: " + ret);
        return ret;
    }

    /**
     * @param zone 密码区
     *             0 - 表示总密码，密码长度为2个字节。
     * @return 成功："0000|"     失败："错误码|错误提示信息"
     * @brief 获取102卡密码
     */
    public static String readPinCount102(short zone) {
        String ret = BasicOper.dc_readcount_102(zone);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "readcount_102: success: " + retArr[1]);
        else
            Log.d(TAG, "readcount_102: error code: " + ret);
        return ret;
    }

    /**
     * @return 成功："0000|"     失败："错误码|错误提示信息"
     * @brief 对102卡进行熔丝操作
     */
    public static String dc_fuse_102() {

        String ret = BasicOper.dc_fuse_102();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "fuse_102: success: " + retArr[1]);
        else
            Log.d(TAG, "fuse_102: error code: " + ret);
        return ret;
    }


    public static String card102(short zone,String passWord,int offset,int length) {
        String result = "";
        String[] resultArr;
        result = BasicOper.dc_check_102();
        resultArr = result.split("\\|", -1);
        if (resultArr[0].equals("0000")) {
            String ret = BasicOper.dc_checkpass_102(zone, passWord);
            String retArr[] = ret.split("\\|", -1);
            if (retArr[0].equals("0000")){
                String ret1 = BasicOper.dc_read_102(offset, length);
                String retArr1[] = ret1.split("\\|", -1);
                if (retArr1[0].equals("0000")){
                    Log.d(TAG, "read_102: success: " + retArr1[1]);
                    return retArr1[1];
                }else return ret1;

            }else return ret;
        }
        return "无卡。。。";
    }


}
