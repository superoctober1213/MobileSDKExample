package com.decard.mobilesdkexample.ToolUtils;

public class ResultDealUtil {
    private final static String TAG = "RESULT_DEAL_LOG";

    private static String[] strSplit(String str) {
        return str.split("\\|", -1);
    }

    public static boolean isSuccess(String str) {
        String ret[] = strSplit(str);
        if (ret[0].equals("0000")) {
            return true;
        }
        return false;
    }

    public static String resultInfo(String str) {
        String ret[] = strSplit(str);
        return isSuccess(str) ? ret[1] : str;
    }
}
