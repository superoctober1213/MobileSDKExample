package com.decard.mobilesdkexample.OperaUtils;

import android.content.res.Resources;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.MainActivity;
import com.decard.mobilesdkexample.R;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;
import com.decard.mobilesdkexample.UI.CardOperaActivity;
import com.decard.print.PrinterLogic;

import java.io.UnsupportedEncodingException;

/**
 * @Author: LD
 * @Description: 打印接口示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class PrintUtil {
    private static final String TAG = "PRINT_PICTURE_LOG";
    static final PrinterLogic printerLogic = new PrinterLogic();

    /**
     * @param width  图片宽度不能大于384（最好是8的倍数）
     * @param height 图片高度
     * @param res    Resources
     * @param id     资源ID
     * @brief 打印图片
     */
    private int printPicture(int width, int height, Resources res, int id) {
        /**
         * @brief 设置打印机参数
         * [in] fontSize 字体设置，0x00表示字符大小为8*16，0x01表示字符大小为12*24，0x02表示字符大小为16*32。
         * [in] alignment 对齐设置，0x00表示左对齐，0x01表示居中，0x02表示右对齐。
         * [in] leftMargin 左边距设置，字符大小为8*16时<48，字符大小为12*24时<32，字符大小为16*32时<24。
         * [in] rightMargin 右边距设置，字符大小为8*16时<48，字符大小为12*24时<32，字符大小为16*32时<24。
         * [in] rowPitch 行间隔设置，单位为点。
         * [in] printOutRate 打印速度设置（0x00~0x03），0x00最快，0x03最慢。
         * */
        String setPrint = BasicOper.dc_setprint(0x02, 0x01, 0, 0, 10, 0x00);
        Log.d("print", "BasicOper.dc_setprint:" + setPrint);
        //进纸 进纸到设备内用于打印  参数为进纸行数 单位为点
        String enter = BasicOper.dc_printenter(50);
        Log.d("print", "BasicOper.dc_printenter:" + enter);
        int temp = printerLogic.PrintLineByLinePicture(width, height, res, id);
        Log.d("print", "PrintLineByLinePicture:" + temp);
        return temp;
    }

    //打印文本字符信息
    private String printChar(String printString) {
        String setPrint = BasicOper.dc_setprint(0x02, 0x01, 0, 0, 10, 0x00);
        Log.d("print", "BasicOper.dc_setprint:" + setPrint);
        String enter = BasicOper.dc_printenter(50);
        Log.d("print", "BasicOper.dc_printenter:" + enter);
        String print_char = "";
        try {
            byte[] strByte = printString.getBytes("GBK");
            print_char = BasicOper.dc_printcharacter(strByte);
            Log.d("print", "BasicOper.dc_printcharacter:" + print_char);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return print_char;
    }

    /**
     * @param type 0:打印字符   1：打印一维码     2：打印图片
     */
    public static int printFactor(int type,Resources res) {
        //打印机参数设置
        String setPrint = BasicOper.dc_setprint(0x02, 0x02, 0, 0, 10, 0x00);
        Log.d("print", "BasicOper.dc_setprint:" + setPrint);
        //打印机进纸设置
        String enter = BasicOper.dc_printenter(50);
        Log.d("print", "BasicOper.dc_printenter:" + enter);
        String printState = BasicOper.dc_printstatus();//获取打印机状态
        Log.d(TAG, "打印机状态: " + printState);
        switch (type) {
            case 0:
                String printString = "西安德卡科技！";
                try {
                    byte[] strByte = printString.getBytes("GBK");
                    //打印字符
                    String print_char = BasicOper.dc_printcharacter(strByte);
                    Log.d("print", "BasicOper.dc_printcharacter:" + print_char);
                    if(print_char.split("\\|",-1)[0].equals("0000")){
                        return 0;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                //一维码内容
                String strTemp = "15901234618";
                try {
                    byte[] byteTemp = strTemp.getBytes("GBK");
                    //打印一维码
                    String temp = BasicOper.dc_printOnedimensional(50, 0x01, 0x01, byteTemp);
                    Log.d("print", "PrintLineByLinePicture:" + temp);
                    if(temp.split("\\|",-1)[0].equals("0000")){
                        return 0;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                //打印图片
                int temp = printerLogic.PrintLineByLinePicture(320, 233,res, R.drawable.photo);
                Log.d("print", "PrintLineByLinePicture:" + temp);
                if(temp == 0){
                    return 0;
                }
                break;
        }
        return -1;
    }

}
