package com.decard.mobilesdkexample.DevicesOperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

/**
 * @Author: LD
 * @Description: 电动读卡器接口示例
 * @other:
 * @CreateDate: 2020/9/8
 */
public class SelfServiceDevice {
    private final static String TAG = "SELF_SERVICE_DEVICE_LOG";

    /**
     * @return 成功："0000|位置状态"   失败："错误码|错误提示信息
     * 00 - 无卡。
     * 01 - 无卡，卡在前门口，处夹卡位置。
     * 02 - 无卡，卡在前门口，处不夹卡位置。
     * 10 - 有卡，不可操作任何卡。
     * 11 - 有卡，可操作磁条。
     * 12 - 有卡，可操作接触。
     * 14 - 有卡，可操作非接触。
     * @brief 检测卡位置状态。
     */
    public static String cardPosition() {
        String ret = BasicOper.dc_SelfServiceDeviceCardStatus();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000")) {
            switch (retArr[1]) {
                case "00":
                    return "无卡";
                case "01":
                    return "无卡，卡在前门口处夹卡位置";
                case "02":
                    return "无卡，卡在前门口处不夹卡位置";
                case "10":
                    return "有卡，不可操作任何卡";
                case "11":
                    return "有卡，可操作磁条";
                case "12":
                    return "有卡，可操作接触";
                case "14":
                    return "有卡，可操作非接触";
            }
        }
        return ret;
    }

    /**
     * @return 成功："0000|结果"
     * 失败："错误码|错误提示信息"
     * 0000 - 表示无法检测到相应卡片。
     * 0001 - 表示设备内无卡。
     * 0011 - 表示Type A CPU Card。
     * 0013 - 表示Type A Mifare S50。
     * 0014 - 表示Type A Mifare S70。
     * 0015 - 表示Type A Mifare Ultralight。
     * 0021 - 表示Type B CPU Card。
     * 0022 - 表示Type B 存储卡。
     * 0031 - 表示接触T=0 CPU Card。
     * 0032 - 表示接触T=1 CPU Card。
     * 0041 - 表示4442 Card。
     * 0042 - 表示4428 Card。
     * @brief 检测卡类型   设备将自动移动卡片并且检测卡片类型。
     */
    public static String checkCardType() {
        String ret = BasicOper.dc_SelfServiceDeviceCheckCardType();
        String retArr[] = ret.split("\\|", -1);
        if (retArr[0].equals("0000")) {
            switch (retArr[1]) {
                case "0000":
                    return "无法检测到相应卡片";
                case "0001":
                    return "设备内无卡";
                case "0011":
                    return "Type A CPU Card";
                case "0013":
                    return "Type A Mifare S50";
                case "0014":
                    return "Type A Mifare S70";
                case "0015":
                    return "Type A Mifare Ultralight";
                case "0021":
                    return "Type B CPU Card";
                case "0022":
                    return "Type B 存储卡";
                case "0031":
                    return "接触T=0 CPU Card";
                case "0032":
                    return "接触T=1 CPU Card";
                case "0041":
                    return "4442 Card";
                case "0042":
                    return "4428 Card";
            }
        }
        return ret;
    }

    /**
     * @brief 设置弹卡模式
     * 0 - 弹到前端并夹卡。
     * 1 - 弹道后端。
     * 2 - 弹到前端不夹卡。
     * 成功："0000|"
     * 失败："错误码|错误提示信息"
     */
    public static String ejectCardConfig(int mode) {
        return BasicOper.dc_SelfServiceDeviceConfig(mode);
    }

    /**
     * @param mode 模式。
     *             0 - 后端禁止进卡。
     *             1 - 后端磁卡方式进卡。
     *             2 - 后端非磁卡方式进卡。
     * @return 成功："0000|"
     * 失败："错误码|错误提示信息"
     * @brief 设置后端进卡模式。
     */
    public static String enterBackConfig(int mode) {
        return BasicOper.dc_SelfServiceDeviceConfigBack(mode);
    }

    /**
     * @param mode 模式。
     *             0 - 前端禁止进卡。
     *             1 - 前端磁卡方式进卡。
     *             2 - 前端开关方式进卡。
     *             3 - 前端磁信号方式进卡。
     * @return 成功："0000|"
     * 失败："错误码|错误提示信息"
     * @brief 设置前端进卡模式。
     */
    public static String enterFrontConfig(int mode) {
        return BasicOper.dc_SelfServiceDeviceConfigFront(mode);
    }

    /**
     * @param mode 模式。
     *             0 - 前端不夹卡。
     *             1 - 前端夹卡。
     *             2 - 接触式IC卡位置。
     *             3 - 射频卡位置。
     *             4 - 磁卡位置。
     *             5 - 弹卡到后端。
     * @return 成功："0000|"
     * 失败："错误码|错误提示信息"
     * @brief 设置停卡位置
     */
    public static String stopCardPosition(int mode) {
        return BasicOper.dc_SelfServiceDeviceConfigPlace(mode);
    }

    /**
     * @return 成功："0000|结果"
     * 失败："错误码|错误提示信息"
     * @brief 复位自助设备 使自助设备进入上电初始状态，并且设置参数为缺省参数。
     */
    public static String resetDevice() {
        return BasicOper.dc_SelfServiceDeviceReset();
    }

    /**
     * @return 成功："0000|结果"     失败："错误码|错误提示信息"
     * 0001 表示掉电无法使用。
     * bit0 - 电闸门开关传感器，0表示打开，1表示关闭。
     * bit1 - 压卡传感器，0表示卡已被下压，1表示无卡。
     * bit2~bit7 - 分别表示从前端到后端的传感器，每个传感器值0表示有卡，值1表示无卡。
     * @brief 获取设备传感器的状态。
     */
    public static String getSensorStatus() {
        return BasicOper.dc_SelfServiceDeviceSensorStatus();
    }

    /**
     * @birfe 弹出卡片
     * mode 模式。
     * 0 - 弹到前端并夹卡。
     * 1 - 弹道后端。
     * 2 - 弹到前端不夹卡
     */
    public static String CardEject(int outTime, int mode) {
        String ret = BasicOper.dc_SelfServiceDeviceCardEject(outTime, mode);
        Log.d(TAG, "BasicOper.dc_SelfServiceDeviceCardEject: " + ret);
        return ret;
    }

    /**
     * @param outTime 设备超时值，单位为秒。
     * @param mode    模式。
     *                0 - 前端进卡，不带磁条。
     *                1 - 前端进卡，带磁条。
     *                2 - 后端进卡，不带磁条。
     *                3 - 后端进卡，带磁条。
     * @return 成功："0000|结果"
     * 失败："错误码|错误提示信息"
     * 0001 - 设备内已有卡。
     * 0002 - 接收超时。
     * 0003 - 读磁卡错误。
     * 0004 - 参数设置出错。
     * 0005 - 异常卡正常弹出。
     * 0006 - 异常卡卡在设备内。
     * @brief 进入卡片
     */
    public static String enterCard(int outTime, int mode) {
        return BasicOper.dc_SelfServiceDeviceCardInject(outTime, mode);
    }

    /**
     * @param outTime 设备超时值，单位为秒。
     * @param mode    模式。
     *                0 - 移动到磁条卡操作位置。
     *                1 - 移动到接触卡操作位置。
     *                2 - 移动到非接触卡操作位置。
     * @return 成功："0000|结果"
     * 失败："错误码|错误提示信息"
     * 0001 - 设备内没有卡。
     * 0002 - 接收超时。
     * 0003 - 参数设置出错。
     * 0004 - 卡片已到前门不夹卡位置，不可操作卡片。
     * @brief 移动卡片
     */
    public static String moveCard(int outTime, int mode) {
        return BasicOper.dc_SelfServiceDeviceCardMove(outTime, mode);
    }
}