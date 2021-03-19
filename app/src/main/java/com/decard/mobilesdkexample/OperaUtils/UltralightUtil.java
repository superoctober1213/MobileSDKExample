package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.ResultDealUtil;

/**
 * @Author: LD
 * @Description: Ultralight卡接口调用示例
 * @other:
 * @CreateDate: 2020/9/9
 */
public class UltralightUtil {
    private final static String TAG = "ULTRALIGHT_CARD_LOG";

    /**
     * @param mode 0x00表示对空闲卡进行操作，0x01表示对所有卡操作。
     * @return 成功：返回卡片序列号   失败：返回失败信息
     * @brief 寻卡
     */
    public static String findCard(int mode) {
        String ret = BasicOper.dc_card_n_hex(mode);
        Log.d(TAG,"BasicOper.dc_card_n_hex: "+ret);
        return ResultDealUtil.resultInfo(ret);
    }

    /**
     * @param passWord 密码，固定为16个字节。
     * @return 返回验证结果
     * @brief 验证Mifare Ultralight C卡密码。
     */
    public static boolean checkPassword(String passWord) {
        String ret = BasicOper.dc_auth_ulc_hex(passWord);
        Log.d(TAG,"BasicOper.dc_auth_ulc_hex: "+ret);
        return ResultDealUtil.isSuccess(ret);
    }

    /**
     * @param index 读取卡内数据，对于M1卡，一次读取一个块的数据，为16个字节；对于ML卡，一次读取相同属性的两页，为8个字节。
     * @return 返回读卡结果
     * @brief 同步读卡
     */
    public static String readCard(int index) {       //0-15
        return BasicOper.dc_read_hex(index);
    }

    /**
     * @param index    块号:
     *                 M1卡 - S50块地址（0~63），S70块地址（0~255）。
     *                 ML卡 - 页地址（0~11）。
     * @param valueHex 块数据 长度固定为16字节，16进制字符串形式
     * @return 返回写卡结果
     * @brief 写入数据到卡片内，对于M1卡，一次必须写入一个块的数据，为16个字节；对于ML卡，一次必须写入一个页的数据，为4个字节。
     */
    public static String writeCard(int index, String valueHex) {
        return BasicOper.dc_write_hex(index, valueHex);
    }

    public static String operaUltralight(int mode,String passWord,int index){
        String ret = BasicOper.dc_card_n_hex(mode);     //寻卡
        if(ret.split("\\|",-1)[0].equals("0000")){
            String ret1 = BasicOper.dc_auth_ulc_hex(passWord);      //校验密码
            Log.d(TAG,"BasicOper.dc_auth_ulc_hex: "+ret1);
            if(ret1.split("\\|",-1)[0].equals("0000")){
                return BasicOper.dc_read_hex(index);
            }else  return "密码校验失败！";
        }
        return "无卡";
    }
}
