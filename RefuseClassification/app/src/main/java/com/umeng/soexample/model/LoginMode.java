package com.umeng.soexample.model;

import android.app.Activity;
import android.os.Handler;

import com.umeng.soexample.Callback.LoginCallback;
import com.xuexiang.xui.widget.button.CountDownButton;

public interface LoginMode {
    void login(Activity activity,String name, LoginCallback loginCallback);
}
