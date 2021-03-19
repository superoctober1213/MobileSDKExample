package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

/**
 * @Author: LD
 * @Description: M1卡接口
 * @other:
 * @CreateDate: 2020/9/7
 */
public class M1CardUtil {
    private final static String TAG = "MA_CARD_LOG";

    /**
     * @param card_n_hex 操作类型
     *                   0x00:对空闲卡进行操作  0x01对所有卡进行操作
     * @param model      密码类型
     *                   0x00 - 表示用设备内部装载的第0套A密码来验证当前选取卡片的A密码。
     *                   0x01 - 表示用设备内部装载的第1套A密码来验证当前选取卡片的A密码。
     *                   0x02 - 表示用设备内部装载的第2套A密码来验证当前选取卡片的A密码。
     *                   0x04 - 表示用设备内部装载的第0套B密码来验证当前选取卡片的B密码。
     *                   0x05 - 表示用设备内部装载的第1套B密码来验证当前选取卡片的B密码。
     *                   0x06 - 表示用设备内部装载的第2套B密码来验证当前选取卡片的B密码。
     * @param secNum     对应装载的扇区号 0-15
     * @param key        秘钥
     * @param index      读取的块地址号
     * @brief 装载秘钥
     */
    public static String load_M1_key(int card_n_hex, int model, int secNum, String key, int index) {
//        String load_key_hex = BasicOper.dc_load_key_hex(model, secNum, key).split("\\|")[0]; //装载设备密码
//        if (load_key_hex.equals("0000")) {
            Log.d(TAG, "BasicOper.dc_load_key_hex: 设备密码装载成功！");
            return read_M1_card(card_n_hex, model, secNum, index,key);
//        } else Log.d(TAG, "BasicOper.dc_load_key_hex: 设备密码装载失败！");
//        return "失败！";
    }

    /**
     * @param card_n_hex 操作类型
     *                   0x00:对空闲卡进行操作  0x01对所有卡进行操作
     * @param model      密码类型
     *                   0x00 - 表示用设备内部装载的第0套A密码来验证当前选取卡片的A密码。
     *                   0x01 - 表示用设备内部装载的第1套A密码来验证当前选取卡片的A密码。
     *                   0x02 - 表示用设备内部装载的第2套A密码来验证当前选取卡片的A密码。
     *                   0x04 - 表示用设备内部装载的第0套B密码来验证当前选取卡片的B密码。
     *                   0x05 - 表示用设备内部装载的第1套B密码来验证当前选取卡片的B密码。
     *                   0x06 - 表示用设备内部装载的第2套B密码来验证当前选取卡片的B密码。
     * @param secNum     对应验证的扇区号
     * @param index      读取的块地址号
     * @brief 读取M1卡信息
     */
    private static String read_M1_card(int card_n_hex, int model, int secNum, int index,String key) {
        String config_card = BasicOper.dc_config_card(0).split("\\|")[0];
        if (config_card.equals("0000")) {
            long startM1Time = System.currentTimeMillis();
            String temp_card_n_hex_str = BasicOper.dc_card_n_hex(card_n_hex);      //寻卡、防冲突、选卡    param：0x00:对空闲卡进行操作  0x01对所有卡进行操作
            Log.d(TAG, "BasicOper.dc_card_n_hex_str: 寻卡状态！" + temp_card_n_hex_str);
            String temp_card_n_hex = temp_card_n_hex_str.split("\\|")[0];
            Log.d(TAG, "BasicOper.dc_card_n_hex: 寻卡状态！" + temp_card_n_hex);
            if (temp_card_n_hex.equals("0000")) {
                long startAuth = System.currentTimeMillis();
                String authentication_pass_str = BasicOper.dc_authentication_pass(model, secNum,key);//BasicOper.dc_authentication(model, secNum);
                Log.e(TAG, "read_M1_card authentication_pass time: " + (System.currentTimeMillis() - startAuth));
                String authentication_pass = authentication_pass_str.split("\\|")[0];
                Log.d(TAG, "BasicOper.dc_authentication: 秘钥验证返回值！" + authentication_pass);
                if (authentication_pass.equals("0000")) {
                    Log.d(TAG, "BasicOper.dc_authentication_pass: 密钥验证成功！");
                    String card_info = BasicOper.dc_read_hex(index);                                //读取数据  param: index:块号
                    Log.d(TAG, "BasicOper.dc_read_hex：" + card_info);
                    Log.e(TAG, "read_M1_card time: " + (System.currentTimeMillis() - startM1Time));
                    return card_info;
                }
            }
        }
        return "失败！";
    }

    /**
     * @param card_n_hex 操作类型
     *                   0x00:对空闲卡进行操作  0x01对所有卡进行操作
     * @param model      密码类型
     *                   0x00 - 表示用设备内部装载的第0套A密码来验证当前选取卡片的A密码。
     *                   0x01 - 表示用设备内部装载的第1套A密码来验证当前选取卡片的A密码。
     *                   0x02 - 表示用设备内部装载的第2套A密码来验证当前选取卡片的A密码。
     *                   0x04 - 表示用设备内部装载的第0套B密码来验证当前选取卡片的B密码。
     *                   0x05 - 表示用设备内部装载的第1套B密码来验证当前选取卡片的B密码。
     *                   0x06 - 表示用设备内部装载的第2套B密码来验证当前选取卡片的B密码。
     * @param secNum     对应验证的扇区号
     * @param index      写入的块地址号
     * @brief M1卡信息写入
     */
    public static void write_M1_card(int card_n_hex, int model, int secNum, int index) {
        String temp_card_n_hex = BasicOper.dc_card_n_hex(card_n_hex).split("\\|")[0];      //寻卡、防冲突、选卡    param：0x00:对空闲卡进行操作  0x01对所有卡进行操作
        if (temp_card_n_hex.equals("0000")) {
            Log.d(TAG, "BasicOper.dc_card_n_hex: 寻卡成功！");
            String authentication_pass = BasicOper.dc_authentication_pass(model, secNum, "FFFFFFFFFFFF").split("\\|")[0];
            if (authentication_pass.equals("0000")) {
                Log.d(TAG, "BasicOper.dc_authentication_pass: 密钥验证成功！");
                String card_info = BasicOper.dc_write_hex(index, "00000000000000000000000000000001");
                Log.d(TAG, "BasicOper.dc_write_hex：" + card_info);
            }
        }
    }
}
