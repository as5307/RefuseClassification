package com.umeng.soexample.Callback;

import cn.sharesdk.framework.Platform;

/*
登录回调接口
*/
public interface  LoginCallback {
        void onError(String mag,Throwable arg2);
        void onSuccess(Platform platform);
        void onCancel();
}
