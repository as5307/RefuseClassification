package com.umeng.soexample;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.widget.ImageView;

import androidx.multidex.MultiDex;


import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import com.mob.MobSDK;
import com.orhanobut.hawk.Hawk;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xutil.XUtil;

import cn.bmob.v3.Bmob;

public class MyAppliaction extends Application {

    private String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private String accessKeyId = "LTAIRZWh5VHKraJB";
    private String accessKeySecret = "fky1WHCT3KRpwDanKQf9ZJdEA7w9jM";


    //设置百度APPID/AK/SK
    public static final String APP_ID = "17144408";
    public static final String API_KEY = "64EbWZMY653g1H4N8zLiPGE1";
    public static final String SECRET_KEY = "Q90BipsuoycjqUGeMxA1enakpaLlYTf9";


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //mob初始化
        MobSDK.init(this);


        //hawk
        Hawk.init(this).build();

        //XUI初始化
        XUI.init(this);
        XUI.debug(true);
        XUtil.init(this);

        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5d39eec5");

        //bmob初始化
        Bmob.resetDomain("http://xingruan.xyz/8/");

        Bmob.initialize(this, "6f7f26253b53887d77ad24bab877a769");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

}

