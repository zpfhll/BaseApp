package com.example.its.baseapplication.gamen.splash;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.its.baseapplication.R;
import com.example.its.baseapplication.gamen.base.BaseActivity;
import com.example.its.baseapplication.gamen.base.Constants;

@Route(path = Constants.ACTIVITY_SPLASH)
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
