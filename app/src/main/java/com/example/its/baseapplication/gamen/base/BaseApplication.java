package com.example.its.baseapplication.gamen.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.its.baseapplication.BuildConfig;
import com.example.its.baseapplication.common.database.DaoMaster;
import com.example.its.baseapplication.common.database.DaoSession;

public class BaseApplication extends Application {

    private static DaoSession daoSession;
    private static DaoMaster sDaoMaster;
    private static final String DB_NAME = "sqlite.db";

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.CHECK_USB) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init( this );
    }

    public void setupDatabase(String password) {
        if (daoSession == null) {
            if (sDaoMaster == null) {
                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), DB_NAME, null);
                sDaoMaster = new DaoMaster(helper.getEncryptedWritableDb(password));
            }
            daoSession = sDaoMaster.newSession();
        }
    }
}
