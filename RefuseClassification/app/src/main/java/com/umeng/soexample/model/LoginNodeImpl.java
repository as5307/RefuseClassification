package com.umeng.soexample.model;

import android.app.Activity;


import com.umeng.soexample.Callback.LoginCallback;
import com.umeng.soexample.utils.DialogUntil;


import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

public class LoginNodeImpl implements LoginMode {


    private String TAG = "LoginNodeImpl";

    @Override
    public void login(Activity activity, String name,LoginCallback loginCallback) {
        Platform platform = ShareSDK.getPlatform(name);
        DialogUntil.getInstance().showLoadBox(activity, "正在登录....");
        //要功能不要数据
        platform.authorize();
        // 回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //登录成功
                loginCallback.onSuccess(platform);

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //授权失败
                loginCallback.onError(throwable.getMessage(), throwable);
            }


            @Override
            public void onCancel(Platform platform, int i) {
                //授权取消
                loginCallback.onCancel();

            }
        });
    }
}
