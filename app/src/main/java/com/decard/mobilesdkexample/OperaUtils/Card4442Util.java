package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;

import java.util.Random;

/**
 * @Author: LD
 * @Description: 4442卡接口示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class Card4442Util {
    private final static String TAG = "4442_CARD_LOG";

    //@return  成功："0000|" 表示存在4442卡。 失败："0001|" 表示失败或不存在。
    public static String check4442Card() {
        return BasicOper.dc_Check_4442();
    }

    /**
     * @param pin pin密码     固定为三个字节
     * @return 成功："0000|"     失败："错误码|错误提示信息"
     * @brief 验证4442卡密码
     */
    public static String verifyPIN_4442(String pin) {
        String ret = BasicOper.dc_verifypin_4442_hex(pin);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "verifyPIN_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "verifyPIN_4442: error code: " + ret);
        return ret;
    }

    /**
     * @return 成功："0000|密码(HEX)，固定为3个字节"      失败："错误码|错误提示信息"
     * @brief 读取4442密码
     */
    public static String readPin_4442_hex() {
        String ret = BasicOper.dc_readpin_4442_hex();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "readPin_4442_hex: success: " + retArr[1]);
        else
            Log.d(TAG, "readPin_4442_hex: error code: " + ret);
        return ret;
    }

    /**
     * @return 成功："0000|密码(HEX)，固定为2个字节"       失败："错误码|错误提示信息"
     * @brief 读取4442卡的密码计数，此计数值表示可以尝试验证密码的次数。
     */
    public static String readPinCount() {
        String ret = BasicOper.dc_readpincount_4442();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "readpincount_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "readpincount_4442: error code: " + ret);
        return ret;
    }

    /**
     * @param passWord 密码（HEX），固定为3个字节
     * @return 成功：成功："0000|"        失败："错误码|错误提示信息"
     * @brief 修改4442卡的密码
     */
    public static String changePin(String passWord) {
        String ret = BasicOper.dc_changepin_4442_hex(passWord);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "changepin_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "changepin_4442: error code: " + ret);
        return ret;
    }

    /**
     * @param offset 偏移量
     * @param length 读取数据长度
     * @return 成功："0000|数据(HEX)" —返回的数据，数据中含有0x00字节的位置表示已经被置保护         失败："错误码|错误提示信息"
     * @brief 读4442卡保护位
     */
    public static String readProtect(int offset, int length) {
        String ret = BasicOper.dc_readprotect_4442_hex(offset, length);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "readprotect_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "readprotect_4442: error code: " + ret);
        return ret;
    }

    /**
     * @param offset  偏移量
     * @param length  写入数据的长度
     * @param dataHex 写入数据（HEX）—数据中和卡内原有数据相同的字节位置将被置保护
     * @return 成功："0000|"      失败："错误码|错误提示信息"
     * @brief 写4442卡保护位
     */
    public static String writeProtect(int offset, int length, String dataHex) {
        String ret = BasicOper.dc_writeprotect_4442_hex(offset, length, dataHex);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "writeprotect_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "writeprotect_4442: error code: " + ret);
        return ret;
    }

    /**
     * @param offset 偏移量
     * @param length 读取数据的长度
     * @return 成功："0000|"     失败："错误码|错误提示信息"
     * @brief 读4442卡数据
     */
    public static String read4442Data(int offset, int length) {
        String ret = BasicOper.dc_read_4442_hex(offset, length);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "read_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "read_4442: error code: " + ret);
        return ret;
    }

    /**
     * @param offset  偏移量
     * @param length  写入数据的长度
     * @param dataHex 写入的数据（HEX）
     * @return 成功："0000|"     失败："错误码|错误提示信息"
     * @brief 写入数据到4442卡中
     */
    public static String write4442Data(int offset, int length, String dataHex) {
        String ret = BasicOper.dc_write_4442_hex(offset, length, dataHex);
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "write_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "write_4442: error code: " + ret);
        return ret;
    }

    /**
     * @return 成功："0000|"         失败："错误码|错误提示信息"
     * @brief 对4442卡进行下电操作
     */
    public static String down4442() {
        String ret = BasicOper.dc_down_4442();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000"))
            Log.d(TAG, "down_4442: success: " + retArr[1]);
        else
            Log.d(TAG, "down_4442: error code: " + ret);
        return BasicOper.dc_down_4442();
    }


    public static String card4442() {
        String result = "";
        String[] resultArr;
        result = BasicOper.dc_Check_4442();
        resultArr = result.split("\\|", -1);
        if (!resultArr[0].equals("0000")) {
            return result;
        }

        result = BasicOper.dc_read_4442_hex(6, 2);
        resultArr = result.split("\\|", -1);
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        String str1 = resultArr[1].substring(2, 4);
        if (!str1.equals("15")) {
            return result;
        }
        result = BasicOper.dc_verifypin_4442_hex("B62307");
        resultArr = result.split("\\|", -1);
        if (!resultArr[0].equals("0000")) {
            return result;
        }

        String count = "";
        result = BasicOper.dc_readpincount_4442();
        resultArr = result.split("\\|", -1);
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        count = resultArr[1];
        if (Integer.parseInt(count) < 2) {
            return result;
        }
        result = BasicOper.dc_read_4442_hex(0, 20);
        resultArr = result.split("\\|", -1);
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        String writeData = rndString(100);
        result = BasicOper.dc_write_4442_hex(33, 50, writeData);
        resultArr = result.split("\\|", -1);
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        result = BasicOper.dc_read_4442_hex(33, 50);
        resultArr = result.split("\\|", -1);
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        if (!resultArr[1].equals(writeData)) {
            return result;
        }
        result = BasicOper.dc_down_4442();
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        result = BasicOper.dc_write_4442_hex(32, 2, "8899");
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        result = BasicOper.dc_read_4442_hex(32, 2);
        if (!resultArr[0].equals("0000")) {
            return result;
        }
        if (!resultArr[1].equals("8899")) {
            return result;
        }
        return result;
    }

    private static String rndString(int num) {
        String str = "";
        String[] strtemp = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        for (int i = 0; i < num; i++) {
            Random ra = new Random();
            int tempi = ra.nextInt(15);
            str = str + strtemp[tempi];
        }
        return str;
    }
}