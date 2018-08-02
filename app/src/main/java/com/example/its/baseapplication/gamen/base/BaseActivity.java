package com.example.its.baseapplication.gamen.base;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class BaseActivity extends FragmentActivity {

    protected  boolean isRegistEventBus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isRegistEventBus){
            EventBus.getDefault().register(this);
        }
    }

    protected void baseStartActivity(@Constants.ACTIVITY_PATH  String path){
        ARouter.getInstance().build(path).navigation();
    }

    protected void baseStartActivity(Uri uri){
        ARouter.getInstance().build(uri).navigation();
    }

    protected void baseStartActivity(@Constants.ACTIVITY_PATH  String path,int enterAnim, int exitAnim){
        ARouter.getInstance().build(path).withTransition(enterAnim,exitAnim).navigation();
    }

    protected void baseStartActivity(@Constants.ACTIVITY_PATH  String path,ActivityOptionsCompat compat){
        ARouter.getInstance().build(path).withOptionsCompat(compat).navigation();
    }

    protected void baseStartActivity(@Constants.ACTIVITY_PATH  String path, @Postcard.FlagInt int flag){
        ARouter.getInstance().build(path).withFlags(flag).navigation(this);
    }

    protected void baseStartActivity(@Constants.ACTIVITY_PATH  String path, @Postcard.FlagInt int flag,int enterAnim, int exitAnim){
        ARouter.getInstance().build(path).withFlags(flag).withTransition(enterAnim,exitAnim).navigation(this);
    }

    protected void sendEntity(EventBusEntity eventBusEntity){
        EventBus.getDefault().post(eventBusEntity);
    }

    protected void sendEntityWithSticky(EventBusEntity eventBusEntity){
        EventBus.getDefault().postSticky(eventBusEntity);
    }

    @Override
    protected void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

}
