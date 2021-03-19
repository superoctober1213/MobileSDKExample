package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;

import com.decard.NDKMethod.BasicOper;
import com.decard.entitys.IDCard;
import com.decard.mobilesdkexample.ToolUtils.DataDealUtil;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;

/**
 * @Author: LD
 * @Description: 身份证接口
 * @other:
 * @CreateDate: 2020/9/7
 */
public class IDCardUtil {
    private final static String TAG = "ID_CARD_LOG";
    private static int photoLen = 1024;
    private static byte[] photo_len = DataDealUtil.intToByteArray(photoLen);
    private static byte[] photo = new byte[1024];
    private static int textLen = 256;
    private static byte[] text_len = DataDealUtil.intToByteArray(textLen);
    private static byte[] text = new byte[256];

    private static int fingerprintLen = 1024;
    private static byte[] fingerprint_len = DataDealUtil.intToByteArray(fingerprintLen);
    private static byte[] fingerprint = new byte[1024];
    private static int extraLen = 70;
    private static byte[] extra_len = DataDealUtil.intToByteArray(extraLen);
    private static byte[] extra = new byte[70];

    /**
     * @param type 类型参数
     * @return 成功：返回IDCard对象
     * @n 1 - 读取文字信息、相片信息和指纹信息。
     * @n 2 - 读取追加住址信息。
     * @n 3 - 读取文字信息、相片信息、指纹信息和追加住址信息。
     * @brief 读取身份证信息  公安部协议
     */
    public static IDCard getIdCard(int type) {
        IDCard idCard = BasicOper.dc_SamAReadCardInfo(type);
        return idCard;
    }

    /**
     * @return 成功 返回IDCard对象
     * @brief 读取身份证信息  标准协议
     */
    public static IDCard dc_get_i_d_raw_info() {
        IDCard idCard = BasicOper.dc_get_i_d_raw_info();
        return idCard;
    }

    public static void getIdInfo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(BasicOper.dc_getuid_i_d().startsWith("0000")){
                        IDCard idCard = BasicOper.dc_get_i_d_raw_info();
                        Log.d("idCardInfo", "身份证: "+idCard.toString());
                        return;
                    }
                }
            }
        }).start();
    }

    /**
     * @param type 类型参数
     * @return 成功 返回IDCard对象
     * @n 7 - 读取文字信息、相片信息和指纹信息。
     * @brief 读取身份证信息  直连协议  Z90专用
     */
    public static IDCard dc_IdCardReadCardInfo(int type) {
        IDCard idCard = BasicOper.dc_IdCardReadCardInfo(type);
        return idCard;
    }

    /************************************** 以下接口是针对身份证原始数据操作的接口 ************************************************/

    /**
     * @param type
     * @return 成功返回0   其他均失败
     * @brief 获取身份证原始数据
     * @n 1 - 读取文字信息、相片信息和指纹信息。
     * @n 2 - 读取追加住址信息。
     * @n 3 - 读取文字信息、相片信息、指纹信息和追加住址信息。
     */
    public static short getSamAReadCardInfoRaw(int type) {
        /**
         * @brief 读身份证。
         * @par 说明：
         * 读取身份证的原始信息数据。
         * @param[in] type 类型。
         * @n 1 - 读取文字信息、相片信息和指纹信息。
         * @n 2 - 读取追加住址信息。
         * @n 3 - 读取文字信息、相片信息、指纹信息和追加住址信息。
         * @param text_len 返回文字信息的长度。
         * @param text 返回的文字信息，请至少分配256个字节。
         * @param photo_len 返回相片信息的长度。
         * @param photo 返回的相片信息，请至少分配1024个字节。
         * @param fingerprint_len 返回指纹信息的长度。
         * @param fingerprint 返回的指纹信息，请至少分配1024个字节。
         * @param extra_len 返回追加住址信息的长度。
         * @param extra 返回的追加住址信息，请至少分配70个字节。
         * @return <0表示失败，==0表示成功。
         */
        short res = BasicOper.dc_SamAReadCardInfo(type, text_len, text, photo_len, photo, fingerprint_len, fingerprint, extra_len, extra);
        return res;
    }

    /**
     * @return 返回身份证文字信息字符串
     * @brief 解析文字信息
     */
    public static String dc_IdCardReadCardInfoString() {
        String result = "";

        textLen = DataDealUtil.byteArrayToInt(text_len);
        //中华人民共和国居民身份证文字信息
        byte[] name = new byte[30];
        byte[] sex = new byte[8];
        byte[] nation = new byte[64];
        byte[] birthday = new byte[16];
        byte[] address = new byte[144];
        byte[] id = new byte[36];
        byte[] department = new byte[30];
        byte[] startTime = new byte[16];
        byte[] endTime = new byte[16];
        byte[] reserved = new byte[70];

        //外国人永久居留证文字信息
        byte[] Foreign_name = new byte[244];
        byte[] Foreign_sex = new byte[8];
        byte[] Foreign_number = new byte[64];
        byte[] Foreign_citizenship = new byte[16];
        byte[] Foreign_chinese_name = new byte[64];
        byte[] Foreign_expire_start_day = new byte[36];
        byte[] Foreign_expire_end_day = new byte[36];
        byte[] Foreign_birth_day = new byte[36];
        byte[] Foreign_version_number = new byte[12];
        byte[] Foreign_department_code = new byte[20];
        byte[] Foreign_type_sign = new byte[8];
        byte[] Foreign_reserved = new byte[16];

        //港澳台居民居住证文字信息
        byte[] Hongkong_macao_taiwan_name = new byte[64];
        byte[] Hongkong_macao_taiwan_sex = new byte[8];
        byte[] Hongkong_macao_taiwan_reserved = new byte[12];
        byte[] Hongkong_macao_taiwan_birth_day = new byte[36];
        byte[] Hongkong_macao_taiwan_address = new byte[144];
        byte[] Hongkong_macao_taiwan_number = new byte[76];
        byte[] Hongkong_macao_taiwan_department = new byte[64];
        byte[] Hongkong_macao_taiwan_expire_start_day = new byte[36];
        byte[] Hongkong_macao_taiwan_expire_end_day = new byte[36];
        byte[] Hongkong_macao_taiwan_pass_number = new byte[40];
        byte[] Hongkong_macao_taiwan_sign_count = new byte[12];
        byte[] Hongkong_macao_taiwan_reserved2 = new byte[16];
        byte[] Hongkong_macao_taiwan_type_sign = new byte[8];
        byte[] Hongkong_macao_taiwan_reserved3 = new byte[16];

        if ((text[248] == 'I') && (text[249] == 0)) {       //外国人永久居留证
            short res = BasicOper.dc_ParseTextInfoForForeigner(2, textLen, text, Foreign_name, Foreign_sex, Foreign_number, Foreign_citizenship, Foreign_chinese_name, Foreign_expire_start_day, Foreign_expire_end_day, Foreign_birth_day, Foreign_version_number, Foreign_department_code, Foreign_type_sign, Foreign_reserved);
            Log.d(TAG, "Read info dc_ParseTextInfoForForeigner: " + res);
            if (res == 0) {
                String foreign_name = new String(Foreign_name).trim() + "\\|";
                String foreign_sex = dc_ParseOtherInfo(0x20, Foreign_sex).split("\\|")[1] + "|";
                String foreign_number = new String(Foreign_number).trim() + "|";
                String foreign_citizenship = dc_ParseOtherInfo(0x22, Foreign_citizenship).split("\\|")[1] + "|";
                String foreign_chinese_name = new String(Foreign_chinese_name).trim() + "|";
                String foreign_expire_start_day = new String(Foreign_expire_start_day).trim() + "|";
                String foreign_expire_end_day = new String(Foreign_expire_end_day).trim() + "|";
                String foreign_birth_day = new String(Foreign_birth_day).trim() + "|";
                String foreign_version_number = new String(Foreign_version_number).trim() + "|";
                String foreign_department_code = new String(Foreign_department_code).trim() + "|";
                String foreign_type_sign = new String(Foreign_type_sign).trim() + "|";
                String foreign_reserved = new String(Foreign_reserved).trim();

                result = "0000|1|" + foreign_name + foreign_sex + foreign_number + foreign_citizenship + foreign_chinese_name + foreign_expire_start_day + foreign_expire_end_day + foreign_birth_day + foreign_version_number + foreign_department_code + foreign_type_sign + foreign_reserved;

                Log.d(TAG, "Read info dc_ParseTextInfoForForeigner Result: " + result);
                return result;
            } else {
                return "0001|解析失败";
            }
        } else if ((text[248] == 'J') && (text[249] == 0)) {    //港澳台居民居住证
            short res = BasicOper.dc_ParseTextInfoForHkMoTw(2, textLen, text, Hongkong_macao_taiwan_name, Hongkong_macao_taiwan_sex, Hongkong_macao_taiwan_reserved, Hongkong_macao_taiwan_birth_day, Hongkong_macao_taiwan_address, Hongkong_macao_taiwan_number, Hongkong_macao_taiwan_department, Hongkong_macao_taiwan_expire_start_day, Hongkong_macao_taiwan_expire_end_day, Hongkong_macao_taiwan_pass_number, Hongkong_macao_taiwan_sign_count, Hongkong_macao_taiwan_reserved2, Hongkong_macao_taiwan_type_sign, Hongkong_macao_taiwan_reserved3);
            Log.d(TAG, "Read info dc_ParseTextInfoForHkMoTw: " + res);
            if (res == 0) {
                String HMT_name = new String(Hongkong_macao_taiwan_name).trim() + "|";
                String HMT_sex = dc_ParseOtherInfo(0x20, Hongkong_macao_taiwan_sex).split("\\|")[1].trim() + "|";
                String HMT_reserved = new String(Hongkong_macao_taiwan_reserved).trim() + "|";
                String HMT_birth_day = new String(Hongkong_macao_taiwan_birth_day).trim() + "|";
                String HMT_address = new String(Hongkong_macao_taiwan_address).trim() + "|";
                String HMT_number = new String(Hongkong_macao_taiwan_number).trim() + "|";
                String HMT_department = new String(Hongkong_macao_taiwan_department).trim() + "|";
                String HMT_expire_start_day = new String(Hongkong_macao_taiwan_expire_start_day).trim() + "|";
                String HMT_expire_end_day = new String(Hongkong_macao_taiwan_expire_end_day).trim() + "|";
                String HMT_pass_number = new String(Hongkong_macao_taiwan_pass_number).trim() + "|";
                String HMT_sign_count = new String(Hongkong_macao_taiwan_sign_count).trim() + "|";
                String HMT_reserved2 = new String(Hongkong_macao_taiwan_reserved2).trim() + "|";
                String HMT_type_sign = new String(Hongkong_macao_taiwan_type_sign).trim() + "|";
                String HMT_reserved3 = new String(Hongkong_macao_taiwan_reserved3).trim();
                result = "0000|2|" + HMT_name + HMT_sex + HMT_reserved + HMT_birth_day + HMT_address + HMT_number + HMT_department + HMT_expire_start_day + HMT_expire_end_day + HMT_pass_number + HMT_sign_count + HMT_reserved2 + HMT_type_sign + HMT_reserved3;
                Log.d(TAG, "Read info dc_ParseTextInfoForHkMoTw Result: " + result);
                return result;
            } else {
                return "0001|解析失败";
            }
        } else {            //中华人民共和国居民身份证
            short res = BasicOper.dc_ParseTextInfo(2, textLen, text, name, sex, nation, birthday, address, id, department, startTime, endTime, reserved);
            Log.d(TAG, "Read info dc_ParseTextInfo: " + res);
            if (res == 0) {
                String Name = new String(name).trim() + "|";
                String Sex = dc_ParseOtherInfo(0x20, sex).split("\\|")[1] + "|";
                Log.d(TAG, "#####" + Sex);
                String Nation = dc_ParseOtherInfo(0x21, nation).split("\\|")[1] + "|";
                Log.d(TAG, "#####" + Nation);
                String Birthday = new String(birthday).trim() + "|";
                String Address = new String(address).trim() + "|";
                String Id = new String(id).trim() + "|";
                String Department = new String(department).trim() + "|";
                String Start_time = new String(startTime).trim() + "|";
                String End_time = new String(endTime).trim() + "|";
                String Reserved = new String(reserved).trim();
                result = "0000|0|" + Name + Sex + Nation + Birthday + Address + Id + Department + Start_time + End_time + Reserved;
                Log.d(TAG, "Read info dc_ParseTextInfo Result: " + result);
                return result;
            } else {
                return "0001|解析失败";
            }
        }
    }

    /**
     * @param type 0x20:性别
     *             0x21：民族
     *             0x22：国籍
     * @param info 对应数据
     */
    private static String dc_ParseOtherInfo(int type, byte[] info) {
        byte[] out_info = new byte[16];
        String result = "";
        short res = BasicOper.dc_ParseOtherInfo(type, info, out_info);
        if (res == 0) {
            result = new String(out_info).trim();
            Log.d(TAG, "dc_ParseOtherInfo: " + res + "result: " + result);
            if (result != "") {
                return "0000|" + result;
            } else {
                return "0001|解析值为空";
            }
        } else {
            return "0001|解析失败";
        }
    }

    /**
     * @return photoData 解析后的照片信息
     * @brief 解析图片数据
     */
    public static byte[] getParsePhotoInfo() {
        int photoLen = DataDealUtil.byteArrayToInt(photo_len);
        int PhotoLen1 = 65536;
        byte[] Photo_len1 = DataDealUtil.intToByteArray(PhotoLen1);
        byte[] photoData = new byte[65536];
        short ret = BasicOper.dc_ParsePhotoInfo(1, photoLen, photo, Photo_len1, photoData);
        if (ret == 0L) {
            return photoData;
        }
        return "图片信息解析失败！".getBytes();
    }

    /**
     * @param path 可读写的文件路径
     * @return 0:成功     -1：解析失败    -2：照片数据为空
     * @brief 解析照片信息并保存到指定目录
     * @other 此接口保存文件时需要读写权限
     */
    public static int dc_ParsePhotoInfo(String path) {     //"/mnt/sdcard/dc_photo.bmp"

        if (DataDealUtil.byteArrayToInt(photo) == 0) {
            return -2;                                      //Please read the ID first
        }
        photoLen = DataDealUtil.byteArrayToInt(photo_len);
        byte[] pl = new byte[1];
        short res = BasicOper.dc_ParsePhotoInfo(0, photoLen, photo, pl, path.getBytes());
        Log.d("BasicOperYH", "storage dc_ParsePhotoInfo: " + res);
        if (res == 0) {
            return 0;                                       //success
        }
        return -1;                                          //failure
    }
}
