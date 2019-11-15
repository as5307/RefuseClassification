package com.umeng.soexample.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import androidx.multidex.MultiDex;

import com.mob.MobSDK;
import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.BuildConfig;
import com.umeng.soexample.utils.update.OKHttpUpdateHttpService;
import com.xuexiang.xui.XUI;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.utils.UpdateUtils;
import com.xuexiang.xutil.XUtil;

import cn.bmob.v3.Bmob;

public class BaseApp extends Application {


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


        //初始化XUpdate
        XUpdate.get()
                .debug(BuildConfig.DEBUG)
                //默认设置只在wifi下检查版本更新
                .isWifiOnly(false)
                //默认设置使用get请求检查版本
                .isGet(true)
                //默认设置非自动模式，可根据具体使用配置
                .isAutoMode(false)
                //设置默认公共请求参数
                .param("versionCode", UpdateUtils.getVersionCode(this))
                .param("appKey", this.getPackageName())
                //这个必须设置！实现网络请求功能。
                .setIUpdateHttpService(new OKHttpUpdateHttpService())
                //这个必须初始化
                .init(this);

        //bmob初始化
        Bmob.resetDomain("http://xingruan.xyz/8/");
        Bmob.initialize(this, "6f7f26253b53887d77ad24bab877a769");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }
}