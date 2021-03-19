package com.decard.mobilesdkexample.OperaUtils;

import android.util.Log;
import android.util.TimeUtils;
import android.widget.Toast;

import com.decard.NDKMethod.BasicOper;
import com.decard.mobilesdkexample.ListenerUtils.Scan2DListener;
import com.decard.mobilesdkexample.ToolUtils.LogUtil;
import com.decard.mobilesdkexample.ToolUtils.ResultDealUtil;

/**
 * @Author: LD
 * @Description: 二维码硬扫接口调用示例
 * @other:
 * @CreateDate: 2020/9/7
 */
public class Scan2DUtil {
    private final static String TAG = "SCAN_2D_LOG";
    static boolean isScan = false;

    //二维码扫描
    public static int scan_2D(Scan2DListener scan2DListener) {
        String start_scan_2D = BasicOper.dc_Scan2DBarcodeStart(0x00);  //开启二维码扫描
        Log.d(TAG, "BasicOper.dc_Scan2DBarcodeStart;" + start_scan_2D);
        if (ResultDealUtil.isSuccess(start_scan_2D)) {
            isScan = true;
        }
        while (isScan) {
            String scan_2D_info = BasicOper.dc_Scan2DBarcodeGetData();  //获取二维码扫描结果
            Log.d(TAG, "BasicOper.dc_Scan2DBarcodeGetData：" + scan_2D_info);
            if (ResultDealUtil.isSuccess(scan_2D_info)) {
                isScan = false;
                scan2DListener.getScan2DInfo(ResultDealUtil.resultInfo(scan_2D_info));
                String scan_2D_exit = BasicOper.dc_Scan2DBarcodeExit();         //停止二维码扫描
                Log.d(TAG, "BasicOper.dc_Scan2DBarcodeExit：" + scan_2D_exit);
                return 0;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    //停止扫码
    public static void stop_2D() {
        isScan = false;
        BasicOper.dc_Scan2DBarcodeExit();
    }
}
