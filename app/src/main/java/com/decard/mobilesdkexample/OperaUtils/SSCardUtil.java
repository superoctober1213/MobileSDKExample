package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.EGovernment;
import com.decard.entitys.SSCard;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

/**
 * @Author: LD
 * @Description: 社保卡接口调用示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class SSCardUtil {
    private final static String TAG = "SS_CARD_UTIL";

    //获取社保卡信息
    public static String getSSCardInfo() {
        String egApp = EGovernment.EgAPP_SI_CardPowerOn(1);     //社保卡上电
        Log.d(TAG, egApp + "  EGovernment.EgAPP_SI_CardPowerOn: " + egApp.split("\\|")[0]);
        if (egApp.split("\\|")[0].equals("0000")) {           //上电成功
            Log.d(TAG, "EgAPP_SI_CardPowerOn：设备上电成功!");
            SSCard ssCard = EGovernment.EgAPP_SI_ReadSSCardInfo();      //获取社保卡信息对象
            Log.d(TAG, "EgAPP_SI_ReadSSCardInfo：" + ssCard);
            String egAppOff = EGovernment.EgAPP_SI_CardPowerOff(1);
            Log.d(TAG, "EgAPP_SI_CardPowerOff：" + egAppOff);
            String tempName = ssCard.getName();
            String name = "";
            //过滤名字中间的乱码
            for (int i = 0; i < tempName.length(); i++) {
                char c = tempName.charAt(i);
                if (Character.isSpace(c) || c == '\0') {
                    break;
                }
                System.out.println(name += String.valueOf(c));
            }
            Log.d(TAG, name + " 社保卡姓名：" + ssCard.getName());
            return ssCard.toString();
        }
        return "社保卡上电失败";
    }

    /**
     * @param pin PIN密码
     * @brief 校验PIN
     */
    public static String checkPIN(String pin) {
        String egApp = EGovernment.EgAPP_SI_CardPowerOn(1);     //社保卡上电
        Log.d(TAG, egApp + "  EGovernment.EgAPP_SI_CardPowerOn: " + egApp.split("\\|")[0]);
        if (egApp.split("\\|")[0].equals("0000")) {           //上电成功
            String verifyPIN = EGovernment.EgAPP_SI_VerifyPIN(pin);
            Log.d(TAG, "EgAPP_SI_VerifyPIN：" + verifyPIN);
            if (verifyPIN.split("\\|", -1)[0].equals("0000")) {
                return "校验成功";
            } else {
                return "校验失败";
            }
        }
        return "社保卡上电失败";
    }

    /**
     * @param oldPin 旧PIN
     * @param newPin 新PIN
     * @brief 修改PIN
     */
    public static String checkPIN(String oldPin, String newPin) {
        String egApp = EGovernment.EgAPP_SI_CardPowerOn(1);     //社保卡上电
        Log.d(TAG, egApp + "  EGovernment.EgAPP_SI_CardPowerOn: " + egApp.split("\\|")[0]);
        if (egApp.split("\\|")[0].equals("0000")) {           //上电成功
            String changePIN = EGovernment.EgAPP_SI_ChangePIN(oldPin, newPin);
            Log.d(TAG, "EgAPP_SI_ChangePIN：" + changePIN);
            if (changePIN.split("\\|", -1)[0].equals("0000")) {
                return "PNI修改成功";
            } else {
                return "PNI修改失败";
            }
        }
        return "社保卡上电失败";
    }
}
