package com.example.its.baseapplication.gamen.base;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {

    //画面のパスを設定する箇所　　start
    public static final String  ACTIVITY_SPLASH= "/gamen/splash/SplashActivity";
    //ここから新しい画面を追加してください

    @StringDef({ACTIVITY_SPLASH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ACTIVITY_PATH{}
    //画面のパスを設定する箇所　　end




}
