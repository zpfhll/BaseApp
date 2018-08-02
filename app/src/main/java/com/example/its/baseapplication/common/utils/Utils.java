package com.example.its.baseapplication.common.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;

import com.example.its.baseapplication.BuildConfig;

import java.io.DataOutputStream;

public class Utils {

    /**
     * USBデバッグ有効
     */
    private static final int ENABLE = 1;
    /**
     * USBデバッグ無効
     */
    private static final int DISABLE = 0;

    /**
     * USBデバッグの有無判定
     *
     * @param contentResolver
     *            ContentResolver
     * @return true:USBデバッグ無効; false:USBデバッグ有効
     */
    public static boolean isADBEnabled(ContentResolver contentResolver) {
        boolean result = true;
        try {
            int adbState = Settings.Global.getInt(contentResolver,
                        Settings.Global.ADB_ENABLED);
            switch (adbState) {
                case ENABLE:
                    break;
                case DISABLE:
                    result = false;
                    break;
                default:
                    break;
            }
        } catch (Settings.SettingNotFoundException e) {
        }
        if(BuildConfig.CHECK_USB){
            return result;
        }else{
            return false;
        }
    }

    /**
     * root化のチェック
     *
     * @return　true:root化した　false:root化しない
     */
    public synchronized static boolean getRootAhth() {
        Process process = null;
        DataOutputStream os = null;
        if (!BuildConfig.CHECK_ROOT) {
            return false;
        }

        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            int exitValue = process.waitFor();
            if (exitValue == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
            }
        }
    }
}
