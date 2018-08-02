package com.example.its.baseapplication.common.utils;

import android.util.Log;

import com.example.its.baseapplication.BuildConfig;

public class TCILog {
    /**
     * ログの
     */
    private static final String TAG = "LOG-->:";
    /**
     * ログをプリントするタイプ
     */
    private enum PRINT_TYPE{
        E,
        D,
        W,
        V
    }

    /**
     * ログをプリントする
     * @param type
     * @param msg
     */
    private static void printLog(PRINT_TYPE type,String msg){
        if(BuildConfig.CHECK_USB){
            return;
        }
        switch (type){
            case E:
                Log.e(TAG,msg);
                break;
            case D:
                Log.d(TAG,msg);
                break;
            case W:
                Log.w(TAG,msg);
                break;
            case V:
                Log.v(TAG,msg);
                break;
        }
    }


    public static void e(String msg){
        printLog(PRINT_TYPE.E,msg);
    }

    public static void d(String msg){
        printLog(PRINT_TYPE.D,msg);
    }

    public static void w(String msg){
        printLog(PRINT_TYPE.W,msg);
    }

    public static void v(String msg){
        printLog(PRINT_TYPE.V,msg);
    }
}
